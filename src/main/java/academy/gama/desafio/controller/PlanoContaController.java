package academy.gama.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.gama.desafio.dto.PlanoContaDto;
import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.service.PlanoContaService;

@RestController
@RequestMapping(value = "/planoconta")
public class PlanoContaController {
	@Autowired
	private PlanoContaService service;

	@PostMapping()
	public ResponseEntity<PlanoConta> addConta(@RequestBody PlanoContaDto planoContaDto) throws Exception {
		
		if (service.addPlanoConta(planoContaDto)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}
