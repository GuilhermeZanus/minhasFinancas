package com.Guilherme.minhasFinancas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Guilherme.minhasFinancas.model.entity.Lancamento;
import com.Guilherme.minhasFinancas.model.enums.StatusLancamento;
import com.Guilherme.minhasFinancas.model.repository.LancamentoRepository;
import com.Guilherme.minhasFinancas.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService{

	private LancamentoRepository repository;
	
	@Override
	public Lancamento salvar(Lancamento lancamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lancamento atualizar(Lancamento lancamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Lancamento lancamento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		// TODO Auto-generated method stub
		
	}

}
