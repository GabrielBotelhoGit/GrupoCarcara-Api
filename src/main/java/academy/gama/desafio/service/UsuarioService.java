package academy.gama.desafio.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.exceptions.ObjectNotFoundException;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.ContaRepository;
import academy.gama.desafio.repository.UsuarioRepository;
import enums.TipoConta;
import enums.TipoLancamento;


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
	public boolean addUsuario(Usuario usuario) {	
		if(!existsUsuarioWithLogin(usuario.getLogin())) {
			usuarioRepository.save(usuario);
			return true;
		}
		else {
			return false;			
		}			
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
}