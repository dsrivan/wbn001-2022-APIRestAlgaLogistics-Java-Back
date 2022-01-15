package com.algaworks.APIRestAlgaLogistics.commom;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

/*
 	Anotações

	Essa classe ModelMapperConfig
	tem por objetivo colocar o ModelMapper como se fosse um bean do ecos. spring
	
	@Configuration
	declara que a classe é um componente spring com o objetivo de definição/configuração de bean spring
 
 	assim ficando disponível para ser injetado em outras classes
 
 
 */


