package com.neosakura.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neosakura.model.Produto;
import com.neosakura.repository.ProdutoRepository;
import com.neosakura.util.SalvarImagemUtil;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository ps;
	
	public void add(Produto produto, MultipartFile imagem) {
		String caminho = "images/" + produto.getNome() + ".png";
		SalvarImagemUtil.salvarImagem(caminho,imagem);
		
		ps.save(produto);
	}
	
	public void add(Produto produto) {
		ps.save(produto);
	}
	
	public void remove(Produto produto) {
		ps.delete(produto);
	}
	
	public void removeById(Long id) {
		ps.deleteById(id);
	}
	
	public Produto getbyid(Long id) {
		return ps.getOne(id);
	}
	
	public List<Produto> show(){
		return ps.findAll();
	}
	
	// Pagina Books listagem de mangás e hq's
	public List<Produto>showBooks(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals("manga")) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
	
	public List<Produto> showHq(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals("hq")) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
	
	//Pagina Bedroom listagem de Posters e Quadros
	public List<Produto> showPoster(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals("quartop")) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
	
	public List<Produto> showQuadros(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals("quartoq")) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
	
	//Pagina Used listagem de Coleções e volumes avulsos usados
	public List<Produto> showAvulsos(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals("usadoa")) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
	
	public List<Produto> showColecoes(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals("usadoc")) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
	
	// Pagina index listagem de todos os produtos em promoção
	public List<Produto> showPromocoes(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.isPromocao()) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
	
	//Pagina accessories listagem de colares, pulseiras/braceletes e aneis
	public List<Produto> showColares(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals("acessorio1")) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
	
	public List<Produto> showPulseiras(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals("acessorio2")) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
	
	public List<Produto> showAneis(){
		List<Produto> produtos = show();
		List<Produto> prod = new ArrayList<Produto>();
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals("acessorio3")) {
				prod.add(produto);
			}
		}
		produtos.clear();
		return prod;
	}
}
