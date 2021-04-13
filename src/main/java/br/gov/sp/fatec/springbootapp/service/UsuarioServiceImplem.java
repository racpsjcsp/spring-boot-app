package br.gov.sp.fatec.springbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImplem implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public Usuario criarUsuario(String nome, String email, String nickname) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setNickname(nickname);
		usuarioRepo.save(usuario);
		return usuario;
	}

}