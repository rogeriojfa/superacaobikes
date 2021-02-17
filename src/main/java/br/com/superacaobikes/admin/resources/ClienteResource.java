package br.com.superacaobikes.admin.resources;

import br.com.superacaobikes.admin.domain.Categoria;
import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.dto.CategoriaDTO;
import br.com.superacaobikes.admin.dto.ClienteDTO;
import br.com.superacaobikes.admin.dto.ClienteNewDTO;
import br.com.superacaobikes.admin.services.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

    private final ClienteService cliSrv;

    public ClienteResource(ClienteService cliSrv) {
        this.cliSrv = cliSrv;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente cli = cliSrv.find(id);
        return ResponseEntity.ok().body(cli);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
        Cliente obj = cliSrv.fromDTO(objDto);
        obj = cliSrv.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
        Cliente obj = cliSrv.fromDTO(objDto);
        obj.setId(id);
        obj = cliSrv.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cliSrv.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {

        List<Cliente> lstCat = cliSrv.findAll();
        List<ClienteDTO> lstCatDTO = lstCat.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(lstCatDTO);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Cliente> lstCli = cliSrv.findPage(page, linesPerPage, orderBy, direction);

        Page<ClienteDTO> lstCliDTO = lstCli.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(lstCliDTO);
    }

}
