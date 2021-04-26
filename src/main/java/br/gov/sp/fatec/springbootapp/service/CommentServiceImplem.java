package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Comment;
import br.gov.sp.fatec.springbootapp.entity.Post;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.CommentRepository;
import br.gov.sp.fatec.springbootapp.repository.PostRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import exception.RegistroNaoEncontradoException;

@Service
public class CommentServiceImplem implements CommentService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository commentRepo;	
	
	@Transactional
	public Comment criarComment(String conteudo, String nome, String titulo) {

		Usuario usuario = usuarioRepo.buscaUsuarioPorNome(nome);
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setNickname("");
			
			usuarioRepo.save(usuario);
		}

		Post post = postRepo.findPostByTitulo(nome);
		if (post == null) {
			post = new Post();
			post.setTitulo(titulo);
			post.setUsuario(usuario);
			
			postRepo.save(post);
		}
		
		Comment comment = new Comment();
		comment.setConteudo(conteudo);
		comment.setUsuario(usuario);
		comment.setPost(post);

		commentRepo.save(comment);
		
		return comment;
	}

	@Override
	public List<Comment> buscarTodosComments() {
		return commentRepo.findAll();
	}

	@Override
	public List<Comment> buscarCommentPorUsuario(String nome) {
		
		List<Comment> comments = commentRepo.buscaCommentPorUsuario(nome);
		if(!comments.isEmpty()) {
			return comments;
		}
		
		throw new RegistroNaoEncontradoException("nenhum comentário encontrado");
	}

	@Override
	public List<Comment> buscarCommentPorTituloPost(String titulo) {
		
		List<Comment> comments = commentRepo.buscaCommentPorTituloPost(titulo);
		if(!comments.isEmpty()) {
			return comments;
		}
		
		throw new RegistroNaoEncontradoException("nenhum comentário encontrado");
	}

}
