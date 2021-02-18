/**
 * 
 */
package academy.gama.desafio.dto;

import java.util.List;

import academy.gama.desafio.model.Conta;

/**
 * @author renan
 *
 */
public class ContaDto {
	private Integer id;
	private List<LancamentoDto> lancamentos;
	private String descricao;
	private double saldo;
	private String tipoConta;
	
	public ContaDto(Conta conta, List<LancamentoDto> lancamentosDto) {
		this.id = conta.getId();			
		this.lancamentos = lancamentosDto;
		this.saldo = conta.getSaldo();
	}

	public ContaDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<LancamentoDto> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoDto> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}	
	
	
	
}
