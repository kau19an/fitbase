package br.edu.brazcubas.fitbase.ui;

import java.util.Scanner;

import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.4
 */

public class MenuPrincipal {
	private Scanner sc = new Scanner(System.in);
	
	public void iniciar() {
		int opcao;
		
		do {
			System.out.print("""
					|-----------------------|
					|    ACADEMIA FITBASE   |
					|  O que deseja fazer?  |
					|-----------------------|
					| 1) Aluno              |
					|    2) Matrícula       |
					|    3) Frequência      |
					| 4) Aula               |
					| 5) Instrutor          |
					| 6) Plano              |
					|-----------------------|
					| Digite 0 para sair.   |
					|-----------------------|
					
					> Sua escolha:\s""");
			opcao = sc.nextInt();
			sc.nextLine();
			
			switch (opcao) {
				case 1 -> {
					MenuAluno menuAluno = new MenuAluno();
					menuAluno.exibirMenu();
				}
				case 2 -> {
					MenuInscricao menuInscricao = new MenuInscricao();
					menuInscricao.exibirMenu();
				}
				case 3 -> {
					MenuFrequencia menuFrequencia = new MenuFrequencia();
					menuFrequencia.exibirMenu();
				}
				case 4 -> {
					MenuAula menuAula = new MenuAula();
					menuAula.exibirMenu();
				}
				case 5 -> {
					MenuInstrutor menuInstrutor = new MenuInstrutor();
					menuInstrutor.exibirMenu();
				}
				case 6 -> {
					MenuPlano menuPlano = new MenuPlano();
					menuPlano.exibirMenu();
				}
				case 0 -> System.out.println("\n\u001B[1m[INFO]\u001B[0m Programa encerrado. Volte sempre!");
				default -> {
					Console.limpar();
					System.out.println("\n\u001B[1;31m[ERRO]\u001B[0m Opção não existe. Tente novamente.\n");
				}
			}
		} while (opcao != 0);
	}
}
