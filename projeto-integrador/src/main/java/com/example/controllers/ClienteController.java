package com.example.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Cliente;
import com.example.repositories.ClienteRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ClienteController {
    private ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    public Handler cadastrarGet = (Context ctx) -> {
        ctx.render("addcliente.html");
    };

    public Handler cadastrarPost = (Context ctx) -> {
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
        ctx.render("addcliente.html", dados);

    };

    public Handler excluirGet = (Context ctx) -> {
        ctx.render("excluicliente.html");
    };

    public Handler excluirPost = (Context ctx) -> {
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
    public Handler listarGet = (Context ctx) -> {
        Resultado<List<Cliente>> resultado = repository.listar();
        Map<String, Object> dados = new HashMap<>();

        if (resultado.foiSucesso()) {
            dados.put("clientes", resultado.comoSucesso().getObj());
            dados.put("totalClientes ", resultado.comoSucesso().getObj().size());
        } else {
            dados.put("erro ", resultado.getMsg());
        }
        dados.put("titulo", "lista de clientes");
        ctx.render("listcliente.html", dados);
    };
    public Handler editarGet = (Context ctx) -> {
        Map<String, Object> dados = new HashMap<>();
        Resultado<List<Cliente>> resultadoLista = repository.listar();
        if (resultadoLista.foiSucesso()) {
            dados.put("clientes", resultadoLista.comoSucesso().getObj());
        }

        ctx.render("editacliente.html", dados);
    };
    public Handler editarPost = (Context ctx) -> {

        Map<String, Object> dados = new HashMap<>();
        String swapid = ctx.formParam("id");

        if (swapid == null || swapid.isBlank()) {
            dados.put("mensagem", "Selecione um cliente antes de salvar.");

            Resultado<List<Cliente>> resultadoLista = repository.listar();
            if (resultadoLista.foiSucesso()) {
                dados.put("clientes", resultadoLista.comoSucesso().getObj());
            }

            ctx.render("editacliente.html", dados);
            return;
        }

        int id = Integer.parseInt(swapid);
        String cpf = ctx.formParam("cpf");
        String email = ctx.formParam("email");
        Cliente cliente = new Cliente(id, cpf, email);
        Resultado<Cliente> resultado = repository.editar(cliente);
        if (resultado.foiSucesso()) {
            dados.put("mensagem", resultado.getMsg());
            dados.put("clienteSelecionado", id);
        } else {
            dados.put("mensagem", resultado.getMsg());
        }

        Resultado<List<Cliente>> resultadoLista = repository.listar();
        if (resultadoLista.foiSucesso()) {
            dados.put("clientes", resultadoLista.comoSucesso().getObj());
        }

        ctx.render("editacliente.html", dados);
    };

}