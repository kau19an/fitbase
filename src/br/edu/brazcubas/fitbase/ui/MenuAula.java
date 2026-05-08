package br.edu.brazcubas.fitbase.ui;

import java.util.List;
import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.AulaDAO;
import br.edu.brazcubas.fitbase.dao.InstrutorDAO;
import br.edu.brazcubas.fitbase.entities.Aula;
import br.edu.brazcubas.fitbase.entities.Instrutor;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.1
 */

public class MenuAula {
	private Scanner sc = new Scanner(System.in);
	private AulaDAO dao = new AulaDAO();

	public void exibirMenu() {
		int opcao;

		do {
			Console.limpar();

			System.out.print("""
					|--------------------------|
					|  ACADEMIA FITBASE: AULA  |
					|--------------------------|
					| 1) Cadastrar nova aula   |
					| 2) Listar todas as aulas |
					| 3) Atualizar aula        |
					| 4) Excluir aula          |
					|--------------------------|
					| Digite 0 para voltar.    |
					|--------------------------|
					
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
	
	// Cadastrar aula
	public void cadastrar() {
		Console.limpar();
		
		System.out.print("""
				|------------------------|
				| ACADEMIA FITBASE: AULA |
				|    Cadastrando aula    |
				|------------------------|
				""");
		Aula aula = new Aula();
		
		System.out.print("\nDigite o nome:\n> ");
		aula.setNome(sc.nextLine());
		
		System.out.print("\nDigite a descrição:\n> ");
		aula.setDescricao(sc.nextLine());
		
		System.out.print("\nDigite o horário (HH:MM):\n> ");
		aula.setHorario(sc.nextLine());
		
		System.out.print("\nDigite a duração (em minutos):\n> ");
		aula.setDuracao(Integer.parseInt(sc.nextLine()));
		
		System.out.print("\nDigite a capacidade máxima de alunos:\n> ");
		aula.setCapacidadeMax(Integer.parseInt(sc.nextLine()));
		
		// Lista todos os instrutores disponíveis
		InstrutorDAO instrutorDAO = new InstrutorDAO();
		List<Instrutor> instrutores = instrutorDAO.listarTodos();
		
		if (instrutores.isEmpty()) {
			System.out.println("\u001B[1;33m[AVISO]\u001B[0m É necessário que pelo menos um(a) instrutor(a) exista no banco de dados. Cadastre um(a) antes de continuar.\n");
	        Console.pausar();
	        return;
	    }
		
		System.out.println("""
	    		|----------------------------------|
	    		|      ACADEMIA FITBASE: AULA      |
	    		| Lista de instrutores disponíveis |
	    		|----------------------------------|
	    		""");
		for (Instrutor i : instrutores) {
			System.out.printf("[%d] %s\n", i.getId(), i.getNomeCompleto());
		}
		
		System.out.print("\nDigite o ID do instrutor:\n> ");
		try {			
			int idInstrutor = Integer.parseInt(sc.nextLine());
			
			Instrutor instrutorEscolhido = new Instrutor();
			instrutorEscolhido.setId(idInstrutor);
			aula.setInstrutor(instrutorEscolhido);
		} catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID da aula inválido.\n");
	        Console.pausar();
	        return;
	    }
	    
		System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...\n");
		dao.cadastrar(aula);
		
		Console.pausar();
	}
	
	// Listar todas as aulas
	public void listar() {
		Console.limpar();
		
		System.out.println("""
				|----------------------------|
				|   ACADEMIA FITBASE: AULA   |
				| Lista de aulas cadastradas |
				|----------------------------|
				""");
		List<Aula> lista = dao.listarTodos();
		
		if (lista.isEmpty()) {
            System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhuma aula foi encontrada.\n");
        } else {
            for (Aula a : lista) {
                System.out.println(a.toString());
            }
        }
		
		Console.pausar();
	}
	
	// Atualizar aula
	public void atualizar() {
		Console.limpar();
		
		System.out.println("""
				|---------------------------|
				|   ACADEMIA FITBASE: AULA  |
				|     Atualizando dados     |
				|---------------------------|
				""");
		listar();
		
		System.out.print("\nDigite o ID da aula a ser atualizada:\n> ");
		
		try {
			int id = Integer.parseInt(sc.nextLine());
			Aula aula = new Aula();
			aula.setId(id);
	        
	        System.out.println("\n\u001B[1m[INFO]\u001B[0m Você pode digitar os novos dados ou repetir os atuais.");
	        
	        System.out.print("\nNome da aula:\n> ");
			aula.setNome(sc.nextLine());
			
			System.out.print("\nDescrição:\n> ");
			aula.setDescricao(sc.nextLine());
			
			System.out.print("\nHorário (HH:MM):\n> ");
			aula.setHorario(sc.nextLine());
			
			System.out.print("\nDuração (em minutos):\n> ");
			aula.setDuracao(Integer.parseInt(sc.nextLine()));
			
			System.out.print("\nCapacidade máxima de alunos:\n> ");
			aula.setCapacidadeMax(Integer.parseInt(sc.nextLine()));
			
			System.out.print("\nID do instrutor:\n> ");
			int idInstrutor = Integer.parseInt(sc.nextLine());
			Instrutor instrutor = new Instrutor();
			instrutor.setId(idInstrutor);
			aula.setInstrutor(instrutor);
			
			System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...\n");
			dao.atualizar(aula);
		} catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.\n");
	    } catch (Exception e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m Erro ao atualizar aula: " + e.getMessage() + "\n");
	    }
		
		Console.pausar();
	}
	
	// Excluir aula
	public void excluir() {
		Console.limpar();
		
		System.out.println("""
				|------------------------|
				| ACADEMIA FITBASE: AULA |
				|     Excluindo aula     |
				|------------------------|
				""");
		listar();
		
		System.out.print("Digite o ID da aula a ser excluída:\n> ");
		
		try {
            int id = Integer.parseInt(sc.nextLine());
            dao.excluir(id);
        } catch (NumberFormatException e) {
            System.out.println("\n\u001B[1;31m[ERRO]\u001B[0m ID inválido. Insira somente números.\n");
        }
		
		Console.pausar();
	}
}
