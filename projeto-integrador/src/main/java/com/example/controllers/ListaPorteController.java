package com.example.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Porte;
import com.example.repositories.PorteRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListaPorteController {

    private PorteRepository repositorio; 
    
    public ListaPorteController(PorteRepository repositorio){
        this.repositorio = repositorio; 
    }

    public Handler get = (Context ctx) ->{
      
        Resultado<List<Porte>> resultado = repositorio.listar(); 

        Map<String,Object> dados = new HashMap<>();
        if(resultado.foiSucesso()){
            dados.put("portes", resultado.comoSucesso().getObj()); 
            dados.put("totalPortes", resultado.comoSucesso().getObj().size()); 
        }
        else{
            dados.put("erro", resultado.getMsg());
        }
        dados.put("titulo", "lista de portes"); 
        ctx.render("listporte.html",dados);
    };
}   
