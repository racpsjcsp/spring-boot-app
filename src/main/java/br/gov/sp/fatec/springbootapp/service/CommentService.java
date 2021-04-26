package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import br.gov.sp.fatec.springbootapp.entity.Comment;

public interface CommentService {

	public Comment criarComment(String conteudo, String usuario, String titulo);
	
	public List<Comment> buscarTodosComments();

	public List<Comment> buscarCommentPorUsuario(String nome);

	public List<Comment> buscarCommentPorTituloPost(String titulo);
	
}
