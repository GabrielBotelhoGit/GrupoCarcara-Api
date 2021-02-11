package academy.gama.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.gama.desafio.dto.MovimentacaoDto;
import academy.gama.desafio.service.LancamentoService;

@RestController
@RequestMapping(value="/lancamentos")
public class LancamentoController {
	@Autowired
	LancamentoService lancamentoService;
	
	@PostMapping()
	public ResponseEntity<?> realizar(@RequestBody MovimentacaoDto movimentacaoDto) {						
		lancamentoService.inserirLancamento(movimentacaoDto);
		return ResponseEntity.ok().build();		
	}
}

