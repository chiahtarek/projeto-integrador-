package com.example.repositories;

import com.example.daos.PessoaDAO;
import com.example.models.Pessoa;
import com.example.utils.Resultado;

public class PessoaRepository {

    private PessoaDAO dao;

    public PessoaRepository(PessoaDAO dao) {
        this.dao = dao;
    }

    public Resultado<Pessoa> cadastrar(String nome, String telefone) {
        Pessoa pessoa = new Pessoa(nome, telefone);
        return dao.criar(pessoa);

    }

}
