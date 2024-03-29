package com.sistema.money.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sistema.money.api.model.Pessoa;
import com.sistema.money.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {

	  Pessoa pessoaSalva = this.pessoaRepository.findById(codigo)
	      .orElseThrow(() -> new EmptyResultDataAccessException(1));

	  BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");

	  return this.pessoaRepository.save(pessoaSalva);
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
	
	private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaSalva = this.pessoaRepository.findById(codigo)
			      .orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		return pessoaSalva;
	}
}
