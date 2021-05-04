package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Post;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public List<Usuario> findByNomeContainsIgnoreCase(String nome);

//    public Optional<Usuario> findByNome(String nome);
    public Usuario findByNome(String nome);
    public Usuario findByNomeOrNickname(String nome, String nickname);
    public Usuario findByNomeAndEmail(String nome, String email);
    public Usuario findByNickname(String nickname);
    public Usuario findByEmail(String email);    

    public List<Usuario> findByPostsTitulo(String post);
    
    @Query("select u from Usuario u where u.nome = ?1")
    public Usuario buscaUsuarioPorNome(String nome);

    @Query("select u from Usuario u where u.nome = ?1 and u.email = ?2")
    public Usuario buscaUsuarioPorNomeEEmail(String nome, String email);
    
    @Query("select p from Usuario u inner join u.posts p where u.nome = ?1")
    public List<Post> buscaPorPostsDoUsuario(String nome);
    
    @Query("select u from Usuario u where u.id = ?1")
    public Usuario buscaUsuarioPorId(Long id);
    
//    @Query("select u from Usuario u where u.role = ?1")
    public Usuario findByRole(String role);
    
}