package com.example.repositories;

import com.example.daos.PagamentoDAO;
import com.example.daos.TicketDAO;
import com.example.models.Pagamento;
import com.example.utils.Resultado;

public class PagamentoRepository {
    
    private TicketDAO ticketDAO; 
    private PagamentoDAO pagamentoDAO; 

    public PagamentoRepository(TicketDAO ticketDAO,PagamentoDAO pagamentoDAO){
        this.ticketDAO = ticketDAO; 
        this.pagamentoDAO = pagamentoDAO; 
    }

    public Resultado<Pagamento> registrar(Pagamento pagamento){
        return pagamentoDAO.registrar(pagamento); 
    }


}
