package br.com.superacaobikes.admin.resources;

import br.com.superacaobikes.admin.domain.Produto;
import br.com.superacaobikes.admin.dto.ProdutoDTO;
import br.com.superacaobikes.admin.resources.utils.URL;
import br.com.superacaobikes.admin.services.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

    private final ProdutoService prdSrv;

    public ProdutoResource(ProdutoService prdSrv) {
        this.prdSrv = prdSrv;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id){
        Produto prod = prdSrv.find(id);
        return ResponseEntity.ok().body(prod);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> lstProd = prdSrv.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);

        Page<ProdutoDTO> lstProdDTO = lstProd.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(lstProdDTO);
    }
}
