package br.edu.brazcubas.fitbase.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author Kauan Farias
 * @version 1.0
 */

// AVISO: O firewall da faculdade bloqueia a porta de acesso ao Supabase. Rode o programa em outra rede.

public class Database {
	// Carrega o arquivo .env na memória
	private static final Dotenv dotenv = Dotenv.load();

	// Obtém os valores do .env para acesso ao banco de dados
	private static final String URL = dotenv.get("DB_URL");
	private static final String USER = dotenv.get("DB_USER");
	private static final String PASS = dotenv.get("DB_PASS");

	// Método para se conectar com o banco de dados
	public static Connection getConnection() {
		try { // Tenta estabelecer uma conexão com os valores das variáveis
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) { // Se não ocorrer bem, lança um erro
			throw new RuntimeException("\u001B[1;31m[ERRO]\u001B[0m Banco de dados - Conexão não estabelecida: \u001B[3m"
					+ e.getMessage() + "\u001B[0m");
		}
	}
}
