package com.example.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.example.models.Carro;
import com.example.models.FabricaConexoes;
import com.example.utils.DBUtils;
import com.example.utils.Resultado;

public class JDBCCarroDAO implements CarroDAO {

    private FabricaConexoes fabrica;

    public JDBCCarroDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado<Carro> cadastrar(Carro carro) {
        String sql = "Insert into projeto_veiculo (placa, modelo, marca, cor, cliente_pessoa_codigo, porte_codigo) values (?, ?, ?, ?, ?, ?)";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, carro.getPlaca());
            pstm.setString(2, carro.getModelo());
            pstm.setString(3, carro.getMarca());
            pstm.setString(4, carro.getCor());
            pstm.setInt(5, carro.getCliente_id());
            pstm.setInt(6, carro.getPorte_id());

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Resultado<Carro> editar(Carro carro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public Resultado<Carro> excluir(Carro carro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

}
