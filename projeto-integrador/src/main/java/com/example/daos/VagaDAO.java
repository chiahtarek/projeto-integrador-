package com.example.daos;

import java.util.List;

import com.example.models.Vaga;
import com.example.utils.Resultado;

public interface VagaDAO {
    public Resultado<List<Vaga>> listaVagasLivres(); 
    public Resultado<Vaga> buscar(int codigo); 
}
