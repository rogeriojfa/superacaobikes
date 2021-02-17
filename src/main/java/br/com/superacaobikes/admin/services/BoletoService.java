package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date dataPedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pgto.setDataVencimento(cal.getTime());
    }
}
