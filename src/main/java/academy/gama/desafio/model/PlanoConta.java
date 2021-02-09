/**
 * 
 */
package academy.gama.desafio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import enums.TipoLancamento;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo,
 *         Renan Alencar, Wesley Vicente
 *
 */
@Entity 
public class PlanoConta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 200)
	private String descricao;
	@Column(nullable = false)
	private String login;		
	private TipoLancamento tipoMovimento;
	@OneToOne(mappedBy = "conta")
	private Lancamento lancamento;

	public PlanoConta(long id, String descricao, String login, TipoLancamento tipoMovimento) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.login = login;		
		this.tipoMovimento = tipoMovimento;
	}

	public PlanoConta(String login, String descricao, TipoLancamento tipoMovimento) {
		this.descricao = descricao;
		this.login = login;		
		this.tipoMovimento = tipoMovimento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public TipoLancamento getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(TipoLancamento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

}
