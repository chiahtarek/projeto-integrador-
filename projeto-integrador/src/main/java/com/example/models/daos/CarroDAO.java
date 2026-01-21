package com.example.models.daos;

import java.util.List;

import com.example.models.Carro;
import com.example.utils.Resultado;

public interface CarroDAO {
    Resultado<Carro> cadastrar(Carro carro);  
    Resultado<List<Carro>> listar(); 
    Resultado<Carro> editar(Carro carro); 
    Resultado<Carro> excluir(Carro carro); 
    Resultado<Carro> buscar(String placa); 
    Resultado<Carro> buscarCarroTicket(int id); 
}
