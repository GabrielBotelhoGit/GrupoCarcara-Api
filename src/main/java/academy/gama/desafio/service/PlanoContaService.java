package academy.gama.desafio.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.PlanoContaDto;
import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.repository.PlanoContaRepository;
import enums.TipoLancamento;

@Service
public class PlanoContaService {
	@Autowired
	PlanoContaRepository planoContaRepository;
	@Autowired
	UsuarioService usuarioService;
	
	@Transactional
	public PlanoConta getPlanoContaWithId(Integer idPlanoConta) {		
		return planoContaRepository.getPlanoContaWithId(idPlanoConta);
	}
	
	@Transactional
	public void addPlanoConta(PlanoContaDto planoContaDto) {
		PlanoConta planoConta = new PlanoConta();
		planoConta.setDescricao(planoContaDto.getDescricao());
		planoConta.setLogin(planoContaDto.getLogin());
		planoConta.setAtivo(true);
		planoConta.setTipoLancamento(TipoLancamento.valueOf(planoContaDto.getTipoLancamento()));
		if(usuarioService.existsUsuarioWithLogin(planoConta.getLogin())) {
			planoContaRepository.save(planoConta);			
		}
		else {
			throw new IllegalArgumentException();
		}	
		
	}
	
	@Transactional 
	public void updatePlanoContaById(Integer id, PlanoContaDto planoContaDto) {
		PlanoConta planoConta = getPlanoContaWithId(id);
		planoConta.setLogin(planoContaDto.getLogin());
		planoConta.setDescricao(planoContaDto.getDescricao());
		planoConta.setAtivo(planoContaDto.isAtivo());
		planoConta.setTipoLancamento(TipoLancamento.valueOf(planoContaDto.getTipoLancamento()));
		planoContaRepository.save(planoConta);
	}
		
	@Transactional
	public List<PlanoContaDto> getPlanoContaDtoByUserAndAtivo(String login, boolean ativo){
		List<PlanoConta> list = planoContaRepository.getListaPlanoContaByUserAndAtivo(login, ativo);	
		return list.stream().map( x -> new PlanoContaDto(x)).collect(Collectors.toList());
		
	}
	
	@Transactional
	public boolean deletaPlanoConta(int id, String login) {
		planoContaRepository.deleteById(id);
		return !planoContaRepository.existsById(id);
	}
	
}
