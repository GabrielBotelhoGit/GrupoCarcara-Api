/**
 * 
 */
package academy.gama.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author renan
 *
 * Classe que implementa a interface UserDetails do Spring Security
 */
public class UserSS implements UserDetails {
	
//	private String id;
	private String login;
	private String senha;
	// falta determinar o perfil do usuário do sistema
//	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
		
	}

	public UserSS(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
//		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	/***
	 * Método que contém a regra de negocio para quando o usuário expira
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
