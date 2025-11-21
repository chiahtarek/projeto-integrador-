package com.example.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Porte;
import com.example.repositories.PorteRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ExcluiPorteController {

    private PorteRepository repositorio;

    public ExcluiPorteController(PorteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Handler get = (Context ctx) -> {
        ctx.render("excluiporte.html");
    };

    public Handler post = (Context ctx) -> {
        Resultado<Porte> resultado2;
        Map<String, Object> dados = new HashMap<>();
        String descricao = ctx.formParam("descricao");
        Resultado<List<Porte>> resultado = repositorio.listar();
        if (resultado.foiSucesso()) {
            List<Porte> portes = resultado.comoSucesso().getObj();

            for (Porte p : portes) {
                if (p.getDescricao().equals(descricao)) {
                    resultado2 = repositorio.excluir(p.getDescricao());
                    if (resultado2.foiSucesso()) {
                        dados.put("mensagem", resultado2.getMsg());
                    } else {
                        dados.put("erro", resultado2.getMsg());
                    }
                }
            }

        }

        ctx.render("excluiporte.html", dados);
    };

}
