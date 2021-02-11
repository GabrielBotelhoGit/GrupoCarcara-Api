package academy.gama.desafio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.ContaDto;
import academy.gama.desafio.dto.DashboardDto;
import academy.gama.desafio.repository.ContaRepository;
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
		DashboardDto dashboardDto = new DashboardDto();
		
		inicio = inicio.concat(" 00:00");
		fim = fim.concat(" 00:00");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime inicioDate = LocalDateTime.parse(inicio, formatter);
		LocalDateTime fimDate = LocalDateTime.parse(fim, formatter);
		
		ContaDto contaDtoDebito = new ContaDto(contaService.getContaWithLoginAndTipoContaAndDateBetween(login, TipoConta.CB, inicioDate, fimDate));
		dashboardDto.setContaBanco(contaDtoDebito);
		ContaDto contaDtoCredito = new ContaDto(contaService.getContaWithLoginAndTipoContaAndDateBetween(login, TipoConta.CC, inicioDate, fimDate));
		dashboardDto.setContaCredito(contaDtoCredito);
		return dashboardDto;
	}
}
