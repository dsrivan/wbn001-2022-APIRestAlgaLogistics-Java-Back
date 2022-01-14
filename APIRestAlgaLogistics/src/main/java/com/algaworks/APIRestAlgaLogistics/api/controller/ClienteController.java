package com.algaworks.APIRestAlgaLogistics.api.controller;

import java.util.List;
//import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.APIRestAlgaLogistics.domain.model.Cliente;
import com.algaworks.APIRestAlgaLogistics.domain.repository.ClienteRepository;
import com.algaworks.APIRestAlgaLogistics.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)				
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
		/* 
		 	clienteRepository.findById(clienteId)
			.map(cliente -> ResponseEntity.ok(cliente))
			.orElse(ResponseEntity.notFound().build());
				
		 	ou pode ser assim
		 	
			Optional<Cliente> cliente = clienteRepository.findById(clienteId);
			
			if (cliente.isPresent()) {
				return ResponseEntity.ok(cliente.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		 */
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalogoClienteService.salvar(cliente);
		//return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(
			@PathVariable Long clienteId,
			@Valid @RequestBody Cliente cliente) {
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(clienteId);
		//cliente = clienteRepository.save(cliente);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		//clienteRepository.deleteById(clienteId);
		catalogoClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
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

	Entitymanager
	responsável por refletir no BD, as operações realizadas (CRUD)
	
	PersistenceContext
	gera um 'container' que agrega os objetos manipulados pelo Entitymanager
	injeta no na variável manager, um contexto Entitymanager
	
	JPQL no createQuery
	trata-se de uma linguagem orientada a objeto, específica do Jacarta Persistence
	
	@Autowired
	define que se trata de estar injetando uma dependência que está sendo gerenciada pelo spring
	
	@AllArgsConstructor
	gera um construtor com todas as propriedades que tem na classe (no caso o ClienteRepository)
	
	@PathVariable
	faz um biding da variável que está sendo passada no endpoint para ser usada como parâmetro no método
	
	orElse
	pode ter um cliente ou estar sem nada
	então retorna o que está dentro do optional, que pode ser ou um cliente ou null
	
	ResponseEntity
	representa a resposta que será retornada
	podendo customizar melhor a resposta
	como o cabeçalho, código da resposta, etc.
	
	cliente.isPresent()
	verifica se existe um cliente ali dentro da var.
	se tiver, retorna status ok/200 e o cliente 
	-> ResponseEntity.ok(cliente.get());
	
	se não, retorna status 404/notfound
	-> ResponseEntity.notFound().build();
	o build() é para construir um ResponseEntity not found/404
	
	method reference
	.map(ResponseEntity::ok)
	
	@RequestMapping("/clientes")
	ao nível de classe, define o endereço padrão, pra não ter que ficar repetindo no código todo
	
	@RequestBody 
	deserealiza o json vindo do corpo da requisição e atribui na var.
	
	@ResponseStatus(HttpStatus.CREATED)
	adiciona o status do response, como 201/Created
	
	@PutMapping
	para atualizar parte do recurso/objeto
	
	cliente.setId(clienteId);
	define o Id do recurso/objeto que deverá ser encontrado para atualizar
	
	ResponseEntity.noContent().build();
	envia o código 204 e sem conteúdo, pois foi feito a exclusão do recurso/objeto
	
	Bean Validation
	para validar objetos java
	
	
	
*/