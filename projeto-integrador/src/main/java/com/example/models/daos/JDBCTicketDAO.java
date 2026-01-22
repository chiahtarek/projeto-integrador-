package com.example.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.models.FabricaConexoes;
import com.example.models.Ticket;
import com.example.utils.DBUtils;
import com.example.utils.Resultado;

public class JDBCTicketDAO implements TicketDAO {

    private FabricaConexoes fabrica;

    public JDBCTicketDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado<Ticket> registraEntrada(Ticket ticket) {
        String sql = "insert into projeto_ticket (data_hora_entrada, data_hora_saida, status, id_vaga, placa) values (?, ? , ? , ? , ?)";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setObject(1, ticket.getData_hora_entrada());
            pstm.setObject(2, ticket.getData_hora_saida());
            pstm.setBoolean(3, ticket.isStatus());
            pstm.setInt(4, ticket.getVaga().getId());
            pstm.setString(5, ticket.getCarro().getPlaca());
            int rows = pstm.executeUpdate();
            if (rows == 1) {
                return Resultado.sucesso("Ticket Registrado", ticket);
            }   
            return Resultado.erro("Erro ao registrar ticket");
        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<Ticket> registraSaida(Ticket ticket) {
        String sql = "UPDATE projeto_ticket set data_hora_saida = ?, status = ?, valor = ? where id = ?";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setTimestamp(1, Timestamp.valueOf(ticket.getData_hora_saida()));
            pstm.setBoolean(2, ticket.isStatus());
            pstm.setDouble(3, ticket.getValor());
            pstm.setInt(4, ticket.getId()); 
            int rows = pstm.executeUpdate();
            if (rows == 1) {
                return Resultado.sucesso("Saída registrada", ticket);
            } else {
                return Resultado.erro("Erro ao registrar saída ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());

        }
    }

    @Override
    public Resultado<List<Ticket>> listar() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "select * from projeto_ticket";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDateTime data_entrada = rs.getTimestamp("data_hora_entrada").toLocalDateTime();
                Timestamp saidaSwap = rs.getTimestamp("data_hora_saida"); 
                LocalDateTime data_saida = null; 
                if(saidaSwap != null){
                     data_saida = saidaSwap.toLocalDateTime();  
                }
               
                Double valor = rs.getDouble("valor"); 
                Ticket ticket; 
                if(data_saida != null){
                    ticket = new Ticket(id, data_entrada,data_saida, valor);
                }else{
                    ticket = new Ticket(id, data_entrada, valor); 
                }
                tickets.add(ticket);
            }
            return Resultado.sucesso("Sucesso ao listar", Collections.unmodifiableList(tickets));
        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<Ticket> buscar(int id) {
        String sql = "select * from projeto_ticket where id = ?";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                LocalDateTime data_hora_entrada = rs.getTimestamp("data_hora_entrada").toLocalDateTime();
                Ticket ticket = new Ticket(id, data_hora_entrada, null, rs.getBoolean("status"), null, null);
                return Resultado.sucesso("Ticket encontrado", ticket);
            }
            return Resultado.erro("erro ao buscar ticket");
        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<Double> buscaValor(Ticket ticket) {
        String sql = "select data_hora_entrada, data_hora_saida from projeto_ticket where id = ?";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, ticket.getId());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                LocalDateTime data_entrada = rs.getTimestamp("data_hora_entrada").toLocalDateTime();
                LocalDateTime data_saida = rs.getTimestamp("data_hora_saida").toLocalDateTime();
                long minutosSwap = Duration.between(data_entrada, data_saida).toMinutes();
                Double minutos = (double) minutosSwap;
                Double valor = minutos * 0.15;
                return Resultado.sucesso("Valor encontrado", valor);
            }
            return Resultado.erro("Erro ao buscar valor");
        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

}
