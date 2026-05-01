package br.edu.brazcubas.fitbase.ui;

import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.AlunoDAO;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class MenuAluno {
	private Scanner sc = new Scanner(System.in);
	private AlunoDAO alunoDAO = new AlunoDAO();
	
	public void exibirMenu() {
		int opcao;
		
		do {
			Console.limpar();
			
			System.out.print("""
					\n---> MENU ALUNO <---
					1) Cadastrar novo aluno
					2) Listar todos os alunos
					3) Atualizar aluno
					4) Excluir aluno
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
