package com.example.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.example.models.FabricaConexoes;
import com.example.models.Porte;
import com.example.utils.Resultado;

public class JDBCPorteDAO implements PorteDAO{

    private FabricaConexoes fabrica; 

    public JDBCPorteDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica; 
    }

    @Override
    public Resultado<Porte> salvar(Porte porte) {
        Connection con = null; 
        try {
            con = fabrica.getConnection();

            String sql = "INSERT into projeto_porte (descricao) VALUES (?)"; 
            PreparedStatement pstm = con.prepareStatement(sql); 

            pstm.setString(1, porte.getDescricao());

            int rows = pstm.executeUpdate(); 
            if(rows == 1){
                return Resultado.sucesso("Porte cadastrado", porte); 
            }
            else{
                return Resultado.erro("Erro ao cadastrar"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Resultado.erro(e.getMessage());  
        }
      
    }

    @Override
    public Resultado<List<Porte>> listar() {
        List<Porte> lista = new ArrayList<>(); 
        Connection con; 

        try {
            con = fabrica.getConnection();
            String sql = "SELECT * FROM projeto_porte"; 
            PreparedStatement pstm = con.prepareStatement(sql); 

            ResultSet result = pstm.executeQuery();

            while(result.next()){
                int codigo = result.getInt("codigo"); 
                String descricao = result.getString("descricao"); 

                Porte porte = new Porte(codigo, descricao); 
                lista.add(porte); 
            }
            return Resultado.sucesso("Lista", Collections.unmodifiableList(lista)); 

        } catch (SQLException e) {
           return Resultado.erro(e.getMessage()); 
        }
    }
    @Override
    public Resultado<Porte> excluir(Porte porte){
        Connection con; 
        try{
            con = fabrica.getConnection(); 
            String sql = "DELETE FROM projeto_porte where codigo = ?";
            PreparedStatement pstm = con.prepareStatement(sql); 
            pstm.setInt(1, porte.getCodigo());
            int rows = pstm.executeUpdate(); 
            if(rows == 1){
                return Resultado.sucesso("Porte excluido", porte); 
            }
            else{
                return Resultado.erro("Erro ao cadastrar"); 
            }

        }
        catch(SQLException e){
            e.printStackTrace();
            return Resultado.erro(e.getMessage()); 
        }
    }
    
}
