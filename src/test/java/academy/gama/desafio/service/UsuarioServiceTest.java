package academy.gama.desafio.service;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import academy.gama.desafio.controller.UsuarioController;
import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.repository.UsuarioRepository;

class UsuarioServiceTest {
	@Autowired
	private UsuarioService us;

	@Test
	void adicionarUsuario() throws Exception{
		//cenario
		UsuarioDto userDto = new UsuarioDto();
		userDto.setLogin("teste");
		userDto.setSenha("123456");
		
		UsuarioService us = new UsuarioService();
		
		
		//ação
		us.addUsuario(userDto);
		
		//verificação
		fail();
		
	}

}
