/**
 * 
 */
package academy.gama.desafio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import academy.gama.desafio.dto.UsuarioDto;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo,
 *         Renan Alencar, Wesley Vicente
 *
 */
@Entity
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, length = 20)
	private String login;
	@Column(nullable = false, length = 50)
	private String senha;
	@Column(length = 200)
	private String nome;
	@Column(nullable = false, length = 11)
	private String cpf;
	@OneToMany(mappedBy = "usuario")
	private List<Conta> contas;

	public Usuario() {
		
	}
	
	public Usuario(String login, String senha, String nome, String cpf) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Usuario(UsuarioDto usuarioDto) {
		this.login = usuarioDto.getLogin();
		this.senha = usuarioDto.getSenha();
		this.nome = usuarioDto.getNome();
		this.cpf = usuarioDto.getCpf();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + ", nome=" + nome + ", cpf=" + cpf + "]";
	}

}
