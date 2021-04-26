package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.entity.Comment;
import br.gov.sp.fatec.springbootapp.service.CommentService;


@RestController
@RequestMapping(value = "/comment")
@CrossOrigin
public class CommentController {

	@Autowired
	private CommentService commentService;
		
	@GetMapping
	public List<Comment> buscarTodosComments() {
		return commentService.buscarTodosComments();
	}

	@JsonView(View.CommentResumo.class)
	@GetMapping(value = "/usuario")
    public List<Comment> buscarCommentsPorUsuario(@RequestParam(value="usuario") String nome) {
        return commentService.buscarCommentPorUsuario(nome);
    }
	
	@JsonView(View.CommentResumo.class)
	@GetMapping(value = "/titulo")
    public List<Comment> buscarCommentsPorTituloPost(@RequestParam(value="titulo") String titulo) {
        return commentService.buscarCommentPorTituloPost(titulo);
    }
	
//	@JsonView(View.CommentResumo.class)
//	@GetMapping(value = "/usuario-post")
//    public List<Comment> buscarCommentsPorUsuarioPost(@RequestParam(value="usuario") String nome) {
//        return commentService.buscarCommentPorUsuario(nome);
//    }

}
