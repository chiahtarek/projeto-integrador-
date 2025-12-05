package com.example.controllers;

import java.util.HashMap;
import java.util.Map;

import com.example.models.Cliente;
import com.example.repositories.ClienteRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CadastraClienteController {
    private ClienteRepository repository;

    public CadastraClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    public Handler get = (Context ctx) -> {
        ctx.render("addcliente.html");
    };

    public Handler post = (Context ctx) -> {
        String swapid = ctx.formParam("id");
        int id = Integer.parseInt(swapid);
        String cpf = ctx.formParam("cpf");
        String email = ctx.formParam("email");

        Resultado<Cliente> resultado = repository.cadastrar(id, cpf, email);
        Map<String, Object> dados = new HashMap<>();
        if (resultado.foiSucesso()) {
            dados.put("mensagem", resultado.getMsg());
        } else {
            dados.put("erro", resultado.getMsg());
        }
        ctx.render("addcliente.html");

    };

}
