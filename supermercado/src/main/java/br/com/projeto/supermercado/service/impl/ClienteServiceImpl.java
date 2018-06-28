package br.com.projeto.supermercado.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.supermercado.model.Pessoa;
import br.com.projeto.supermercado.repository.PessoaRepositorio;
import br.com.projeto.supermercado.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private PessoaRepositorio clienteRepositorio;
	
	@Override
	public void adicionar(Pessoa pessoa) {
		pessoa.setPapel("CLIENTE");
		clienteRepositorio.save(pessoa);
	}

	@Override
	public void editar(Pessoa pessoa) {
		clienteRepositorio.save(pessoa);
		
	}

	@Override
	public void remover(Long id) {
		clienteRepositorio.delete(id);
		
	}

	@Override
	public List<Pessoa> listar() {
		return clienteRepositorio.findAll();
	}

	@Override
	public Pessoa buscarPorEmail(String email) {
		return clienteRepositorio.findByEmail(email);
	}
	
}


