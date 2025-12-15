package com.example.daos;

import java.util.List;

import com.example.models.Porte;
import com.example.utils.Resultado;

public interface PorteDAO {
    
    public Resultado<Porte> salvar(Porte porte); 
    public Resultado<List<Porte>> listar(); 
    public Resultado<Porte> excluir(Porte porte);
    public Resultado<Porte> editar(Porte porte); 
    public Resultado<Porte> buscarPorId(int id); 
    public Resultado<Porte> buscarPorteCarro(String placa); 
}
