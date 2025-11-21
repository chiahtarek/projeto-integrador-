package com.example.controllers;

import java.util.HashMap;
import java.util.Map;

import com.example.models.Porte;
import com.example.repositories.PorteRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class EditaPorteController {
    private PorteRepository repositorio; 

    public EditaPorteController(PorteRepository repositorio){
        this.repositorio = repositorio; 
    }
    public Handler get = (Context ctx)->{
        ctx.render("editaporte.html"); 
    };
    public Handler post = (Context ctx)->{
        Resultado<Porte> resultado;
        Map<String,Object> dados = new HashMap<>();
        String swapId = ctx.formParam("id");
        int id = Integer.parseInt(swapId); 
        String descricao = ctx.formParam("descricao"); 
        Porte porte = new Porte(id, descricao);
        resultado = repositorio.editar(porte); 
        if(resultado.foiSucesso()){
            dados.put("mensagem", resultado.getMsg());
        }
        else{
            dados.put("erro", resultado.getMsg()); 
        }
        ctx.render("excluiporte.html"); 
    };
}
