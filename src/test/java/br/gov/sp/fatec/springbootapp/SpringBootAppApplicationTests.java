package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.entity.Post;
import br.gov.sp.fatec.springbootapp.entity.Comment;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.repository.CommentRepository;
import br.gov.sp.fatec.springbootapp.repository.PostRepository;

import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;


@SpringBootTest
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private CommentRepository commentRepo;

	@Test
	void contextLoads() {
    }
    
    @Test
    @Transactional
    @Rollback
    void testaInsercao() {
        Usuario usuario = new Usuario();
        usuario.setNome("Rafael");
        usuario.setEmail("rafael@gmail.com");
        usuario.setNickname("rafa");
        usuarioRepo.save(usuario);

        assertNotNull(usuario.getId());
    }

    @Test
    @Transactional
    @Rollback
    void testaPost() {
        Post post = new Post();
        Usuario usuario = usuarioRepo.findById(1L).get();
        post.setTitulo("Titulo do Post");
        post.setConteudo("Conteudo do Post");
        post.setUsuario(usuario);
        postRepo.save(post);

        assertNotNull(post.getId());

        assertNotNull(usuario.getPosts().iterator().next().getId());
    }

    @Test
    @Transactional
    @Rollback
    void testaComment1() {
        Post post = postRepo.findById(1L).get();
        Usuario usuario = usuarioRepo.findById(1L).get();
        Comment comment = new Comment();

        comment.setConteudo("Conteudo do Comment1");
        comment.setUsuario(usuario);
        comment.setPost(post);
        commentRepo.save(comment);

        assertNotNull(comment.getId());

        assertNotNull(post.getComments().iterator().next().getId());

    }


     @Test
     @Transactional
     @Rollback
    void testaComment2() {
        Post post = postRepo.findById(1L).get();
        Usuario usuario = usuarioRepo.findById(1L).get();
        Comment comment = new Comment();

        comment.setConteudo("Conteudo do Comment2");
        comment.setUsuario(usuario);
        // comment.setPost(post);
        commentRepo.save(comment);
        post.setComments(new HashSet<Comment>());
        post.getComments().add(comment);
        postRepo.save(post);

        assertNotNull(comment.getId());

        assertNotNull(post.getComments().iterator().next().getId());

        //assertEquals("Conteudo do Comment2", post.getComments().iterator().next().getConteudo());

    }
}
