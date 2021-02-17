package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Categoria;
import br.com.superacaobikes.admin.domain.Estado;
import br.com.superacaobikes.admin.repositories.EstadoRepository;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;

import java.util.Optional;

public class EstadoService {

    private final EstadoRepository estRep;


    public EstadoService(EstadoRepository estRep) {
        this.estRep = estRep;
    }

    public Estado find(Integer id){
        Optional<Estado> obj = estRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
    }
}
