package br.gov.sp.fatec.springbootapp.service;

import java.util.List;
import java.util.Optional;

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
	
	@Override
	public List<Usuario> buscarTodosUsuarios() {
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
		
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}
		
		throw new RuntimeException("Usuário não encontrado");
	}
	
	@Override
	public Usuario buscarUsuarioPorNome(String nome) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findByNome(nome); //usuarioRepo.findByNome(nome);
		
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}
		
		throw new RuntimeException("Usuário não encontrado");
	}
	
	@Override
	public Usuario buscarUsuarioPorNickname(String nickname) {
		Optional<Usuario> usuarioOpt = Optional.of(usuarioRepo.findByNickname(nickname));
		
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}
		
		throw new RuntimeException("Usuário não encontrado");
	}
	
	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		Optional<Usuario> usuarioOpt = Optional.of(usuarioRepo.findByEmail(email));
		
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}
		
		throw new RuntimeException("Usuário não encontrado");
	}

	@Override
	public String deletarUsuario(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
		
		if(usuarioOpt.isPresent()) {
			usuarioRepo.deleteById(id);
			return "Usuário deletado com sucesso";
		}
		
		throw new RuntimeException("usuário não encontrado");		
	}

	@Override
	public Usuario alterarUsuario(Long id, String nome, String nickname, String email) {
		Optional<Usuario> usuarioOpt = Optional.of(usuarioRepo.buscaUsuarioPorId(id)); //findById(id);
		
		if(usuarioOpt.isPresent()) {
			//usuarioRepo.buscaUsuarioPorId(id);
			
			usuarioOpt.get().setNome(nome);
			usuarioOpt.get().setNickname(nickname);
			usuarioOpt.get().setEmail(email);
			usuarioRepo.save(usuarioOpt.get());
			
			return usuarioOpt.get();
		}
		
		throw new RuntimeException("usuário não encontrado");
	}
	
}