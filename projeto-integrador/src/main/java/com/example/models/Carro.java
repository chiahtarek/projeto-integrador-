package com.example.models;

public class Carro {
    private String placa;
    private String modelo;
    private String marca;
    private String cor;
    private int cliente_id;
    private int porte_id;

    public Carro(String placa, String modelo, String marca, String cor, int cliente_id, int porte_id) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
        this.cliente_id = cliente_id;
        this.porte_id = porte_id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getPorte_id() {
        return porte_id;
    }

    public void setPorte_id(int porte_id) {
        this.porte_id = porte_id;
    }

}
