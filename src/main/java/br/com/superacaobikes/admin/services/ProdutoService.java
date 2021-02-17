package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Categoria;
import br.com.superacaobikes.admin.domain.Produto;
import br.com.superacaobikes.admin.repositories.CategoriaRepository;
import br.com.superacaobikes.admin.repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository prdRep;
    private final CategoriaRepository catRep;

    public ProdutoService(ProdutoRepository prdRep, CategoriaRepository catRep) {
        this.prdRep = prdRep;
        this.catRep = catRep;
    }
    public Produto find(Integer id){
        Optional<Produto> obj = prdRep.findById(id);
        return obj.orElse(null);
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = catRep.findAllById(ids);
        return prdRep.search(nome, categorias, pageRequest);
    }

}
