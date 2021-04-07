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
    void testaInsercaoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Augusto");
        usuario.setEmail("augusto@gmail.com");
        usuario.setNickname("guto");
        usuarioRepo.save(usuario);

        assertEquals("Augusto", usuario.getNome());
    }

    void testaUsuario() {
        Usuario usuario = usuarioRepo.findById(1L).get();
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
    void testaPost2() {
        Post post = new Post();
        Usuario usuario = usuarioRepo.findById(1L).get();

        // assertEquals("Conteudo do Post...", usuario.getPosts().iterator().next().getConteudo());
        assertEquals("Conteudo do Post", usuario.getPosts().iterator().next().getConteudo());
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
        commentRepo.save(comment);

        post.setComments(new HashSet<Comment>());
        post.getComments().add(comment);
        postRepo.save(post);

        assertNotNull(comment.getId());
        assertNotNull(post.getComments().iterator().next().getId());
    }

    // @Test
    // @Transactional
    // @Rollback
    // void testaComment3() {
    //     Post post = postRepo.findById(1L).get();
    //     Usuario usuario = usuarioRepo.findById(1L).get();
    //     Comment comment = new Comment();

    //     assertEquals("Conteudo do Comentario...", post.getComments().iterator().next().getConteudo());
    // }

     @Test
    // @Transactional
    // @Rollback
    void testaBuscaUsuarioNomeContains() {
        
        List<Usuario> usuarios = usuarioRepo.findByNomeContainsIgnoreCase("R");
        assertFalse(usuarios.isEmpty());
    }

    @Test
    // @Transactional
    // @Rollback
    void testaBuscaUsuarioNome() {
        List<Usuario> usuarios = usuarioRepo.findByNomeContainsIgnoreCase("Rafael");

        assertFalse(usuarios.isEmpty());
    }

    @Test
    // @Transactional
    // @Rollback
    void testaBuscaUsuarioNomeEmail() {
        Usuario usuario = usuarioRepo.findByNomeAndEmail("Rafael", "rafa@gmail.com");

        assertNotNull(usuario);
    }

    @Test
    // @Transactional
    // @Rollback
    void testaBuscaNomePost() {
        List<Usuario> usuarios = usuarioRepo.findByPostsTitulo("Meu Post");

        assertFalse(usuarios.isEmpty());
    }

}
