package br.edu.brazcubas.fitbase.entities;

import java.time.LocalDate;

/**
 * A classe base que vai representar uma pessoa do sistema.
 * Contém informaççoes comuns como nome, cpf e contato.
 * Essa classe será herdada por outras, como Aluno, Instrutor.
 * 
 * @author Breno Christaziano
 * @version 1.0
 */

public class Pessoa {

    protected String primeiroNome;
    protected String meioNome;
    protected String ultimoNome;
    protected String cpf;
    protected String telefone;

    public Pessoa(String primeiroNome, String meioNome, String ultimoNome,
                  String cpf, String telefone){

        this.primeiroNome = primeiroNome;
        this.meioNome = meioNome;
        this.ultimoNome = ultimoNome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

     public String getNomeCompleto() {
        String nome = primeiroNome + " ";

        if (meioNome != null && !meioNome.isEmpty()) {
            nome += meioNome + " ";
        }

        nome += ultimoNome;

        return nome;
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

