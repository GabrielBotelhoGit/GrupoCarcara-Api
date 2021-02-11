package academy.gama.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.PlanoContaRepository;
import academy.gama.desafio.repository.UsuarioRepository;

@Service
public class PlanoContaService {
	@Autowired
	PlanoContaRepository planoContaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public PlanoConta getPlanoContaWithId(Integer idPlanoConta) {		
		return planoContaRepository.getPlanoContaWithId(idPlanoConta);
	}

	public boolean addPlanoConta(PlanoConta planoConta) {
		if(existsUsuarioWithLogin(planoConta.getLogin())) {
			planoContaRepository.save(planoConta);
			return true;
		}
		else {
			return false;			
		}	
		
	}
	public boolean existsUsuarioWithLogin(String login) {
		Usuario usuario = usuarioRepository.getUsuarioWithLogin(login);
		if(usuario != null) {			
			return true;
		}
		else {
			return false;			
		}
	}
}
