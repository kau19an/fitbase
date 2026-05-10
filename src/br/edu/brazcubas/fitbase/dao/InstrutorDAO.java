package br.edu.brazcubas.fitbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.brazcubas.fitbase.db.Database;
import br.edu.brazcubas.fitbase.entities.Instrutor;

/**
 * @author Breno Christaziano
 * @author Kauan Farias
 * @version 1.2
 */

public class InstrutorDAO {
	// Cadastrar instrutor
	public void cadastrar(Instrutor instrutor) {
		String sql = "INSERT INTO instrutor (ins_primeiro_nome, ins_meio_nome, ins_ultimo_nome, ins_cpf, ins_telefone, ins_especialidade, ins_horarios_trabalho) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, instrutor.getPrimeiroNome());
			stmt.setString(2, instrutor.getMeioNome());
			stmt.setString(3, instrutor.getUltimoNome());
			stmt.setString(4, instrutor.getCpf());
			stmt.setString(5, instrutor.getTelefone());
			stmt.setString(6, instrutor.getEspecialidade());
			stmt.setString(7, instrutor.getHorariosTrabalho());

			stmt.execute();
			System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m " + instrutor.getPrimeiroNome()
					+ " foi cadastrado(a) no banco de dados.");
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao cadastrar instrutor(a): " + e.getMessage());
		}
	}

	// Listar todos os instrutores
	public List<Instrutor> listarTodos() {
		List<Instrutor> lista = new ArrayList<>();
		String sql = """
				SELECT i.*, 
			       COUNT(DISTINCT ia.alu_id) as total_alunos_atendidos,
			       COALESCE(STRING_AGG(DISTINCT au.aul_nome, ' | '), 'Nenhuma') as aulas_ministradas
				FROM instrutor i
				LEFT JOIN aula au ON i.ins_id = au.ins_id
				LEFT JOIN inscricao_aula ia ON au.aul_id = ia.aul_id
				GROUP BY i.ins_id
				ORDER BY i.ins_primeiro_nome ASC
				""";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Instrutor instrutor = new Instrutor();
				instrutor.setId(rs.getInt("ins_id"));

				instrutor.setPrimeiroNome(rs.getString("ins_primeiro_nome"));
				instrutor.setMeioNome(rs.getString("ins_meio_nome"));
				instrutor.setUltimoNome(rs.getString("ins_ultimo_nome"));

				instrutor.setCpf(rs.getString("ins_cpf"));
				instrutor.setTelefone(rs.getString("ins_telefone"));

				instrutor.setEspecialidade(rs.getString("ins_especialidade"));
				instrutor.setHorariosTrabalho(rs.getString("ins_horarios_trabalho"));
				
				instrutor.setQtdAlunos(rs.getInt("total_alunos_atendidos"));
				instrutor.setAulasMinistradas(rs.getString("aulas_ministradas"));

				lista.add(instrutor);
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao listar instrutores: " + e.getMessage());
		}

		return lista;
	}

	// Atualizar instrutor
	public void atualizar(Instrutor instrutor) {
		String sql = "UPDATE instrutor SET ins_primeiro_nome=?, ins_meio_nome=?, ins_ultimo_nome=?, ins_cpf=?, ins_telefone=?, ins_especialidade=?, ins_horarios_trabalho=? WHERE ins_id=?";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, instrutor.getPrimeiroNome());
			stmt.setString(2, instrutor.getMeioNome());
			stmt.setString(3, instrutor.getUltimoNome());
			stmt.setString(4, instrutor.getCpf());
			stmt.setString(5, instrutor.getTelefone());
			stmt.setString(6, instrutor.getEspecialidade());
			stmt.setString(7, instrutor.getHorariosTrabalho());
			stmt.setInt(8, instrutor.getId());

			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m O(A) instrutor(a) \"" + instrutor.getPrimeiroNome()
						+ "\" foi atualizado(a).");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao atualizar instrutor(a): " + e.getMessage());
		}
	}

	// Excluir instrutor
	public void excluir(int id) {
		String sql = "DELETE FROM instrutor WHERE ins_id=?";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);

			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m O(A) instrutor(a) foi excluído(a).");
			} else {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m ID não encontrado. Tente novamente.");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao excluir instrutor(a): " + e.getMessage());
		}
	}
}
