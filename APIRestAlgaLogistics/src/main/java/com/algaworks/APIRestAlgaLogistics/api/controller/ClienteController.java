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
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(
			@PathVariable Long clienteId,
			@Valid @RequestBody Cliente cliente) {
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogoClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}

/*
	Anota????es
	
	@RestController
	declara que ?? capaz de receber requisi????es e devolver respostas
	
	@GetMapping("/clientes")
	declara o endpoint respons??vel para disponibilizar o m??todo
	
	documenta????o de http codes
	1) https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
	2) https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status
	3) https://httpstatuses.com/
	
	termo "Content Negotiation"
	consiste em disponibilizar o retorno em formatos diferentes
	ex>: json e xml
	o json ?? o formato padr??o, mas se quiser add outro, tem que add a depend??ncia do tipo, no arquivo pom.xml
	ex> add resposta em formato xml, add no pom.xml	
	<dependency>
		<groupId>com.fasterxml.jackson.dataformat</groupId>
		<artifactId>jackson-dataformat-xml</artifactId>
	</dependency>

	Entitymanager
	respons??vel por refletir no BD, as opera????es realizadas (CRUD)
	
	PersistenceContext
	gera um 'container' que agrega os objetos manipulados pelo Entitymanager
	injeta no na vari??vel manager, um contexto Entitymanager
	
	JPQL no createQuery
	trata-se de uma linguagem orientada a objeto, espec??fica do Jacarta Persistence
	
	@Autowired
	define que se trata de estar injetando uma depend??ncia que est?? sendo gerenciada pelo spring
	
	@AllArgsConstructor
	gera um construtor com todas as propriedades que tem na classe (no caso o ClienteRepository)
	
	@PathVariable
	faz um biding da vari??vel que est?? sendo passada no endpoint para ser usada como par??metro no m??todo
	
	orElse
	pode ter um cliente ou estar sem nada
	ent??o retorna o que est?? dentro do optional, que pode ser ou um cliente ou null
	
	ResponseEntity
	representa a resposta que ser?? retornada
	podendo customizar melhor a resposta
	como o cabe??alho, c??digo da resposta, etc.
	
	cliente.isPresent()
	verifica se existe um cliente ali dentro da var.
	se tiver, retorna status ok/200 e o cliente 
	-> ResponseEntity.ok(cliente.get());
	
	se n??o, retorna status 404/notfound
	-> ResponseEntity.notFound().build();
	o build() ?? para construir um ResponseEntity not found/404
	
	method reference
	.map(ResponseEntity::ok)
	
	@RequestMapping("/clientes")
	ao n??vel de classe, define o endere??o padr??o, pra n??o ter que ficar repetindo no c??digo todo
	
	@RequestBody 
	deserealiza o json vindo do corpo da requisi????o e atribui na var.
	
	@ResponseStatus(HttpStatus.CREATED)
	adiciona o status do response, como 201/Created
	
	@PutMapping
	para atualizar parte do recurso/objeto
	
	cliente.setId(clienteId);
	define o Id do recurso/objeto que dever?? ser encontrado para atualizar
	
	ResponseEntity.noContent().build();
	envia o c??digo 204 e sem conte??do, pois foi feito a exclus??o do recurso/objeto
	
	Bean Validation
	para validar objetos java
	
	
	
*/