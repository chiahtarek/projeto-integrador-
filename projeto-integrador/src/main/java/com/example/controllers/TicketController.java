package com.example.controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Carro;
import com.example.models.Ticket;
import com.example.models.Vaga;
import com.example.repositories.CarroRepository;
import com.example.repositories.TicketRepository;
import com.example.repositories.VagaRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class TicketController {
    private TicketRepository ticketRepository;
    private CarroRepository carroRepository;
    private VagaRepository vagaRepository;

    public TicketController(TicketRepository ticketRepository,
            CarroRepository carroRepository,
            VagaRepository vagaRepository) {
        this.ticketRepository = ticketRepository;
        this.carroRepository = carroRepository;
        this.vagaRepository = vagaRepository;
    }

    public Handler cadastrarGet = (Context ctx) -> {
        Map<String, Object> dados = new HashMap<>();
        Resultado<List<Carro>> carros = carroRepository.listar();
        if (carros.foiSucesso()) {
            dados.put("carros", carros.comoSucesso().getObj());
        }
        Resultado<List<Vaga>> vagas = vagaRepository.listarVagasLivres();
        if (vagas.foiSucesso()) {
            dados.put("vagas", vagas.comoSucesso().getObj());
        }
        ctx.render("addticket.html", dados);

    };
    public Handler cadastrarPost = (Context ctx) ->{
        Map<String, Object> dados = new HashMap<>(); 
        String placa = ctx.formParam("placacarro"); 
        String swapVaga = ctx.formParam("vaga"); 
        int idVaga = Integer.parseInt(swapVaga); 

        Carro carro = carroRepository.buscar(placa).comoSucesso().getObj();
        Vaga vaga = vagaRepository.buscar(idVaga).comoSucesso().getObj(); 

        LocalDateTime data_hora_entrada = LocalDateTime.now(); 

        Ticket ticket = new Ticket(data_hora_entrada, null, false, vaga, carro); 

        Resultado<Ticket> result = ticketRepository.registraEntrada(ticket); 

        if(result.foiSucesso()){
           dados.put("mensagem" , result.getMsg()); 
        }else{
            dados.put("erro", result.getMsg()); 
        }

        ctx.render("addticket.html", dados); 

    }; 
     public Handler registraSaidaGet = (Context ctx)->{
        Map<String,Object> dados = new HashMap<>(); 
        Resultado<List<Ticket>> tickets = ticketRepository.listar(); 
        if(tickets.foiSucesso()){
            dados.put("tickets", tickets.comoSucesso().getObj()); 
        }
        ctx.render("saidaticket.html", dados); 
     }; 
     public Handler registraSaidaPost = (Context ctx)->{
        Map<String,Object> dados = new HashMap<>(); 
        String idSwap = ctx.formParam("ticketid"); 
        int id = Integer.parseInt(idSwap); 
        Ticket ticket = ticketRepository.buscar(id).comoSucesso().getObj();

        LocalDateTime data_saida = LocalDateTime.now(); 
        ticket.setData_hora_saida(data_saida);
        ticket.setStatus(true);

        long minutoSwap = Duration.between(ticket.getData_hora_entrada(), ticket.getData_hora_saida()).toMinutes(); 
        Double minutos = (double) minutoSwap;
        Double valor = minutos * 0.15; 
        ticket.setValor(valor);
    
        Resultado<Ticket> result = ticketRepository.registraSaida(ticket); 
        if(result.foiSucesso()){
            dados.put("mensagem", result.getMsg()); 
        }
        else{
            dados.put("erro", result.getMsg()); 
        }
        ctx.render("saidaticket.html", dados); 
     }; 

     public Handler listarGet = (Context ctx) ->{
        Map<String, Object> dados = new HashMap<>(); 
        Resultado<List<Ticket>> result = ticketRepository.listar(); 
        if(result.foiSucesso()){
            dados.put("tickets", result.comoSucesso().getObj()); 
            dados.put("titulo", "Lista de Tickets"); 
            dados.put("totalTickets", result.comoSucesso().getObj().size());
        }else{
            dados.put("erro", result.getMsg()); 
        }
        ctx.render("tickets.html", dados); 
     }; 
}
