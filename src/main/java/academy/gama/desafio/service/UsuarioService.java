package academy.gama.desafio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.ContaDto;
import academy.gama.desafio.dto.LoginDto;
import academy.gama.desafio.dto.SessaoDto;
import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.exceptions.ObjectNotFoundException;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.ContaRepository;
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

	@Transactional
	public boolean addUsuario(UsuarioDto usuarioDtp) {
		Usuario usuario = new Usuario();
		usuario.setCpf(usuarioDtp.getCpf());
		usuario.setLogin(usuarioDtp.getLogin());
		usuario.setNome(usuarioDtp.getNome());
		usuario.setSenha(usuarioDtp.getSenha());
		if (!existsUsuarioWithLogin(usuario.getLogin())) {
			usuarioRepository.save(usuario);
			incluirUsuarioConta(usuario);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	private void incluirUsuarioConta(Usuario usuario) {
		String senhaCriptografada = usuario.getSenha();
		usuario.setSenha(senhaCriptografada);

		contaService.addContasIniciais(usuario);

		usuarioRepository.save(usuario);

	}

	@Transactional
	public Usuario getUsuarioWithLoginAndSenha(String login, String senha) {
		return usuarioRepository.getUsuarioWithLoginAndSenha(login, senha);
	}

	@Transactional
	public boolean existsUsuarioWithLogin(String login) {
		Usuario usuario = usuarioRepository.getUsuarioWithLogin(login);
		if (usuario != null) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public Usuario search(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Não encontrado! ID: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	@Transactional
	public List<UsuarioDto> findAll(Integer id) {
		List<Usuario> list = usuarioRepository.findUsuarioAndConta(id);
		return list.stream().map(x -> new UsuarioDto(x)).collect(Collectors.toList());
	}

	public SessaoDto Logar(LoginDto loginDto) {
		SessaoDto sessaoDto = new SessaoDto();
		Usuario usuario = getUsuarioWithLoginAndSenha(loginDto.getUsuario(), loginDto.getSenha());
		sessaoDto.setUsuario(new UsuarioDto(usuario));
		Conta contaDebito = contaService.getContaWithLoginAndTipoConta(usuario.getLogin(), TipoConta.CB);
		sessaoDto.setContaDebito(new ContaDto(contaDebito));
		Conta contaCredito = contaService.getContaWithLoginAndTipoConta(usuario.getLogin(), TipoConta.CC);
		sessaoDto.setContaCredito(new ContaDto(contaCredito));
		return sessaoDto;
	}

}