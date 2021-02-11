/**
 * 
 */
package academy.gama.desafio.dto;

import java.util.ArrayList;
import java.util.List;

import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Lancamento;

/**
 * @author renan
 *
 */
public class ContaDto {
	private long id;
	private List<LancamentoDto> lancamentos;
	private double saldo;
	
	public ContaDto(Conta conta) {
		this.id = conta.getId();
		List<LancamentoDto> lancamentosDto = new ArrayList<LancamentoDto>();
		if(conta.getLancamentos() != null) {
			for(Lancamento lancamento: conta.getLancamentos()) {
				lancamentosDto.add(new LancamentoDto(lancamento));
			}			
		}		
		this.lancamentos = lancamentosDto;
		this.saldo = conta.getSaldo();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
	
}
