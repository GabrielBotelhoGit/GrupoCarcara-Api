package academy.gama.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.model.Conta;

@SpringBootTest
public class ContaServiceTest {
	
	@TestConfiguration
	static class ContaServiceTestConfiguration{
		@Bean
		public ContaService cs() {
			return new ContaService();
		}
		@Bean
		public UsuarioService us() {
			return new UsuarioService();
		}
	}
	
	@Autowired
	ContaService cs;
	
	@Autowired
	UsuarioService us;
	
	@Test
	public void retorna_lista_de_contas() {
		/**
		 * Este caso cobre as funções addContasIniciais, addPlanosDeContasIniciais e getContasWithLogin da classe ContaService
		 */
		UsuarioDto userDto = us.addUsuario(new UsuarioDto("12345678910", "mocklogin", "Zé Teste", "123456"));
		List<Conta> listaContas = cs.getContasWithLogin(userDto.getLogin());
		assertNotNull(listaContas);
		for(Conta conta: listaContas) {
			assertNotNull(conta);
			assertEquals( conta.getUsuario().getLogin(), userDto.getLogin());
		}
	}

}
