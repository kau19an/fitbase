package br.edu.brazcubas.fitbase.entities;

import java.time.LocalDate;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class Aluno {
	private Integer id;

	private String primeiroNome;
	private String meioNome;
	private String ultimoNome;
	private String cpf;
	private LocalDate dataNasc;
	private String email;
	private String telefone;
	private LocalDate dataMatricula;
	private Plano plano; // FK: id

	// Construtor
	public Aluno(Integer id, String primeiroNome, String meioNome, String ultimoNome, String cpf, LocalDate dataNasc,
			String email, String telefone, LocalDate dataMatricula, Plano plano) {
		this.id = id;
		this.primeiroNome = primeiroNome;
		this.meioNome = meioNome;
		this.ultimoNome = ultimoNome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.email = email;
		this.telefone = telefone;
		this.dataMatricula = dataMatricula;
		this.plano = plano;
	}

	// Getters
	public Integer getId() {
		return id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public String getMeioNome() {
		return meioNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public Plano getPlano() {
		return plano;
	}

	// Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public void setMeioNome(String meioNome) {
		this.meioNome = meioNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setDataMatricula(LocalDate dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}
}
