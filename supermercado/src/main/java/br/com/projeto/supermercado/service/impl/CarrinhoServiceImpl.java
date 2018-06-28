package br.com.projeto.supermercado.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.supermercado.model.Carrinho;
import br.com.projeto.supermercado.model.Compra;
import br.com.projeto.supermercado.model.ItemCompra;
import br.com.projeto.supermercado.model.Pessoa;
import br.com.projeto.supermercado.model.Produto;
import br.com.projeto.supermercado.repository.CompraRepositorio;
import br.com.projeto.supermercado.repository.ItemCompraRepositorio;
import br.com.projeto.supermercado.service.CarrinhoService;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {
	
	@Autowired
	private CompraRepositorio compraRepositorio;
	
	@Autowired
	private ItemCompraRepositorio itemCompraRepositorio;

	@Override
	public void adicionarItem(Carrinho carrinho, Produto produto) {
		if(produtoExiste(carrinho, produto.getId())) {
			incrementarQuantidade(carrinho, produto.getId());
		} else {
			ItemCompra itemCompra = new ItemCompra();
			itemCompra.setProduto(produto);
			itemCompra.setQuantidade(1);
			itemCompra.calcularTotal();
			carrinho.getItens().add(itemCompra);
		}
	}

	@Override
	public void removerItem(Carrinho carrinho, Long id) {
		for (ItemCompra itemCompra : carrinho.getItens()) {
			if(itemCompra.getProduto().getId() == id) {
				itemCompra.calcularTotal();
				carrinho.getItens().remove(itemCompra);
			}
		}
		
		
	}

	@Override
	public void decrementarQuantidade(Carrinho carrinho, Long id) {
		for (ItemCompra itemCompra : carrinho.getItens()) {
			if(itemCompra.getProduto().getId() == id) {

				itemCompra.decrementar();
				itemCompra.calcularTotal();

				
				if(itemCompra.getQuantidade() == 0) {
					removerItem(carrinho, id);
				}
			}
		}
	}

	@Override
	public void incrementarQuantidade(Carrinho carrinho, Long id) {
		for (ItemCompra itemCompra : carrinho.getItens()) {
			if(itemCompra.getProduto().getId() == id) {
				itemCompra.calcularTotal();
				itemCompra.incrementar();
			}
		}
	}
	
	private boolean produtoExiste(Carrinho carrinho, Long id) {
		for (ItemCompra itemCompra : carrinho.getItens()) {
			if(itemCompra.getProduto().getId() == id) {
				return true;
			}
		}
		return false;

	}

	@Override
	public void finalizar(Carrinho carrinho, Pessoa pessoa) {
		
		double total = 0.0;
		
		for (ItemCompra itemCompra : carrinho.getItens()) {
			total += itemCompra.getValorItem();
		}

		Compra compra = new Compra();
		compra.setData(new Date());
		compra.setPessoa(pessoa);
		compra.setValor(total);

		compraRepositorio.save(compra);
		
		ItemCompra itemTeste = carrinho.getItens().get(0);
		
		
		itemTeste.setCompra(compra);
		itemTeste.calcularTotal();
		
		itemCompraRepositorio.save(itemTeste);
		
		
		
	}

	@Override
	public List<Compra> listarCompras() {
		return compraRepositorio.findAll();
	}

}


