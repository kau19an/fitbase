package br.edu.brazcubas.fitbase.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Kauan Farias
 * @version 1.1
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
	
	// Construtores
	public Aluno() {}

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
	
	@Override
	public String toString() {
		// Para que a data seja exibida no formato usado no Brasil
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
	    // Se o nome do meio for nulo ou vazio, ignora; senão, o exibe
	    String meio = (meioNome == null || meioNome.trim().isEmpty()) ? "" : meioNome + " ";
	    
	    // Junta cada parte do nome, eliminando os espaços antes e depois (se houver)
	    String nomeCompleto = (primeiroNome + " " + meio + ultimoNome).trim();
	    
	    // Retorna os dados do aluno já formatados para exibir na lista
	    return String.format("""
	            [%d] %s
	            - CPF: %s
	            - Data de nascimento: %s
	            - E-mail: %s
	            - Telefone: %s
	            - Data de matrícula: %s
	            ------------------------------------------""",
	            id, nomeCompleto.toUpperCase(), // Para fins de destaque
	            cpf,
	            (dataNasc != null ? dataNasc.format(formato) : "Não informada"), 
	            email,
	            telefone,
	            (dataMatricula != null ? dataMatricula.format(formato) : "Não informada")
	    );
	}
}
