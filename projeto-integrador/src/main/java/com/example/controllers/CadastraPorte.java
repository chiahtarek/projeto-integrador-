package com.example.controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.HashMap;
import java.util.Map;

import com.example.models.Porte;
import com.example.repositories.PorteRepository;
import com.example.utils.Resultado;

public class CadastraPorte {

    private PorteRepository repositorio;

    public CadastraPorte(PorteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Handler get = (Context ctx) ->{
       ctx.render(  "addporte.html");
    }; 

    public Handler post = (Context ctx)->{
        String descricao = ctx.formParam("descricao"); 
        
        Resultado<Porte> resultado = repositorio.cadastrar(descricao); 

        Map<String,Object> dados = new HashMap<>(); 

        if(resultado.foiSucesso()){
            dados.put("mensagem", resultado.getMsg()); 
        }
        else{
            dados.put("erro", resultado.getMsg()); 
        }
        ctx.render("addporte.html", dados); 
    }; 
}
