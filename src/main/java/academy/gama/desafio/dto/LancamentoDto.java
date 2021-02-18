/**
 * 
 */
package academy.gama.desafio.dto;

import java.time.format.DateTimeFormatter;

import academy.gama.desafio.model.Lancamento;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo, Renan Alencar, Wesley Vicente
 *
 */
public class LancamentoDto {
	private Integer id;	
	private String data;
    private String descricao;    
    private PlanoContaDto planoConta;    
    private Double valor;
    private Integer conta;
    private String contaDestino;
        
    
	public LancamentoDto(Lancamento lancamento) {
		super();
		this.id = lancamento.getId();		
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.data = lancamento.getData().format(dateFormater);
		this.descricao = lancamento.getDescricao();
		this.planoConta = new PlanoContaDto(lancamento.getPlanoConta());
		this.valor = lancamento.getValor();		
	}
	
	
	public LancamentoDto() {
		// TODO Auto-generated constructor stub
	}


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
		return data;
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
	public PlanoContaDto getPlanoConta() {
		return planoConta;
	}
	public void setPlanoConta(PlanoContaDto planoConta) {
		this.planoConta = planoConta;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
    
    
}
