package com.Guilherme.minhasFinancas.service;

import java.util.List;

import com.Guilherme.minhasFinancas.model.entity.Lancamento;
import com.Guilherme.minhasFinancas.model.enums.StatusLancamento;

public interface LancamentoService {

	Lancamento salvar(Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
	
	void deletar (Lancamento lancamento);
	
	List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
	void atualizarStatus (Lancamento lancamento, StatusLancamento status);
}
