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
import academy.gama.desafio.dto.TokenDto;
import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.exceptions.ObjectNotFoundException;
import academy.gama.desafio.mapper.ContaMapper;
import academy.gama.desafio.mapper.UsuarioMapper;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.UsuarioRepository;
import academy.gama.desafio.security.JWTUtil;
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
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	ContaService contaService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	// encripta a senha digitada pelo usuário
	@Autowired
	private BCryptPasswordEncoder pe;

	@Transactional
	public UsuarioDto addUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setCpf(usuarioDto.getCpf());
		usuario.setLogin(usuarioDto.getLogin());
		usuario.setNome(usuarioDto.getNome());
		usuario.setSenha(pe.encode(usuarioDto.getSenha()));
		if (!existsUsuarioWithLogin(usuario.getLogin())) {
			usuarioRepository.save(usuario);
			incluirUsuarioConta(usuario);
			return usuarioDto;
		} else {
			throw new IllegalStateException();
		}
	}

	@Transactional
	private void incluirUsuarioConta(Usuario usuario) {		

		contaService.addContasIniciais(usuario);

		usuarioRepository.save(usuario);

	}

	@Transactional
	public Usuario getUsuarioWithLoginAndSenha(String login, String senha) {
		Usuario usuario = getUsuarioWithLogin(login);
		if(pe.matches(senha, usuario.getSenha())) {
			return usuario;
		}
		else {
			return new Usuario();
		}
	}
	
	@Transactional
	public Usuario getUsuarioWithLogin(String login) {
		return usuarioRepository.getUsuarioWithLogin(login);
	}

	@Transactional
	public boolean existsUsuarioWithLogin(String login) {
		Usuario usuario = usuarioRepository.getUsuarioWithLogin(login);
		if (usuario != null) {
			return true;
		} else {
			return false;
		}
		
		sessaoDto.setContaDebito(new ContaDto(contaDebito));
		sessaoDto.setContaCredito(new ContaDto(contaCredito));
		return sessaoDto;
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

	@Transactional
	public SessaoDto logar(LoginDto loginDto) {
		SessaoDto sessaoDto = new SessaoDto();

		ContaMapper contaMapper = new ContaMapper();
		UsuarioMapper usuarioMapper = new UsuarioMapper();				
		Usuario usuario = getUsuarioWithLoginAndSenha(loginDto.getUsuario(), loginDto.getSenha());		
		
		if(usuario == null) {
			throw new IllegalArgumentException("Usuário ou senha errados!");
		}
		
		TokenDto tokenDto = jwtUtil.getToken(loginDto.getUsuario());
		
		sessaoDto.setToken(tokenDto.getToken());
		sessaoDto.setDataInicio(tokenDto.getHoraInicio());
		sessaoDto.setDataFim(tokenDto.getHoraFim());

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