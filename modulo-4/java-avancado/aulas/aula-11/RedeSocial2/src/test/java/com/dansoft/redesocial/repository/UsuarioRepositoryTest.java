package com.dansoft.redesocial.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.dansoft.redesocial.model.Usuario;
import com.github.javafaker.Faker;

@DataJpaTest
class UsuarioRepositoryTest {

	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private Faker faker;
    
    @TestConfiguration
    static class FakerTestConfig {

        @Bean
        public Faker faker() {
            return new Faker(new Locale("pt-BR"));
        }
    }

    private Usuario geradorUsuarioFaker() {
		Usuario usuario = new Usuario();
		usuario.setId(faker.number().randomNumber());
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setSenha("123@Teste");
		return usuario;
	}
    
    
    @Test
    void init(){
        assertThat(testEntityManager).isNotNull();
        assertThat(usuarioRepository).isNotNull();
    }
    
    @Test
    void inserirUsuario_DeveInserirERetornarOUsuario() {
        Usuario usuario = geradorUsuarioFaker();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        assertThat(usuarioSalvo).isNotNull(); 
        assertThat(usuarioSalvo.getId()).isGreaterThan(0);
        assertThat(usuarioSalvo.getNome()).isEqualTo(usuario.getNome());
        assertThat(usuarioSalvo.getEmail()).isEqualTo(usuario.getEmail());
        assertThat(usuarioSalvo.getSenha()).isEqualTo(usuario.getSenha());
    }
    
    @Test
    void createEmployee_WithExistingEmail_ThrowsException() {
    	Usuario employee1 = geradorUsuarioFaker();
        testEntityManager.persistFlushFind(employee1);
        
    }

}
