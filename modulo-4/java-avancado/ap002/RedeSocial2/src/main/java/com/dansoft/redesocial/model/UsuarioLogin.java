package com.dansoft.redesocial.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLogin implements UserDetails {
	private static final long serialVersionUID = 3075760374715476815L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "login", nullable = false, length = 32)
	@NotEmpty(message = "O login não deve ser vazio")
	@NotNull(message = "O login não deve ser nulo")
	@Size(min = 5, message = "O login deve ter no mínimo 5 caracteres")
	private String login;
	
	@Column(name = "email", length = 50, unique = true)
	@NotEmpty(message = "O email não deve ser vazio")
	@NotNull(message = "O email não deve ser nulo")
	@Email(regexp = ".+[@].+[\\.].+", message = "Email inválido")
	private String email;

	@Column(name = "senha", nullable = false, length = 32)
	@NotEmpty(message = "A senha não deve ser vazia")
	@NotNull(message = "A senha não deve ser nula")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
	@Pattern(regexp = ".*[a-z].*", message = "A senha deve conter pelo menos uma letra minúscula")
	@Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter pelo menos uma letra maiúscula")
	@Pattern(regexp = ".*\\d.*", message = "A senha deve conter pelo menos um dígito")
	@Pattern(regexp = ".*[@#$%^&+=.!].*", message = "A senha deve conter pelo menos um caractere especial")
	private String senha;

	@Column(name = "role", nullable = false, length = 32)
	private UsuarioLoginRole role;

	public UsuarioLogin(String login, String email, String senha, UsuarioLoginRole role) {
		super();
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == UsuarioLoginRole.ADMIN)
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		else
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
