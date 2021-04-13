package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Usuario;

public interface UsuarioService {

	public Usuario criarUsuario(String nome, String email, String nickname);

}
