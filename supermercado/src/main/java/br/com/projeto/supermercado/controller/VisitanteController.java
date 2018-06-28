package br.com.projeto.supermercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.projeto.supermercado.model.Pessoa;
import br.com.projeto.supermercado.model.Produto;
import br.com.projeto.supermercado.service.ClienteService;
import br.com.projeto.supermercado.service.ProdutoService;

@Controller
public class VisitanteController {
	
	@Autowired
	private ClienteService clienteService; 

	@Autowired
	private ProdutoService produtoService; 
	
	@GetMapping("/")
	public String paginaInicial(Model model) {
		List<Produto> produtos = produtoService.listar();
		
		model.addAttribute("produtos", produtos);
		
		return "index";
		
	}
	
	@GetMapping("/cadastro")
	public String paginaCadastro() {
		return "cadastro_cliente";
	}
	
	@PostMapping("/cadastro")
	public String cadastrarCliente(@ModelAttribute Pessoa pessoa, RedirectAttributes redirectAttributes) {
		
		clienteService.adicionar(pessoa);
		
		redirectAttributes.addFlashAttribute("SUCESSO", "Cadastro realizado com sucesso. Faça seu login!");
		
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String paginaLogin() {
		return "login";
	}

}
