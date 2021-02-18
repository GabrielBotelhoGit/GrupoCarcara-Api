package academy.gama.desafio.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.UsuarioRepository;
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
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Autowired
	UsuarioService usuarioService;

	@Test
	public void adicionar_e_buscar_Usuario(){
		//cenário
		Usuario user = new Usuario("12345678910", "mocklogin", "Zé Teste", "123456");
		UsuarioDto userDto = new UsuarioDto(user);
		
		//ação
		UsuarioDto saved = usuarioService.addUsuario(userDto);
		Usuario found   = usuarioService.getUsuarioWithLogin(user.getLogin());
		
		//verificação
		error.checkThat(userDto, is(saved));
		error.checkThat(found, notNullValue());
		error.checkThat(found.getLogin(), is(user.getLogin()));
	}
	
}
