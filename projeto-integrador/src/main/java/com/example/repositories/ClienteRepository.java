package com.example.repositories;

import java.util.List;

import com.example.daos.ClienteDAO;
import com.example.daos.PessoaDAO;
import com.example.models.Cliente;
import com.example.models.Pessoa;
import com.example.utils.Resultado;

public class ClienteRepository {
    private ClienteDAO clienteDAO;
    private PessoaDAO pessoaDAO;

    public ClienteRepository(ClienteDAO clienteDAO, PessoaDAO pessoaDAO) {
        this.clienteDAO = clienteDAO;
        this.pessoaDAO = pessoaDAO;
    }

    public Resultado<Cliente> cadastrar(int id, String cpf, String email) {
        Resultado<Pessoa> rPessoa = pessoaDAO.buscarPorId(id);
        if (!rPessoa.foiSucesso()) {
            return Resultado.erro("Pessoa com id " + id + "nao encontrado");
        }
        Pessoa pessoa = rPessoa.comoSucesso().getObj();
        Cliente cliente = new Cliente(pessoa.getId(), cpf, email);
        return clienteDAO.cadastrar(cliente);
    }

    public Resultado<List<Cliente>> listar() {
        return clienteDAO.listar();
    }

    public Resultado<Cliente> excluir(int id) {
        Resultado<List<Cliente>> result = clienteDAO.listar();
        if (result.foiSucesso()) {
            List<Cliente> clientes = result.comoSucesso().getObj();
            for (Cliente c : clientes) {
                if (c.getId() == id) {
                    Cliente cliente = new Cliente(c.getId(), c.getCpf(), c.getEmail());
                    return clienteDAO.excluir(cliente);
                }
            }

        }
        return result.comoErro();

    }
    // public Resultado<Cliente> editar (Cliente cliente){

    // }

}
