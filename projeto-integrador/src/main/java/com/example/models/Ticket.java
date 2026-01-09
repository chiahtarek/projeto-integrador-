package com.example.models;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private LocalDateTime data_hora_entrada;
    private LocalDateTime data_hora_saida;
    private boolean status;
    private Vaga vaga;
    private Carro carro;
    private Double valor;

    public Ticket(int id, LocalDateTime data_hora_entrada, LocalDateTime data_hora_saida, boolean status, Vaga vaga,
            Carro carro, Double valor) {
        this.id = id;
        this.data_hora_entrada = data_hora_entrada;
        this.data_hora_saida = data_hora_saida;
        this.status = status;
        this.vaga = vaga;
        this.carro = carro;
        this.valor = valor;
    }

    public Ticket(int id, LocalDateTime data_hora_entrada, LocalDateTime data_hora_saida, boolean status, Vaga vaga,
            Carro carro) {
        this.id = id;
        this.data_hora_entrada = data_hora_entrada;
        this.data_hora_saida = data_hora_saida;
        this.status = status;
        this.vaga = vaga;
        this.carro = carro;
    }

    public Ticket(LocalDateTime data_hora_entrada, LocalDateTime data_hora_saida, boolean status, Vaga vaga,
            Carro carro) {
        this.data_hora_entrada = data_hora_entrada;
        this.data_hora_saida = data_hora_saida;
        this.status = status;
        this.vaga = vaga;
        this.carro = carro;
    }

    public Ticket(int id, LocalDateTime data_hora_entrada,LocalDateTime data_hora_saida, Double valor) {
        this.id = id;
        this.data_hora_entrada = data_hora_entrada;
        this.data_hora_saida = data_hora_saida; 
        this.valor = valor; 
    }
    public Ticket(int id, LocalDateTime data_hora_entrada, Double valor) {
        this.id = id;
        this.data_hora_entrada = data_hora_entrada; 
        this.valor = valor; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData_hora_entrada() {
        return data_hora_entrada;
    }

    public void setData_hora_entrada(LocalDateTime data_hora_entrada) {
        this.data_hora_entrada = data_hora_entrada;
    }

    public LocalDateTime getData_hora_saida() {
        return data_hora_saida;
    }

    public void setData_hora_saida(LocalDateTime data_hora_saida) {
        this.data_hora_saida = data_hora_saida;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    

}
