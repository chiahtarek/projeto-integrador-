package com.example.controllers;

import com.example.models.Porte;
import com.example.repositories.PorteRepository;

public class ExcluiPorteController {

    private PorteRepository repositorio; 
    
    public ExcluiPorteController(PorteRepository repositorio){
        this.repositorio = repositorio; 
    }
    
}
