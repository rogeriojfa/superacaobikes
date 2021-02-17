package br.com.superacaobikes.admin.services.validation;

import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.dto.ClienteDTO;
import br.com.superacaobikes.admin.repositories.ClienteRepository;
import br.com.superacaobikes.admin.resources.exception.FieldMessage;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    final ClienteRepository cliRep;
    final HttpServletRequest request;

    public ClienteUpdateValidator(ClienteRepository cliRep, HttpServletRequest request) {
        this.cliRep = cliRep;
        this.request = request;
    }

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = cliRep.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)){
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
