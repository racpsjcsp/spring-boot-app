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
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@JsonView({View.PostResumo.class})
	@Column(name = "titulo")
    private String titulo;
    
	@JsonView({View.PostResumo.class})
	@Column(name = "conteudo")
    private String conteudo;
    
	@JsonView(View.PostResumo.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Comment> comments;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
    public Set<Comment> getComments() {
        return this.comments;
    }
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
