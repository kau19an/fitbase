package br.edu.brazcubas.fitbase.entities;

/**
 * @author Kauan Farias
 * @version 1.2
 */

public class Plano {
	private Integer id;

	private String nome;
	private String descricao;
	private String beneficios;
	private int duracao;
	private double valorMensal;

	// Construtores
	public Plano() {
	}

	public Plano(Integer id, String nome, String descricao, String beneficios, int duracao, double valorMensal) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.beneficios = beneficios;
		this.duracao = duracao;
		this.valorMensal = valorMensal;
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

	public String getBeneficios() {
		return beneficios;
	}

	public int getDuracao() {
		return duracao;
	}

	public double getValorMensal() {
		return valorMensal;
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

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}

	@Override
	public String toString() {
		// Retorna os dados do plano já formatados para exibir na lista
		return String.format("""
				[%d] %s
				- Descrição: %s
				- Benefícios: %s
				- Duração: %d mês(es)
				- Valor mensal: R$%.2f
				-------------------------------------""", id, getNome(), getDescricao(), getBeneficios(), getDuracao(),
				getValorMensal());
	}
}
