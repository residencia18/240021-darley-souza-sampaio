package com.dansoft.redesocial.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Postagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codigo", nullable = false, length = 11, unique = true)
	private String codigo;

	@Column(name = "texto", nullable = false, length = 255)
	@NotEmpty(message = "O texto não deve ser vazio")
	@NotNull(message = "O texto não deve ser nulo")
	private String texto;

	@Column(name = "data", nullable = false)
	private Date dataPostagem;

	@PrePersist
	protected void onCreate() {
		dataPostagem = new Date();
	}

	@ManyToOne
	@JoinColumn(name = "Usuario_id", referencedColumnName = "id", nullable = false)
	private Usuario usuario;

	public Postagem() {
	}

	public Postagem(String texto, Date createTime, Usuario usuario) {
		this.texto = texto;
		this.usuario = usuario;
	}

}
