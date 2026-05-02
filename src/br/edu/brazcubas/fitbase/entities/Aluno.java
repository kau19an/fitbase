package br.edu.brazcubas.fitbase.entities;

import java.time.LocalDate;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class Aluno extends Pessoa {

    private Integer id;
    private LocalDate dataNasc;
    private String email;
    private LocalDate dataMatricula;
    private Plano plano;

    // Construtor
    public Aluno(Integer id,
                 String primeiroNome, String meioNome, String ultimoNome,
                 String cpf, LocalDate dataNasc, String telefone, String email,
                 LocalDate dataMatricula, Plano plano) {

        super(primeiroNome, meioNome, ultimoNome, cpf, telefone);

        this.id = id;
        this.dataNasc = dataNasc;
        this.email = email;
        this.dataMatricula = dataMatricula;
        this.plano = plano;
    }

	 @Override
     public String getNomeCompleto() {
        return "Aluno: " + super.getNomeCompleto();
    }  


	// Getters
	public Integer getId() {
		return id;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public String getEmail() {
		return email;
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


	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDataMatricula(LocalDate dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}
}
