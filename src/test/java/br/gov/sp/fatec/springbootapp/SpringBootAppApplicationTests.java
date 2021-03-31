package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.entity.Post;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.repository.PostRepository;


@SpringBootTest
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;
    private PostRepository postRepo;

	@Test
	void contextLoads() {
    }
    
    @Test
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
        // post.setUsuario(usuario);
        postRepo.save(post);

        assertNotNull(post.getId());
        // assertEquals("Titulo do Post",usuario.getPosts().iterator().next().getTitulo());
    }

}
