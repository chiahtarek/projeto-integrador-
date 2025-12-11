package com.example.repositories;

import java.util.List;

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

        System.out.println("Porte encontrado: "
                + (rPorte.foiSucesso() ? rPorte.comoSucesso().getObj().getCodigo() : "não encontrado"));
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

    public Resultado<List<Carro>> listar() {
        return carroDAO.listar();
    }

    public Resultado<Carro> editar(Carro carro) {
        Resultado<List<Carro>> res = carroDAO.listar();
        if (res.foiSucesso()) {
            List<Carro> carros = res.comoSucesso().getObj();
            for (Carro c : carros) {
                if (c.getPlaca().equals(carro.getPlaca())) {
                    return carroDAO.editar(carro);
                }
            }
        }
        return Resultado.erro("erro ao buscar");
    }
    public Resultado<Carro> excluir(String placa){
        Resultado<List<Carro>> resCarro = carroDAO.listar(); 
        if(resCarro.foiSucesso()){
            List<Carro> carros = resCarro.comoSucesso().getObj();
            for(Carro c: carros){
                if(c.getPlaca().equals(placa)){
                    return carroDAO.excluir(c); 
                }
            }
        }
        return Resultado.erro("erro ao excluir "); 
    }
}
