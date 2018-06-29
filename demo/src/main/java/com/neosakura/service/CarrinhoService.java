package com.neosakura.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosakura.model.Carrinho;
import com.neosakura.model.Produto;
import com.neosakura.model.Usuario;
import com.neosakura.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository cr;
	
	@Autowired
	private ProdutoService ps;
	
	public void add(Carrinho carrinho) {
		cr.save(carrinho);
	}
	
	public void saveInCarrinho(List<Produto> produtos, Usuario user) {
		for (Produto produto : produtos) {
			Carrinho carrinho = new Carrinho();
			carrinho.setUserid(user.getId());
			carrinho.setPnome(produto.getNome());
			carrinho.setQtd(produto.getQtd());
			carrinho.setPreco(produto.getPreco());
			cr.save(carrinho);
		}
	}
	
	public void removeById(Long id) {
		cr.deleteById(id);
	}
	
	public Carrinho getById(Long id) {
		return cr.getOne(id);
	}
	
	public List<Carrinho> show(){
		return cr.findAll();
	}
	
	public List<Carrinho> showHistorico(Long id){
		List<Carrinho> carrinho = cr.findAll();
		List<Carrinho> meucarrinho = new ArrayList<Carrinho>();
		for (Carrinho carrinhoaux : carrinho) {
			if(carrinhoaux.getUserid() == id) {
				meucarrinho.add(carrinhoaux);
			}
		}
		//carrinho.clear();
		return meucarrinho;
	}
	
	//Carrinho Temporario
	public List<Produto> push(List<Produto> produtos, Long id){// Adiciona ao ArrayList de carrinho e remove do banco
		Produto pr = ps.getbyid(id);
		if(pr.getQtd() == 0) {
			return produtos;
		}
		for (Produto produto : produtos) {//Se já adicionei um antes apenas incremente a qtd
			if(produto.getId() == id) {
				produto.setQtd(produto.getQtd() + 1);
				pr.setQtd(pr.getQtd() - 1);
				ps.add(pr);
				return produtos;
			}
		}
		pr.setQtd(pr.getQtd() - 1);
		ps.add(pr);//sobreescreve no banco
		pr.setQtd(1);
		produtos.add(pr);
		System.out.println("okokokokok");
		return produtos;
	}
	
	public List<Produto> remove(List<Produto> produtos, Long id){//remove produto do carrinho e devolve ao banco
		Produto pr = ps.getbyid(id);
		for (Produto produto : produtos) {
			if(produto.getId() == id) {
				if(produto.getQtd() > 0) {
					produto.setQtd(produto.getQtd() - 1);
					pr.setQtd(pr.getQtd() + 1);
					ps.add(pr);
			
				}
				if(produto.getQtd() == 0) {
					produtos.remove(produto);
					return produtos;
				}
			}
		}
		return produtos;
	}
	
	public List<Produto> cancelar(List<Produto> produtos, Long id){// função auxiliar para devolver os produtos ao banco
		Produto pr = ps.getbyid(id);
		for (Produto produto : produtos) {
			if(produto.getId() == id) {
				pr.setQtd(pr.getQtd() + produto.getQtd());
				produto.setQtd(0);
				produtos.remove(produto);
				ps.add(pr);
				return produtos;
			}
		}
		return produtos;
	}
	
	public List<Produto> cancelarall(List<Produto> prod ,List<Produto> produtos){
		for (Produto produto : prod) {
			produtos = cancelar(produtos, produto.getId());//devolver ao banco
		}
		return produtos;
	}
	
	public double value(List<Produto> produtos) {
		double value = 0;
		for (Produto produto : produtos) {
			value += produto.getQtd() * produto.getPreco();
		}
		return value;
	}
}