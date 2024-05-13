package com.dansoft.redesocial.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/swagger-ui/**", "/v3/api-docs", "/swagger-resources/**", "/webjars/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/v2/usuarios/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.POST, "/v1/usuarios/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.POST, "/postagens/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.GET, "/v2/usuarios/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.GET, "/v1/usuarios/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.GET, "/postagens/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.DELETE, "/v2/usuarios/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.DELETE, "/v1/usuarios/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.DELETE, "/postagens/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.PUT, "/v2/usuarios/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.PUT, "/v1/usuarios/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.PUT, "/postagens/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers(HttpMethod.PUT, "/recovery/{email}").permitAll()
						.requestMatchers(HttpMethod.PUT, "/reset-password").permitAll().anyRequest().authenticated())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
