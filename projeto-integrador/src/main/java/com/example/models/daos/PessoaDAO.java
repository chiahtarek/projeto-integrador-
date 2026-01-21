package com.example.models.daos;

import java.util.List;

import com.example.models.Pessoa;
import com.example.utils.Resultado;

public interface PessoaDAO {
        Resultado<Pessoa> criar(Pessoa pessoa);

        Resultado<List<Pessoa>> listar();

        Resultado<Pessoa> buscarPorId(int id);
}
