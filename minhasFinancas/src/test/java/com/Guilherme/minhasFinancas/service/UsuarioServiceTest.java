package com.Guilherme.minhasFinancas.service;

import java.util.Optional;

//
//

import org.junit.jupiter.api.Assertions;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.Guilherme.minhasFinancas.exception.ErroAutenticacao;
import com.Guilherme.minhasFinancas.exception.RegraNegocioException;
import com.Guilherme.minhasFinancas.model.entity.Usuario;
//
import com.Guilherme.minhasFinancas.model.repository.UsuarioRepository;
import com.Guilherme.minhasFinancas.service.impl.UsuarioServiceImpl;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@SpyBean
	UsuarioServiceImpl service;
	
	@MockBean
	UsuarioRepository repository;
//	
//	@BeforeEach
//	public void setUp() {
//		
//		service = new UsuarioServiceImpl(repository);
//	}
	
	@Test
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
		Assertions.assertThrows(RegraNegocioException.class, ()->service.validarEmail("email@email.com"));
	}
	
	@Test 
	public void deveAutenticarUmUsuarioComSucesso() {
		
		//cenario
		String email = "email@email.com";
		String senha = "senha";
		
		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();		
		
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		//acao
		Usuario result = service.autenticar(email, senha);
		
		//verificacao
		Assertions.assertNotNull(result);
		Assertions.assertDoesNotThrow(()->service.autenticar(email, senha));		
	}
	
	
	@Test 
	public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComEmailInformado() {
		
		//cenario			
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		
		//verificação		
		ErroAutenticacao erroAutenticacao = Assertions.assertThrows(ErroAutenticacao.class, 
				()->service.autenticar("email@email.com", "senha"));		
		Assertions.assertEquals("Usuario não encontrado para o email informado", erroAutenticacao.getMessage());
	}
	
	@Test
	public void deveLancarErroQuandoSenhaNaoBater() {
		
		//cenario
		String email = "email@email.com";
		String senhaCorreta = "senhaCorreta";
		String senhaErrada = "senhaErrada";
				
		Usuario usuario = Usuario.builder().email(email).senha(senhaCorreta).id(1l).build();		
				
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

		// ação
		ErroAutenticacao erroAutenticacao = Assertions.assertThrows(ErroAutenticacao.class, 
						()->service.autenticar(email, senhaErrada));				
		Assertions.assertEquals("Senha inválida", erroAutenticacao.getMessage());		
	}
	
	@Test
	public void deveSalvarUmUsuario() {
		//cenario
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());		
		
		Usuario usuario = Usuario.builder()
				.nome("nome")
				.email("email@email.com")
				.senha("senhaCorreta")
				.id(1l)
				.build();
		
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

		//ação
		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
		
		//verificação
		Assertions.assertAll(
				() -> Assertions.assertEquals("nome", usuario.getNome()),
				() -> Assertions.assertEquals("email@email.com", usuario.getEmail()),
				() -> Assertions.assertEquals("nome", usuario.getNome()),
				() -> Assertions.assertEquals("nome", usuario.getNome()),
				() -> Assertions.assertDoesNotThrow(()->service.salvarUsuario(new Usuario()))
				);
	}
	
	@Test
	public void naoDeveSalvarUmUsuarioComEmailJaCadastrado() {
		//cenario
		Usuario usuario = Usuario.builder()
				.nome("nome")
				.email("email@email.com")
				.senha("senhaCorreta")
				.id(1l)
				.build();
		
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail("email@email.com");
		
		//ação
		Assertions.assertThrows(RegraNegocioException.class, ()->service.salvarUsuario(usuario));
		Mockito.verify(repository, Mockito.never()).save(usuario);
		
	}
	
}
