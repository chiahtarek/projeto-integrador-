package com.example.controllers;

import java.util.HashMap;
import java.util.Map;

import com.example.models.Carro;
import com.example.repositories.CarroRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CarroController {
    private CarroRepository repository;

    public CarroController(CarroRepository repository) {
        this.repository = repository;
    }

    public Handler cadastraGet = (Context ctx) ->{
        ctx.render("addcarro.html");
    };

    public Handler cadastraPost = (Context ctx) ->{
        String placa = ctx.formParam("placa"); 
        String modelo = ctx.formParam("modelo");
        String marca = ctx.formParam("marca");
        String cor = ctx.formParam("cor");
        String swapIdCliente = ctx.formParam("id_cliente"); 
        int id_cliente = Integer.parseInt(swapIdCliente); 
        String swapIdPorte = ctx.formParam("id_porte"); 
        int id_porte = Integer.parseInt(swapIdPorte);

        Resultado<Carro> resultado = repository.cadastrar(placa, modelo, marca, cor, id_cliente, id_porte); 
        Map<String, Object> dados = new HashMap<>();
        if(resultado.foiSucesso()){
            dados.put("mensagem", resultado.getMsg()); 
        }
        else{
            dados.put("erro", resultado.getMsg()); 
        }
        ctx.render("addcarro.html", dados); 
    }; 
}
