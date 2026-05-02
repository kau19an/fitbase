package br.edu.brazcubas.fitbase.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Kauan Farias
 * @author Breno Christaziano
 * @version 1.2
 */

public class Aluno extends Pessoa {

    private Integer id;
    private LocalDate dataNasc;
    private String email;
    private LocalDate dataMatricula;
    private Plano plano; // FK: id

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
