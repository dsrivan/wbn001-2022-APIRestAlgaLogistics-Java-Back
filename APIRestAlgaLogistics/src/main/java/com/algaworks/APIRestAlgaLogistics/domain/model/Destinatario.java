package com.algaworks.APIRestAlgaLogistics.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinatario {
	
	@NotBlank
	@Column(name ="destinatario_nome")
	private String nome;

	@NotBlank
	@Column(name ="destinatario_logradouro")
	private String logradouro;

	@NotBlank
	@Column(name ="destinatario_numero")
	private String numero;

	@Column(name ="destinatario_complemento")
	private String complemento;

	@NotBlank
	@Column(name ="destinatario_bairro")
	private String bairro;
}

/*
	Anotações	
	
	@Embeddable
	essa classe por estar sendo usada como @Embedded em outra classe
	então na classe em questão, deve-se haver o @Embeddable
	
	@Column(name ="destinatario_nome")
	serve para customizar o nome da prop, pois esse campo será criado na tabela Entrega
	Então serve para deixar claro do que se trata o determinado campo
	
*/

