package br.edu.brazcubas.fitbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.brazcubas.fitbase.db.Supabase;
import br.edu.brazcubas.fitbase.entities.Plano;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class PlanoDAO {
	// TODO: cadastrar()
	
	// Listar todos os planos
	public List<Plano> listar() {		
		List<Plano> planos = new ArrayList<>();
		String sql = "SELECT * FROM plano";
		
		try (Connection conn = Supabase.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                Plano plano = new Plano();
	                plano.setId(rs.getInt("pln_id"));
	                
	                plano.setNome(rs.getString("pln_nome"));
	                plano.setDescricao(rs.getString("pln_descricao"));
	                plano.setBeneficios(rs.getString("pln_beneficios"));
	                
	                plano.setDuracao(rs.getInt("pln_duracao"));
	                plano.setValorMensal(rs.getDouble("pln_valor_mensal"));
	                
	                planos.add(plano);
	            }
	        } catch (SQLException e) {
	            System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao listar planos: " + e.getMessage());
	        }
		
	        return planos;
	}
	
	// TODO: atualizar()
	
	// TODO: excluir()
}
