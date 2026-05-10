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
import br.edu.brazcubas.fitbase.entities.Frequencia;

/**
 * @author Breno Christaziano
 * @author Kauan Farias
 * @version 1.2
 */

public class FrequenciaDAO {
	// Cadastrar frequência
	public void cadastrar(Frequencia frequencia) {
		String sql = "INSERT INTO frequencia (frq_data_entrada, frq_hora_entrada, alu_id) VALUES (?, ?, ?)";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDate(1, Date.valueOf(frequencia.getDataEntrada()));
			stmt.setString(2, frequencia.getHoraEntrada());
			stmt.setInt(3, frequencia.getAluno().getId());

			stmt.execute();
			System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m A frequência foi registrada.\n");
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao cadastrar frequência: " + e.getMessage());
		}
	}

	// Listar todas as frequências (por aluno)
	public List<Frequencia> listarTodas(int aluId) {
		List<Frequencia> lista = new ArrayList<>();
		String sql = """
				SELECT f.*, a.alu_primeiro_nome, a.alu_meio_nome, a.alu_ultimo_nome
				FROM frequencia f
				JOIN aluno a ON f.alu_id = a.alu_id
				WHERE f.alu_id = ?
				ORDER BY f.frq_data_entrada DESC, f.frq_hora_entrada DESC
				""";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, aluId);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Frequencia frequencia = new Frequencia();

					frequencia.setId(rs.getInt("frq_id"));
					frequencia.setDataEntrada(rs.getDate("frq_data_entrada").toLocalDate());
					frequencia.setHoraEntrada(rs.getString("frq_hora_entrada"));

					// FK: aluno
					Aluno aluno = new Aluno();
					aluno.setId(rs.getInt("alu_id"));
					aluno.setPrimeiroNome(rs.getString("alu_primeiro_nome"));
					aluno.setMeioNome(rs.getString("alu_meio_nome"));
					aluno.setUltimoNome(rs.getString("alu_ultimo_nome"));
					frequencia.setAluno(aluno);

					lista.add(frequencia);
				}
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao listar frequências: " + e.getMessage());
		}
		
		return lista;
	}

	// Atualizar frequência
	public void atualizar(Frequencia frequencia) {
		String sql = "UPDATE frequencia SET frq_data_entrada=?, frq_hora_entrada=?, alu_id=? WHERE frq_id=?";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDate(1, Date.valueOf(frequencia.getDataEntrada()));
			stmt.setString(2, frequencia.getHoraEntrada());
			stmt.setInt(3, frequencia.getAluno().getId());
			stmt.setInt(4, frequencia.getId());

			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m A frequência foi atualizada.\n");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao atualizar frequência: " + e.getMessage());
		}
	}

	// Excluir frequência
	public void excluir(int id) {
		String sql = "DELETE FROM frequencia WHERE frq_id=?";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);

			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m A frequência foi excluída.\n");
			} else {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m ID não encontrado. Tente novamente.");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao excluir frequência: " + e.getMessage());
		}
	}
}
