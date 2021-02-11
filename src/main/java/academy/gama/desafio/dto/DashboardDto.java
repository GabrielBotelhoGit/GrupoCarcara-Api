/**
 * 
 */
package academy.gama.desafio.dto;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo, Renan Alencar, Wesley Vicente
 *
 */
public class DashboardDto {
	private ContaDto contaBanco;
	private ContaDto contaCredito;
	
	public ContaDto getContaBanco() {
		return contaBanco;
	}
	public void setContaBanco(ContaDto contaBanco) {
		this.contaBanco = contaBanco;
	}
	public ContaDto getContaCredito() {
		return contaCredito;
	}
	public void setContaCredito(ContaDto contaCredito) {
		this.contaCredito = contaCredito;
	}
	
}
