package com.example.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Carro;
import com.example.models.Cliente;
import com.example.models.FabricaConexoes;
import com.example.models.Porte;
import com.example.utils.DBUtils;
import com.example.utils.Resultado;

public class JDBCCarroDAO implements CarroDAO {

    private FabricaConexoes fabrica;

    public JDBCCarroDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado<Carro> cadastrar(Carro carro) {
        String sql = "Insert into projeto_veiculo (placa, modelo, marca, cor, cliente_id, porte_codigo) values (?, ?, ?, ?, ?, ?)";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, carro.getPlaca());
            pstm.setString(2, carro.getModelo());
            pstm.setString(3, carro.getMarca());
            pstm.setString(4, carro.getCor());
            pstm.setInt(5, carro.getCliente().getId());
            pstm.setInt(6, carro.getPorte().getCodigo());

            int rows = pstm.executeUpdate();
            if (rows == 1) {
                return Resultado.sucesso("Carro cadastrado", carro);
            }
            return Resultado.erro("erro ao cadastrar");

        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<List<Carro>> listar() {
        String sql = "Select * from projeto_veiculo";
        List<Carro> carros = new ArrayList<>();
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                String placa = rs.getString("placa");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                String cor = rs.getString("cor");

                Carro carro = new Carro(placa, modelo, marca, cor, null, null);
                carros.add(carro);
            }
            return Resultado.sucesso("Lista", carros);

        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<Carro> editar(Carro carro) {
        String sql = "UPDATE projeto_veiculo set marca = ?, modelo = ?, cor = ?, cliente_id = ?, porte_codigo = ? where placa = ?";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, carro.getModelo());
            pstm.setString(2, carro.getMarca());
            pstm.setString(3, carro.getCor());
            pstm.setInt(4, carro.getCliente().getId());
            pstm.setInt(5, carro.getPorte().getCodigo());
            pstm.setString(6, carro.getPlaca());

            int rows = pstm.executeUpdate();
            if (rows == 1) {
                return Resultado.sucesso("Carro editado", carro);
            } else {
                return Resultado.erro("Erro ao editar carro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<Carro> excluir(Carro carro) {
        String sql = "DELETE FROM projeto_veiculo where placa = ? ";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, carro.getPlaca());
            int rows = pstm.executeUpdate();
            if (rows == 1) {
                return Resultado.sucesso("Carro excluido", carro);
            } else {
                return Resultado.erro("Erro ao excluir");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

}
