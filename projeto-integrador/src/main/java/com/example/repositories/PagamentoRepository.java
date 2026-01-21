package com.example.repositories;

import com.example.models.Pagamento;
import com.example.models.daos.PagamentoDAO;
import com.example.models.daos.TicketDAO;
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
