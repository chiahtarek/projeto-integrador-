package com.example.repositories;

import java.util.List;

import com.example.daos.VagaDAO;
import com.example.models.Vaga;
import com.example.utils.Resultado;

public class VagaRepository {
    private VagaDAO vagaDAO; 

    public VagaRepository(VagaDAO vagaDAO){
        this.vagaDAO = vagaDAO; 
    }
    public Resultado<List<Vaga>> listarVagasLivres(){
        return vagaDAO.listaVagasLivres(); 
    }
    public Resultado<Vaga> buscar(int codigo){
        return vagaDAO.buscar(codigo); 
    }
}
