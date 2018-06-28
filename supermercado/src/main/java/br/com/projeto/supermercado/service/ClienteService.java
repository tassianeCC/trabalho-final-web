package br.com.projeto.supermercado.service;

import java.util.List;

import br.com.projeto.supermercado.model.Pessoa;

public interface ClienteService {
	void adicionar(Pessoa pessoa);
	
	void editar(Pessoa pessoa);
	
	void remover(Long id);
	
	List<Pessoa> listar();

	Pessoa buscarPorEmail(String email);
}
