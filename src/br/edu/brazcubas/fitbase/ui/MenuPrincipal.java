package br.edu.brazcubas.fitbase.ui;

import java.util.Scanner;

import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.2
 */

public class MenuPrincipal {
	private Scanner sc = new Scanner(System.in);
	
	public void iniciar() {
		int opcao;
		
		do {
			System.out.print("""
					|-----------------------|
					|    ACADEMIA FITBASE   |
					| O que deseja acessar? |
					|-----------------------|
					| 1) Aluno              |
					| 2) Aula               |
					| 3) Frequência         |
					| 4) Instrutor          |
					| 5) Plano              |
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
					System.out.println("\n\u001B[1;33m[AVISO]\u001B[0m Essa opção ainda está em construção.");
					Console.pausar();
					Console.limpar();
					
					// MenuAula menuAula = new MenuAula();
					// menuAula.exibirMenu();
				}
				case 3 -> {
					MenuFrequencia menuFrequencia = new MenuFrequencia();
					menuFrequencia.exibirMenu();
				}
				case 4 -> {
					MenuInstrutor menuInstrutor = new MenuInstrutor();
					menuInstrutor.exibirMenu();
				}
				case 5 -> {
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
