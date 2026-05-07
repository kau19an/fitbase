package br.edu.brazcubas.fitbase.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.AlunoDAO;
import br.edu.brazcubas.fitbase.dao.PlanoDAO;
import br.edu.brazcubas.fitbase.entities.Aluno;
import br.edu.brazcubas.fitbase.entities.Plano;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.2
 */

public class MenuAluno {
	private Scanner sc = new Scanner(System.in);
	private AlunoDAO dao = new AlunoDAO();
	
	// Para que a data seja inserida no formato usado no Brasil
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void exibirMenu() {
		int opcao;
		
		do {
			Console.limpar();
			
			System.out.print("""
					|---------------------------|
					|  ACADEMIA FITBASE: ALUNO  |
					|---------------------------|
					| 1) Cadastrar novo aluno   |
					| 2) Listar todos os alunos |
					| 3) Atualizar aluno        |
					| 4) Excluir aluno          |
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
					System.out.println("\u001B[1;31m[ERRO]\u001B[0m Opção inválida. Tente novamente.");
                    Console.pausar();
                }
			}
		} while (opcao != 0);
	}
	
	// Cadastrar aluno
	private void cadastrar() {
		Console.limpar();
		
		System.out.print("""
				|------------------------|
				|    ACADEMIA FITBASE    |
				| Cadastrando novo aluno |
				|------------------------|
				""");
		Aluno aluno = new Aluno();
		
		System.out.print("\nDigite o primeiro nome:\n> ");
		aluno.setPrimeiroNome(sc.nextLine());
		
		System.out.print("\nDigite o nome do meio (se houver):\n> ");
		aluno.setMeioNome(sc.nextLine());
		
		System.out.print("\nDigite o último nome:\n> ");
		aluno.setUltimoNome(sc.nextLine());
		
		System.out.print("\nDigite o CPF (somente números):\n> ");
		aluno.setCpf(sc.nextLine());
		
		System.out.print("\nDigite a data de nascimento (DD/MM/AAAA, inclua as barras):\n> ");
		String dataNascString = sc.nextLine();
		aluno.setDataNasc(LocalDate.parse(dataNascString, formato));
		
		System.out.print("\nDigite o e-mail:\n> ");
		aluno.setEmail(sc.nextLine());
		
		System.out.print("\nDigite o telefone (somente números):\n> ");
		aluno.setTelefone(sc.nextLine());
		
		// Define automaticamente o dia atual
		aluno.setDataMatricula(LocalDate.now());
		
		// Lista todos os planos disponíveis
		PlanoDAO planoDAO = new PlanoDAO();
	    List<Plano> planos = planoDAO.listarTodos();
		
	    if (planos.isEmpty()) {
	        System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhum plano encontrado no banco de dados. Cadastre um antes de continuar.");
	        Console.pausar();
	        return;
	    }
	    
	    System.out.println("""
	    		|-----------------------------|
	    		|       ACADEMIA FITBASE      |
	    		| Lista de planos disponíveis |
	    		|-----------------------------|
	    		""");
	    for (int i = 0; i < planos.size(); i++) {
	        Plano p = planos.get(i);
	        System.out.printf("[%d] %s\n", p.getId(), p.getNome());
	    }
	    
	    System.out.print("\nDigite o ID do plano:\n> ");
	    try {
	    	int idPlano = Integer.parseInt(sc.nextLine());
	    	
	    	Plano planoEscolhido = new Plano();
	    	planoEscolhido.setId(idPlano);
	    	
	        aluno.setPlano(planoEscolhido);
	    } catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID do plano inválido.");
	        Console.pausar();
	        return;
	    }
	    
		System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...");
		dao.cadastrar(aluno);
		Console.pausar();
	}
	
	// Listar todos os alunos
	private void listar() {
		Console.limpar();
		
		System.out.println("""
				|-----------------------------|
				|       ACADEMIA FITBASE      |
				| Lista de alunos cadastrados |
				|-----------------------------|
				""");
		List<Aluno> lista = dao.listarTodos();
		
		if (lista.isEmpty()) {
            System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhum aluno foi encontrado.");
        } else {
            for (Aluno a : lista) {
                System.out.println(a.toString());
            }
        }
		Console.pausar();
	}
	
	// Atualizar aluno
	private void atualizar() {
		Console.limpar();
		
		System.out.println("""
				|----------------------------|
				|      ACADEMIA FITBASE      |
				| Atualizando dados do aluno |
				|----------------------------|
				""");
		System.out.print("Digite o ID do aluno a ser atualizado:\n> ");
		
		try {
	        int id = Integer.parseInt(sc.nextLine());
	        Aluno aluno = new Aluno();
	        aluno.setId(id);
	        
	        System.out.println("\n\u001B[1m[INFO]\u001B[0m Você pode digitar os novos dados ou repetir os atuais.");
	        
	        System.out.print("\nPrimeiro nome:\n> ");
			aluno.setPrimeiroNome(sc.nextLine());
			
			System.out.print("\nNome do meio (se houver):\n> ");
			aluno.setMeioNome(sc.nextLine());
			
			System.out.print("\nÚltimo nome:\n> ");
			aluno.setUltimoNome(sc.nextLine());
			
			System.out.print("\nCPF (somente números):\n> ");
			aluno.setCpf(sc.nextLine());
			
			System.out.print("\nData de nascimento (DD/MM/AAAA, inclua as barras):\n> ");
			String dataNascString = sc.nextLine();
			aluno.setDataNasc(LocalDate.parse(dataNascString, formato));
			
			System.out.print("\nE-mail:\n> ");
			aluno.setEmail(sc.nextLine());
			
			System.out.print("\nTelefone (somente números):\n> ");
			aluno.setTelefone(sc.nextLine());
			
			System.out.print("\nData de matrícula (DD/MM/AAAA, inclua as barras):\n> ");
	        String dataMatrString = sc.nextLine();
	        aluno.setDataMatricula(LocalDate.parse(dataMatrString, formato));
			
			System.out.println("\n\u001B[1m[INFO]\u001B[0m Enviando os dados recebidos...");
			dao.atualizar(aluno);
		} catch (NumberFormatException e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Tente novamente.");
	    } catch (Exception e) {
	        System.out.println("\u001B[1;31m[ERRO]\u001B[0m Erro ao atualizar aluno: " + e.getMessage());
	    }
		Console.pausar();
	}
	
	// Excluir aluno
	private void excluir() {
		Console.limpar();
		
		System.out.println("""
				|--------------------|
				|  ACADEMIA FITBASE  |
				| Excluindo um aluno |
				|--------------------|
				""");
		System.out.print("Digite o ID do aluno a ser excluído:\n> ");
		
		try {
            int id = Integer.parseInt(sc.nextLine());
            dao.excluir(id);
        } catch (NumberFormatException e) {
            System.out.println("\n\u001B[1;31m[ERRO]\u001B[0m ID inválido. Insira somente números.\n");
        }
		Console.pausar();
	}
}
