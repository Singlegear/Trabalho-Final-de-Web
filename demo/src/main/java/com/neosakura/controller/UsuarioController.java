package com.neosakura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neosakura.model.Usuario;
import com.neosakura.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService us;
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@RequestMapping("/login/io")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logoutuser() {
		ModelAndView mv = new ModelAndView("/produto/home");
		return mv;
	}
	
	@RequestMapping("/registrar")
	public ModelAndView registrar() {
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Usuario user) {
		us.add(user);
		ModelAndView mv = new ModelAndView("redirect:/produto/home");
		return mv;
	}
	
	public Usuario returnUser() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		Usuario usuario = us.getByLogin(user.getUsername());
		return usuario;
	}
	
}
