package academy.gama.desafio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.gama.desafio.dto.PlanoContaDto;
import academy.gama.desafio.service.PlanoContaService;

@RestController
@RequestMapping(value = "/planoconta")
public class PlanoContaController {
	@Autowired
	private PlanoContaService planoContaService;

	@RequestMapping(method = RequestMethod.GET, params = "login")
	public ResponseEntity<?> getContas(@RequestParam(required = false, name = "login") String login)
			throws IllegalArgumentException {
		boolean ativo = true;
		return ResponseEntity.ok(planoContaService.getPlanoContaDtoByUserAndAtivo(login, ativo));
	}

	@PostMapping()
	public ResponseEntity<String> addConta(@Valid @RequestBody PlanoContaDto planoContaDto) throws Exception {
		try {
			planoContaService.addPlanoConta(planoContaDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(String.format(
					"Plano Conta %s já existe no sistema e não pode ser criado, por favor tente um diferente.",
					planoContaDto.getDescricao()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(
					("Houve algum erro intento no cadasto e com isso, não pode ser criado, por favor tente mais tarde."),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> find(@Valid @PathVariable Integer id, @RequestBody PlanoContaDto planoContaDto) {

		try {
			planoContaService.updatePlanoContaById(id, planoContaDto);
			return ResponseEntity.ok().build();
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(String.format(
					"Plano Conta %s já existe no sistema e não pode ser criado, por favor tente um diferente.",
					planoContaDto.getDescricao()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(
					("Houve algum erro intento no cadasto e com isso, não pode ser criado, por favor tente mais tarde."),
					HttpStatus.BAD_REQUEST);
		}

	}
	@DeleteMapping
	public ResponseEntity<?> deletePlanoConta(Integer id, String login){
		try {
			planoContaService.deletaPlanoConta(id, login);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					("Houve algum erro interno e o registro não pode ser deletado no momento."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

}
