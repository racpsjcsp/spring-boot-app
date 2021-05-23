package br.gov.sp.fatec.springbootapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import exception.RegistroNaoEncontradoException;

@Service("usuarioService")
public class UsuarioServiceImplem implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private PasswordEncoder passEncoder;	
	
	@Override
	public Usuario criarUsuario(String nome, String email, String nickname, String role, String senha) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setNickname(nickname);
		usuario.setRole(role);
		usuario.setSenha(passEncoder.encode(senha));

		usuarioRepo.save(usuario);
		return usuario;
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public List<Usuario> buscarTodosUsuarios() {
		return usuarioRepo.findAll();
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN, USER')")
	public Usuario buscarUsuarioPorId(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
		
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}
		
		throw new RegistroNaoEncontradoException("Usuário não encontrado");
	}
	
	@Override
	@PreAuthorize("hasAnyRole('ADMIN, USER')")
	public Usuario buscarUsuarioPorNome(String nome) {
		Usuario usuarioOpt = usuarioRepo.findByNome(nome);
		
		if(usuarioOpt != null) {
			return usuarioOpt;
		}
		
		throw new RegistroNaoEncontradoException("Usuário não encontrado");
	}
	
	@Override
	@PreAuthorize("hasAnyRole('ADMIN, USER')")
	public Usuario buscarUsuarioPorNickname(String nickname) {
		Optional<Usuario> usuarioOpt = Optional.of(usuarioRepo.findByNickname(nickname));
		
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}
		
		throw new RegistroNaoEncontradoException("Usuário não encontrado");
	}
	
	@Override
	@PreAuthorize("hasAnyRole('ADMIN, USER')")
	public Usuario buscarUsuarioPorEmail(String email) {
		Optional<Usuario> usuarioOpt = Optional.of(usuarioRepo.findByEmail(email));
		
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}
		
		throw new RegistroNaoEncontradoException("Usuário não encontrado");
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deletarUsuario(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
		
		if(usuarioOpt.isPresent()) {
			usuarioRepo.deleteById(id);
			return "Usuário deletado com sucesso";
		}
		
		throw new RegistroNaoEncontradoException("usuário não encontrado");		
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Usuario alterarUsuario(Long id, String nome, String nickname, String email, String role, String senha) {
		Optional<Usuario> usuarioOpt = Optional.of(usuarioRepo.buscaUsuarioPorId(id)); //findById(id);
		
		if(usuarioOpt.isPresent()) {			
			usuarioOpt.get().setNome(nome);
			usuarioOpt.get().setNickname(nickname);
			usuarioOpt.get().setEmail(email);
			usuarioRepo.save(usuarioOpt.get());
			
			return usuarioOpt.get();
		}
		
		throw new RuntimeException("usuário não encontrado");
	}
	
	@Override	
	public Usuario buscarUsuarioPorRole(String role) {
		Optional<Usuario> usuarioOpt = Optional.of(usuarioRepo.findByRole(role));
		
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}
		
		throw new RegistroNaoEncontradoException("Usuário não encontrado");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepo.findByNome(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário "+ usuario +" não encontrado");
		}
		
		return User
				.builder()
				.username(username)
				.password(usuario.getSenha()).authorities(usuario.getRole()).build();
	}
}