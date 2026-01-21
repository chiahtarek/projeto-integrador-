package com.example.repositories;

import java.util.List;

import com.example.models.Carro;
import com.example.models.Ticket;
import com.example.models.Vaga;
import com.example.models.daos.CarroDAO;
import com.example.models.daos.JDBCTicketDAO;
import com.example.models.daos.TicketDAO;
import com.example.models.daos.VagaDAO;
import com.example.utils.Resultado;

public class TicketRepository {
    private TicketDAO ticketDAO;
    private CarroDAO carroDAO;
    private VagaDAO vagaDAO;

    public TicketRepository(TicketDAO ticketDAO, CarroDAO carroDAO) {
        this.ticketDAO = ticketDAO;
        this.carroDAO = carroDAO;
    }

    public Resultado<Ticket> registraEntrada(Ticket ticket) {
        return ticketDAO.registraEntrada(ticket);
    }

    public Resultado<List<Ticket>> listar() {
        Resultado<List<Ticket>> result = ticketDAO.listar();
        if (result.foiSucesso()) {
            List<Ticket> tickets = result.comoSucesso().getObj();
            for (Ticket t : tickets) {
                Resultado<Carro> resultCarro = carroDAO.buscarCarroTicket(t.getId());
                if (resultCarro.foiSucesso()) {
                    t.setCarro(resultCarro.comoSucesso().getObj());
                } else {
                    return Resultado.erro(resultCarro.getMsg());
                }
            }

        }
        return result;
    }

    public Resultado<Ticket> buscar(int id) {
        Resultado<Ticket> result = ticketDAO.buscar(id);
        if (result.foiSucesso()) {
            Ticket ticket = result.comoSucesso().getObj();
            Resultado<Carro> resultCarro = carroDAO.buscarCarroTicket(ticket.getId());
            if (resultCarro.foiSucesso()) {
                ticket.setCarro(resultCarro.comoSucesso().getObj());
            }
            return Resultado.sucesso("ticket encontrado", ticket);
        }
        return Resultado.erro("erro ao buscar ticket");

    }
    public Resultado<Ticket> registraSaida(Ticket ticket){
        return ticketDAO.registraSaida(ticket); 
    }

}
