package br.com.projeto.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.supermercado.model.Compra;

@Repository
public interface CompraRepositorio extends JpaRepository<Compra, Long>{

}
