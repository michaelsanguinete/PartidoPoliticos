package com.compass.prova4.validacao;

import lombok.Getter;
import lombok.Setter;

public class ErroDeFormularioDTO {
	
	@Getter
	@Setter
	private String campo;
	@Getter
	@Setter
	private String mensagem;
	
	public ErroDeFormularioDTO(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

}
