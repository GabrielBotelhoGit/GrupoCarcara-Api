package academy.gama.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.model.Conta;
import academy.gama.desafio.repository.ContaRepository;
import enums.TipoConta;

@Service
public class ContaService {
	@Autowired
	ContaRepository contaRepository;
	public void addConta(Conta conta) {		
		contaRepository.save(conta);		
	}
	
	public List<Conta> getContaWithLogin(String login) {		
		return contaRepository.getContaWithLogin(login);
	}
	
	public Conta getContaWithLoginAndTipoConta(String login, TipoConta tipoConta) {		
		return contaRepository.getContaWithLoginAndTipoConta(login, tipoConta);
	}
	
	public List<Conta> getAll(){
		return (List<Conta>) contaRepository.findAll();
	}
}
