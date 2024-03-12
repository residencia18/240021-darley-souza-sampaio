package com.dansoft.redesocial.model;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
public class Postagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codigo", nullable = false, length = 11)
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

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
