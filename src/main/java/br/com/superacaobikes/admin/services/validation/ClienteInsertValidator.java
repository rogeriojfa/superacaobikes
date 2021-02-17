package br.com.superacaobikes.admin.services.validation;

import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.domain.enums.TipoCliente;
import br.com.superacaobikes.admin.dto.ClienteNewDTO;
import br.com.superacaobikes.admin.repositories.ClienteRepository;
import br.com.superacaobikes.admin.resources.exception.FieldMessage;
import br.com.superacaobikes.admin.services.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    final ClienteRepository cliRep;

    public ClienteInsertValidator(ClienteRepository cliRep) {
        this.cliRep = cliRep;
    }

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido!"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDCA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido!"));
        }

        Cliente aux = cliRep.findByEmail(objDto.getEmail());
        if (aux != null){
            list.add(new FieldMessage("email", "O e-mail já existe na base de dados"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
