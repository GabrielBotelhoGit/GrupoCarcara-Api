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
import academy.gama.desafio.repository.UsuarioRepository;
import enums.TipoConta;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo,
 *         Renan Alencar, Wesley Vicente
 *
 */
@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	ContaService contaService;

	public boolean addUsuario(Usuario usuario) {
		if (!existsUsuarioWithLogin(usuario.getLogin())) {
			usuarioRepository.save(usuario);
			return true;
		} else {
			return false;
		}
	}

	public Usuario getUsuarioWithLoginAndSenha(String login, String senha) {
		return usuarioRepository.getUsuarioWithLoginAndSenha(login, senha);
	}

	public boolean existsUsuarioWithLogin(String login) {
		Usuario usuario = usuarioRepository.getUsuarioWithLogin(login);
		if (usuario != null) {
			return true;
		} else {
			return false;
		}
	}

	public SessaoDto Logar(LoginDto loginDto) {
		SessaoDto sessaoDto = new SessaoDto();
		Usuario usuario = getUsuarioWithLoginAndSenha(loginDto.getUsuario(), loginDto.getSenha());
		sessaoDto.setUsuario(new UsuarioDto(usuario));
		List<Conta> contas = contaService.getContaWithLogin(usuario.getLogin());
		Conta contaDebito = new Conta();
		Conta contaCredito = new Conta();
		
		for (Conta conta : contas) {
			if (conta.getTipoConta().equals(TipoConta.CB)) {
				contaDebito = conta;
			}
			if (conta.getTipoConta().equals(TipoConta.CC)) {
				contaCredito = conta;
			}
		}
		
		sessaoDto.setContaDebito(new ContaDto(contaDebito));
		sessaoDto.setContaCredito(new ContaDto(contaCredito));
		return sessaoDto;
	}
}