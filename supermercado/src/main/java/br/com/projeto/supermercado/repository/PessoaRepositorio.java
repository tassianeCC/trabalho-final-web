package br.com.projeto.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.supermercado.model.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Long>{
	
	Pessoa findByEmail(String email);

}
