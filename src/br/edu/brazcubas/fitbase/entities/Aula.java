package br.edu.brazcubas.fitbase.entities;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class Aula {
	private Integer id;
	
	private String nome;
	private String descricao;
	private int capacidadeMax;
	private String horario;
	private int duracao;
	private Instrutor instrutor; // FK: id
	
	// ...
}
