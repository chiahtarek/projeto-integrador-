package com.example.controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Porte;
import com.example.repositories.PorteRepository;
import com.example.utils.Resultado;

public class PorteController {

    private PorteRepository repositorio;

    public PorteController(PorteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Handler cadastrarGet = (Context ctx) -> {
        ctx.render("addporte.html");
    };

    public Handler cadastrarPost = (Context ctx) -> {
        String descricao = ctx.formParam("descricao");

        Resultado<Porte> resultado = repositorio.cadastrar(descricao);

        Map<String, Object> dados = new HashMap<>();

        if (resultado.foiSucesso()) {
            dados.put("mensagem", resultado.getMsg());
        } else {
            dados.put("erro", resultado.getMsg());
        }
        ctx.render("addporte.html", dados);
    };

    public Handler excluirGet = (Context ctx) -> {
        ctx.render("excluiporte.html");
    };

    public Handler excluirPost = (Context ctx) -> {
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
    public Handler listar = (Context ctx) -> {

        Resultado<List<Porte>> resultado = repositorio.listar();

        Map<String, Object> dados = new HashMap<>();
        if (resultado.foiSucesso()) {
            dados.put("portes", resultado.comoSucesso().getObj());
            dados.put("totalPortes", resultado.comoSucesso().getObj().size());
        } else {
            dados.put("erro", resultado.getMsg());
        }
        dados.put("titulo", "lista de portes");
        ctx.render("listporte.html", dados);
    };

    public Handler editarGet = (Context ctx) -> {
        ctx.render("editaporte.html");
    };

    public Handler editarPost = (Context ctx) -> {
        Resultado<Porte> resultado;
        Map<String, Object> dados = new HashMap<>();
        String swapId = ctx.formParam("id");
        int id = Integer.parseInt(swapId);
        String descricao = ctx.formParam("descricao");
        Porte porte = new Porte(id, descricao);
        resultado = repositorio.editar(porte);
        if (resultado.foiSucesso()) {
            dados.put("mensagem", resultado.getMsg());
        } else {
            dados.put("erro", resultado.getMsg());
        }
        ctx.render("excluiporte.html");
    };

}
