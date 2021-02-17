package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Categoria;
import br.com.superacaobikes.admin.domain.Cidade;
import br.com.superacaobikes.admin.repositories.CidadeRepository;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;

import java.util.Optional;

public class CidadeService {

    private final CidadeRepository cddRep;

    public CidadeService(CidadeRepository cddRep) {
        this.cddRep = cddRep;
    }

    public Cidade find(Integer id){
        Optional<Cidade> obj = cddRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
    }

}
