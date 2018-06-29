package com.neosakura.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.neosakura.security.UserDetailsServiceImplementacao;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImplementacao userDetailsImplementacao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/produto/home").permitAll()
		.antMatchers("/produto/books").permitAll()
		.antMatchers("/produto/accessories").permitAll()
		.antMatchers("/produto/bedroom").permitAll()
		.antMatchers("/produto/used").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/registrar").permitAll()
		.antMatchers("/salvar").permitAll()
		.antMatchers("/produto/vizualizar").permitAll()
		.antMatchers("/produto/cadastrarproduto").hasRole("ADMIN")
		.antMatchers("/produto/listar").hasRole("ADMIN")
		.antMatchers("/produto/remover/{id}").hasRole("ADMIN")
		.antMatchers("/produto/atualizar/{id}").hasRole("ADMIN")
		.antMatchers("/produto/save").hasRole("ADMIN")
		.antMatchers("/produto/salvar").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login/io")
		.permitAll()
		
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/img/**");
	}
}
