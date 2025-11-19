package com.example.repositories;

import java.util.List;

import com.example.daos.PorteDAO;
import com.example.models.Porte;
import com.example.utils.Resultado;

public class PorteRepository {
    private PorteDAO dao;

    public PorteRepository(PorteDAO dao) {
        this.dao = dao;
    }

    public Resultado<Porte> cadastrar(String descricao) {
        Porte porte = new Porte(descricao);
        return dao.salvar(porte);
    }
    public Resultado<List<Porte>> listar(){
        return dao.listar();
    }
    public Resultado<Porte> excluir(Porte porte){
        return dao.excluir(porte); 
    }

}
