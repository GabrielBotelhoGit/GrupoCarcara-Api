package academy.gama.desafio.dto;

import java.time.LocalDateTime;

public class SessaoDto {
	public ContaDto contaDebito;	
	public ContaDto contaCredito;
	public String dataFim;
	public String dataInicio;
	public String token;
	public UsuarioDto usuario;
	
	public ContaDto getContaDebito() {
		return contaDebito;
	}
	public void setContaDebito(ContaDto contaDebito) {
		this.contaDebito = contaDebito;
	}
	public ContaDto getContaCredito() {
		return contaCredito;
	}
	public void setContaCredito(ContaDto contaCredito) {
		this.contaCredito = contaCredito;
	}
	
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UsuarioDto getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDto usuarioDto) {
		this.usuario = usuarioDto;
	}
	
	@Override
	public String toString() {
		return "SessaoDto [contaDebito=" + contaDebito + ", contaCredito=" + contaCredito + ", dataFim=" + dataFim
				+ ", dataInicio=" + dataInicio + ", token=" + token + ", usuario=" + usuario + "]";
	}
	
	
}
