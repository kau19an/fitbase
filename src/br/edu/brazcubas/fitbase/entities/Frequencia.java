package br.edu.brazcubas.fitbase.entities;

import java.time.LocalDate;

/**
 * @author Kauan Farias
 * @version 1.0
 */

public class Frequencia {
	private Integer id;
	
	private LocalDate dataEntrada;
	private String horaEntrada;
	private Aluno aluno; // FK: id
	
	// ...
}
