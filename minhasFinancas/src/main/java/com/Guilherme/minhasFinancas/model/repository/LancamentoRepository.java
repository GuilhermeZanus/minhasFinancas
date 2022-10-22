package com.Guilherme.minhasFinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Guilherme.minhasFinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
