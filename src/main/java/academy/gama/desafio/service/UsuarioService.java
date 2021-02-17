package academy.gama.desafio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.LoginDto;
import academy.gama.desafio.dto.SessaoDto;
import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.exceptions.ObjectNotFoundException;
import academy.gama.desafio.mapper.ContaMapper;
import academy.gama.desafio.mapper.UsuarioMapper;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.UsuarioRepository;
import academy.gama.desafio.security.UserSS;
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
	
	// encripta a senha digitada pelo usuário
	@Autowired
	private BCryptPasswordEncoder pe;

	@Transactional
	public void addUsuario(UsuarioDto usuarioDtp) {
		Usuario usuario = new Usuario();
		usuario.setCpf(usuarioDtp.getCpf());
		usuario.setLogin(usuarioDtp.getLogin());
		usuario.setNome(usuarioDtp.getNome());
		usuario.setSenha(usuarioDtp.getSenha());
		if (!existsUsuarioWithLogin(usuario.getLogin())) {
			usuarioRepository.save(usuario);
			incluirUsuarioConta(usuario);

		} else {
			if (existsUsuarioWithLogin(usuario.getLogin()))
				throw new IllegalStateException();
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

		ContaMapper contaMapper = new ContaMapper();
		UsuarioMapper usuarioMapper = new UsuarioMapper();
		Usuario usuario = getUsuarioWithLoginAndSenha(loginDto.getUsuario(), pe.encode(loginDto.getSenha()));
		sessaoDto.setUsuario(usuarioMapper.getUsuarioDtoFromEntity(usuario));

		Conta contaDebito = contaService.getContaWithLoginAndTipoConta(usuario.getLogin(), TipoConta.CB);
		sessaoDto.setContaDebito(contaMapper.getContaDtoFromEntity(contaDebito));
		Conta contaCredito = contaService.getContaWithLoginAndTipoConta(usuario.getLogin(), TipoConta.CC);
		sessaoDto.setContaCredito(contaMapper.getContaDtoFromEntity(contaCredito));
		return sessaoDto;
	}

	/***
	 * 
	 * @return
	 */
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}