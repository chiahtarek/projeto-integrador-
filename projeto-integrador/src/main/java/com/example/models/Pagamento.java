package com.example.models;

import java.time.LocalDateTime;

public class Pagamento {
    private int id;
    private LocalDateTime data_hora;
    private String forma;
    private Ticket ticket;

    public Pagamento(int id, LocalDateTime data_hora, String forma, Ticket ticket) {
        this.id = id;
        this.data_hora = data_hora;
        this.forma = forma;
        this.ticket = ticket;
    }

    public Pagamento(LocalDateTime data_hora, String forma, Ticket ticket) {
        this.data_hora = data_hora;
        this.forma = forma;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}
