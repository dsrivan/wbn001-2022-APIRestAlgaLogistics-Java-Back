package com.algaworks.APIRestAlgaLogistics.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Problema {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;

	@Getter
	@AllArgsConstructor
	public static class Campo {
		private String nome;
		private String mensagem;
	}
}

/*
	Anotações
	
	classe que será utilizada para tratamento de erros
	e enviar respostas personalizadas
	
	@JsonInclude(Include.NON_NULL)
	incluir apenas propriedades que não estiverem nulas
	
*/
