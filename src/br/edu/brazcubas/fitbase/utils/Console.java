package br.edu.brazcubas.fitbase.utils;

import java.util.Scanner;

/**
 * @author Kauan Farias
 * @version 1.1
 */

public class Console {
	// Pula 50 linhas ao voltar nos submenus
	public static void limpar() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	// Breve pausa para dar tempo de ler
	public static void pausar() {
		@SuppressWarnings("resource") // Evita que o aviso da falta de 'sc.close()' apareça
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nPressione Enter para continuar...");
		sc.nextLine();
	}
}
