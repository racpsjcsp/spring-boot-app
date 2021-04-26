package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import br.gov.sp.fatec.springbootapp.entity.Usuario;

public interface UsuarioService {

	public Usuario criarUsuario(String nome, String email, String nickname);
	
	public List<Usuario> buscarTodosUsuarios();
	
	public Usuario buscarUsuarioPorId(Long id);
	
	public Usuario buscarUsuarioPorNome(String nome);
	
	public Usuario buscarUsuarioPorNickname(String nickname);

	public Usuario buscarUsuarioPorEmail(String email);

	public String deletarUsuario(Long id);

//	public Usuario alterarUsuario(Long id);

	public Usuario alterarUsuario(Long id, String nome, String nickname, String email);
}
