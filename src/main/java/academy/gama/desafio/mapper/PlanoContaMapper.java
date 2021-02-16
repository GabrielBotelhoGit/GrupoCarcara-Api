package academy.gama.desafio.mapper;

import academy.gama.desafio.dto.PlanoContaDto;
import academy.gama.desafio.model.PlanoConta;
import enums.TipoLancamento;

public class PlanoContaMapper {
	public PlanoConta getPlanoContaFromDto(PlanoContaDto planoContaDto) {
		PlanoConta planoConta = new PlanoConta();
		planoConta.setId(planoContaDto.getId());
		planoConta.setDescricao(planoContaDto.getDescricao());
		planoConta.setTipoLancamento(TipoLancamento.valueOf(planoContaDto.getTipoLancamento()));
		planoConta.setLogin(planoContaDto.getLogin());
		planoConta.setAtivo(planoContaDto.isAtivo());
		return planoConta;
	}
	
	public PlanoContaDto getPlanoContaDtoFromEntity(PlanoConta planoConta) {
		PlanoContaDto planoContaDto = new PlanoContaDto();
		planoContaDto.setId(planoConta.getId());
		planoContaDto.setDescricao(planoConta.getDescricao());
		planoContaDto.setTipoLancamento(planoConta.getTipoLancamento().name());
		planoContaDto.setLogin(planoConta.getLogin());
		planoContaDto.setAtivo(planoConta.isAtivo());
		return planoContaDto;
	}
}
