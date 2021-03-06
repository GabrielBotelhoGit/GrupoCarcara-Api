package academy.gama.desafio.service;

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
	public PlanoContaDto addPlanoConta(PlanoContaDto planoContaDto) {
		PlanoConta planoConta = new PlanoConta();
		planoConta.setDescricao(planoContaDto.getDescricao());
		planoConta.setLogin(planoContaDto.getLogin());
		planoConta.setAtivo(true);
		planoConta.setTipoLancamento(TipoLancamento.valueOf(planoContaDto.getTipoLancamento()));
		planoConta.setPadrao(false);
		if (usuarioService.existsUsuarioWithLogin(planoConta.getLogin())) {
			planoContaRepository.save(planoConta);
			planoContaDto.setId(planoConta.getId());
			return planoContaDto;
		} else {
			throw new IllegalArgumentException();
		}

	}
	
	@Transactional
	public void addPlanosDeContaIniciais(Usuario usuario) {
		PlanoConta planoContaR = new PlanoConta();
		planoContaR.setAtivo(true);
		planoContaR.setDescricao("Receita");
		planoContaR.setLogin(usuario.getLogin());
		planoContaR.setTipoLancamento(TipoLancamento.R);
		planoContaR.setPadrao(true);
		planoContaRepository.save(planoContaR);
		
		PlanoConta planoContaD = new PlanoConta();
		planoContaD.setAtivo(true);
		planoContaD.setDescricao("Despesa");
		planoContaD.setLogin(usuario.getLogin());
		planoContaD.setTipoLancamento(TipoLancamento.D);
		planoContaD.setPadrao(true);
		planoContaRepository.save(planoContaD);
		
		PlanoConta planoContaTC = new PlanoConta();
		planoContaTC.setAtivo(true);
		planoContaTC.setDescricao("Transferência entre contas");
		planoContaTC.setLogin(usuario.getLogin());
		planoContaTC.setTipoLancamento(TipoLancamento.TC);
		planoContaTC.setPadrao(true);
		planoContaRepository.save(planoContaTC);
		
		PlanoConta planoContaTU = new PlanoConta();
		planoContaTU.setAtivo(true);
		planoContaTU.setDescricao("Transferência entre usuários");
		planoContaTU.setLogin(usuario.getLogin());
		planoContaTU.setTipoLancamento(TipoLancamento.TU);
		planoContaTU.setPadrao(true);
		planoContaRepository.save(planoContaTU);

	}

	@Transactional
	public PlanoContaDto updatePlanoContaById(Integer id, PlanoContaDto planoContaDto) {
		PlanoConta planoConta = getPlanoContaWithId(id);
		planoConta.setLogin(planoContaDto.getLogin());
		planoConta.setDescricao(planoContaDto.getDescricao());
		planoConta.setAtivo(planoContaDto.isAtivo());
		planoConta.setTipoLancamento(TipoLancamento.valueOf(planoContaDto.getTipoLancamento()));		
		planoContaRepository.save(planoConta);
		planoContaDto.setId(id);
		return planoContaDto;
	}

	@Transactional
	public List<PlanoContaDto> getPlanoContaDtoByUserAndAtivo(String login, boolean ativo) {
		List<PlanoConta> list = new ArrayList<PlanoConta>();				
		list = planoContaRepository.getListaPlanoContaByUserAndAtivo(login, ativo);			
		return list.stream().map(x -> new PlanoContaDto(x)).collect(Collectors.toList());

	}
	
	@Transactional
	public List<PlanoContaDto> getPlanoContaDtoByUserAndAtivoAndTiposLancamento(String login, boolean ativo, String[] tiposLancamento) {
		List<PlanoConta> list = new ArrayList<PlanoConta>();				
		List<TipoLancamento> listTipoLancamento = new ArrayList<TipoLancamento>();
		
		for(String tipo : tiposLancamento) {
			listTipoLancamento.add(TipoLancamento.valueOf(tipo));
		}
		
		list = planoContaRepository.getListaPlanoContaByUserAndAtivoAndTiposLancamento(login, ativo, listTipoLancamento);			
				
		return list.stream().map(x -> new PlanoContaDto(x)).collect(Collectors.toList());

	}

	@Transactional
	public boolean deletaPlanoConta(int id, String login) {
		planoContaRepository.deleteById(id);
		return !planoContaRepository.existsById(id);
	}

}
