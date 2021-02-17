package academy.gama.desafio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.MovimentacaoDto;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Lancamento;
import academy.gama.desafio.repository.LancamentoRepository;
import enums.TipoConta;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo, Renan Alencar, Wesley Vicente
 *
 */
@Service
public class LancamentoService {
	@Autowired
	LancamentoRepository lancamentoRepository;
	@Autowired
	ContaService contaService;
	@Autowired
	PlanoContaService planoContaService;
	
	@Transactional
	public List<Lancamento> getLancamentosWithIdContaAndDateBetween(Integer idConta, LocalDateTime inicio, LocalDateTime fim){
		List<Lancamento> lancamentos = lancamentoRepository.getLancamentosWithIdContaAndDateBetween(idConta, inicio, fim);		
		return lancamentos;
	}	
	
	@Transactional
	public void inserirLancamento(MovimentacaoDto movimentacaoDto) {
		Lancamento lancamento = new Lancamento();
				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime data = LocalDateTime.parse(movimentacaoDto.getData(), formatter);
		lancamento.setData(data);
		lancamento.setDescricao(movimentacaoDto.getDescricao());
		lancamento.setIdConta(movimentacaoDto.getConta());
		lancamento.setPlanoConta(planoContaService.getPlanoContaWithId(movimentacaoDto.getPlanoConta()));
		lancamento.setValor(movimentacaoDto.getValor());
		
		lancamentoRepository.save(lancamento);
		
		Conta contaUsuario = contaService.getContaWithId(movimentacaoDto.getConta());
		switch(lancamento.getPlanoConta().getTipoLancamento()) {
		case D:			
			contaService.debitarValor(contaUsuario, lancamento.getValor());
			break;
		case R:			
			contaService.acrescentarValor(contaUsuario, lancamento.getValor());
			break;
		case TC:
			Conta contaDebito = contaService.getContaWithLoginAndTipoConta(contaUsuario.getUsuario().getLogin(), TipoConta.CB);
			Conta contaCredito = contaService.getContaWithLoginAndTipoConta(contaDebito.getUsuario().getLogin(), TipoConta.CC);
			contaService.debitarValor(contaDebito, movimentacaoDto.getValor());
			contaService.acrescentarValor(contaCredito, movimentacaoDto.getValor());
			break;
		case TU:
			Conta contaDebitoOrigem = contaService.getContaWithLoginAndTipoConta(contaUsuario.getUsuario().getLogin(), TipoConta.CB);			
			Conta contaDebitoDestino = contaService.getContaWithLoginAndTipoConta(movimentacaoDto.getContaDestino(), TipoConta.CB);
			contaService.debitarValor(contaDebitoOrigem, movimentacaoDto.getValor());
			contaService.acrescentarValor(contaDebitoDestino, movimentacaoDto.getValor());
			break;		
			//Precisamos fazer uma movimentação para a conta de destino
		}
		
	}
}
