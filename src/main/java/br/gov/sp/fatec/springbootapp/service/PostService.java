package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Post;

public interface PostService {

	public Post criarPost(String titulo, String conteudo, String nome, String email);

}
