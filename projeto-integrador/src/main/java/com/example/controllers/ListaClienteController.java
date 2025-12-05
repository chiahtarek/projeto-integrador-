package com.example.controllers;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Cliente;
import com.example.repositories.ClienteRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;



public class ListaClienteController {
    private ClienteRepository repository; 
    
    public ListaClienteController(ClienteRepository repository){
        this.repository = repository; 
    }

    public Handler get = (Context ctx) -> {
        Resultado<List<Cliente>> resultado = repository.listar(); 
        Map<String, Object> dados = new HashMap<>();

        if(resultado.foiSucesso()){
            dados.put("clientes", resultado.comoSucesso().getObj()); 
            dados.put("totalClientes ", resultado.comoSucesso().getObj().size()); 
        }
        else{
            dados.put("erro ", resultado.getMsg()); 
        }
        dados.put("titulo", "lista de clientes"); 
        ctx.render("listcliente.html",dados); 
    }; 
}
