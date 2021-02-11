package academy.gama.desafio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.service.UsuarioService;

@RestController
@RequestMapping(value="/user")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Usuario obj = service.search(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@PostMapping()
	public void addConta(@RequestBody UsuarioDto usuarioDtp) throws Exception {
		service.addUsuario(usuarioDtp);
		
	}
}
