package br.edu.brazcubas.fitbase.ui;

import java.util.List;
import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.PlanoDAO;
import br.edu.brazcubas.fitbase.entities.Plano;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.1
 */

public class MenuPlano {
	private Scanner sc = new Scanner(System.in);
	private PlanoDAO dao = new PlanoDAO();
	
	public void exibirMenu() {
		int opcao;
		
		do {
			Console.limpar();
			
			System.out.print("""
					\n---- MENU PLANO ----
					1) Cadastrar novo plano
					2) Listar todos os planos
					3) Atualizar plano
					4) Excluir plano
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
		System.out.print("\n---- NOVO CADASTRO DE PLANO ----\n");
		Plano plano = new Plano();
		
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
		
		System.out.println("\nEnviando os dados recebidos...");
		dao.cadastrar(plano);
		Console.pausar();
	}
	
	// Listar todos os planos
	private void listar() {
		System.out.println("\n---- LISTA DE PLANOS CADASTRADOS ----");
		List<Plano> lista = dao.listarTodos();
		
		if (lista.isEmpty()) {
            System.out.println("Nenhum plano foi encontrado.");
        } else {
            for (Plano p : lista) {
                System.out.println(p.toString());
            }
        }
		Console.pausar();
	}
	
	// Atualizar plano
	private void atualizar() {
		System.out.println("\n---- ATUALIZAÇÃO DE PLANO ----");
		System.out.print("> Digite o ID do plano: ");
		
		try {
	        int id = Integer.parseInt(sc.nextLine());
	        Plano plano = new Plano();
	        plano.setId(id);
	        
	        System.out.println("\nDigite os novos dados ou repita os atuais:");
	        
	        System.out.print("\n> Nome : ");
			plano.setNome(sc.nextLine());
			
			System.out.print("\n> Descrição: ");
			plano.setDescricao(sc.nextLine());
			
			System.out.print("\n> Benefícios: ");
			plano.setBeneficios(sc.nextLine());
			
			System.out.print("\n> Duração (em meses): ");
			plano.setDuracao(sc.nextInt());
		    
			System.out.print("\n> Valor mensal: R$");
			plano.setValorMensal(sc.nextDouble());
			
			System.out.println("\nEnviando os dados recebidos...");
			dao.atualizar(plano);
		} catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.");
	    } catch (Exception e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m Erro ao atualizar plano: " + e.getMessage());
	    }
		Console.pausar();
	}
	
	// Excluir aluno
	private void excluir() {
		System.out.println("\n---- EXCLUSÃO DE PLANO ----");
		System.out.print("> Digite o ID do plano: ");
		
		try {
            int id = Integer.parseInt(sc.nextLine());
            dao.excluir(id);
        } catch (NumberFormatException e) {
            System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID invalido. Tente novamente.");
        }
		Console.pausar();
	}
}
