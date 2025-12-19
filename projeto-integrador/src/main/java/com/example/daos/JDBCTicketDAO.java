package com.example.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.models.FabricaConexoes;
import com.example.models.Ticket;
import com.example.utils.Resultado;

public class JDBCTicketDAO implements TicketDAO {

    private FabricaConexoes fabrica;

    public JDBCTicketDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado<Ticket> cadastrar(Ticket ticket) {
        String sql = "insert into projeto_ticket (data_hora_entrada, data_hora_saida, status, id_vaga, placa) values (?, ? , ? , ? , ?)";
        try (Connection con = fabrica.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

                pstm.setObject(1, ticket.getData_hora_entrada());
                pstm.setObject(2, ticket.getData_hora_saida());
                pstm.setBoolean(3, ticket.isStatus());
                pstm.setInt(4, ticket.getVaga().getId());
                pstm.setString(5, ticket.getCarro().getPlaca());
                int rows = pstm.executeUpdate(); 
                if(rows == 1){
                    return Resultado.sucesso("Ticket Registrado", ticket); 
                }
                return Resultado.erro("Erro ao registrar ticket"); 
        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

}
