package br.com.projeto.supermercado.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.supermercado.model.Produto;
import br.com.projeto.supermercado.repository.ProdutoRepositorio;
import br.com.projeto.supermercado.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@Override
	public void adicionar(Produto produto) {
		produtoRepositorio.save(produto);
	}

	@Override
	public void editar(Produto produto) {
		produtoRepositorio.save(produto);

	}

	@Override
	public void remover(Long id) {
		produtoRepositorio.delete(id);

	}

	@Override
	public List<Produto> listar() {
		return produtoRepositorio.findAll();
	}

	@Override
	public Produto buscar(Long id) {
		return produtoRepositorio.findOne(id);
	}

}
