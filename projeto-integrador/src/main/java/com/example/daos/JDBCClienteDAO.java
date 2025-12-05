package com.example.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.example.models.Cliente;
import com.example.models.FabricaConexoes;
import com.example.models.Pessoa;
import com.example.utils.DBUtils;
import com.example.utils.Resultado;

public class JDBCClienteDAO implements ClienteDAO {

    private FabricaConexoes fabrica;

    public JDBCClienteDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado<Cliente> cadastrar(Cliente cliente) {
        String sql = "INSERT INTO projeto_cliente (pessoa_codigo, cpf, email) VALUES (?, ?, ?)";

        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, cliente.getId());
            pstm.setString(2, cliente.getCpf());
            pstm.setString(3, cliente.getEmail());

            int rows = pstm.executeUpdate();

            if (rows == 1) {
                int id = DBUtils.getLastId(pstm); 
                cliente.setId(id); 
                return Resultado.sucesso("Cliente cadastrado", cliente);
            }
            return Resultado.erro("Erro ao cadastrar cliente");

        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<List<Cliente>> listar() {
        String sql = "SELECT * FROM projeto_cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                ResultSet result = pstm.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("pessoa_codigo"); 
                String cpf = result.getString("cpf");
                String email = result.getString("email");

                Cliente cliente = new Cliente(id, cpf, email);
                clientes.add(cliente);
            }
            return Resultado.sucesso("Lista ", Collections.unmodifiableList(clientes));

        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }

    }

    @Override
    public Resultado<Cliente> excluir(Cliente cliente) {
        String sql = "DELETE FROM projeto_cliente where pessoa_codigo = ?";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, cliente.getId());
            int rows = pstm.executeUpdate();
            if (rows == 1) {
                return Resultado.sucesso("Excluido com sucesso", cliente);
            } else {
                return Resultado.erro("erro ao excluir");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

}
