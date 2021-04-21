package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Post;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    
//     public List<Post> findByNomePostIgnoringCase(String titulo);
	public Post findPostByTituloAndConteudo(String titulo, String conteudo);
	
	public Post findPostByTitulo(String titulo);
	
	
	@Query("select p from Post p inner join p.usuario u where u.nome = ?1")
	public List<Post> buscaPostsByUsuario(String nome);
}