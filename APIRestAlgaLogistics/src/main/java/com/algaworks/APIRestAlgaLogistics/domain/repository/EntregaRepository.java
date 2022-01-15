package com.algaworks.APIRestAlgaLogistics.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.APIRestAlgaLogistics.domain.model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long > {

}
