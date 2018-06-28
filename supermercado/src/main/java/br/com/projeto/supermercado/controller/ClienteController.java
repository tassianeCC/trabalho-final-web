package br.com.projeto.supermercado.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.projeto.supermercado.model.Carrinho;
import br.com.projeto.supermercado.model.Pessoa;
import br.com.projeto.supermercado.model.Produto;
import br.com.projeto.supermercado.service.CarrinhoService;
import br.com.projeto.supermercado.service.ClienteService;
import br.com.projeto.supermercado.service.ProdutoService;
import br.com.projeto.supermercado.utils.UtilsCarrinho;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CarrinhoService carrinhoService;

	@Autowired
	private ClienteService clienteService;
	
	
	
	@GetMapping("/carrinho")
	public String carrinhoCompras(Model model, HttpServletRequest request) {
		Carrinho carrinho = UtilsCarrinho.getCarrinhoDaSession(request);
		model.addAttribute("carrinho", carrinho);

		return "carrinho";
	}

	@GetMapping("/add/{id}")
	public String carrinhoCompras(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		Produto produto = produtoService.buscar(id);
		
		if(produto  != null) {
			carrinhoService.adicionarItem(UtilsCarrinho.getCarrinhoDaSession(request), produto);
		}

		redirectAttributes.addFlashAttribute("SUCESSO", "O produto foi excluido com sucesso!");
		return "redirect:/cliente/carrinho";		
	}

	@GetMapping("/carrinho/remove/{id}")
	public String removerItemCarrinho(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("SUCESSO", "O item foi excluido com sucesso!");
		
		carrinhoService.removerItem(UtilsCarrinho.getCarrinhoDaSession(request), id);
		
		return "redirect:/cliente/carrinho";		
	}

	@GetMapping("/carrinho/incrementar/{id}")
	public String incrementarQuantidadeCarrinho(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		carrinhoService.incrementarQuantidade(UtilsCarrinho.getCarrinhoDaSession(request), id);
		
		return "redirect:/cliente/carrinho";		
	}

	@GetMapping("/carrinho/decrementar/{id}")
	public String decrementarQuantidadeCarrinho(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("SUCESSO", "O item foi excluido com sucesso!");
		
		carrinhoService.decrementarQuantidade(UtilsCarrinho.getCarrinhoDaSession(request), id);
		
		return "redirect:/cliente/carrinho";		
	}
	
	
	
	@GetMapping("/finalizar")
	public String save(@ModelAttribute("carrinho") Carrinho carrinho, HttpServletRequest request, Authentication authentication) {
		Pessoa cliente = clienteService.buscarPorEmail(authentication.getName());
		
		carrinhoService.finalizar(UtilsCarrinho.getCarrinhoDaSession(request), cliente);
		
		UtilsCarrinho.removeCarrinhoDaSession(request);
		
		return "redirect:/cliente/historico";
		
	}	
	
	@GetMapping("/historico")
	public String historicoCompras(Model model) {
		
		model.addAttribute("compras", carrinhoService.listarCompras());
		
		return "historico";
	}

}
