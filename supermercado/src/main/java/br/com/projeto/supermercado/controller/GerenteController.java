package br.com.projeto.supermercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.projeto.supermercado.model.Produto;
import br.com.projeto.supermercado.service.ProdutoService;

@Controller
@RequestMapping("/gerencia")
public class GerenteController {
	
	@Autowired
	private ProdutoService produtoService; 
	
	@GetMapping("/produtos")
	public String paginaProdutos(Model model) {
		List<Produto> produtos = produtoService.listar();
		
		model.addAttribute("produtos", produtos);
		
		return "produtos";
	}
	
	@GetMapping("/produtos/cadastrar")
	public String paginaCadastro() {		
		return "cadastro_produtos";
	}
	
	@PostMapping("/produtos/cadastrar")
	public String cadastrarProduto(@ModelAttribute Produto produto, RedirectAttributes redirectAttributes) {
		
		produtoService.adicionar(produto);
		
		redirectAttributes.addFlashAttribute("SUCESSO", "Produto cadastrado com sucesso!");
		
		return "redirect:/gerencia/produtos";
	}

	@GetMapping("/produtos/exluir/{id}")
	public String paginaCadastro(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		produtoService.remover(id);
		
		redirectAttributes.addFlashAttribute("SUCESSO", "O produto foi excluido com sucesso!");
		
		return "redirect:/gerencia/produtos";
	}
	
}
