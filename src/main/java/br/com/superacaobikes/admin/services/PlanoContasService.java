package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.PlanoContas;
import br.com.superacaobikes.admin.repositories.PlanoContasRepository;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;
import java.util.Optional;

public class PlanoContasService {

    private final PlanoContasRepository plcRep;

    public PlanoContasService(PlanoContasRepository plcRep) {
        this.plcRep = plcRep;
    }

    public PlanoContas find(Integer id){
        Optional<PlanoContas> obj = plcRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PlanoContas.class.getName()));
    }

}
