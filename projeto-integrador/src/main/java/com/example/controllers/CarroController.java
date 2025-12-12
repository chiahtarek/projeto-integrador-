package com.example.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Carro;
import com.example.models.Cliente;
import com.example.models.Porte;
import com.example.repositories.CarroRepository;
import com.example.utils.Resultado;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CarroController {
    private CarroRepository repository;

    public CarroController(CarroRepository repository) {
        this.repository = repository;
    }

    public Handler cadastraGet = (Context ctx) -> {
        ctx.render("addcarro.html");
    };

    public Handler cadastraPost = (Context ctx) -> {
        String placa = ctx.formParam("placa");
        String modelo = ctx.formParam("modelo");
        String marca = ctx.formParam("marca");
        String cor = ctx.formParam("cor");
        String swapIdCliente = ctx.formParam("id_cliente");
        int id_cliente = Integer.parseInt(swapIdCliente);
        Cliente cliente = new Cliente(id_cliente);
        String swapIdPorte = ctx.formParam("id_porte");
        int id_porte = Integer.parseInt(swapIdPorte);
        Porte porte = new Porte(id_porte, null);

        Resultado<Carro> resultado = repository.cadastrar(placa, modelo, marca, cor, cliente, porte);
        Map<String, Object> dados = new HashMap<>();
        if (resultado.foiSucesso()) {
            dados.put("mensagem", resultado.getMsg());
        } else {
            dados.put("erro", resultado.getMsg());
        }
        ctx.render("addcarro.html", dados);
    };
    public Handler listarGet = (Context ctx) -> {
        Resultado<List<Carro>> resultado = repository.listar();
        Map<String, Object> dados = new HashMap<>();

        if (resultado.foiSucesso()) {
            dados.put("carros", resultado.comoSucesso().getObj());
            dados.put("totalCarros", resultado.comoSucesso().getObj().size());

        } else {
            dados.put("erro", resultado.getMsg());
        }
        dados.put("titulo", "lista de carros");
        ctx.render("listcarro.html", dados);
    };
    public Handler editarGet = (Context ctx) -> {
        ctx.render("editcarro.html");
    };
    public Handler editarPost = (Context ctx) -> {
        String placa = ctx.formParam("placa");
        String modelo = ctx.formParam("modelo");
        String marca = ctx.formParam("marca");
        String cor = ctx.formParam("cor");
        String swapIdCliente = ctx.formParam("id_cliente");
        int id_cliente = Integer.parseInt(swapIdCliente);
        Cliente cliente = new Cliente(id_cliente, null, null);
        String swapIdPorte = ctx.formParam("id_porte");
        int id_porte = Integer.parseInt(swapIdPorte);
        Porte porte = new Porte(id_porte, null);

        Carro carro = new Carro(placa, modelo, marca, cor, cliente, porte);

        Resultado<Carro> resultado = repository.editar(carro);
        Map<String, Object> dados = new HashMap<>();
        if (resultado.foiSucesso()) {
            dados.put("mensagem", resultado.getMsg());
        } else {
            dados.put("erro", resultado.getMsg());
        }
        ctx.render("editcarro.html", dados);
    };
    public Handler excluirGet = (Context ctx) -> {
        ctx.render("excluicarro.html");
    };
    public Handler excluirPost = (Context ctx) -> {
        String placa = ctx.formParam("placa");
        Resultado<Carro> result = repository.excluir(placa);
        Map<String, Object> dados = new HashMap<>();
        if (result.foiSucesso()) {
            dados.put("mensagem", result.getMsg());
        } else {
            dados.put("erro", result.getMsg());
        }
        ctx.render("excluicarro.html", dados);
    };
}
