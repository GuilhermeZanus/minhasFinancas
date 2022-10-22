package com.Guilherme.minhasFinancas.service;

import org.junit.jupiter.api.BeforeEach;

//
//

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.Guilherme.minhasFinancas.model.entity.Usuario;
//
import com.Guilherme.minhasFinancas.model.repository.UsuarioRepository;
import com.Guilherme.minhasFinancas.service.impl.UsuarioServiceImpl;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	
	UsuarioService service;
	
	UsuarioRepository repository;
	
	@BeforeEach
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test //(expected = Test.None.class)
	public void deveValidarEmail() {
		
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		//acao
		service.validarEmail("email@email.com");		
	}
	
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		
		//acao
		service.validarEmail("email@email.com");		
	}
	
}
