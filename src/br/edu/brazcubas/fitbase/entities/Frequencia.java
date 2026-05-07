package br.edu.brazcubas.fitbase.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Kauan Farias
 * @version 1.2
 */

public class Frequencia {
	private Integer id;

	private LocalDate dataEntrada;
	private String horaEntrada;
	private Aluno aluno; // FK: id

	// Construtores
	public Frequencia() {
	}

	public Frequencia(Integer id, LocalDate dataEntrada, String horaEntrada, Aluno aluno) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.horaEntrada = horaEntrada;
		this.aluno = aluno;
	}

	// Getters
	public Integer getId() {
		return id;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public Aluno getAluno() {
		return aluno;
	}

	// Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public String toString() {
		// Para que a data seja exibida no formato usado no Brasil
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Retorna os dados da frequência já formatados para exibir na lista
		return String.format("""
				[ID: %d] Entrada: %s às %s
				\n-----\n""", id,
				(dataEntrada != null ? dataEntrada.format(formato) : "Não informada"), horaEntrada);
	}
}
