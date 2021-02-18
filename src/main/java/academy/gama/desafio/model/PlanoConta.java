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
import javax.persistence.OneToMany;

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
	private Integer id;
	@Column(length = 200)
	private String descricao;
	@Column(length = 20, nullable = false)
	private String login;		
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipoLancamento;	
	private boolean ativo;
	private boolean padrao;

	public PlanoConta(Integer id, String descricao, String login, TipoLancamento tipoLancamento, boolean ativo, boolean padrao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.login = login;		
		this.tipoLancamento = tipoLancamento;
		this.ativo = ativo;
	}

	public PlanoConta(String login, String descricao, TipoLancamento tipoLancamento, boolean ativo, boolean padrao) {
		this.descricao = descricao;
		this.login = login;		
		this.tipoLancamento = tipoLancamento;
		this.ativo = ativo;
	}

	public PlanoConta() { 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	
	/*public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}*/

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isPadrao() {
		return padrao;
	}

	public void setPadrao(boolean padrao) {
		this.padrao = padrao;
	}
	
	
	
	

}
