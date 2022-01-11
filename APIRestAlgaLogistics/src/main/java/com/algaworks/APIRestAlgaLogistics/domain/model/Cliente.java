package com.algaworks.APIRestAlgaLogistics.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
	private Long id;
	private String nome;
	private String email;
	private String telefone;
}

/*
	Anotações
	
	Lombok
	para não precisar gerar código Boilerplate manualmente
	@Getter -> gera os getters para as props da classe
	@Setter -> gera os setters para as props da classe
*/