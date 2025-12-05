package com.example.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Cliente;
import com.example.repositories.ClienteRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ExcluiClienteController {

    private ClienteRepository repository;

    public ExcluiClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    public Handler get = (Context ctx) -> {
        ctx.render("excluicliente.html");
    };

    public Handler post = (Context ctx) -> {
        Resultado<Cliente> resultadoSwap;
        Map<String, Object> dados = new HashMap<>();
        String idSwap = ctx.formParam("id");
        int id = Integer.parseInt(idSwap);
        Resultado<List<Cliente>> resultado = repository.listar();
        if (resultado.foiSucesso()) {
            List<Cliente> clientes = resultado.comoSucesso().getObj();
            for (Cliente c : clientes) {
                if (c.getId() == id) {
                    resultadoSwap = repository.excluir(id);
                    if (resultadoSwap.foiSucesso()) {
                        dados.put("mensagem", resultadoSwap.getMsg());
                    } else {
                        dados.put("erro", resultadoSwap.getMsg());
                    }
                }
            }
        }
        ctx.render("excluicliente.html", dados);
    };
}
