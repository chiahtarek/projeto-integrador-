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

    public Resultado<List<Porte>> listar() {
        return dao.listar();
    }

    public Resultado<Porte> excluir(String descricao) {
        Porte porte = new Porte(descricao);
        return dao.excluir(porte);
    }

    public Resultado<Porte> editar(Porte porte) {
        Resultado<List<Porte>> res = dao.listar();
        if (res.foiSucesso()) {
            List<Porte> portes = res.comoSucesso().getObj();
            for (Porte p : portes) {
                if (p.getCodigo() == porte.getCodigo()) {
                    return dao.editar(porte);
                }
            }
        }
        return Resultado.erro("Erro ao editar!");

    }

}
