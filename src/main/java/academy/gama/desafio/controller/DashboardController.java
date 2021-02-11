package academy.gama.desafio.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.gama.desafio.dto.DashboardDto;
import academy.gama.desafio.service.DashboardService;

@RestController
@RequestMapping(value="/dashboard")
public class DashboardController {
	@Autowired
	DashboardService dashboardService;
	
	@RequestMapping(method = RequestMethod.GET,
			params = {
					"login",
					"inicio",
					"fim"
			})
	public ResponseEntity<?> getDashboard(@RequestParam("login") String login, @RequestParam("inicio") String inicio, @RequestParam("fim") String fim) {
		inicio = inicio.concat(" 00:00");
		fim = fim.concat(" 00:00");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime inicioDate = LocalDateTime.parse(inicio, formatter);
		LocalDateTime fimDate = LocalDateTime.parse(fim, formatter);
		DashboardDto dto = dashboardService.getDashboard(login, inicioDate, fimDate);
		return ResponseEntity.ok().body(dto);		
	}
}