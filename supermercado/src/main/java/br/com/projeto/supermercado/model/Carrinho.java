package br.com.projeto.supermercado.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	
	private List<ItemCompra> itens = new ArrayList<ItemCompra>();

	public List<ItemCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
	}

}
