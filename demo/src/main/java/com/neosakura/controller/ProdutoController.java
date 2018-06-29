package com.neosakura.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neosakura.model.Carrinho;
import com.neosakura.model.Produto;
import com.neosakura.model.Usuario;
import com.neosakura.service.CarrinhoService;
import com.neosakura.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService ps;
	
	@Autowired
	private CarrinhoService cs;
	
	@Autowired
	private UsuarioController uc;
	
	public List<Produto> carrinho = new ArrayList<Produto>();
	
	@RequestMapping("/historico")
	public ModelAndView historico() {
		ModelAndView mv = new ModelAndView("car");
		Usuario user = uc.returnUser();
		List<Carrinho> c = cs.showHistorico(user.getId());
		mv.addObject("historico", c);
		return mv;
	}
	
	@RequestMapping("/vizualizar/{id}")
	public ModelAndView vizualizarproduto(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("vizualizarproduto");
		mv.addObject("produto", ps.getbyid(id));
		return mv;
	}
	
	// Operações com o carrinho
	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("carrinho");
		return mv;
	}
	
	@RequestMapping("/carrinho/{id}")
	public ModelAndView carrinho(@PathVariable Long id) {
		carrinho = cs.push(carrinho, id);
		ModelAndView mv = new ModelAndView("carrinho");
		mv.addObject("produtos", carrinho);
		return mv;
	}
	
	@GetMapping("/carrinho/listar")
	public ModelAndView litarcarrinho() {
		ModelAndView mv = new ModelAndView("redirect:/produto/carrinho");
		mv.addObject("produtos",carrinho);
		return mv;
	}
	
	@RequestMapping("/carrinho/remover/{id}")
	public ModelAndView removeCarrinho(@PathVariable Long id) {
		carrinho = cs.remove(carrinho, id);
		ModelAndView mv = new ModelAndView("carrinho");
		mv.addObject("produtos", carrinho);
		return mv;
	}
	
	@RequestMapping("/carrinho/cancelar")
	public ModelAndView pcancelarCarrinho() {
		carrinho = cs.cancelarall(ps.show(), carrinho);//passa os produtos do banco e o carrinho
		ModelAndView mv = new ModelAndView("carrinho");//para que o carrinho possa devolver os produtos
		mv.addObject("produtos", carrinho);			   // ao banco
		return mv;
	}
	
	@GetMapping("/carrinho/salvar")
	public ModelAndView salvaCarrinho() {
		
		cs.saveInCarrinho(carrinho, uc.returnUser());
		ModelAndView mv = new ModelAndView("redirect:/produto/home");
		carrinho.clear();
		return mv;
	}
	
	
	// Transição das paginas HTML
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("promocoes", ps.showPromocoes());
		return mv;
	}
	
	@GetMapping("/books")
	public ModelAndView books() {
		ModelAndView mv = new ModelAndView("books");
		mv.addObject("mangas", ps.showBooks());
		mv.addObject("hqs", ps.showHq());
		return mv;
	}
	
	@RequestMapping("/accessories")
	public ModelAndView accessories() {
		ModelAndView mv = new ModelAndView("accessories");
		mv.addObject("colares", ps.showColares());
		mv.addObject("pulseiras", ps.showPulseiras());
		mv.addObject("aneis", ps.showAneis());
		return mv;
	}
	
	@RequestMapping("/bedroom")
	public ModelAndView bedroom() {
		ModelAndView mv = new ModelAndView("bedroom");
		mv.addObject("quartoposter", ps.showPoster());
		mv.addObject("quartoquadro", ps.showQuadros());
		return mv;
	}
	
	@RequestMapping("/used")
	public ModelAndView used() {
		ModelAndView mv = new ModelAndView("used");
		mv.addObject("avulsos", ps.showAvulsos());
		mv.addObject("colecoes", ps.showColecoes());
		return mv;
	}
	
	// CRUD de Produtos
	
	@RequestMapping("/cadastrarproduto")
	public ModelAndView formulariocadastroproduto() {
		ModelAndView mv = new ModelAndView("produto");
		mv.addObject("produto", new Produto());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarproduto(Produto produto, @RequestParam(value= "imagem") MultipartFile imagem) {
//		Produto resultadobusca = ps.getbyid(produto.getId());
//		if(resultadobusca.getNome() != produto.getNome())
		ps.add(produto, imagem);
		ModelAndView mv = new ModelAndView("redirect:/produto/cadastrarproduto");
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView salvarprodutoatualizado(Produto produto, @RequestParam(value= "imagem") MultipartFile imagem) {
		ps.add(produto, imagem);
		ModelAndView mv = new ModelAndView("redirect:/produto/listar");
		return mv;
	}
	
	@GetMapping("/listar")
	public ModelAndView listarproduto() {
		List<Produto> produtos = ps.show();
		ModelAndView mv = new ModelAndView("listar_produtos");
		mv.addObject("todosOsProdutos", produtos);
		return mv;
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizarproduto(@PathVariable Long id) {
		Produto produto = ps.getbyid(id);
		ModelAndView mv = new ModelAndView("update_produtos");
		mv.addObject("produto", produto);
		return mv;
	}
	
	@RequestMapping("/remover/{id}")
	public ModelAndView removerproduto(@PathVariable Long id){
		ps.removeById(id);
		ModelAndView mv = new ModelAndView("redirect:/produto/listar");
		return mv;
	}
	
	
}
