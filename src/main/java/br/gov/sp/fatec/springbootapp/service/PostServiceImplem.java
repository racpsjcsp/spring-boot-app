package br.gov.sp.fatec.springbootapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Post;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.PostRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;


@Service("postService")
public class PostServiceImplem implements PostService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	@Transactional
	public Post criarPost(String titulo, String conteudo, String nome, String email) {

		Usuario usuario = usuarioRepo.buscaUsuarioPorNomeEEmail(nome, email);
		
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setNickname("");
			usuarioRepo.save(usuario);
		}
		
		
		Post post = new Post();		
		post.setTitulo(titulo);
		post.setConteudo(conteudo);
		post.setUsuario(usuario);
		postRepo.save(post);
		
		return post;
	}
	

	
	

}