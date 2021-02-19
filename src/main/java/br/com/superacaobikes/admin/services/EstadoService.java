package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Estado;
import br.com.superacaobikes.admin.repositories.EstadoRepository;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    private final EstadoRepository estRep;


    public EstadoService(EstadoRepository estRep) {
        this.estRep = estRep;
    }

    public List<Estado> findAll(){
        return estRep.findAllByOrderByNome();
    }
}
