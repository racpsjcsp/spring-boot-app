package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioService.buscarTodosUsuarios();
    }
	
	@GetMapping(value = "/{id}")
    public Usuario buscarPorId(@PathVariable("id") Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }
	
//	@JsonView(View.UsuarioResumo.class)
	@GetMapping(value = "/nome")
    public Usuario buscarPorNome(@RequestParam(value="nome") String nome) {
        return usuarioService.buscarUsuarioPorNome(nome);
    }
	
	@GetMapping(value = "/nickname")
	public Usuario buscarPorNickname(@RequestParam(value="nickname") String nickname) {
        return usuarioService.buscarUsuarioPorNickname(nickname);
    }
	
	@GetMapping(value = "/email")
	public Usuario buscarPorEmail(@RequestParam(value="email") String email) {
        return usuarioService.buscarUsuarioPorEmail(email);
    }
	
	@PostMapping
	public ResponseEntity<Usuario> cadastraNovoUsuario(@RequestBody Usuario usuario,
			UriComponentsBuilder uriComponentsBuilder) {
		usuario = usuarioService.criarUsuario(
				usuario.getNome(),
				usuario.getEmail(),
				usuario.getNickname()
				);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(
				uriComponentsBuilder.path(
						"/usuario/" + usuario.getId()).build().toUri());
		return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
	}
	
//	@PutMapping("/altera-nome/{id}")
//	public Usuario alteraNomeUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
//		return usuarioService.alterarUsuario(
//				id,
//				usuario.getNome(),
//				usuario.getNickname(),
//				usuario.getEmail()
//				);	
//	}
	
//	@DeleteMapping(value = "/usuario-deleta/{id}")
//	public String deletaUsuario(@PathVariable Long id) {
//		return usuarioService.deletarUsuario(id);		
//	}
	
	@PutMapping("/altera-nome/{id}")
	public ResponseEntity<Usuario> alteraNomeUsuario(
			@PathVariable Long id,
			@RequestBody Usuario usuario,
			UriComponentsBuilder uriComponentsBuilder) {
		
		usuario = usuarioService.alterarUsuario(
				id,
				usuario.getNome(),
				usuario.getNickname(),
				usuario.getEmail()
				);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		responseHeaders.setLocation(
				uriComponentsBuilder.path(
						"/usuario/" + usuario.getId()).build().toUri());
		
		return ResponseEntity.ok().headers(responseHeaders).body(usuario);// <Usuario>(usuario, responseHeaders, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/usuario-deleta/{id}")
	public ResponseEntity<String> deletaUsuario(
			@PathVariable Long id,
			UriComponentsBuilder uriComponentsBuilder) {
		
		usuarioService.deletarUsuario(id);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		return ResponseEntity.accepted().headers(responseHeaders).body("Usuário deletado com sucesso");
	}

}