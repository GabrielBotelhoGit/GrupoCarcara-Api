package academy.gama.desafio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import academy.gama.desafio.dto.LoginDto;
import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.repository.UsuarioRepository;
import academy.gama.desafio.service.CadastroService;
import academy.gama.desafio.service.LoginService;

@Component
public class Sistema {
	@Autowired
	CadastroService cadastroService;
	@Autowired
	LoginService loginService;	
	
	
	@Transactional
	public void incluirUsuario() {
		UsuarioDto usuarioDto = new UsuarioDto("12530952728", "GabrielBotelho", "Gabriel Carreiras Botelho", "123321");
		cadastroService.Cadastrar(usuarioDto);
		LoginDto loginDto = new LoginDto(usuarioDto.getLogin(), usuarioDto.getSenha());
		System.out.println(loginService.Logar(loginDto).toString());
		
	}
}
