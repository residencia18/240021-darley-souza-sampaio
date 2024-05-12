package com.dansoft.redesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dansoft.redesocial.repository.UsuarioLoginRepository;

@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	UsuarioLoginRepository usuarioLoginRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		return usuarioLoginRepository.findByLogin(login);
	}

}
