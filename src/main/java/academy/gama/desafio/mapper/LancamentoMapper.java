package academy.gama.desafio.mapper;

import java.util.ArrayList;
import java.util.List;

import academy.gama.desafio.dto.LancamentoDto;
import academy.gama.desafio.model.Lancamento;

public class LancamentoMapper {	
	
	public Lancamento getLancamentoFromDto(LancamentoDto lancamentoDto){
		Lancamento lancamento = new Lancamento();
		
		PlanoContaMapper planoContaMapper = new PlanoContaMapper();
		
		lancamento.setId(lancamentoDto.getId());		
		lancamento.setData(lancamentoDto.getData());
		lancamento.setDescricao(lancamentoDto.getDescricao());		
		lancamento.setPlanoConta(planoContaMapper.getPlanoContaFromDto(lancamentoDto.getPlanoConta()));
		lancamento.setValor(lancamentoDto.getValor());
		return lancamento;
	}
	
	public LancamentoDto getLancamentoDtoFromEntity(Lancamento lancamento){
		LancamentoDto lancamentoDto = new LancamentoDto();
		
		PlanoContaMapper planoContaMapper = new PlanoContaMapper();
		
		lancamentoDto.setId(lancamento.getId());
		lancamentoDto.setConta(lancamento.getIdConta());
		lancamentoDto.setData(lancamento.getData());
		lancamentoDto.setDescricao(lancamento.getDescricao());		
		lancamentoDto.setPlanoConta(planoContaMapper.getPlanoContaDtoFromEntity(lancamento.getPlanoConta()));
		lancamentoDto.setValor(lancamento.getValor());
		return lancamentoDto;
	}
	
	public List<LancamentoDto> getLancamentosDtoFromEntity(List<Lancamento> lancamentos){
		List<LancamentoDto> lancamentosDto = new ArrayList<LancamentoDto>();
		
		for(Lancamento lancamento : lancamentos) {
			lancamentosDto.add(getLancamentoDtoFromEntity(lancamento));
		}
		return lancamentosDto;
	}
}
