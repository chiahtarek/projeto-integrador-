package com.example.daos;

import com.example.models.Ticket;
import com.example.utils.Resultado;

public interface TicketDAO {
    Resultado<Ticket> cadastrar(Ticket ticket); 
}
