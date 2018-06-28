package br.com.projeto.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.supermercado.model.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{

}
