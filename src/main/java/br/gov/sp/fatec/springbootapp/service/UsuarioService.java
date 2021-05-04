package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.springbootapp.entity.Usuario;

public interface UsuarioService extends UserDetailsService {

	public Usuario criarUsuario(String nome, String email, String nickname, String role, String senha);
	
	public List<Usuario> buscarTodosUsuarios();
	
	public Usuario buscarUsuarioPorId(Long id);
	
	public Usuario buscarUsuarioPorNome(String nome);
	
	public Usuario buscarUsuarioPorNickname(String nickname);

	public Usuario buscarUsuarioPorEmail(String email);

	public String deletarUsuario(Long id);

	public Usuario alterarUsuario(Long id, String nome, String nickname, String email, String role, String senha);
	
	public Usuario buscarUsuarioPorRole(String role);
}
