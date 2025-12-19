package com.example.models;

public class Vaga {
    private int id;
    private String corredor;
    private boolean status;

    public Vaga(int id, String corredor, boolean status) {
        this.id = id;
        this.corredor = corredor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorredor() {
        return corredor;
    }

    public void setCorredor(String corredor) {
        this.corredor = corredor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
