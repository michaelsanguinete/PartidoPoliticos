package com.compass.sprint4.dto;

import com.compass.sprint4.entity.Associado;

import lombok.Data;

@Data
public class AssociadoPartidoForm {
	
	public AssociadoPartidoForm(Associado associado) {
	}
	
	private int idAssociado;
	private int idPartido;
	
	
	}


