package br.edu.brazcubas.fitbase.app;

import java.sql.Connection;

import br.edu.brazcubas.fitbase.db.Supabase;

/**
 * @author Kauan Farias
 * @version 1.2
 */

public class FitBase {
	public static void main(String[] args) {
		// 1. Verifica a conexão com o banco de dados (Supabase)
		boolean status = false;

		try (Connection conn = Supabase.getConnection()) {
			if (conn != null) { // Se não retornar nulo, deu certo
				status = true;
			}
		} catch (Exception e) { // Se retornar nulo, mostra o erro
			status = false;
			System.out.println(e.getMessage());
		}
		
		if (status != true) {
			System.exit(0);
		} else {
			// Menu
		}
	}
}
