package academy.gama.desafio.dto;

import academy.gama.desafio.model.PlanoConta;

public class PlanoContaDto {
	private Integer id;
	private String descricao;	
	private String login;	
	private String tipoMovimento;
	
	public PlanoContaDto(PlanoConta planoConta) {
		this.id = planoConta.getId();
		this.descricao = planoConta.getDescricao();
		this.login = planoConta.getLogin();
		this.tipoMovimento = planoConta.getTipoLancamento().name();
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
	public String getTipoMovimento() {
		return tipoMovimento;
	}
	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}
	
	
}
