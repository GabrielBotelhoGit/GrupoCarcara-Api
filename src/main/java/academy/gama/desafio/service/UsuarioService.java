package academy.gama.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.UsuarioRepository;


/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo, Renan Alencar, Wesley Vicente
 *
 */
@Service
public class UsuarioService {	
	@Autowired
	UsuarioRepository usuarioRepository;
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
}