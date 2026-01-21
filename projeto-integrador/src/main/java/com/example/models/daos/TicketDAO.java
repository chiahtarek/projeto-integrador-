package com.example.models.daos;

import java.util.List;

import com.example.models.Ticket;
import com.example.utils.Resultado;

public interface TicketDAO {
    Resultado<Ticket> registraEntrada(Ticket ticket); 
    Resultado<Ticket> registraSaida(Ticket ticket); 
    Resultado<List<Ticket>> listar(); 
    Resultado<Ticket> buscar(int id); 
    Resultado<Double> buscaValor(Ticket ticket); 
}
