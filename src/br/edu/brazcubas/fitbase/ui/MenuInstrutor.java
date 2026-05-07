package br.edu.brazcubas.fitbase.ui;

import java.util.List;
import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.InstrutorDAO;
import br.edu.brazcubas.fitbase.entities.Instrutor;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.2
 */

public class MenuInstrutor {
	private Scanner sc = new Scanner(System.in);
	private InstrutorDAO dao = new InstrutorDAO();
	
	public void exibirMenu() {
		int opcao;
		
		do {
			Console.limpar();
			
			System.out.print("""
					|--------------------------------|
					|   ACADEMIA FITBASE: INSTRUTOR  |
					|--------------------------------|
					| 1) Cadastrar novo instrutor    |
					| 2) Listar todos os instrutores |
					| 3) Atualizar instrutor         |
					| 4) Excluir instrutor           |
					|--------------------------------|
					| Digite 0 para voltar.          |
					|--------------------------------|
					
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
	
	// Cadastrar instrutor
	private void cadastrar() {
		Console.limpar();
		
		System.out.print("""
				|-----------------------------|
				| ACADEMIA FITBASE: INSTRUTOR |
				|    Cadastrando instrutor    |
				|-----------------------------|
				""");
		Instrutor instrutor = new Instrutor();
		
		System.out.print("\nDigite o primeiro nome:\n> ");
		instrutor.setPrimeiroNome(sc.nextLine());
		
		System.out.print("\nDigite o nome do meio (se houver):\n> ");
		instrutor.setMeioNome(sc.nextLine());
		
		System.out.print("\nDigite o último nome:\n> ");
		instrutor.setUltimoNome(sc.nextLine());
		
		System.out.print("\nDigite o CPF (somente números):\n> ");
		instrutor.setCpf(sc.nextLine());
		
		System.out.print("\nDigite o telefone (somente números):\n> ");
		instrutor.setTelefone(sc.nextLine());
		
		System.out.print("\nDigite sua especialidade:\n> ");
		instrutor.setEspecialidade(sc.nextLine());
		
		System.out.print("\nDigite seu(s) horário(s) de trabalho:\n> ");
		instrutor.setHorariosTrabalho(sc.nextLine());
		
		System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...\n");
		dao.cadastrar(instrutor);
		
		Console.pausar();
	}
	
	// Listar todos os instrutores
	private void listar() {
		Console.limpar();
		
		System.out.println("""
				|----------------------------------|
				|    ACADEMIA FITBASE: INSTRUTOR   |
				| Lista de instrutores cadastrados |
				|----------------------------------|
				""");
		List<Instrutor> lista = dao.listarTodos();
		
		if (lista.isEmpty()) {
            System.out.println("Nenhum instrutor foi encontrado.\n");
        } else {
            for (Instrutor i : lista) {
                System.out.println(i.toString());
            }
        }
		
		Console.pausar();
	}
	
	// Atualizar instrutor
	private void atualizar() {
		Console.limpar();
		
		System.out.println("""
				|--------------------------------|
				|   ACADEMIA FITBASE: INSTRUTOR  |
				|        Atualizando dados       |
				|--------------------------------|
				""");
		System.out.print("Digite o ID do(a) instrutor(a) a ser atualizado(a):\n> ");
		
		try {
	        int id = Integer.parseInt(sc.nextLine());
	        Instrutor instrutor = new Instrutor();
	        instrutor.setId(id);
	        
	        System.out.println("\n\u001B[1m[INFO]\u001B[0m Você pode digitar os novos dados ou repetir os atuais.");
	        
	        System.out.print("\n> Primeiro nome: ");
			instrutor.setPrimeiroNome(sc.nextLine());
			
			System.out.print("\n> Nome do meio (se houver): ");
			instrutor.setMeioNome(sc.nextLine());
			
			System.out.print("\n> Último nome: ");
			instrutor.setUltimoNome(sc.nextLine());
			
			System.out.print("\n> CPF (somente números): ");
			instrutor.setCpf(sc.nextLine());
			
			System.out.print("\n> Telefone (somente números): ");
			instrutor.setTelefone(sc.nextLine());
			
			System.out.print("\n> Especialidade: ");
			instrutor.setEspecialidade(sc.nextLine());
			
			System.out.print("\n> Horário(s) de trabalho: ");
			instrutor.setHorariosTrabalho(sc.nextLine());
			
			System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...\n");
			dao.atualizar(instrutor);
		} catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.\n");
	    } catch (Exception e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m Erro ao atualizar instrutor(a): " + e.getMessage() + "\n");
	    }
		
		Console.pausar();
	}
	
	// Excluir instrutor
	private void excluir() {
		Console.limpar();
		
		System.out.println("""
				|-----------------------------|
				| ACADEMIA FITBASE: INSTRUTOR |
				|     Excluindo instrutor     |
				|-----------------------------|
				""");
		System.out.print("Digite o ID do(a) instrutor(a) a ser excluído(a):\n> ");
		
		try {
            int id = Integer.parseInt(sc.nextLine());
            dao.excluir(id);
        } catch (NumberFormatException e) {
            System.out.println("\n\u001B[1;31m[ERRO]\u001B[0m ID invalido. Tente novamente.\n");
        }
		
		Console.pausar();
	}
}
