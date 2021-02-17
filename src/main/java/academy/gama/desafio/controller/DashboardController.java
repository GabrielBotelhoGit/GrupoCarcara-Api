package academy.gama.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.gama.desafio.dto.DashboardDto;
import academy.gama.desafio.service.DashboardService;

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {
	@Autowired
	DashboardService dashboardService;

	@RequestMapping(method = RequestMethod.GET, params = { "login", "inicio", "fim" })
	public ResponseEntity<?> getDashboard( @RequestParam("login") String login,
			@RequestParam("inicio") String inicio, @RequestParam("fim") String fim) {
		try {
			DashboardDto dto = dashboardService.getDashboard(login, inicio, fim);
			return ResponseEntity.ok().body(dto);
		} catch (IllegalStateException e) {
			return new ResponseEntity<>(
					String.format("Usuario %s Não encontrado, por favor tente um login diferente.", login),
					HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(String.format("Houve algum erro intento, por favor tente mais tarde.", login),
					HttpStatus.BAD_REQUEST);
		}
	}
}
