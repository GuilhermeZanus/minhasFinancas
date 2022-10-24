package com.Guilherme.minhasFinancas.model.repository;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.Guilherme.minhasFinancas.model.entity.Usuario;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest //faz um rollback deletando as alterações no fim de cada teste
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenario
		Usuario usuario = criarUsuario();
		
		// substitui o 'repository.save(usuario)' para não utilizar um método do próprio repository;
		entityManager.persist(usuario);
		
		//ação
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verificação
//		Assertions.assertThat(result).isTrue();
		Assertions.assertTrue(result);
		
	}
	
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		//cenario
				
		//ação
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verificação
//		Assertions.assertThat(result).isFalse();
		Assertions.assertFalse(result);
	}
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		//cenario
		Usuario usuario = criarUsuario();
		
		//ação
		Usuario usuarioSalvo = repository.save(usuario);
		
		//verificação
		Long id = usuarioSalvo.getId();
		
//		Assertions.assertThat(id).isNotNull();	
		Assertions.assertNotNull(id);
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		//cenario
		Usuario usuario = criarUsuario();
		
		//acao
		entityManager.persist(usuario);
		
		//verificacao
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
//		Assertions.assertThat(result.isPresent()).isTrue();
		Assertions.assertTrue(result.isPresent());
		
	}
	
	@Test
	public void deveRetornarVazioAoBuscarUmUsuarioPorEmailQuandoNaoExisteNaBase() {
		//cenario		
		
		//acao		
		
		//verificacao
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
//		Assertions.assertThat(result.isPresent()).isFalse();
		Assertions.assertFalse(result.isPresent());
		
	}


	public static Usuario criarUsuario() {
		return Usuario
				.builder()
				.nome("usuario")
				.email("usuario@email.com")
				.senha("senha")
				.build();
	}
}
