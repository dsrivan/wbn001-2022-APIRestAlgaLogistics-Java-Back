package com.algaworks.APIRestAlgaLogistics.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioModel {
	private String nome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
}

/*
 	Anotações

	modelo de representação
	basicamente é o resultado respondido à requisição
	uma classe de modelo de representação, visa moldar o modelo da entrega
	assim podendo controlar facilmente os campos que serão entregues na requisição
	fazendo com que não exponha possíveis campos sensíveis que não deverim ser expostos
 
 
 
 
 */
