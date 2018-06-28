package br.com.projeto.supermercado.service;

import java.util.List;

import br.com.projeto.supermercado.model.Produto;

public interface ProdutoService {

	void adicionar(Produto produto);
	
	void editar(Produto produto);
	
	void remover(Long id);

	Produto buscar(Long id);
	
	List<Produto> listar();
	
}
