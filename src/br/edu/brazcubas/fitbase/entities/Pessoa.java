package br.edu.brazcubas.fitbase.entities;

/**
 * @author Breno Christaziano
 * @author Kauan Farias
 * @version 1.1
 */

public class Pessoa {
	protected String primeiroNome;
	protected String meioNome;
	protected String ultimoNome;
	protected String cpf;
	protected String telefone;

	// Construtores
	public Pessoa() {
	}

	public Pessoa(String primeiroNome, String meioNome, String ultimoNome, String cpf, String telefone) {
		this.primeiroNome = primeiroNome;
		this.meioNome = meioNome;
		this.ultimoNome = ultimoNome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	// Getters
	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public String getMeioNome() {
		return meioNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public String getNomeCompleto() {
		// Se o nome do meio for nulo ou vazio, ignora; senão, o exibe
		String meio = (meioNome == null || meioNome.trim().isEmpty()) ? "" : meioNome + " ";

		// Junta cada parte do nome, eliminando os espaços antes e depois (se houver)
		String nomeCompleto = (primeiroNome + " " + meio + ultimoNome).trim();

		return nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	// Setters
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

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
