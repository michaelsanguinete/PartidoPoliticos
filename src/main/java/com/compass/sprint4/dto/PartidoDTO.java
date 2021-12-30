package com.compass.sprint4.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.compass.sprint4.entity.IdeologiaEnum;
import com.compass.sprint4.entity.Partido;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDTO {
	
	private int id;
	private String nome;
	private String sigla;
	private IdeologiaEnum ideologia;
	@JsonFormat(pattern = "dd/MM/yyyy" , shape = JsonFormat.Shape.STRING )
	private LocalDate data_fundacao;
	
	public PartidoDTO(Partido partido) {
		this.nome = partido.getNome();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.data_fundacao = partido.getData_fundacao();
	}
	

	public static List<PartidoDTO> converter(List<Partido> partido) {
		return partido.stream().map(PartidoDTO::new).collect(Collectors.toList());
	}

}


