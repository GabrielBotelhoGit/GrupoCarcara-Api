package academy.gama.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.ContaDto;
import academy.gama.desafio.dto.LoginDto;
import academy.gama.desafio.dto.SessaoDto;
import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Usuario;
import enums.TipoConta;

@Service
public class LoginService {
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	ContaService contaService;

	public SessaoDto Logar(LoginDto loginDto) {
		SessaoDto sessaoDto = new SessaoDto();			
		Usuario usuario = usuarioService.getUsuarioWithLoginAndSenha(loginDto.getUsuario(), loginDto.getSenha());
		sessaoDto.setUsuario(new UsuarioDto(usuario));				
		Conta contaDebito = contaService.getContaWithLoginAndTipoConta(usuario.getLogin(), TipoConta.CB);		
		sessaoDto.setContaDebito(new ContaDto(contaDebito));
		Conta contaCredito = contaService.getContaWithLoginAndTipoConta(usuario.getLogin(), TipoConta.CC);
		sessaoDto.setContaCredito(new ContaDto(contaCredito));			
		return sessaoDto;
	}
}
