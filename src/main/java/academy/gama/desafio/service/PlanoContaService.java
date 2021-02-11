package academy.gama.desafio.service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.PlanoContaDto;
import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.PlanoContaRepository;
import academy.gama.desafio.repository.UsuarioRepository;
import enums.TipoLancamento;

@Service
public class PlanoContaService {
	@Autowired
	PlanoContaRepository planoContaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public PlanoConta getPlanoContaWithId(Integer idPlanoConta) {		
		return planoContaRepository.getPlanoContaWithId(idPlanoConta);
	}

	public boolean addPlanoConta(PlanoContaDto planocontaDto) {
		PlanoConta planoConta = new PlanoConta();
		planoConta.setDescricao(planocontaDto.getDescricao());
		planoConta.setLogin(planocontaDto.getLogin());
		TipoLancamento tipoLancamento = Enum.valueOf(TipoLancamento.class, planocontaDto.getTipoLancamento());
		planoConta.setTipoLancamento(tipoLancamento);
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
	@Transactional
	public List<PlanoContaDto> listarPlanoConta(String login){
		List<PlanoConta> list = planoContaRepository.getListaPlanoContaById(login);
		return list.stream().map( x -> new PlanoContaDto(x)).collect(Collectors.toList());
		
	}
	
}
