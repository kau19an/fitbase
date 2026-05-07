package br.edu.brazcubas.fitbase.ui;

import java.util.List;
import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.PlanoDAO;
import br.edu.brazcubas.fitbase.entities.Plano;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.2
 */

public class MenuPlano {
	private Scanner sc = new Scanner(System.in);
	private PlanoDAO dao = new PlanoDAO();
	
	public void exibirMenu() {
		int opcao;
		
		do {
			Console.limpar();
			
			System.out.print("""
					|---------------------------|
					|  ACADEMIA FITBASE: PLANO  |
					|---------------------------|
					| 1) Cadastrar novo plano   |
					| 2) Listar todos os planos |
					| 3) Atualizar plano        |
					| 4) Excluir plano          |
					|---------------------------|
					| Digite 0 para voltar.     |
					|---------------------------|
					
					> Sua escolha:\s""");
			
			try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }
			
			switch (opcao) {
				case 1 -> cadastrar();
	            case 2 -> listar();
	            case 3 -> atualizar();
				case 4 -> excluir();
				case 0 -> { Console.limpar(); } // Menu principal
				default -> {
					System.out.println("\u001B[1;31m[ERRO]\u001B[0m Opção inválida. Tente novamente.\n");
                    Console.pausar();
                }
			}
		} while (opcao != 0);
	}
	
	// Cadastrar aluno
	private void cadastrar() {
		Console.limpar();
		
		System.out.print("""
				|-------------------------|
				| ACADEMIA FITBASE: PLANO |
				|    Cadastrando plano    |
				|-------------------------|
				""");
		Plano plano = new Plano();
		
		System.out.print("\nDigite o nome:\n> ");
		plano.setNome(sc.nextLine());
		
		System.out.print("\nDigite a descrição:\n> ");
		plano.setDescricao(sc.nextLine());
		
		System.out.print("\nDigite os benefícios:\n> ");
		plano.setBeneficios(sc.nextLine());
		
		System.out.print("\nDigite a duração (em meses):\n> ");
		plano.setDuracao(sc.nextInt());
	    
		System.out.print("\nDigite o valor mensal:\n> R$ ");
		plano.setValorMensal(sc.nextDouble());
		
		System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...\n");
		dao.cadastrar(plano);
		
		Console.pausar();
	}
	
	// Listar todos os planos
	private void listar() {
		Console.limpar();
		
		System.out.println("""
				|-----------------------------|
				|   ACADEMIA FITBASE: PLANO   |
				| Lista de planos cadastrados |
				|-----------------------------|
				""");
		List<Plano> lista = dao.listarTodos();
		
		if (lista.isEmpty()) {
			System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhum plano foi encontrado.\n");
        } else {
            for (Plano p : lista) {
                System.out.println(p.toString());
            }
        }
		
		Console.pausar();
	}
	
	// Atualizar plano
	private void atualizar() {
		Console.limpar();
		
		System.out.println("""
				|----------------------------|
				|   ACADEMIA FITBASE: PLANO  |
				|      Atualizando dados     |
				|----------------------------|
				""");
		System.out.print("Digite o ID do plano a ser atualizado:\n> ");
		
		try {
	        int id = Integer.parseInt(sc.nextLine());
	        Plano plano = new Plano();
	        plano.setId(id);
	        
	        System.out.println("\n\u001B[1m[INFO]\u001B[0m Você pode digitar os novos dados ou repetir os atuais.");
	        
	        System.out.print("\n> Nome : ");
			plano.setNome(sc.nextLine());
			
			System.out.print("\n> Descrição: ");
			plano.setDescricao(sc.nextLine());
			
			System.out.print("\n> Benefícios: ");
			plano.setBeneficios(sc.nextLine());
			
			System.out.print("\n> Duração (em meses): ");
			plano.setDuracao(sc.nextInt());
		    
			System.out.print("\n> Valor mensal: R$ ");
			plano.setValorMensal(sc.nextDouble());
			
			System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...\n");
			dao.atualizar(plano);
		} catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.\n");
	    } catch (Exception e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m Erro ao atualizar plano: " + e.getMessage() + "\n");
	    }
		
		Console.pausar();
	}
	
	// Excluir aluno
	private void excluir() {
		Console.limpar();
		
		System.out.println("""
				|-------------------------|
				| ACADEMIA FITBASE: PLANO |
				|     Excluindo plano     |
				|-------------------------|
				""");
		System.out.print("Digite o ID do plano a ser excluído:\n> ");
		
		try {
            int id = Integer.parseInt(sc.nextLine());
            dao.excluir(id);
        } catch (NumberFormatException e) {
            System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID invalido. Tente novamente.\n");
        }
		
		Console.pausar();
	}
}
