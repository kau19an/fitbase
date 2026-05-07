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
 * @version 1.1
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
					|--------------------------------|
					|  ACADEMIA FITBASE: FREQUÊNCIA  |
					|--------------------------------|
					| 1) Registrar nova frequência   |
					| 2) Listar todas as frequências |
					| 3) Atualizar frequência        |
					| 4) Excluir frequência          |
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
	
	// Cadastrar frequência
	private void cadastrar() {
		Console.limpar();
		
		System.out.print("""
				|------------------------------|
				| ACADEMIA FITBASE: FREQUÊNCIA |
				|    Registrando frequência    |
				|------------------------------|
				""");
		Frequencia frequencia = new Frequencia();
		
		System.out.print("\nDigite o ID do(a) aluno(a) que receberá a frequência:\n> ");
		
		try {
			int idAluno = Integer.parseInt(sc.nextLine());
			
			// FK: aluno
			Aluno aluno = new Aluno();
			aluno.setId(idAluno);
			frequencia.setAluno(aluno);
		} catch (NumberFormatException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID do(a) aluno(a) inválido. Tente novamente.\n");
			Console.pausar();
			return; // Cancela se for digitado letra em vez de número
		}
		
		System.out.print("\nDigite a data de entrada (DD/MM/AAAA, inclua as barras):\n> ");
		String dataString = sc.nextLine();
		frequencia.setDataEntrada(LocalDate.parse(dataString, formato));
				
		System.out.print("\nDigite a hora de entrada (HH:MM):\n> ");
		frequencia.setHoraEntrada(sc.nextLine());
		
		System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...\n");
		dao.cadastrar(frequencia);
		
		Console.pausar();
	}
	
	// Listar todas as frequências
	private void listar() {
		Console.limpar();
		
		System.out.println("""
				|----------------------------------|
				|   ACADEMIA FITBASE: FREQUÊNCIA   |
				| Lista de frequências cadastradas |
				|----------------------------------|
				""");
		System.out.print("Digite o ID do(a) aluno(a) que deseja visualizar:\n> ");
		
		try {
			int aluId = Integer.parseInt(sc.nextLine());
			List<Frequencia> lista = dao.listarTodas(aluId);
			
			if (lista.isEmpty()) {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhuma frequência encontrada para este(a) aluno(a).\n");
			} else {
				String nome = lista.get(0).getAluno().getNomeCompleto();
				
				System.out.println("\n--> FREQUÊNCIAS DE " + nome.toUpperCase() + " <--");
				for (Frequencia f : lista) {
					System.out.println(f.toString());
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.\n");
		}
		
		Console.pausar();
	}
	
	// Atualizar frequência
	private void atualizar() {
		Console.limpar();
		
		System.out.println("""
				|---------------------------------|
				|   ACADEMIA FITBASE: FREQUÊNCIA  |
				|      Atualizando frequência     |
				|---------------------------------|
				""");
		System.out.print("Digite o ID do(a) aluno(a) a ser atualizado(a):\n> ");
		
		try {
			int idAluno = Integer.parseInt(sc.nextLine());
			
			// 1. Busca as frequências desse aluno
			List<Frequencia> lista = dao.listarTodas(idAluno);
			if (lista.isEmpty()) {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhuma frequência encontrada para este(a) aluno(a).\n");
				Console.pausar();
				return;
			}
			
			// 2. Lista suas frequências para referência
			String nome = lista.get(0).getAluno().getNomeCompleto();
			
			System.out.println("\n--> FREQUÊNCIAS DE " + nome.toUpperCase() + " <--");
			for (Frequencia f : lista) {
				System.out.println(f.toString());
			}
			
			// 3. Pergunta quais das listadas acima deseja atualizar
			System.out.print("\nDigite o ID da frequência a ser atualizada:\n> ");
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
				System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID não existe ou não pertence a este(a) aluno(a).\n");
				Console.pausar();
				return;
			}
			
			
			Frequencia frequencia = new Frequencia();
			frequencia.setId(idFreq);
			
			// FK: aluno
			Aluno aluno = new Aluno();
			aluno.setId(idAluno);
			frequencia.setAluno(aluno);
			
			System.out.println("\n\u001B[1m[INFO]\u001B[0m Você pode digitar os novos dados ou repetir os atuais.");
			
			System.out.print("\nData de entrada (DD/MM/AAAA, inclua as barras):\n> ");
			String dataString = sc.nextLine();
			frequencia.setDataEntrada(LocalDate.parse(dataString, formato));
			
			System.out.print("\nHora de entrada (HH:MM):\n> ");
			frequencia.setHoraEntrada(sc.nextLine());
			
			System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...\n");
			dao.atualizar(frequencia);
		} catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.\n");
	    } catch (Exception e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m Erro ao atualizar frequência: " + e.getMessage() + "\n");
	    }
		
		Console.pausar();
	}
	
	// Excluir frequência
	private void excluir() {
		Console.limpar();
		
		System.out.println("""
				|------------------------------|
				| ACADEMIA FITBASE: FREQUÊNCIA |
				|     Excluindo frequência     |
				|------------------------------|
				""");
		System.out.print("\nDigite o ID do(a) aluno(a) a ter sua frequência excluída:\n> ");
		
		try {
			// 1. Busca e lista as frequências desse aluno
			int idAluno = Integer.parseInt(sc.nextLine());
			
			List<Frequencia> lista = dao.listarTodas(idAluno);
			if (lista.isEmpty()) {
				System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhuma frequência encontrada para este(a) aluno(a).\n");
				Console.pausar();
				return; // Se não tiver o que excluir, volta
			}
			
			String nome = lista.get(0).getAluno().getNomeCompleto();
			
			System.out.println("\n--> FREQUÊNCIAS DE " + nome.toUpperCase() + " <--");
			for (Frequencia f : lista) {
				System.out.println(f.toString());
			}
			
			// 2. Pergunta quais das listadas acima deseja excluir
			System.out.print("\nDigite o ID da frequência a ser excluída:\n> ");
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
				System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID não existe ou não pertence a este(a) aluno(a).\n");
			}
		} catch (NumberFormatException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID invalido. Tente novamente.\n");
        }
		
		Console.pausar();
	}
}
