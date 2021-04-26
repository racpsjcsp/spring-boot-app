package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Post;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.PostRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import exception.RegistroNaoEncontradoException;


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

	@Override
	public List<Post> buscarTodosPosts() {
		// TODO Auto-generated method stub
		return postRepo.findAll();
	}

	@Override
	public Post buscarPostPorTitulo(String titulo) {
		Post post = postRepo.findPostByTitulo(titulo);
		if(post != null) {
			return post;
		}
		
		throw new RegistroNaoEncontradoException("Post não encontrado");
	}
	
	@Override
	public List<Post> buscarPostPorUsuario(String nome) {
		List<Post> posts = postRepo.buscaPostsByUsuario(nome);
		if(!posts.isEmpty()) {
			return posts;
		}
		
		throw new RegistroNaoEncontradoException("nenhum post encontrado");
	}

}
