package com.algaworks.APIRestAlgaLogistics.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.APIRestAlgaLogistics.domain.exception.EntidadeNaoEncontradaExpection;
import com.algaworks.APIRestAlgaLogistics.domain.model.Entrega;
import com.algaworks.APIRestAlgaLogistics.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;

	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExpection("Entrega não encontrada."));
	}
}
