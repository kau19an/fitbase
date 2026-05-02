package br.edu.brazcubas.fitbase.entities;

/**
 * @author Kauan Farias
 * @version 1.1
 */

public class Instrutor extends Pessoa {
	private Integer id;

	private String especialidade;
	private String horariosTrabalho;

	// Construtores
	public Instrutor() {
		super();
	}

	public Instrutor(Integer id, String primeiroNome, String meioNome, String ultimoNome, String cpf, String telefone,
			String especialidade, String horariosTrabalho) {
		super(primeiroNome, meioNome, ultimoNome, cpf, telefone);

		this.id = id;
		this.especialidade = especialidade;
		this.horariosTrabalho = horariosTrabalho;
	}

	// Getters
	public Integer getId() {
		return id;
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

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void setHorariosTrabalho(String horariosTrabalho) {
		this.horariosTrabalho = horariosTrabalho;
	}
}
