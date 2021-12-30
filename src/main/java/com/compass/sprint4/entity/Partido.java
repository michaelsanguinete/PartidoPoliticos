package com.compass.sprint4.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class Partido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String sigla;
	private IdeologiaEnum ideologia;
	private LocalDate data_fundacao;
	
	public Partido(String nome, String sigla, IdeologiaEnum ideologia, LocalDate data_fundacao) {
		super();
		this.nome = nome;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.data_fundacao = data_fundacao;
	}

	

}
