package com.example.models;

public class Cliente extends Pessoa {
    private int id;
    private String cpf;
    private String email;

    public Cliente(int id, String nome, String telefone, String cpf, String email) {
        super(id, nome, telefone);
        this.cpf = cpf;
        this.email = email;
    }

    public Cliente(String nome, String telefone, String cpf, String email) {
        super(nome, telefone);
        this.cpf = cpf;
        this.email = email;
    }

    public Cliente(String cpf, String email) {
        this.cpf = cpf;
        this.email = email;

    }

    public Cliente(int id, String cpf, String email) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
