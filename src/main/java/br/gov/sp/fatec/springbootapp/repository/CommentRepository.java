package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query("select c from Comment c inner join c.usuario u where u.nome = ?1")
	public List<Comment> buscaCommentPorUsuario(String nome);
	
	@Query("select c from Comment c inner join c.post p where p.titulo = ?1")
	public List<Comment> buscaCommentPorTituloPost(String titulo);
	
}