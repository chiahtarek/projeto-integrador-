package com.example.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.models.FabricaConexoes;
import com.example.models.Vaga;
import com.example.utils.Resultado;

public class JDBCVagaDAO implements VagaDAO {

    private FabricaConexoes fabrica;

    public JDBCVagaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado<List<Vaga>> listaVagasLivres() {
        String sql = "select * from projeto_vaga where status = 0";
        List<Vaga> vagas = new ArrayList<>();
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("codigo");
                String localizacao = rs.getString("localizacao");
                Boolean status = rs.getBoolean("status");
                Vaga vaga = new Vaga(id, localizacao, status);
                vagas.add(vaga);
            }
            return Resultado.sucesso("vagas livres", vagas);
        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

}
