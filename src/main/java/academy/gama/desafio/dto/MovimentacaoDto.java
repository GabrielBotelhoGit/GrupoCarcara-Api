package academy.gama.desafio.dto;

public class MovimentacaoDto {
	private Integer id;	
	private String data;
    private String descricao;    
    private Integer planoConta;    
    private Double valor;
    private Integer conta;
    private String contaDestino;           
	
	
	public Integer getConta() {
		return conta;
	}


	public void setConta(Integer conta) {
		this.conta = conta;
	}


	public String getContaDestino() {
		return contaDestino;
	}


	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getData() {
		return this.data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getPlanoConta() {
		return planoConta;
	}
	public void setPlanoConta(Integer planoConta) {
		this.planoConta = planoConta;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
