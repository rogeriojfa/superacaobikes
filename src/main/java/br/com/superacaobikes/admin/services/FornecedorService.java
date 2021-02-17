package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Fornecedor;
import br.com.superacaobikes.admin.repositories.FornecedorRepository;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;

import java.util.Optional;

public class FornecedorService {

    private final FornecedorRepository forRep;

    public FornecedorService(FornecedorRepository forRep) {
        this.forRep = forRep;
    }

    public Fornecedor find(Integer id){
        Optional<Fornecedor> obj = forRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Fornecedor.class.getName()));
    }

}
