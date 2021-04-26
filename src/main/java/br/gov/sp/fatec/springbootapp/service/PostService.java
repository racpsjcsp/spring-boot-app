package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import br.gov.sp.fatec.springbootapp.entity.Post;

public interface PostService {

	public Post criarPost(String titulo, String conteudo, String nome, String email);

	public List<Post> buscarTodosPosts();

	public Post buscarPostPorTitulo(String titulo);

	public List<Post> buscarPostPorUsuario(String nome);

//	public Post criarPost();

}
