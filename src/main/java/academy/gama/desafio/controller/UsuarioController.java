package academy.gama.desafio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.exceptions.ObjectNotFoundException;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.service.UsuarioService;

@RestController
@RequestMapping(value = "/user")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@Valid @PathVariable Integer id) {
		try {
			Usuario obj = service.search(id);
			return ResponseEntity.ok().body(obj);
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<>(
					String.format("Usuario de ID %s Não encontrado, por favor tente um ID diferente.", id),
					HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(("Houve algum erro intento, por favor tente mais tarde."),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping()
	public ResponseEntity<String> addConta(@Valid @RequestBody UsuarioDto usuarioDTO) {
		try {
			service.addUsuario(usuarioDTO);
			return new ResponseEntity<>(
					String.format("Parabéns, usuario %s criado com sucesso!", usuarioDTO.getLogin()),
					HttpStatus.CREATED);
		} catch (IllegalStateException e) {
			return new ResponseEntity<>(String.format(
					"Usuario %s já existe no sistema e não pode ser criado, por favor tente um login diferente.",
					usuarioDTO.getLogin()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(
					("Houve algum erro intento no cadasto e usuario não pode ser criado, por favor tente mais tarde."),
					HttpStatus.BAD_REQUEST);
		}
	}

}
