package br.edu.brazcubas.fitbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.brazcubas.fitbase.db.Database;
import br.edu.brazcubas.fitbase.entities.Plano;

/**
 * @author Kauan Farias
 * @author Breno Christaziano
 * @version 1.2
 */

public class PlanoDAO {
	// Cadastrar plano
	public void cadastrar(Plano plano) {
		String sql = "INSERT INTO plano (pln_nome, pln_descricao, pln_beneficios, pln_duracao, pln_valor_mensal) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, plano.getNome());
			stmt.setString(2, plano.getDescricao());
			stmt.setString(3, plano.getBeneficios());
			stmt.setInt(4, plano.getDuracao());
			stmt.setDouble(5, plano.getValorMensal());

			stmt.execute();
			System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m O plano \"" + plano.getNome()
					+ "\" foi cadastrado.");
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao cadastrar plano: " + e.getMessage());
		}
	}

	// Listar todos os planos
	public List<Plano> listarTodos() {
		List<Plano> planos = new ArrayList<>();
		String sql = "SELECT * FROM plano ORDER BY pln_id ASC";

		try (Connection conn = Database.getConnection();
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

	// Atualizar aula
	public void atualizar(Plano plano) {
		String sql = "UPDATE plano SET pln_nome=?, pln_descricao=?, pln_beneficios=?, pln_duracao=?, pln_valor_mensal=? WHERE pln_id=?";

		try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, plano.getNome());
			stmt.setString(2, plano.getDescricao());
			stmt.setString(3, plano.getBeneficios());
			stmt.setInt(4, plano.getDuracao());
			stmt.setDouble(5, plano.getValorMensal());
			stmt.setInt(6, plano.getId());

			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m O plano \"" + plano.getNome()
						+ "\" foi atualizado.");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao atualizar plano: " + e.getMessage());
		}
	}

	// Excluir aula
	public void excluir(int id) {
		String sql = "DELETE FROM plano WHERE pln_id=?";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);

			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m O plano foi excluído.");
			} else {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m ID não encontrado. Tente novamente.");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao atualizar plano: " + e.getMessage());
		}
	}
}
