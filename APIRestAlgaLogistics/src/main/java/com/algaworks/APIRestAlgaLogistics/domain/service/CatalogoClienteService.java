package com.algaworks.APIRestAlgaLogistics.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.APIRestAlgaLogistics.domain.exception.NegocioException;
import com.algaworks.APIRestAlgaLogistics.domain.model.Cliente;
import com.algaworks.APIRestAlgaLogistics.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
/*
	Anotações

	torna a classe um componente do spring porém com uma sermäntica de serviço
	ou seja, que representa os serviços
	terá regras de neǵocio

	@Transactional
	se trata de uma transação do BD, ou seja, em caso de erro, pode haver RollBack


*/