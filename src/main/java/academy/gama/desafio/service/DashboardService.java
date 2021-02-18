package academy.gama.desafio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.ContaDto;
import academy.gama.desafio.dto.DashboardDto;
import academy.gama.desafio.mapper.ContaMapper;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Lancamento;
import enums.TipoConta;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo, Renan Alencar, Wesley Vicente
 *
 */
@Service
public class DashboardService {
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private LancamentoService lancamentoService;
	

	@Transactional
	public DashboardDto getDashboard(String login, String inicio, String fim) {		
		DashboardDto dashboardDto = new DashboardDto();
		
		inicio = inicio.concat(" 00:00");
		fim = fim.concat(" 23:59");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime inicioDate = LocalDateTime.parse(inicio, formatter);
		LocalDateTime fimDate = LocalDateTime.parse(fim, formatter);
		
		ContaMapper contaMapper = new ContaMapper();
		
		Conta contaDebito = contaService.getContaWithLoginAndTipoConta(login, TipoConta.CB);
		List<Lancamento> lancamentosDebito = lancamentoService.getLancamentosWithIdContaAndDateBetween(contaDebito.getId(), inicioDate, fimDate);			
		ContaDto contaDtoDebito = contaMapper.getContaDtoFromEntity(contaDebito, lancamentosDebito); 				
		dashboardDto.setContaBanco(contaDtoDebito);
		
		Conta contaCredito = contaService.getContaWithLoginAndTipoConta(login, TipoConta.CC);
		List<Lancamento> lancamentosCredito = lancamentoService.getLancamentosWithIdContaAndDateBetween(contaCredito.getId(), inicioDate, fimDate);
		ContaDto contaDtoCredito = contaMapper.getContaDtoFromEntity(contaCredito, lancamentosCredito); 				
		dashboardDto.setContaCredito(contaDtoCredito);
		
		return dashboardDto;
	}
}
