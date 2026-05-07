package br.edu.brazcubas.fitbase.ui;

import java.util.List;
import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.InstrutorDAO;
import br.edu.brazcubas.fitbase.entities.Aluno;
import br.edu.brazcubas.fitbase.entities.Instrutor;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.1
 */

public class MenuInstrutor {
	private Scanner sc = new Scanner(System.in);
	private InstrutorDAO dao = new InstrutorDAO();
	
	public void exibirMenu() {
		int opcao;
		
		do {
			Console.limpar();
			
			System.out.print("""
					\n---- MENU INSTRUTOR ----
					1) Cadastrar novo instrutor
					2) Listar todos os instrutores
					3) Atualizar instrutor
					4) Excluir instrutor
					0) Voltar
					>\s""");
			opcao = sc.nextInt();
			sc.nextLine();
			
			switch (opcao) {
				case 1 -> cadastrar();
	            case 2 -> listar();
	            case 3 -> atualizar();
				case 4 -> excluir();
				case 0 -> { Console.limpar(); } // Menu principal
				default -> {
					System.out.println("\u001B[1;31m[ERRO]\u001B[0m Opção inválida. Tente novamente.");
	                Console.pausar();
	            }
			}
		} while (opcao != 0);
	}
	
	// Cadastrar aluno
	private void cadastrar() {
		System.out.print("\n---- NOVO CADASTRO DE INSTRUTOR ----\n");
		Instrutor instrutor = new Instrutor();
		
		System.out.print("\n> Primeiro nome: ");
		instrutor.setPrimeiroNome(sc.nextLine());
		
		System.out.print("\n> Nome do meio (se não houver, pressione Enter): ");
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
		
		System.out.println("\nEnviando os dados recebidos...");
		dao.cadastrar(instrutor);
		Console.pausar();
	}
	
	// Listar todos os alunos
	private void listar() {
		System.out.println("\n---- LISTA DE INSTRUTORES CADASTRADOS ----");
		List<Instrutor> lista = dao.listarTodos();
		
		if (lista.isEmpty()) {
            System.out.println("Nenhum instrutor foi encontrado.");
        } else {
            for (Instrutor i : lista) {
                System.out.println(i.toString());
            }
        }
		Console.pausar();
	}
	
	// Atualizar instrutor
	private void atualizar() {
		System.out.println("\n---- ATUALIZAÇÃO DE INSTRUTOR ----");
		System.out.print("> Digite o ID do instrutor: ");
		
		try {
	        int id = Integer.parseInt(sc.nextLine());
	        Instrutor instrutor = new Instrutor();
	        instrutor.setId(id);
	        
	        System.out.println("\nDigite os novos dados ou repita os atuais:");
	        
	        System.out.print("\n> Primeiro nome: ");
			instrutor.setPrimeiroNome(sc.nextLine());
			
			System.out.print("\n> Nome do meio (se não houver, pressione Enter): ");
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
			
			System.out.println("\nEnviando os dados recebidos...");
			dao.atualizar(instrutor);
		} catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.");
	    } catch (Exception e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m Erro ao atualizar instrutor: " + e.getMessage());
	    }
		Console.pausar();
	}
	
	// Excluir instrutor
	private void excluir() {
		System.out.println("\n---- EXCLUSÃO DE INSTRUTOR ----");
		System.out.print("> Digite o ID do instrutor: ");
		
		try {
            int id = Integer.parseInt(sc.nextLine());
            dao.excluir(id);
        } catch (NumberFormatException e) {
            System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID invalido. Tente novamente.");
        }
		Console.pausar();
	}
}
