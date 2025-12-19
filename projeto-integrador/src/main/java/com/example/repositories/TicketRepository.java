package com.example.repositories;

import com.example.daos.JDBCTicketDAO;
import com.example.daos.TicketDAO;

public class TicketRepository {
    private TicketDAO ticketDAO;

    public TicketRepository(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }
    

}
