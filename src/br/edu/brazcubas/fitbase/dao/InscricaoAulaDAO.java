package br.edu.brazcubas.fitbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import br.edu.brazcubas.fitbase.db.Database;
import br.edu.brazcubas.fitbase.entities.InscricaoAula;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class InscricaoAulaDAO {
	// Cadastrar inscrição
	public boolean cadastrar(InscricaoAula inscricao) {
		try (Connection conn = Database.getConnection()) {
			// 1. Verifica se o plano está vencido
			if (isPlanoVencido(conn, inscricao.getAluno().getId())) {
				System.out.println(
						"\u001B[1;31m[ERRO]\u001B[0m Inscrição negada: O plano deste(a) aluno(a) está vencido.\n");
				return false;
			}

			// 2. Verifica a capacidade da aula
			if (isAulaLotada(conn, inscricao.getAula().getId())) {
				System.out.println(
						"\u001B[1;31m[ERRO]\u001B[0m Inscrição negada: A aula já atingiu sua capacidade máxima.\n");
				return false;
			}

			// 3. Verifica choques de horários
			if (temChoqueHorarios(conn, inscricao.getAluno().getId(), inscricao.getAula().getId())) {
				return false;
			}

			// Se passou por todas as regras de negócio, pode inscrevê-lo
			String sql = "INSERT INTO inscricao_aula (alu_id, aul_id) VALUES (?, ?)";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, inscricao.getAluno().getId());
				stmt.setInt(2, inscricao.getAula().getId());

				stmt.execute();

				String sqlBuscaNomes = """
						SELECT a.alu_primeiro_nome, au.aul_nome
						FROM aluno a, aula au
						WHERE a.alu_id = ? AND au.aul_id = ?
						""";

				try (PreparedStatement stmtBusca = conn.prepareStatement(sqlBuscaNomes)) {
					stmtBusca.setInt(1, inscricao.getAluno().getId());
					stmtBusca.setInt(2, inscricao.getAula().getId());

					try (ResultSet rs = stmtBusca.executeQuery()) {
						if (rs.next()) {
							String nomeAluno = rs.getString("alu_primeiro_nome");
							String nomeAula = rs.getString("aul_nome");

							System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m " + nomeAluno
									+ " foi matriculado(a) na aula \"" + nomeAula + "\".");
						}
					}
				}

				return true;
			}

		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao cadastrar inscrição: " + e.getMessage());
			return false;
		}
	}

	// Métodos auxiliares para a regra de negócio
	private boolean isPlanoVencido(Connection conn, int idAluno) throws SQLException {
		// Obtém a data que o aluno entrou e a duração de seu plano
		String sql = """
				SELECT a.alu_data_matricula, p.pln_duracao
				FROM aluno a
				JOIN plano p ON a.pln_id = p.pln_id
				WHERE a.alu_id = ?
				""";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idAluno);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					LocalDate dataMatricula = rs.getDate("alu_data_matricula").toLocalDate();
					int duracaoMeses = rs.getInt("pln_duracao");

					// Calcula o vencimento
					LocalDate dataVencimento = dataMatricula.plusMonths(duracaoMeses);

					// Se a data de hoje for depois do vencimento, está vencido
					return LocalDate.now().isAfter(dataVencimento);
				}
			}
		}
		return true; // Se não encontrar o aluno, impede
	}

	private boolean isAulaLotada(Connection conn, int idAula) throws SQLException {
		// Conta quantos alunos já estão inscritos e compara com o limite da aula
		String sql = """
				SELECT
				    (SELECT COUNT(*) FROM inscricao_aula WHERE aul_id = ?) AS qtd_inscritos,
				    aul_capacidade_max
				FROM aula
				WHERE aul_id = ?
				""";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idAula);
			stmt.setInt(2, idAula);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int inscritos = rs.getInt("qtd_inscritos");
					int capacidadeMax = rs.getInt("aul_capacidade_max");

					return inscritos >= capacidadeMax;
				}
			}
		}
		return true; // Se ocorrerem erros na busca, impede
	}

	private boolean temChoqueHorarios(Connection conn, int idAluno, int idAulaNova) throws SQLException {
		// Busca o horário da aula que o aluno deseja entrar
		String horarioAulaNova = null;
		String sqlBuscaHorario = "SELECT aul_horario FROM aula WHERE aul_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sqlBuscaHorario)) {
			stmt.setInt(1, idAulaNova);

			try (ResultSet rs1 = stmt.executeQuery()) {
				if (rs1.next()) {
					horarioAulaNova = rs1.getString("aul_horario");
				}
			}
		}

		if (horarioAulaNova == null)
			return false;

		// Verifica se o aluno já tem alguma inscrição neste mesmo horário
		String sqlConflito = """
				SELECT a.aul_id
				FROM inscricao_aula ia
				JOIN aula a ON ia.aul_id = a.aul_id
				WHERE ia.alu_id = ? AND a.aul_horario = ?
				""";

		try (PreparedStatement stmt2 = conn.prepareStatement(sqlConflito)) {
	        stmt2.setInt(1, idAluno);
	        stmt2.setString(2, horarioAulaNova);
	        try (ResultSet rs2 = stmt2.executeQuery()) {
	            if (rs2.next()) { // Se retornou algo, há conflito
	                String nomeAulaAntiga = rs2.getString("aul_nome");
	                System.out.println("\u001B[1;33m[AVISO]\u001B[0m Este(a) aluno(a) está matriculado(a) na aula \"" + nomeAulaAntiga + "\" neste mesmo horário (" + horarioAulaNova + ").\n");
	                return true; 
	            }
	            return false;
	        }
	    }
	}
	
	// Excluir inscrição
	public void excluir(int idAluno, int idAula) {
	    String sql = "DELETE FROM inscricao_aula WHERE alu_id = ? AND aul_id = ?";
	    try (Connection conn = Database.getConnection();
	            PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idAluno);
	        stmt.setInt(2, idAula);
	        
	        if (stmt.executeUpdate() > 0) {
	        	System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m Matrícula cancelada.\n");
	        } else {
	            System.out.println("\u001B[1;33m[AVISO]\u001B[0m Inscrição não encontrada.");
	        }
	    } catch (SQLException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m Erro ao excluir inscrição: " + e.getMessage());
	    }
	}
}
