package academy.gama.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.security.JWTUtil;

@RunWith(SpringRunner.class)
@DataJpaTest
class UsuarioServiceTest {

	@TestConfiguration
	static class UsuarioServiceTestConfiguration{
		@Bean
		public UsuarioService usuarioService() {
			return new UsuarioService();
		}
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		@Bean
		public ContaService contaService() {
			return new ContaService();
		}
		@Bean
		public LancamentoService lancamentosService() {
			return new LancamentoService();
		}
		@Bean
		public PlanoContaService planoContaService() {
			return new PlanoContaService();
		}
		@Bean
		public JWTUtil jwtUtil() {
			return new JWTUtil();
		}

	}
	
	@Autowired
	UsuarioService usuarioService;

	@Test
	public void adicionar_e_buscar_Usuario(){
		/**
		 * Cobre as funções addUsuario e getUsuarioWithLogin da classe UsuarioService e suas exceções
		 */
		
		//cenário
		Usuario user = new Usuario("12345678910", "mocklogin", "Zé Teste", "123456");
		UsuarioDto userDto = new UsuarioDto(user);
		
		//ação
		UsuarioDto saved = usuarioService.addUsuario(userDto);
		Usuario found   = usuarioService.getUsuarioWithLogin(user.getLogin());
		
		//verificação
		assertNotNull(found);
		assertEquals(userDto, saved);
		assertEquals(found.getLogin(), user.getLogin());
		

		//erros
		try { //verifica a adição de usuário repetido
			usuarioService.addUsuario(userDto);
		} catch (IllegalStateException e) {
			assertEquals(e.getClass(), IllegalStateException.class);
		} catch (Exception e) {
			fail("Retornou uma exceção inesperada");
		}
	}
	@Test
	public void busca_usuario_com_login_e_senha() {
		/**
		 * Cobre a função getUsuarioWithLoginAndSenha da classe UsuarioService e suas exceções
		 */
		
		//cenário
		Usuario user = new Usuario("12345678910", "mocklogin", "Zé Teste", "123456");
		usuarioService.addUsuario(new UsuarioDto(user));				
		
		//ação
		Usuario found   = usuarioService.getUsuarioWithLoginAndSenha(user.getLogin(), user.getSenha());
		
		//verificação
		assertNotNull(found);
		
		
		//erros
		try { //verifica a busca com uma senha errada
			found   = usuarioService.getUsuarioWithLoginAndSenha(user.getLogin(), "Senhaerrada");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals(e.getMessage(), "Senha incorreta!");
		} catch (Exception e) {
			fail("Retornou uma exceção inesperada");
		}
		
		try { //verifica a busca com um usuário inexistente
			found   = usuarioService.getUsuarioWithLoginAndSenha("inexistente", user.getSenha());
		} catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals(e.getMessage(), "Usuário não existe");
		} catch (Exception e) {
			fail("Retornou uma exceção inesperada");
		}
	}
	
}
