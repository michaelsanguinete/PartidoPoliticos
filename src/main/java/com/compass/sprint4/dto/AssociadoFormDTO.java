package com.compass.sprint4.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.sprint4.entity.Associado;
import com.compass.sprint4.entity.CargoEnum;
import com.compass.sprint4.entity.SexoEnum;
import com.compass.sprint4.repository.AssociadoRepository;

import lombok.Data;

@Data
public class AssociadoFormDTO {

	@NotNull
	@NotEmpty
	private String nome;
	@Enumerated(EnumType.STRING)
	private CargoEnum cargo;
	private LocalDate dataNascimento;
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;

	public Associado converter() {
		return new Associado(nome, cargo, dataNascimento, sexo);
	}

	public Associado atualizar(int id, AssociadoRepository repository) {

		Associado associado = repository.getById(id);
		associado.setNome(this.nome);
		associado.setCargo(this.cargo);
		associado.setDataNascimento(this.dataNascimento);
		associado.setSexo(this.sexo);
		return associado;
	}

}
