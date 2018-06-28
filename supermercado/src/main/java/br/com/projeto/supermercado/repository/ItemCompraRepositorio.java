package br.com.projeto.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.supermercado.model.ItemCompra;

@Repository
public interface ItemCompraRepositorio extends JpaRepository<ItemCompra, Long>{

}
