package br.com.superacaobikes.admin.resources;

import br.com.superacaobikes.admin.domain.Cidade;
import br.com.superacaobikes.admin.domain.Estado;
import br.com.superacaobikes.admin.dto.CidadeDTO;
import br.com.superacaobikes.admin.dto.EstadoDTO;
import br.com.superacaobikes.admin.services.CidadeService;
import br.com.superacaobikes.admin.services.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

    private final EstadoService estSrv;
    private final CidadeService cddSrv;

    public EstadoResource(EstadoService estSrv, CidadeService cddSrv) {
        this.estSrv = estSrv;
        this.cddSrv = cddSrv;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> lstEst = estSrv.findAll();
        List<EstadoDTO> lstEstDTO = lstEst.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(lstEstDTO);
    }

    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
        List<Cidade> lstCdd = cddSrv.findByEstado(estadoId);
        List<CidadeDTO> lstCddDTO = lstCdd.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(lstCddDTO);
    }
}