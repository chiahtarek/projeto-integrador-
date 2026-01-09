package com.example.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Pagamento;
import com.example.models.Ticket;
import com.example.repositories.PagamentoRepository;
import com.example.repositories.TicketRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PagamentoController {
    private PagamentoRepository pagamentoRepository; 
    private TicketRepository ticketRepository; 

    public PagamentoController(PagamentoRepository pagamentoRepository,TicketRepository ticketRepository){
        this.pagamentoRepository = pagamentoRepository; 
        this.ticketRepository = ticketRepository; 
    }

    public Handler registrarGet = (Context ctx) ->{
        Map<String, Object> dados = new HashMap<>(); 
        Resultado<List<Ticket>> tickets = ticketRepository.listar(); 
        if(tickets.foiSucesso()){
            dados.put("tickets", tickets.comoSucesso().getObj()); 
        }
        ctx.render("addpag.html", dados); 

    }; 
    public Handler registrarPost = (Context ctx) ->{
        Map<String, Object> dados = new HashMap<>(); 
        String ticketSwap = ctx.formParam("ticketid"); 
        int ticketid = Integer.parseInt(ticketSwap); 
        Ticket ticket = ticketRepository.buscar(ticketid).comoSucesso().getObj(); 
        String forma = ctx.formParam("formaPagamento"); 
        LocalDateTime data_hora = LocalDateTime.now(); 
        Pagamento pagamento = new Pagamento(data_hora,forma,ticket); 
        Resultado<Pagamento> result = pagamentoRepository.registrar(pagamento); 
        if(result.foiSucesso()){
            dados.put("mensagem", result.getMsg()); 
        }else{
            dados.put("erro", result.getMsg()); 
        }
        ctx.render("addpag.html", dados); 
    }; 


}
