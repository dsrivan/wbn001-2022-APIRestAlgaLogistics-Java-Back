package com.algaworks.APIRestAlgaLogistics.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.APIRestAlgaLogistics.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();		
		cliente1.setId(1L);
		cliente1.setNome("Ivan");
		cliente1.setTelefone("17 99602-2603");
		cliente1.setEmail("iivan.si@msn.com");

		Cliente cliente2 = new Cliente();		
		cliente2.setId(2L);
		cliente2.setNome("Mustafá");
		cliente2.setTelefone("17 99602-2603");
		cliente2.setEmail("iivan.si.mustafa@msn.com");
		
		return Arrays.asList(cliente1, cliente2);
	}
}

/*
	Anotações
	
	@RestController
	declara que é capaz de receber requisições e devolver respostas
	
	@GetMapping("/clientes")
	declara o endpoint responsável para disponibilizar o método
*/