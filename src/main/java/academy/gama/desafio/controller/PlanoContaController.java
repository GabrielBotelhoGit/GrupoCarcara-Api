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
import academy.gama.desafio.service.PlanoContaService;

@RestController
@RequestMapping(value = "/planoconta")
public class PlanoContaController {
	@Autowired
	private PlanoContaService planoContaService;

	@RequestMapping(method = RequestMethod.GET, params = {"login"})
	public ResponseEntity<?> getContas(@RequestParam(name = "login") String login)
			throws IllegalArgumentException {
		boolean ativo = true;
		return ResponseEntity.ok(planoContaService.getPlanoContaDtoByUserAndAtivo(login, ativo));
	}

	@PostMapping()
	public ResponseEntity<?> addConta(@RequestBody PlanoContaDto planoContaDto) throws Exception {
		try {
			return new ResponseEntity<>(planoContaService.addPlanoConta(planoContaDto), HttpStatus.CREATED);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(String.format("Usuário %s não encontrado", planoContaDto.getLogin()),
					HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(("Houve algum erro intento, por favor tente mais tarde."),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id, @RequestBody PlanoContaDto planoContaDto) {

		try {
			return new ResponseEntity<>(planoContaService.updatePlanoContaById(id, planoContaDto), HttpStatus.CREATED);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(String.format("Usuário %s não encontrado", planoContaDto.getLogin()),
					HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(
					("Houve algum erro intento, por favor tente mais tarde."),
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value="/param", method = RequestMethod.GET, params = {"login", "tipoLancamento"})
	public ResponseEntity<?> getContasWitTipoLancamento(@RequestParam(name = "login") String login, @RequestParam(name="tipoLancamento",required = false) String[] tipoLancamento)
			throws IllegalArgumentException {
		boolean ativo = true;
		return ResponseEntity.ok(planoContaService.getPlanoContaDtoByUserAndAtivoAndTiposLancamento(login, ativo, tipoLancamento));
	}

}
