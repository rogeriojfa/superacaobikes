package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.ItemPedido;
import br.com.superacaobikes.admin.domain.PagamentoComBoleto;
import br.com.superacaobikes.admin.domain.Pedido;
import br.com.superacaobikes.admin.domain.enums.EstadoPagamento;
import br.com.superacaobikes.admin.repositories.ItemPedidoRepository;
import br.com.superacaobikes.admin.repositories.PagamentoRepository;
import br.com.superacaobikes.admin.repositories.PedidoRepository;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedRep;
    private final PagamentoRepository pagRep;
    private final BoletoService bolSrv;
    private final ProdutoService prdSrv;
    private final ItemPedidoRepository itpRep;
    private final ClienteService cliSrv;
    private final EmailService emlSrv;

    public PedidoService(PedidoRepository pedRep, PagamentoRepository pagRep, BoletoService bolSrv, ProdutoService prdSrv, ItemPedidoRepository itpRep, ClienteService cliSrv, EmailService emlSrv) {
        this.pedRep = pedRep;
        this.pagRep = pagRep;
        this.bolSrv = bolSrv;
        this.prdSrv = prdSrv;
        this.itpRep = itpRep;
        this.cliSrv = cliSrv;
        this.emlSrv = emlSrv;
    }

    public Pedido find(Integer id){
        Optional<Pedido> obj = pedRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }


    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setData(new Date());
        obj.setCliente(cliSrv.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            bolSrv.preencherPagamentoComBoleto(pagto, obj.getData());
        }
        obj = pedRep.save(obj);
        pagRep.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(prdSrv.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getVlrVenda());
            ip.setPedido(obj);
        }
        itpRep.saveAll(obj.getItens());
        emlSrv.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }
}
