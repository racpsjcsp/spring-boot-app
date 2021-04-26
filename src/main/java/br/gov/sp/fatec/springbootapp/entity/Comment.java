package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "comentario")
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@JsonView(View.CommentResumo.class)
	@Column(name = "conteudo")
    private String conteudo;
    
	@JsonView(View.CommentResumo.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post")
    @JsonIgnore
    private Post post;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
}
