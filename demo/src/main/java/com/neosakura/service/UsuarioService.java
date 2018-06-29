package com.neosakura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neosakura.model.Usuario;
import com.neosakura.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository ur;
	
	public void add(Usuario usuario) {
		if(!(usuario.getPassword().equals("") || usuario.getNome().equals("") || usuario.getUsername().equals(""))) {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			ur.save(usuario);
		}
		
	}
	
	public void remove(long id) {
		ur.deleteById(id);
	}
	
	public Usuario getById(long id) {
		return ur.getOne(id);
	}
	
	public Usuario getByLogin(String login) {
		return ur.findByLogin(login);
	}
	
	public List<Usuario> show(){
		List<Usuario> usuarios = ur.findAll();
		return usuarios;
	}
}
