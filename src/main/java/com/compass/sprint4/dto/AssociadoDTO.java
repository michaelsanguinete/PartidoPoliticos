package com.compass.sprint4.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.compass.sprint4.entity.Associado;
import com.compass.sprint4.entity.CargoEnum;
import com.compass.sprint4.entity.SexoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssociadoDTO {

	private int id;
	private String nome;
	private CargoEnum cargo;
	private LocalDate dataNascimento;
	private SexoEnum sexo;

	public AssociadoDTO(Associado associado) {
		this.nome = associado.getNome();
		this.cargo = associado.getCargo();
		this.dataNascimento = associado.getDataNascimento();
		this.sexo = associado.getSexo();
	}

	public AssociadoDTO() {

	}

	public static List<AssociadoDTO> converter(List<Associado> associado) {
		return associado.stream().map(AssociadoDTO::new).collect(Collectors.toList());

	}
}
