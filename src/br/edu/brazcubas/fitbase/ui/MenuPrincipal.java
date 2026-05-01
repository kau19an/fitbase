package br.edu.brazcubas.fitbase.ui;

import java.util.Scanner;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class MenuPrincipal {
	private Scanner sc = new Scanner(System.in);
	
	public void iniciar() {
		int opcao;
		
		do {
			System.out.print("""
					Seja bem-vindo(a) ao sistema da academia Fitbase.
					> Escolha qual entidade deseja acessar abaixo.
					
					1) Aluno
					2) Aula
					3) Instrutor
					4) Plano
					0) Sair
					>\s""");
			opcao = sc.nextInt();
			sc.nextLine();
			
			switch (opcao) {
				case 1 -> {
					MenuAluno menuAluno = new MenuAluno();
					menuAluno.exibirMenu();
				}
				case 2 -> {
					MenuAula menuAula = new MenuAula();
					menuAula.exibirMenu();
				}
				case 3 -> {
					MenuInstrutor menuInstrutor = new MenuInstrutor();
					menuInstrutor.exibirMenu();
				}
				case 4 -> {
					MenuPlano menuPlano = new MenuPlano();
					menuPlano.exibirMenu();
				}
				case 0 -> System.out.print("\nPrograma encerrado. Volte sempre!");
				default -> System.out.println("\u001B[1;31m[ERRO]\u001B[0m Opção inválida. Tente novamente.");
			}
		} while (opcao != 0);
	}
}
