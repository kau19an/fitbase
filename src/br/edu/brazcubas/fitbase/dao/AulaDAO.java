package br.edu.brazcubas.fitbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.brazcubas.fitbase.db.Database;
import br.edu.brazcubas.fitbase.entities.Aula;
import br.edu.brazcubas.fitbase.entities.Instrutor;

/**
 * @author Kauan Farias
 * @version 1.1
 */

public class AulaDAO {
	// Cadastrar aula
	public void cadastrar(Aula aula) {
		String sql = "INSERT INTO aula (aul_nome, aul_descricao, aul_capacidade_max, aul_horario, aul_duracao, ins_id) VALUES (?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, aula.getNome());
			stmt.setString(2, aula.getDescricao());
			stmt.setInt(3, aula.getCapacidadeMax());
			stmt.setString(4, aula.getHorario());
			stmt.setInt(5, aula.getDuracao());
			stmt.setInt(6, aula.getInstrutor().getId());
			
			stmt.execute();
			System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m A aula \"" + aula.getNome() + "\" foi cadastrada.");
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao cadastrar aula: " + e.getMessage());
		}
	}
	
	// Listar todas as aulas
	public List<Aula> listarTodos() {
		List<Aula> lista = new ArrayList<>();
		String sql = """
				SELECT au.*, i.ins_primeiro_nome, i.ins_meio_nome, i.ins_ultimo_nome,
			       CASE 
			         WHEN COUNT(ia.alu_id) = 0 THEN '0' 
			         ELSE CAST(COUNT(ia.alu_id) AS VARCHAR) 
			       END as total_alunos
				FROM aula au
				INNER JOIN instrutor i ON au.ins_id = i.ins_id
				LEFT JOIN inscricao_aula ia ON au.aul_id = ia.aul_id
				GROUP BY au.aul_id, i.ins_id
				ORDER BY au.aul_horario ASC;
				""";
		
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Aula aula = new Aula();
				aula.setId(rs.getInt("aul_id"));
				
				aula.setNome(rs.getString("aul_nome"));
				aula.setDescricao(rs.getString("aul_descricao"));
				aula.setHorario(rs.getString("aul_horario"));
				aula.setDuracao(rs.getInt("aul_duracao"));
				aula.setCapacidadeMax(rs.getInt("aul_capacidade_max"));
				
				// FK: instrutor
				Instrutor instrutor = new Instrutor();
				instrutor.setId(rs.getInt("ins_id"));
				
				instrutor.setPrimeiroNome(rs.getString("ins_primeiro_nome"));
				instrutor.setMeioNome(rs.getString("ins_meio_nome"));
				instrutor.setUltimoNome(rs.getString("ins_ultimo_nome"));
				aula.setInstrutor(instrutor);
				
				aula.setTotalInscritos(rs.getString("total_alunos"));
				
				lista.add(aula);
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao listar aulas: " + e.getMessage());
		}
		
		return lista;
	}
	
	// Atualizar aula
	public void atualizar(Aula aula) {
		String sql = "UPDATE aula SET aul_nome=?, aul_descricao=?, aul_capacidade_max=?, aul_horario=?, aul_duracao=?, ins_id=? WHERE aul_id=?";
		
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, aula.getNome());
			stmt.setString(2, aula.getDescricao());
			stmt.setInt(3, aula.getCapacidadeMax());
			stmt.setString(4, aula.getHorario());
			stmt.setInt(5, aula.getDuracao());
			stmt.setInt(6, aula.getInstrutor().getId());
			stmt.setInt(7, aula.getId());
			
			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m A aula \"" + aula.getNome() + "\" foi atualizada.");			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao atualizar aula: " + e.getMessage());
		}
	}
	
	// Excluir aula
	public void excluir(int id) {
		String sql = "DELETE FROM aula WHERE aul_id=?";
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			
			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m A aula foi excluída do banco de dados.");
			} else {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m ID não encontrado. Tente novamente.");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao excluir aula: " + e.getMessage());
		}
	}
}
