package academy.gama.desafio.dto;

import java.io.Serializable;

import academy.gama.desafio.model.PlanoConta;

public class PlanoContaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private String login;
	private String tipoLancamento;
	private boolean ativo;
	private boolean padrao;

	public PlanoContaDto(PlanoConta planoConta) {
		this.id = planoConta.getId();
		this.descricao = planoConta.getDescricao();
		this.login = planoConta.getLogin();
		this.tipoLancamento = planoConta.getTipoLancamento().getDescricao();
		this.ativo = planoConta.isAtivo();
		this.padrao = planoConta.isPadrao();
	}

	public PlanoContaDto() {

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

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

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
