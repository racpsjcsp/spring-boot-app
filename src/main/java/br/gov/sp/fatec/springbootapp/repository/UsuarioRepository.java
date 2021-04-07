package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public List<Usuario> findByNomeContainsIgnoreCase(String nome);

    public Usuario findByNome(String nome);
    public Usuario findByNomeOrNickname(String nome, String nickname);
    public Usuario findByNomeAndEmail(String nome, String email);

    public List<Usuario> findByPostsTitulo(String post);

}