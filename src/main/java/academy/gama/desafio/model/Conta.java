/**
 * 
 */
package academy.gama.desafio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enums.TipoConta;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo,
 *         Renan Alencar, Wesley Vicente
 *
 */
@Entity
public class Conta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 200)
	private String descricao;
	@ManyToOne()
	@JoinColumn(name = "login_usuario", referencedColumnName = "login")
	private Usuario usuario;
	@Column(nullable = false, precision = 9, scale = 3)
	private double saldo = 0;	
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;
	
	public Conta() {
		
	}
	
	public Conta(int id, String descricao, Usuario usuario, double saldo, TipoConta tipoConta) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.usuario = usuario;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
	}
	
	public Conta(String descricao, Usuario loginUsuario, TipoConta tipoConta) {
		super();		
		this.descricao = descricao;
		this.usuario = loginUsuario;		
		this.tipoConta = tipoConta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
		

}
