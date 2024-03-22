package com.dansoft.redesocial.model;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
public class Postagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codigo", nullable = false, length = 11, unique = true)
	private String codigo;

	@Column(name = "texto", nullable = false, length = 255)
	private String texto;

	@ManyToOne
	@JoinColumn(name = "Usuario_id", referencedColumnName = "id", nullable = false)
	private Usuario usuario;

	public Postagem() {
	}

	public Postagem(String texto, Date createTime, Usuario usuario) {
		this.texto = texto;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) throws Exception {
		if(codigo == null)
			throw new Exception("Erro: Código não deve ser nulo.");
		this.codigo = codigo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) throws Exception{
		if(texto == null)
			throw new Exception("Erro: Texto não deve ser nulo.");
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) throws Exception {
		if(usuario == null)
			throw new Exception("Erro: Usuário não deve ser nulo.");
		this.usuario = usuario;
	}

}
