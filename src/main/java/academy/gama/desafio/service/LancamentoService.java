package academy.gama.desafio.service;

import java.time.LocalDateTime;
import java.util.List;

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
	
	public List<Lancamento> getLancamentosWithIdConta(Integer idConta, LocalDateTime inicio, LocalDateTime fim){
		List<Lancamento> lancamentos = lancamentoRepository.getLancamentosWithIdContaAndDateBetween(idConta, inicio, fim);
		for(Lancamento lancamento: lancamentos) {
			lancamento.setPlanoConta(null);
		}
		return lancamentos;
	}
	
	public void inserirLancamento(MovimentacaoDto movimentacaoDto) {
		Lancamento lancamento = new Lancamento();
				
		lancamento.setData(movimentacaoDto.getData());
		lancamento.setDescricao(movimentacaoDto.getDescricao());
		lancamento.setConta(contaService.getContaWithId(movimentacaoDto.getConta()));
		lancamento.setPlanoConta(planoContaService.getPlanoContaWithId(movimentacaoDto.getPlanoConta()));
		lancamento.setValor(movimentacaoDto.getValor());
		
		lancamentoRepository.save(lancamento);
		
		Conta contaUsuario = new Conta();
		switch(lancamento.getPlanoConta().getTipoLancamento()) {
		case D:
			contaUsuario = lancamento.getConta();
			contaService.debitarValor(contaUsuario, lancamento.getValor());
			break;
		case R:
			contaUsuario = lancamento.getConta();
			contaService.acrescentarValor(contaUsuario, lancamento.getValor());
			break;
		case TC:
			Conta contaDebito = lancamento.getConta();
			Conta contaCredito = contaService.getContaWithLoginAndTipoConta(contaDebito.getUsuario().getLogin(), TipoConta.CC);
			contaService.debitarValor(contaDebito, movimentacaoDto.getValor());
			contaService.acrescentarValor(contaCredito, movimentacaoDto.getValor());
			break;
		case TU:
			Conta contaDebitoOrigem = lancamento.getConta();
			Conta contaDebitoDestino = contaService.getContaWithLoginAndTipoConta(movimentacaoDto.getContaDestino(), TipoConta.CB);
			contaService.debitarValor(contaDebitoOrigem, movimentacaoDto.getValor());
			contaService.acrescentarValor(contaDebitoDestino, movimentacaoDto.getValor());
			break;		
			
		}
		
	}
}
