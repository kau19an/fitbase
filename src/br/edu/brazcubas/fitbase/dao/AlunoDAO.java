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
 * @version 1.3
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
			System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m O(A) aluno(a) \"" + aluno.getPrimeiroNome() + "\" foi cadastrado(a).");
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao cadastrar aluno(a): " + e.getMessage());
		}
	}

	// Listar todos os alunos
	public List<Aluno> listarTodos() {
		List<Aluno> lista = new ArrayList<>();
		
		String sql = """
				SELECT a.*, p.pln_nome, p.pln_duracao,
			       COALESCE(STRING_AGG(DISTINCT au.aul_nome, ', '), 'Nenhuma') as aulas_matriculadas,
			       COUNT(DISTINCT f.frq_id) as total_visitas,
			       MAX(f.frq_data_entrada) as ultima_visita,
			       CASE
			         WHEN (a.alu_data_matricula + (p.pln_duracao * interval '1 month')) >= CURRENT_DATE THEN 'Ativo'
			         ELSE 'Vencido'
			       END as status_plano,
			       (a.alu_data_matricula + (p.pln_duracao * interval '1 month')) as data_vencimento
				FROM aluno a
				JOIN plano p ON a.pln_id = p.pln_id
				LEFT JOIN inscricao_aula ia ON a.alu_id = ia.alu_id
				LEFT JOIN aula au ON ia.aul_id = au.aul_id
				LEFT JOIN frequencia f ON a.alu_id = f.alu_id
				GROUP BY a.alu_id, p.pln_nome, p.pln_duracao
				ORDER BY a.alu_primeiro_nome ASC
				""";
		
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
				aluno.setDataNasc(rs.getDate("alu_data_nasc").toLocalDate());
				
				aluno.setEmail(rs.getString("alu_email"));
				aluno.setTelefone(rs.getString("alu_telefone"));
				
				aluno.setDataMatricula(rs.getDate("alu_data_matricula").toLocalDate());
				
				Plano plano = new Plano();
				plano.setId(rs.getInt("pln_id"));
				plano.setNome(rs.getString("pln_nome"));
				aluno.setPlano(plano);
				
				aluno.setInfoAulas(rs.getString("aulas_matriculadas"));
			    aluno.setTotalVisitas(rs.getInt("total_visitas"));
			    
			    Date dataUltima = rs.getDate("ultima_visita");
			    if (dataUltima != null) {
			        aluno.setUltimaVisita(dataUltima.toLocalDate());
			    }
			    
			    aluno.setStatusPlano(rs.getString("status_plano"));

			    Date dataVenc = rs.getDate("data_vencimento");
			    if (dataVenc != null) {
			        aluno.setDataVencimento(dataVenc.toLocalDate());
			    }
				
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
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m O(A) aluno(a) \"" + aluno.getPrimeiroNome() + "\" foi atualizado(a).");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao atualizar aluno(a): " + e.getMessage());
		}
	}
	
	// Excluir aluno
	public void excluir(int id) {
		String sql = "DELETE FROM aluno WHERE alu_id=?";
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			
			if (stmt.executeUpdate() > 0) {
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m O(A) aluno(a) foi excluído(a).");
			} else {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m ID não encontrado. Tente novamente.");
			}
		} catch (SQLException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m Falha ao excluir aluno(a): " + e.getMessage());
		}
	}
}
