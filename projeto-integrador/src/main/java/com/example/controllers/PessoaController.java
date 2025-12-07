package com.example.controllers;

import java.util.HashMap;
import java.util.Map;

import com.example.models.Pessoa;
import com.example.repositories.PessoaRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PessoaController {

    private PessoaRepository repository;

    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    public Handler cadastrarGet = (Context ctx) -> {
        ctx.render("addpessoa.html");
    };
    public Handler cadastrarPost = (Context ctx)->{
        String nome = ctx.formParam("nome"); 
        String telefone = ctx.formParam("telefone"); 

        Resultado<Pessoa>resultado = repository.cadastrar(nome, telefone); 
        Map<String, Object> dados = new HashMap<>(); 

        if(resultado.foiSucesso()){
            dados.put("mensagem", resultado.getMsg());
        }
        else{
            dados.put("erro", resultado.getMsg()); 
        }
        ctx.render("addpessoa.html",dados); 
    };
    
}
