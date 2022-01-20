package com.coffeebrewer.cursoaula2.services;

import com.coffeebrewer.cursoaula2.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instanteDoPedido);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(calendar.getTime());
    }
}
