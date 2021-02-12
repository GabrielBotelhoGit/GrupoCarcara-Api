package academy.gama.desafio.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.PlanoContaDto;
import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.repository.PlanoContaRepository;

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
	public boolean addPlanoConta(PlanoContaDto planocontaDto) {
		PlanoConta planoConta = new PlanoConta();
		planoConta.setDescricao(planocontaDto.getDescricao());
		planoConta.setLogin(planocontaDto.getLogin());
		if(usuarioService.existsUsuarioWithLogin(planoConta.getLogin())) {
			planoContaRepository.save(planoConta);
			return true;
		}
		else {
			return false;			
		}	
		
	}
		
	@Transactional
	public List<PlanoContaDto> listarPlanoConta(String login){
		List<PlanoConta> list = planoContaRepository.getListaPlanoContaByUser(login);
		return list.stream().map( x -> new PlanoContaDto(x)).collect(Collectors.toList());
		
	}
	
	@Transactional
	public boolean deletaPlanoConta(int id, String login) {
		planoContaRepository.deleteById(id);
		return !planoContaRepository.existsById(id);
	}
	
}
