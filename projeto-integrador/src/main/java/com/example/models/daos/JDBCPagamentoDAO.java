package com.example.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.example.models.FabricaConexoes;
import com.example.models.Pagamento;
import com.example.utils.DBUtils;
import com.example.utils.Resultado;

public class JDBCPagamentoDAO implements PagamentoDAO {

    private FabricaConexoes fabrica;

    public JDBCPagamentoDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado<Pagamento> registrar(Pagamento pagamento) {
        String sql = "Insert into projeto_pagamento (data_hora, forma_pg, ticket_id) values (?, ?, ?) ";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setTimestamp(1, Timestamp.valueOf(pagamento.getData_hora()));
            pstm.setString(2, pagamento.getForma());
            pstm.setInt(3, pagamento.getTicket().getId());
            int rows = pstm.executeUpdate();
            if (rows == 1) {
                return Resultado.sucesso("Pagamento registrado", pagamento);
            } else {
                return Resultado.erro("Erro ao registrar pagamento");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

}
