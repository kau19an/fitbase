package br.edu.brazcubas.fitbase.entities;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class Instrutor {
	private Integer id;

	private String primeiroNome;
	private String meioNome;
	private String ultimoNome;
	private String cpf;
	private String telefone;
	private String especialidade;
	private String horariosTrabalho;

	// Construtor
	public Instrutor(Integer id, String primeiroNome, String meioNome, String ultimoNome, String cpf, String telefone,
			String especialidade, String horariosTrabalho) {
		this.id = id;
		this.primeiroNome = primeiroNome;
		this.meioNome = meioNome;
		this.ultimoNome = ultimoNome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.especialidade = especialidade;
		this.horariosTrabalho = horariosTrabalho;
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

	public String getTelefone() {
		return telefone;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public String getHorariosTrabalho() {
		return horariosTrabalho;
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

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void setHorariosTrabalho(String horariosTrabalho) {
		this.horariosTrabalho = horariosTrabalho;
	}
}
