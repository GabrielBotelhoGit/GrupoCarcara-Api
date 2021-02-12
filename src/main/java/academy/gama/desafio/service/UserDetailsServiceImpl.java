/**
 * 
 */
package academy.gama.desafio.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.UsuarioRepository;
import academy.gama.security.UserSS;

/**
 * @author renan
 * Classe de Serviço que implementa a interface UserDetailsService
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.getUsuarioWithLogin(login);
		
		if (usuario == null) {
			throw new UsernameNotFoundException(login);
		}
		return new UserSS(usuario.getLogin(), usuario.getSenha());
	}

}
