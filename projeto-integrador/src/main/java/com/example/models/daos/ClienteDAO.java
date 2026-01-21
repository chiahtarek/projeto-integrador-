package com.example.models.daos;

import java.util.List;

import com.example.models.Cliente;
import com.example.utils.Resultado;

public interface ClienteDAO {
    Resultado<Cliente> cadastrar(Cliente cliente); 
    Resultado<List<Cliente>> listar(); 
    Resultado<Cliente> excluir(Cliente cliente); 
    Resultado<Cliente> editar(Cliente cliente); 
    Resultado<Cliente> buscarPorId(int id); 
    Resultado<Cliente> buscarClienteCarro(String placa); 
    
}
