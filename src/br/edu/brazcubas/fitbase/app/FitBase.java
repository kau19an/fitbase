package br.edu.brazcubas.fitbase.app;

import java.sql.Connection;

import br.edu.brazcubas.fitbase.db.DatabaseConnection;

public class FitBase {
	public static void main(String[] args) {
		// Verifica a conexão com o banco de dados
		System.out.println("Iniciando conexão com o Supabase...");

		try (Connection conn = DatabaseConnection.getConnection()) {
			if (conn != null) { // Se não retornar nulo, deu certo
				System.out.println("\u001B[1;32m[SUCESSO]\u001B[0m Supabase - Conexão estabelecida.");
			}
		} catch (Exception e) { // Se retornar nulo, mostra o erro
			System.out.println(e.getMessage());
		}
	}
}
