package br.com.projeto.supermercado.service;

import java.util.List;

import br.com.projeto.supermercado.model.Carrinho;
import br.com.projeto.supermercado.model.Compra;
import br.com.projeto.supermercado.model.Pessoa;
import br.com.projeto.supermercado.model.Produto;

public interface CarrinhoService {
	
	void adicionarItem(Carrinho carrinho, Produto produto);

	void removerItem(Carrinho carrinho, Long id);
	
	void decrementarQuantidade(Carrinho carrinho, Long id);
	
	void incrementarQuantidade(Carrinho carrinho, Long id);	
	
	void finalizar(Carrinho carrinho, Pessoa pessoa);
	
	List<Compra> listarCompras();
}
