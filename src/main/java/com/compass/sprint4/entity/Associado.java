package com.compass.sprint4.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Associado {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private CargoEnum cargo;
	private LocalDate dataNascimento;
	private SexoEnum sexo;
	@ManyToOne
	private Partido partido;
	
	public Associado(String nome, CargoEnum cargo, LocalDate dataNascimento, SexoEnum sexo) {
		super();
		this.nome = nome;
		this.cargo = cargo;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
	
	public Associado() {
		
	}
	
	
	

}
