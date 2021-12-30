package com.compass.sprint4.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.sprint4.entity.IdeologiaEnum;
import com.compass.sprint4.entity.Partido;
import com.compass.sprint4.repository.PartidoRepository;

import lombok.Data;

@Data
public class PartidoFormDTO {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String sigla;
	@Enumerated(EnumType.STRING)
	private IdeologiaEnum ideologia;
	private LocalDate data_fundacao;
	
	public Partido converter() {
		return new Partido(nome, sigla, ideologia, data_fundacao);
	}

	public Partido atualizar(int id, PartidoRepository repository) {
		
		Partido partido = repository.getById(id);
		partido.setNome(this.nome);
		partido.setSigla(this.sigla);
		partido.setIdeologia(this.ideologia);
		partido.setData_fundacao(this.data_fundacao);
		
		return partido;
	}
}
