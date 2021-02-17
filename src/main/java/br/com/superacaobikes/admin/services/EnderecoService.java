package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.domain.Endereco;
import br.com.superacaobikes.admin.repositories.ClienteRepository;
import br.com.superacaobikes.admin.repositories.EnderecoRepository;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository endRep;

    public EnderecoService(EnderecoRepository endRep) {
        this.endRep = endRep;
    }

    public Endereco find(Integer id){
        Optional<Endereco> obj = endRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
    }

}
