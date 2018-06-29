package com.neosakura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosakura.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
