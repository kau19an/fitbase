package br.edu.brazcubas.fitbase.ui;

import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.AulaDAO;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class MenuAula {
	private Scanner sc = new Scanner(System.in);
	private AulaDAO aulaDAO = new AulaDAO();
	
	public void exibirMenu() {
		int opcao;
		
		do {
			Console.limpar();
			
			System.out.print("""
					\n---> MENU AULA <---
					1) Cadastrar nova aula
					2) Listar todas as aulas
					3) Atualizar aula
					4) Excluir aula
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
