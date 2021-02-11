package academy.gama.desafio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.ContaDto;
import academy.gama.desafio.dto.DashboardDto;
import enums.TipoConta;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo, Renan Alencar, Wesley Vicente
 *
 */
@Service
public class DashboardService {
	@Autowired
	private ContaService contaService;
	
	public DashboardDto getDashboard(String login, String inicio, String fim) {	
		inicio = inicio.concat(" 00:00");
		fim = fim.concat(" 00:00");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime inicioDate = LocalDateTime.parse(inicio, formatter);
		LocalDateTime fimDate = LocalDateTime.parse(fim, formatter);
		DashboardDto dashboardDto = new DashboardDto();
		
		ContaDto contaDtoDebito = new ContaDto(contaService.getContaWithLoginAndTipoConta(login, TipoConta.CB));
		dashboardDto.setContaBanco(contaDtoDebito);
		ContaDto contaDtoCredito = new ContaDto(contaService.getContaWithLoginAndTipoConta(login, TipoConta.CC));
		dashboardDto.setContaCredito(contaDtoCredito);
		return dashboardDto;
	}
}
