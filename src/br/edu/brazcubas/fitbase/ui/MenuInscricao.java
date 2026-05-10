package br.edu.brazcubas.fitbase.ui;

import java.util.List;
import java.util.Scanner;

import br.edu.brazcubas.fitbase.dao.AulaDAO;
import br.edu.brazcubas.fitbase.dao.InscricaoAulaDAO;
import br.edu.brazcubas.fitbase.entities.Aluno;
import br.edu.brazcubas.fitbase.entities.Aula;
import br.edu.brazcubas.fitbase.entities.InscricaoAula;
import br.edu.brazcubas.fitbase.utils.Console;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class MenuInscricao {
	private Scanner sc = new Scanner(System.in);
	private InscricaoAulaDAO dao = new InscricaoAulaDAO();
	private AulaDAO aulaDAO = new AulaDAO();

	public void exibirMenu() {
		int opcao;
		do {
			Console.limpar();

			System.out.print("""
					|-------------------------------|
					|  ACADEMIA FITBASE: INSCRIÇÃO  |
					|-------------------------------|
					| 1) Matricular aluno           |
					| 2) Cancelar matrícula         |
					|-------------------------------|
					| Digite 0 para voltar.         |
					|-------------------------------|

					> Sua escolha:\s""");

			try {
				opcao = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				opcao = -1;
			}

			switch (opcao) {
			case 1 -> matricular();
			case 2 -> excluir();
			case 0 -> { Console.limpar(); } // Menu principal
			default -> {
				System.out.println("\u001B[1;31m[ERRO]\u001B[0m Opção inválida. Tente novamente.");
				Console.pausar();
			}
			}
		} while (opcao != 0);
	}

	private void matricular() {
		Console.limpar();

		System.out.print("""
				|------------------------------|
				| ACADEMIA FITBASE: INSCRIÇÃO  |
				|      Matriculando aluno      |
				|------------------------------|
				|  Lista de aulas disponíveis  |
				|------------------------------|
				""");
		List<Aula> aulas = aulaDAO.listarTodos();

		if (aulas.isEmpty()) {
			System.out.println(
					"\u001B[1;33m[AVISO]\u001B[0m É necessário que pelo menos uma aula exista no banco de dados. Cadastre uma antes de continuar.\n");
			Console.pausar();
			return;
		}

		for (Aula a : aulas) {
			System.out.printf("[%d] %s - %s (Capacidade máxima: %d)\n", a.getId(), a.getNome(), a.getHorario(),
					a.getCapacidadeMax());
		}

		try {
			System.out.print("\nDigite o ID do(a) aluno(a):\n> ");
			int idAluno = Integer.parseInt(sc.nextLine());

			System.out.print("\nDigite o ID da aula:\n> ");
			int idAula = Integer.parseInt(sc.nextLine());

			// FK: aluno, aula
			Aluno aluno = new Aluno();
			aluno.setId(idAluno);

			Aula aula = new Aula();
			aula.setId(idAula);

			InscricaoAula inscricao = new InscricaoAula(aluno, aula);

			System.out.println("\n\u001B[1m[INFO]\u001B[0m Processando matrícula e verificando regras de negócio...");
			dao.cadastrar(inscricao);

		} catch (NumberFormatException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Insira somente números.");
		}

		Console.pausar();
	}
	
	// Excluir inscrição (Cancelar matrícula)
	public void excluir() {
		Console.limpar();
		
		System.out.println("""
				|-----------------------------|
				| ACADEMIA FITBASE: INSCRIÇÃO |
				|  Cancelamento de matrícula  |
				|-----------------------------|
				""");
		
		try {
			System.out.print("\nDigite o ID do(a) aluno(a):\n> ");
			int idAluno = Integer.parseInt(sc.nextLine());

			System.out.print("\nDigite o ID da aula que deseja cancelar:\n> ");
			int idAula = Integer.parseInt(sc.nextLine());

			System.out.println("\n\u001B[1m[INFO]\u001B[0m Processando cancelamento...");
			dao.excluir(idAluno, idAula);
		} catch (NumberFormatException e) {
			System.out.println("\u001B[1;31m[ERRO]\u001B[0m ID inválido. Insira somente números.\n");
		}
		
		Console.pausar();
	}
}
