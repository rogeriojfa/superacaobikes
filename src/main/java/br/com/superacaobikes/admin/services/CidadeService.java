package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Cidade;
import br.com.superacaobikes.admin.repositories.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cddRep;

    public CidadeService(CidadeRepository cddRep) {
        this.cddRep = cddRep;
    }

    public List<Cidade> findByEstado(Integer estadoId){
        return cddRep.findCidades(estadoId);
    }

}
