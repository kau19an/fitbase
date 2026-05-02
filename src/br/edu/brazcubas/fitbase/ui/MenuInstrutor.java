package br.edu.brazcubas.fitbase.ui;

import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.InstrutorDAO;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class MenuInstrutor {
	private Scanner sc = new Scanner(System.in);
	private InstrutorDAO instrutorDAO = new InstrutorDAO();
	
	public void exibirMenu() {
		int opcao;
		
		do {
			Console.limpar();
			
			System.out.print("""
					\n---> MENU INSTRUTOR <---
					1) Cadastrar novo instrutor
					2) Listar todos os instrutores
					3) Atualizar instrutor
					4) Excluir instrutor
					0) Voltar
					>\s""");
			opcao = sc.nextInt();
			sc.nextLine();
			
			switch (opcao) {
				case 1 -> System.out.println("Opção 1...");
				case 2 -> System.out.println("Opção 2...");
				case 3 -> System.out.println("Opção 3...");
				case 4 -> System.out.println("Opção 4...");
				case 0 -> {} // Menu principal
				default -> System.out.println("\u001B[1;31m[ERRO]\u001B[0m Opção inválida. Tente novamente.");
			}
		} while (opcao != 0);
	}
}
