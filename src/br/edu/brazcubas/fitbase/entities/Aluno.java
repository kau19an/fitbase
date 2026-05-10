package br.edu.brazcubas.fitbase.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Kauan Farias
 * @author Breno Christaziano
 * @version 1.5
 */

public class Aluno extends Pessoa {
	private Integer id;
	private LocalDate dataNasc;
	private String email;
	private LocalDate dataMatricula;
	private Plano plano; // FK: id
	private String infoAulas;
	private int totalVisitas;
	private LocalDate ultimaVisita;
	private String statusPlano;
	private LocalDate dataVencimento;
	
	// Construtores
	public Aluno() {
		super();
	}

	public Aluno(Integer id, String primeiroNome, String meioNome, String ultimoNome, String cpf, LocalDate dataNasc,
			String telefone, String email, LocalDate dataMatricula, Plano plano) {

		super(primeiroNome, meioNome, ultimoNome, cpf, telefone);

		this.id = id;
		this.dataNasc = dataNasc;
		this.email = email;
		this.dataMatricula = dataMatricula;
		this.plano = plano;
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

	public String getInfoAulas() {
		return infoAulas;
	}
	
	public int getTotalVisitas() {
		return totalVisitas;
	}

	public LocalDate getUltimaVisita() {
		return ultimaVisita;
	}

	public String getStatusPlano() {
		return statusPlano;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
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

	public void setInfoAulas(String infoAulas) {
		this.infoAulas = infoAulas;
	}

	public void setTotalVisitas(int totalVisitas) {
		this.totalVisitas = totalVisitas;
	}

	public void setUltimaVisita(LocalDate ultimaVisita) {
		this.ultimaVisita = ultimaVisita;
	}

	public void setStatusPlano(String statusPlano) {
		this.statusPlano = statusPlano;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Override
	public String toString() {
		// Para que a data seja exibida no formato usado no Brasil
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Retorna os dados do aluno já formatados para exibir na lista
		return String.format("""
                [%d] %s
                - CPF: %s
                - Telefone: %s
                - E-mail: %s
                - Data de nascimento: %s
                
                - Matriculado em: %s (desde %s)
                - Status: %s | Vencimento: %s
                - Aulas matriculadas: %s
                - Frequência: %d visita(s) | Última em: %s
                \n-----\n""", 
                getId(), getNomeCompleto(), getCpf(), getTelefone(), email, 
                dataNasc.format(formato), plano.getNome(), dataMatricula.format(formato), 
                (statusPlano != null ? statusPlano : "Desconhecido"),
                (dataVencimento != null ? dataVencimento.format(formato) : "N/A"),                 
                (getInfoAulas() != null ? getInfoAulas() : "Nenhuma"),
                totalVisitas,
                (ultimaVisita != null ? ultimaVisita.format(formato) : "N/A"));
    }
}
