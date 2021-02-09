package academy.gama.desafio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.exceptions.ObjectNotFoundException;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.ContaRepository;
import academy.gama.desafio.repository.UsuarioRepository;
import enums.TipoConta;


/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo, Renan Alencar, Wesley Vicente
 *
 */
@Service
public class UsuarioService {	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	ContaService contaService;
	public boolean addUsuario(Usuario usuario) {	
		if(!existsUsuarioWithLogin(usuario.getLogin())) {
			usuarioRepository.save(usuario);
			incluirUsuarioConta(usuario);
			return true;
		}
		else {
			return false;			
		}			
	}
	
	
	@Transactional
	private void incluirUsuarioConta(Usuario usuario) {
		String senhaCriptografada = usuario.getSenha();
		usuario.setSenha(senhaCriptografada);
		
		Conta conta = new Conta();
		conta.setUsuario(usuario);;
		conta.setSaldo(0.0d);
		conta.setDescricao(TipoConta.CB.getDescricao());
		conta.setTipoConta(TipoConta.CB);
		contaRepository.save(conta);
		
		conta = new Conta();
		conta.setUsuario(usuario);;
		conta.setSaldo(0.0d);
		conta.setDescricao(TipoConta.CC.getDescricao());
		conta.setTipoConta(TipoConta.CC);
		contaRepository.save(conta);
		
		usuarioRepository.save(usuario);
		
		
	}
	
	public Usuario getUsuarioWithLoginAndSenha(String login, String senha) {
		return usuarioRepository.getUsuarioWithLoginAndSenha(login, senha);
	}
	
	public boolean existsUsuarioWithLogin(String login) {
		Usuario usuario = usuarioRepository.getUsuarioWithLogin(login);
		if(usuario != null) {			
			return true;
		}
		else {
			return false;			
		}
	}
	
	public Usuario search(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Não encontrado! ID: "+ id + ", Tipo: "+ Usuario.class.getName())); 
	}
	
	@Transactional
	public List<UsuarioDto> findAll(Integer id) {
		List<Usuario> list = usuarioRepository.findUsuarioAndConta(id);
		return list.stream().map(x -> new UsuarioDto(x)).collect(Collectors.toList());
	}
	
}