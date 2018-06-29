package com.neosakura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosakura.model.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
	
}
