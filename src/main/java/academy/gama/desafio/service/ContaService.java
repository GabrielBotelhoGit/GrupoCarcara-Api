package academy.gama.desafio.service;

import java.time.LocalDateTime;
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
	
	@Autowired
	LancamentoService lancamentoService;
	public void addConta(Conta conta) {		
		contaRepository.save(conta);		
	}
	
	public List<Conta> getContasWithLogin(String login) {		
		return contaRepository.getContasWithLogin(login);
	}
	
	public List<Conta> getContasWithLoginAndDateBetween(String login, LocalDateTime inicio, LocalDateTime fim) {		
		return contaRepository.getContasWithLoginAndDateBetween(login, inicio, fim);
	}
	
	public Conta getContaWithLoginAndTipoConta(String login, TipoConta tipoConta) {
		List<Conta> contasUsuario = this.getContasWithLogin(login);
		Conta contaTipoPedido = new Conta();
		
		for(Conta conta : contasUsuario) {
			if(conta.getTipoConta().equals(tipoConta)) {
				contaTipoPedido = conta;
			}			
		}			
		
		return contaTipoPedido;
		//return contaRepository.getContaWithLoginAndTipoConta(login, tipoConta);
		
	}

	public Conta getContaWithLoginAndTipoContaAndDateBetween(String login, TipoConta tipoConta, LocalDateTime inicio, LocalDateTime fim) {
		List<Conta> contasUsuario = this.getContasWithLoginAndDateBetween(login, inicio, fim);
		Conta contaTipoPedido = new Conta();
		
		for(Conta conta : contasUsuario) {
			if(conta.getTipoConta().equals(tipoConta)) {
				contaTipoPedido = conta;
			}			
		}			
		
		return contaTipoPedido;
		//return contaRepository.getContaWithLoginAndTipoConta(login, tipoConta);
		
	}	
	
	public List<Conta> getAll(){
		return (List<Conta>) contaRepository.findAll();
	}
	
	public Conta getContaWithId(Integer idConta) {		
		return contaRepository.getContaWithId(idConta);
	}	
	
	public void debitarValor(Conta conta, double valor) {		
		conta.setSaldo(conta.getSaldo() - valor);
		contaRepository.save(conta);
	}
	
	public void acrescentarValor(Conta conta, double valor) {		
		conta.setSaldo(conta.getSaldo() + valor);
		contaRepository.save(conta);
	}
	
	
}
