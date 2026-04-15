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

	// Construtor
	public Aula(Integer id, String nome, String descricao, int capacidadeMax, String horario, int duracao,
			Instrutor instrutor) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.capacidadeMax = capacidadeMax;
		this.horario = horario;
		this.duracao = duracao;
		this.instrutor = instrutor;
	}

	// Getters
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getCapacidadeMax() {
		return capacidadeMax;
	}

	public String getHorario() {
		return horario;
	}

	public int getDuracao() {
		return duracao;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	// Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCapacidadeMax(int capacidadeMax) {
		this.capacidadeMax = capacidadeMax;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}
}
