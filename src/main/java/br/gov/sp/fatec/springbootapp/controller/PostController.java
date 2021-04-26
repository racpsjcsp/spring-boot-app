package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.entity.Post;
import br.gov.sp.fatec.springbootapp.service.PostService;


@RestController
@RequestMapping(value = "/post")
@CrossOrigin
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
    public List<Post> buscarTodosPosts() {
        return postService.buscarTodosPosts();
    }

	@JsonView(View.PostResumo.class)
	@GetMapping(value = "/titulo")
    public Post buscarPorTitulo(@RequestParam(value="titulo") String titulo) {
        return postService.buscarPostPorTitulo(titulo);
    }
	
	@JsonView(View.PostResumo.class)
	@GetMapping(value = "/usuario")
    public List <Post> buscarPorUsuario(@RequestParam(value="usuario") String nome) {
        return postService.buscarPostPorUsuario(nome);
    }
	
//	@PostMapping
//	public Post cadastraNovoPost(@RequestBody Post post) {
//		return postService.criarPost();
//	}
}
