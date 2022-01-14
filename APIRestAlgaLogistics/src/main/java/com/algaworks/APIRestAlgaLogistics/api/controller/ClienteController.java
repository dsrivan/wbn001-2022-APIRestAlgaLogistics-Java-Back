package com.algaworks.APIRestAlgaLogistics.api.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.APIRestAlgaLogistics.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		
		return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
	}
}

/*
	Anotações
	
	@RestController
	declara que é capaz de receber requisições e devolver respostas
	
	@GetMapping("/clientes")
	declara o endpoint responsável para disponibilizar o método
	
	documentação de http codes
	1) https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
	2) https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status
	3) https://httpstatuses.com/
	
	termo "Content Negotiation"
	consiste em disponibilizar o retorno em formatos diferentes
	ex>: json e xml
	o json é o formato padrão, mas se quiser add outro, tem que add a dependência do tipo, no arquivo pom.xml
	ex> add resposta em formato xml, add no pom.xml	
	<dependency>
		<groupId>com.fasterxml.jackson.dataformat</groupId>
		<artifactId>jackson-dataformat-xml</artifactId>
	</dependency>

	Entitymanager: responsável por refletir no BD, as operações realizadas (CRUD)
	
	PersistenceContext: gera um 'container' que agrega os objetos manipulados pelo Entitymanager
	injeta no na variável manager, um contexto Entitymanager
	
	JPQL no createQuery
	trata-se de uma linguagem orientada a objeto, específica do Jacarta Persistence
	
	
*/