package academy.gama.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.gama.desafio.dto.PlanoContaDto;
import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.service.PlanoContaService;

@RestController
@RequestMapping(value="/planoconta")
public class PlanoContaController {
	@Autowired
	private PlanoContaService service;
	
	@PostMapping()
	public void addConta(@RequestBody PlanoContaDto body) throws Exception {
		PlanoConta planoConta = new PlanoConta();
		planoConta.setDescricao(body.getDescricao());
		planoConta.setLogin(body.getLogin());
		planoConta.setTipoLancamento(body.getTipoLancamento());
		service.addPlanoConta(planoConta);
	}
	
}
