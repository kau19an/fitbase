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
	
	// ...
}
