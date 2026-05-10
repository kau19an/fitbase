package br.edu.brazcubas.fitbase.entities;

/**
 * @author Kauan Farias
 * @version 1.4
 */

public class Instrutor extends Pessoa {
	private Integer id;
	private String especialidade;
	private String horariosTrabalho;
	private int qtdAlunos;
    private String aulasMinistradas;
	
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

	public int getQtdAlunos() {
        return qtdAlunos;
    }
    
    public String getAulasMinistradas() {
        return aulasMinistradas;
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
	
	public void setQtdAlunos(int qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }

    public void setAulasMinistradas(String aulasMinistradas) {
        this.aulasMinistradas = aulasMinistradas;
    }

	@Override
	public String toString() {
		// Retorna os dados do instrutor já formatados para exibir na lista
		return String.format("""
				[%d] %s
				- CPF: %s
				- Telefone: %s
				- Especialidade: %s
				- Horário(s) de trabalho: %s
				- Aulas ministradas: %s
				- Total de alunos atendidos: %s
				\n-----\n""", id, getNomeCompleto(), cpf, telefone, especialidade,
				horariosTrabalho, (aulasMinistradas != null ? aulasMinistradas : "Nenhuma"), qtdAlunos);
	}
}
