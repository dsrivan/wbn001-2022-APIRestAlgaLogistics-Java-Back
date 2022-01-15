package com.algaworks.APIRestAlgaLogistics.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.APIRestAlgaLogistics.api.assembler.EntregaAssembler;
import com.algaworks.APIRestAlgaLogistics.api.model.EntregaModel;
import com.algaworks.APIRestAlgaLogistics.api.model.input.EntregaInput;
import com.algaworks.APIRestAlgaLogistics.domain.model.Entrega;
import com.algaworks.APIRestAlgaLogistics.domain.repository.EntregaRepository;
import com.algaworks.APIRestAlgaLogistics.domain.service.FinalizacaoEntregaService;
import com.algaworks.APIRestAlgaLogistics.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	private FinalizacaoEntregaService finalizacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega preEntrega = entregaAssembler.toEntity(entregaInput);		
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(preEntrega);
		return entregaAssembler.toModel(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaModel> listar(){
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)) )
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}
}

/*
 	Anotações

	ModelMapper
 	biblioteca para evitar código boilerplate
 	faz mapeamento de obj e faz a transformação de um modelo para outro com pouco código
 
 
 */

