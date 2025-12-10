package com.example.repositories;

import com.example.daos.CarroDAO;
import com.example.daos.ClienteDAO;
import com.example.daos.PorteDAO;
import com.example.models.Carro;
import com.example.models.Cliente;
import com.example.models.Porte;
import com.example.utils.Resultado;

public class CarroRepository {
    private CarroDAO carroDAO;
    private PorteDAO porteDAO;
    private ClienteDAO clienteDAO;

    public CarroRepository(CarroDAO carroDAO, PorteDAO porteDAO, ClienteDAO clienteDAO) {
        this.carroDAO = carroDAO;
        this.porteDAO = porteDAO;
        this.clienteDAO = clienteDAO;
    }

    public Resultado<Carro> cadastrar(String placa, String modelo, String marca, String cor, int cliente_id,
            int porte_id) {
                System.out.println("ID do porte recebido: " + porte_id);
        Resultado<Porte> rPorte = porteDAO.buscarPorId(porte_id);
        if (!rPorte.foiSucesso()) {
            return Resultado.erro("erro ao buscar porte");
        }

        
        Porte porte = rPorte.comoSucesso().getObj();

        System.out.println("Porte encontrado: " + (rPorte.foiSucesso() ? rPorte.comoSucesso().getObj().getCodigo() : "não encontrado"));
        Resultado<Cliente> rCliente = clienteDAO.buscarPorId(cliente_id);
        if (!rCliente.foiSucesso()) {
            return Resultado.erro("erro ao buscar cliente");
        }
        Cliente cliente = rCliente.comoSucesso().getObj();

        Carro carro = new Carro(placa, modelo, marca, cor, cliente.getId(), porte.getCodigo());
        System.out.println("Cliente ID: " + cliente.getId());
        System.out.println("Porte ID: " + porte.getCodigo());
        return carroDAO.cadastrar(carro);
    }
}
