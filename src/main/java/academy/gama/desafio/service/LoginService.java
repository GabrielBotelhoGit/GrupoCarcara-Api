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
	/*
	 * @Autowired UsuarioService usuarioService;
	 * 
	 * @Autowired ContaService contaService;
	 * 
	 * public SessaoDto Logar(LoginDto loginDto) { SessaoDto sessaoDto = new
	 * SessaoDto(); Usuario usuario =
	 * usuarioService.getUsuarioWithLoginAndSenha(loginDto.getUsuario(),
	 * loginDto.getSenha()); sessaoDto.setUsuario(new UsuarioDto(usuario));
	 * List<Conta> contas = contaService.getContaWithLogin(usuario.getLogin());
	 * Conta contaDebito = new Conta(); Conta contaCredito = new Conta(); for(Conta
	 * conta : contas) { if(conta.getTipoConta().equals(TipoConta.CB)) { contaDebito
	 * = conta; } if(conta.getTipoConta().equals(TipoConta.CC)) { contaCredito =
	 * conta; } }
	 * 
	 * sessaoDto.setContaDebito(new ContaDto(contaDebito));
	 * sessaoDto.setContaCredito(new ContaDto(contaCredito)); return sessaoDto; }
	 */
}
