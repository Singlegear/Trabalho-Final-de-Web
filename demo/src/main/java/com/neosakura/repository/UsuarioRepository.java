package com.neosakura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosakura.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByLogin(String login);
}
