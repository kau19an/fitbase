package br.edu.brazcubas.fitbase.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.FrequenciaDAO;
import br.edu.brazcubas.fitbase.entities.Aluno;
import br.edu.brazcubas.fitbase.entities.Frequencia;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class MenuFrequencia {
	private Scanner sc = new Scanner(System.in);
	private FrequenciaDAO dao = new FrequenciaDAO();
	
	// Para que a data seja inserida no formato usado no Brasil
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void exibirMenu() {
		int opcao;
		
		do {
			Console.limpar();
			
			System.out.print("""
					\n---- MENU DA FREQUÊNCIA ----
					1) Cadastrar nova frequência
					2) Listar todas as frequências
					3) Atualizar frequência
					4) Excluir frequência
					0) Voltar
					
					> Opção escolhida:\s""");
			
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
					System.out.println("\u001B[1;31m[ERRO]\u001B[0m Opção inválida. Tente novamente.");
                    Console.pausar();
                }
			}
		} while (opcao != 0);
	}
	
	// Cadastrar frequência
	private void cadastrar() {
		System.out.println("\n---- NOVO CADASTRO DE FREQUÊNCIA ----");
		Frequencia frequencia = new Frequencia();
		
		System.out.print("\n> ID do aluno: ");
		
		try {
			int idAluno = Integer.parseInt(sc.nextLine());
			
			// FK: aluno
			Aluno aluno = new Aluno();
			aluno.setId(idAluno);
			frequencia.setAluno(aluno);
		} catch (NumberFormatException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID do aluno inválido. Tente novamente.");
			Console.pausar();
			return; // Cancela se for digitado letra em vez de número
		}
		
		System.out.print("\n> Data de entrada (DD/MM/AAAA, inclua as barras): ");
		String dataString = sc.nextLine();
		frequencia.setDataEntrada(LocalDate.parse(dataString, formato));
				
		System.out.print("\n> Hora de entrada (HH:MM): ");
		frequencia.setHoraEntrada(sc.nextLine());
		
		System.out.println("\nEnviando os dados recebidos...");
		dao.cadastrar(frequencia);
		Console.pausar();
	}
	
	// Listar todas as frequências
	private void listar() {
		System.out.println("\n---- LISTA DE FREQUÊNCIAS ----");
		System.out.print("> ID do aluno: ");
		
		try {
			int aluId = Integer.parseInt(sc.nextLine());
			List<Frequencia> lista = dao.listarTodas(aluId);
			
			if (lista.isEmpty()) {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhuma frequência encontrada para este aluno.");
			} else {
				String nome = lista.get(0).getAluno().getNomeCompleto();
				
				System.out.println("\n---- FREQUÊNCIAS DE " + nome.toUpperCase() + " ----");
				for (Frequencia f : lista) {
					System.out.println(f.toString());
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.");
		}
		
		Console.pausar();
	}
	
	// Atualizar frequência
	private void atualizar() {
		System.out.println("\n---- ATUALIZAÇÃO DE FREQUÊNCIA ----");
		System.out.print("> ID do aluno: ");
		
		try {
			int idAluno = Integer.parseInt(sc.nextLine());
			
			// 1. Busca as frequências desse aluno
			List<Frequencia> lista = dao.listarTodas(idAluno);
			if (lista.isEmpty()) {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhuma frequência encontrada para este aluno.");
				Console.pausar();
				return;
			}
			
			// 2. Lista suas frequências para referência
			String nome = lista.get(0).getAluno().getNomeCompleto();
			
			System.out.println("\n---- FREQUÊNCIAS DE " + nome.toUpperCase() + " ----");
			for (Frequencia f : lista) {
				System.out.println(f.toString());
			}
			
			// 3. Pergunta quais das listadas acima deseja excluir
			System.out.print("\n> ID da frequência: ");
			int idFreq = Integer.parseInt(sc.nextLine());
			
			// 4. Confirmação pelo bem da segurança
			boolean pertenceAoAluno = false;
			for (Frequencia f : lista) {
				if (f.getId() == idFreq) {
					pertenceAoAluno = true;
					break; // Se encontrou, para de procurar
				}
			}
			
			if (!pertenceAoAluno) {
				System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID não existe ou não pertence a este aluno.");
				Console.pausar();
				return;
			}
			
			
			Frequencia frequencia = new Frequencia();
			frequencia.setId(idFreq);
			
			// FK: aluno
			Aluno aluno = new Aluno();
			aluno.setId(idAluno);
			frequencia.setAluno(aluno);
			
			System.out.println("\nDigite os novos dados ou repita os atuais:");
			
			System.out.print("\n> Data de entrada (DD/MM/AAAA, inclua as barras): ");
			String dataString = sc.nextLine();
			frequencia.setDataEntrada(LocalDate.parse(dataString, formato));
			
			System.out.print("\n> Hora de entrada (HH:MM): ");
			frequencia.setHoraEntrada(sc.nextLine());
			
			System.out.println("\nEnviando os dados recebidos...");
			dao.atualizar(frequencia);
		} catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.");
	    } catch (Exception e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m Erro ao atualizar frequência: " + e.getMessage());
	    }
		
		Console.pausar();
	}
	
	// Excluir frequência
	private void excluir() {
		System.out.println("\n---- EXCLUSÃO DE FREQUÊNCIA ----");
		System.out.print("\n> ID do aluno: ");
		
		try {
			// 1. Busca e lista as frequências desse aluno
			int idAluno = Integer.parseInt(sc.nextLine());
			
			List<Frequencia> lista = dao.listarTodas(idAluno);
			if (lista.isEmpty()) {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhuma frequência encontrada para este aluno.");
				Console.pausar();
				return; // Se não tiver o que excluir, volta
			}
			
			String nome = lista.get(0).getAluno().getNomeCompleto();
			
			System.out.println("\n---- FREQUÊNCIAS DE " + nome.toUpperCase() + " ----");
			for (Frequencia f : lista) {
				System.out.println(f.toString());
			}
			
			// 2. Pergunta quais das listadas acima deseja excluir
			System.out.print("\n> ID da frequência: ");
			int idFreq = Integer.parseInt(sc.nextLine());
			
			// 3. Confirmação pelo bem da segurança
			boolean pertenceAoAluno = false;
			for (Frequencia f : lista) {
				if (f.getId() == idFreq) {
					pertenceAoAluno = true;
					break; // Se encontrou, para de procurar
				}
			}
			
			if (pertenceAoAluno) {
				dao.excluir(idFreq);
			} else {
				System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID não existe ou não pertence a este aluno.");
			}
		} catch (NumberFormatException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID invalido. Tente novamente.");
        }
		
		Console.pausar();
	}
}
