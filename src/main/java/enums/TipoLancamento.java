package enums;

public enum TipoLancamento {
	R(1, "RECEITA"), 
	D(-1, "DESPESA"), 
	TC(-1, "TRANSFERENCIA ENTRE CONTAS"),
	TU(-1, "TRANSFERENCIA ENTRE USUARIOS"),

	;

	private Integer fator;
	private String descricao;

	private TipoLancamento(Integer fator, String descricao) {
		this.fator = fator;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getFator() {
		return fator;
	}
}