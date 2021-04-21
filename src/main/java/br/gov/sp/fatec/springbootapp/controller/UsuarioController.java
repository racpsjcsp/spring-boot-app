package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Usuario cadastraNovoUsuario(@RequestBody Usuario usuario) {
		return usuarioService.criarUsuario(usuario.getNome(), usuario.getEmail(), usuario.getNickname());
	}
	
	
	
//	@GetMapping
//    public List<Usuario> buscarTodosPosts() {
//        return usuarioService.buscarTodosPosts();
//    }
//	
//	@GetMapping
//    public List<Usuario> buscarTodosComentarios() {
//        return usuarioService.buscarTodosComentarios();
//    }
}