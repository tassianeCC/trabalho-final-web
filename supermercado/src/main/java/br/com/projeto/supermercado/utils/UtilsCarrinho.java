package br.com.projeto.supermercado.utils;

import javax.servlet.http.HttpServletRequest;

import br.com.projeto.supermercado.model.Carrinho;

public class UtilsCarrinho {

	public static Carrinho getCarrinhoDaSession(HttpServletRequest request) {
		Carrinho carrinho = (Carrinho) request.getSession().getAttribute("meuCarrinho");
		if (carrinho == null) {
			carrinho = new Carrinho();
			request.getSession().setAttribute("meuCarrinho", carrinho);
		}

		return carrinho;
	}

	public static void removeCarrinhoDaSession(HttpServletRequest request) {
		request.getSession().removeAttribute("meuCarrinho");
	}
	
	

}