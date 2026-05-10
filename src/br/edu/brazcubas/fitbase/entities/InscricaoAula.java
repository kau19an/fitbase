package br.edu.brazcubas.fitbase.entities;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class InscricaoAula {
	private Aluno aluno; // FK
	private Aula aula; // FK

	// Construtores
	public InscricaoAula() {
	}

	public InscricaoAula(Aluno aluno, Aula aula) {
		this.aluno = aluno;
		this.aula = aula;
	}

	// Getters
	public Aluno getAluno() {
		return aluno;
	}

	public Aula getAula() {
		return aula;
	}

	// Setters
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	@Override
	public String toString() {
		// Retorna os dados da inscrição já formatados para exibir na lista
		return String.format("""
				- Aluno(a): %s
				- Aula: %s
				\n-----\n""", (aluno != null ? aluno.getNomeCompleto() : "N/A"),
				(aula != null ? aula.getNome() : "Não informada"));
	}
}