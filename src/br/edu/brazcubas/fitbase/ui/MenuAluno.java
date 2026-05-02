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
 * @version 1.1
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
					\n---- MENU DO ALUNO ----
					1) Cadastrar novo aluno
					2) Listar todos os alunos
					3) Atualizar aluno
					4) Excluir aluno
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
	
	// Cadastrar aluno
	private void cadastrar() {
		System.out.print("\n---- NOVO CADASTRO DE ALUNO ----\n");
		Aluno aluno = new Aluno();
		
		System.out.print("\n> Primeiro nome: ");
		aluno.setPrimeiroNome(sc.nextLine());
		
		System.out.print("\n> Nome do meio (se não houver, pressione Enter): ");
		aluno.setMeioNome(sc.nextLine());
		
		System.out.print("\n> Último nome: ");
		aluno.setUltimoNome(sc.nextLine());
		
		System.out.print("\n> CPF (somente números): ");
		aluno.setCpf(sc.nextLine());
		
		System.out.print("\n> Data de nascimento (DD/MM/AAAA, inclua as barras): ");
		String dataNascString = sc.nextLine();
		aluno.setDataNasc(LocalDate.parse(dataNascString, formato));
		
		System.out.print("\n> E-mail: ");
		aluno.setEmail(sc.nextLine());
		
		System.out.print("\n> Telefone (somente números): ");
		aluno.setTelefone(sc.nextLine());
		
		// Define automaticamente o dia atual
		aluno.setDataMatricula(LocalDate.now());
		
		// Lista todos os planos disponíveis
		PlanoDAO planoDAO = new PlanoDAO();
	    List<Plano> planos = planoDAO.listar();
		
	    if (planos.isEmpty()) { // Se não houver planos:
	        System.out.println("\u001B[1;33m[AVISO]\u001B[0m Nenhum plano encontrado no banco de dados. Cadastre um antes de continuar.");
	        Console.pausar();
	        return;
	    }
	    
	    System.out.println("\n---- PLANOS DISPONÍVEIS ----");
	    for (int i = 0; i < planos.size(); i++) {
	        Plano p = planos.get(i);
	        System.out.printf("[%d] %s\n", p.getId(), p.getNome());
	    }
	    
	    System.out.print("\n> Digite o ID do plano: ");
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
	    
		System.out.println("\nEnviando os dados recebidos...");
		dao.cadastrar(aluno);
		Console.pausar();
	}
	
	// Listar todos os alunos
	private void listar() {
		System.out.println("\n---- LISTA DE ALUNOS CADASTRADOS ----");
		List<Aluno> lista = dao.listarTodos();
		
		if (lista.isEmpty()) {
            System.out.println("Nenhum aluno foi encontrado.");
        } else {
            for (Aluno a : lista) {
                System.out.println(a.toString());
            }
        }
		Console.pausar();
	}
	
	// Atualizar aluno
	private void atualizar() {
		System.out.println("\n---- ATUALIZAÇÃO DE ALUNO ----");
		System.out.print("> Digite o ID do aluno: ");
		
		try {
	        int id = Integer.parseInt(sc.nextLine());
	        Aluno aluno = new Aluno();
	        aluno.setId(id);
	        
	        System.out.println("\nDigite os novos dados ou repita os atuais:");
	        
	        System.out.print("> Primeiro nome: ");
			aluno.setPrimeiroNome(sc.nextLine());
			
			System.out.print("\n> Nome do meio (se não houver, pressione Enter): ");
			aluno.setMeioNome(sc.nextLine());
			
			System.out.print("\n> Último nome: ");
			aluno.setUltimoNome(sc.nextLine());
			
			System.out.print("\n> CPF (somente números): ");
			aluno.setCpf(sc.nextLine());
			
			System.out.print("\n> Data de nascimento (DD/MM/AAAA, inclua as barras): ");
			String dataNascString = sc.nextLine();
			aluno.setDataNasc(LocalDate.parse(dataNascString, formato));
			
			System.out.print("\n> E-mail: ");
			aluno.setEmail(sc.nextLine());
			
			System.out.print("\n> Telefone (somente números): ");
			aluno.setTelefone(sc.nextLine());
			
			System.out.print("\n> Data de matrícula (DD/MM/AAAA, inclua as barras): ");
	        String dataMatrString = sc.nextLine();
	        aluno.setDataMatricula(LocalDate.parse(dataMatrString, formato));
			
			System.out.println("\nEnviando os dados recebidos...");
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
		System.out.println("\n---- EXCLUSÃO DE ALUNO ----");
		System.out.print("> Digite o ID do aluno: ");
		
		try {
            int id = Integer.parseInt(sc.nextLine());
            dao.excluir(id);
        } catch (NumberFormatException e) {
            System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID invalido. Tente novamente.");
        }
		Console.pausar();
	}
}
