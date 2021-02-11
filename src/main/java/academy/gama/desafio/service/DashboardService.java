package academy.gama.desafio.service;

import java.time.LocalDateTime;

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
	
	public DashboardDto getDashboard(String login, LocalDateTime inicio, LocalDateTime fim) {		
		DashboardDto dashboardDto = new DashboardDto();
		
		ContaDto contaDtoDebito = new ContaDto(contaService.getContaWithLoginAndTipoConta(login, TipoConta.CB));
		dashboardDto.setContaBanco(contaDtoDebito);
		ContaDto contaDtoCredito = new ContaDto(contaService.getContaWithLoginAndTipoConta(login, TipoConta.CC));
		dashboardDto.setContaCredito(contaDtoCredito);
		return dashboardDto;
	}
}
