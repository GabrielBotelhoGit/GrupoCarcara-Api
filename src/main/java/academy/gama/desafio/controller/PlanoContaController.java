package academy.gama.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.gama.desafio.dto.PlanoContaDto;
import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.service.PlanoContaService;

@RestController
@RequestMapping(value = "/planoconta")
public class PlanoContaController {
	@Autowired
	private PlanoContaService planoContaService;

	@RequestMapping(method = RequestMethod.GET, params = "login")
	public ResponseEntity<?> getContas(@RequestParam(required = false, name = "login") String login) throws IllegalArgumentException {
		boolean ativo = true;		
		return ResponseEntity.ok(planoContaService.getPlanoContaDtoByUserAndAtivo(login, ativo));						
	} 
	
	@PostMapping()
	public ResponseEntity<PlanoConta> addConta(@RequestBody PlanoContaDto planoContaDto) throws Exception {
		
		try {
			planoContaService.addPlanoConta(planoContaDto);
			return new ResponseEntity<>(HttpStatus.CREATED);			
		}
		catch(IllegalArgumentException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
					

	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id, @RequestBody PlanoContaDto planoContaDto) {
		planoContaService.updatePlanoContaById(id, planoContaDto);
		return ResponseEntity.ok().build();
		
	}

}
