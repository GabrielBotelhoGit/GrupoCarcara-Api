package academy.gama.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.repository.PlanoContaRepository;

@Service
public class PlanoContaService {
	@Autowired
	PlanoContaRepository planoContaRepository;
	
	public PlanoConta getPlanoContaWithId(Integer idPlanoConta) {		
		return planoContaRepository.getPlanoContaWithId(idPlanoConta);
	}
}
