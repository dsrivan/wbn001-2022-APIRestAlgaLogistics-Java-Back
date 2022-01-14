package com.algaworks.APIRestAlgaLogistics.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
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
	
	@Entity
	anotação para fazer o mapeamento entre a classe e a tabela no BD
	por padrão, se não definir um nome de tabela, o nome da classe será usado
	mas para definir um nome diferente do nome da classe, usa-se:
	@Table(name = "nome_tabela")
	
	@Column
	define qual o nome da coluna na tabela, para o atributo da classe
	ex: atributo nome na classe mas na tabela é nome_completo, ficaria:
	@Column(name="nome_completo")
	é opcional explicitar essa anotação, pois por definir como Entity, já será reconhecido automaticamente como uma coluna e seu nome
	
	métodos para definir comparação entre objetos
	equals() e hashCode()
	gera código boilerplate, então usa a anotaçao @EqualsAndHashCode do lombok
	e para definir o atributo que é usado para comparar objetos, insere a anotaçao abaixo no atributo:
	@EqualsAndHashCode.Include
*/