package com.example.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.models.FabricaConexoes;
import com.example.models.Pessoa;
import com.example.utils.DBUtils;
import com.example.utils.Resultado;

public class JDBCPessoaDAO implements PessoaDAO {

    private FabricaConexoes fabrica;

    public JDBCPessoaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado<Pessoa> criar(Pessoa pessoa) {
        String sql = "INSERT INTO projeto_pessoa (nome,telefone) VALUES (?,?) ";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, pessoa.getNome());
            pstm.setString(2, pessoa.getTelefone());
            int rows = pstm.executeUpdate();
            if (rows == 1) {
                int id = DBUtils.getLastId(pstm);
                pessoa.setId(id);
                return Resultado.sucesso("Pessoa cadastrada", pessoa);
            } else {
                return Resultado.erro("Erro ao cadastrar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<List<Pessoa>> listar() {

        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Resultado<Pessoa> buscarPorId(int id) {
        String sql = "SELECT codigo, nome, telefone FROM projeto_pessoa where codigo = ?";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                Pessoa p = new Pessoa(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("telefone"));
                return Resultado.sucesso("Id encontrado", p);
            }
            return Resultado.erro("Id nao encontrado");
        } catch (SQLException e) {

            e.printStackTrace();
            return Resultado.erro(e.getMessage());
        }
    }

}
