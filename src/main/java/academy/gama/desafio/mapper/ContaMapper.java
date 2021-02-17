package academy.gama.desafio.mapper;

import java.util.List;

import academy.gama.desafio.dto.ContaDto;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Lancamento;
import enums.TipoConta;

public class ContaMapper {
	public Conta getContaFromDto(ContaDto contaDto){
		Conta conta = new Conta();
		conta.setId(contaDto.getId());
		conta.setDescricao(contaDto.getDescricao());
		conta.setTipoConta(TipoConta.valueOf(contaDto.getTipoConta()));
		conta.setSaldo(contaDto.getSaldo());
		return conta;
	}
	
	public ContaDto getContaDtoFromEntity(Conta conta) {
		ContaDto contaDto = new ContaDto();
		contaDto.setId(conta.getId());
		contaDto.setDescricao(conta.getDescricao());
		contaDto.setTipoConta(conta.getTipoConta().name());
		contaDto.setSaldo(conta.getSaldo());			
		return contaDto;
	}
	
	public ContaDto getContaDtoFromEntity(Conta conta, List<Lancamento> lancamentos) {
		ContaDto contaDto = getContaDtoFromEntity(conta);
				
		LancamentoMapper lancamentoMapper = new LancamentoMapper();
		
		contaDto.setLancamentos(lancamentoMapper.getLancamentosDtoFromEntity(lancamentos));
		return contaDto;
	}
}
