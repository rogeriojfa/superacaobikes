package br.com.superacaobikes.admin.resources;

import br.com.superacaobikes.admin.domain.Categoria;
import br.com.superacaobikes.admin.domain.Pedido;
import br.com.superacaobikes.admin.dto.CategoriaDTO;
import br.com.superacaobikes.admin.services.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

    private final PedidoService pedSrv;

    public PedidoResource(PedidoService pedSrv) {
        this.pedSrv = pedSrv;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id){
        Pedido ped = pedSrv.find(id);
        return ResponseEntity.ok().body(ped);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        obj = pedSrv.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Pedido>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "data") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {

        Page<Pedido> lstPed = pedSrv.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(lstPed);
    }
}
