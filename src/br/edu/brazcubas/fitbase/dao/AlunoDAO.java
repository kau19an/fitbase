package br.edu.brazcubas.fitbase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.brazcubas.fitbase.db.Database;
import br.edu.brazcubas.fitbase.entities.Aluno;
import br.edu.brazcubas.fitbase.entities.Plano;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class AlunoDAO {
	// Cadastrar aluno
	public void cadastrar(Aluno aluno) {
		String sql = "INSERT INTO aluno (alu_primeiro_nome, alu_meio_nome, alu_ultimo_nome, alu_cpf, alu_data_nasc, alu_email, alu_telefone, alu_data_matricula, pln_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, aluno.getPrimeiroNome());
			stmt.setString(2, aluno.getMeioNome());
			stmt.setString(3, aluno.getUltimoNome());
			stmt.setString(4, aluno.getCpf());
			stmt.setDate(5, Date.valueOf(aluno.getDataNasc()));
			stmt.setString(6, aluno.getEmail());
			stmt.setString(7, aluno.getTelefone());
			stmt.setDate(8, Date.valueOf(aluno.getDataMatricula()));
			stmt.setInt(9, aluno.getPlano().getId());
			
			stmt.execute();
			System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m " + aluno.getPrimeiroNome() + " foi cadastrado(a) no banco de dados.");
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao cadastrar aluno: " + e.getMessage());
		}
	}

	// Listar todos os alunos
	public List<Aluno> listarTodos() {
		List<Aluno> lista = new ArrayList<>();
		String sql = "SELECT * FROM aluno ORDER BY alu_id ASC";
		
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("alu_id"));
				
				aluno.setPrimeiroNome(rs.getString("alu_primeiro_nome"));
				aluno.setMeioNome(rs.getString("alu_meio_nome"));
				aluno.setUltimoNome(rs.getString("alu_ultimo_nome"));
				
				aluno.setCpf(rs.getString("alu_cpf"));
				
				if (rs.getDate("alu_data_nasc") != null) {
					aluno.setDataNasc(rs.getDate("alu_data_nasc").toLocalDate());
				}
				
				aluno.setEmail(rs.getString("alu_email"));
				aluno.setTelefone(rs.getString("alu_telefone"));
				
				if (rs.getDate("alu_data_matricula") != null) {
					aluno.setDataMatricula(rs.getDate("alu_data_matricula").toLocalDate());
				}
				
				Plano plano = new Plano();
				plano.setId(rs.getInt("pln_id"));
				aluno.setPlano(plano);
				
				lista.add(aluno);
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao listar alunos: " + e.getMessage());
		}
		
		return lista;
	}
	
	// Atualizar aluno
	public void atualizar(Aluno aluno) {
		String sql = "UPDATE aluno SET alu_primeiro_nome=?, alu_meio_nome=?, alu_ultimo_nome=?, alu_cpf=?, alu_data_nasc=?, alu_email=?, alu_telefone=?, alu_data_matricula=? WHERE alu_id=?";
		
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, aluno.getPrimeiroNome());
			stmt.setString(2, aluno.getMeioNome());
			stmt.setString(3, aluno.getUltimoNome());
			stmt.setString(4, aluno.getCpf());
			stmt.setDate(5, Date.valueOf(aluno.getDataNasc()));
			stmt.setString(6, aluno.getEmail());
			stmt.setString(7, aluno.getTelefone());
			stmt.setDate(8, Date.valueOf(aluno.getDataMatricula()));
			stmt.setInt(9, aluno.getId());
			
			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m " + aluno.getPrimeiroNome() + " foi atualizado(a) no banco de dados.");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao atualizar aluno: " + e.getMessage());
		}
	}
	
	// Excluir aluno
	public void excluir(int id) {
		String sql = "DELETE FROM aluno WHERE alu_id=?";
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m O aluno foi excluído do banco de dados.");
			} else {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m ID não encontrado. Tente novamente.");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao excluir aluno: " + e.getMessage());
		}
	}
}
