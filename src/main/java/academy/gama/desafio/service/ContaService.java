package academy.gama.desafio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.model.Usuario;
import academy.gama.desafio.repository.ContaRepository;
import academy.gama.desafio.repository.PlanoContaRepository;
import enums.TipoConta;
import enums.TipoLancamento;

@Service
public class ContaService {
	@Autowired
	ContaRepository contaRepository;

	@Autowired
	LancamentoService lancamentoService;

	@Autowired
	PlanoContaRepository planoContaRRepository;

	@Transactional
	public void addConta(Conta conta) {
		contaRepository.save(conta);
	}

	@Transactional
	public void addContasIniciais(Usuario usuario) {
		Conta conta = new Conta();
		conta.setUsuario(usuario);

		conta.setSaldo(0.0d);
		conta.setDescricao(TipoConta.CB.getDescricao());
		conta.setTipoConta(TipoConta.CB);
		contaRepository.save(conta);

		conta = new Conta();
		conta.setUsuario(usuario);

		conta.setSaldo(0.0d);
		conta.setDescricao(TipoConta.CC.getDescricao());
		conta.setTipoConta(TipoConta.CC);
		contaRepository.save(conta);
	}

	@Transactional
	public void addPlanosDeContaIniciais(Usuario usuario) {
		PlanoConta planoContaR = new PlanoConta();
		planoContaR.setAtivo(true);
		planoContaR.setDescricao("Receita");
		planoContaR.setLogin(usuario.getLogin());
		planoContaR.setTipoLancamento(TipoLancamento.R);
		planoContaRRepository.save(planoContaR);
		
		PlanoConta planoContaD = new PlanoConta();
		planoContaD.setAtivo(true);
		planoContaD.setDescricao("Despesa");
		planoContaD.setLogin(usuario.getLogin());
		planoContaD.setTipoLancamento(TipoLancamento.D);
		planoContaRRepository.save(planoContaD);
		
		PlanoConta planoContaTC = new PlanoConta();
		planoContaTC.setAtivo(true);
		planoContaTC.setDescricao("Transferência entre contas");
		planoContaTC.setLogin(usuario.getLogin());
		planoContaTC.setTipoLancamento(TipoLancamento.TC);
		planoContaRRepository.save(planoContaTC);
		
		PlanoConta planoContaTU = new PlanoConta();
		planoContaTU.setAtivo(true);
		planoContaTU.setDescricao("Transferência entre usuários");
		planoContaTU.setLogin(usuario.getLogin());
		planoContaTU.setTipoLancamento(TipoLancamento.TU);
		planoContaRRepository.save(planoContaTU);

	}

	@Transactional
	public List<Conta> getContasWithLogin(String login) {
		return contaRepository.getContasWithLogin(login);
	}

	@Transactional
	public Conta getContaWithLoginAndTipoConta(String login, TipoConta tipoConta) {
		List<Conta> contasUsuario = this.getContasWithLogin(login);
		Conta contaTipoPedido = new Conta();

		for (Conta conta : contasUsuario) {
			if (conta.getTipoConta().equals(tipoConta)) {
				contaTipoPedido = conta;
			}
		}

		return contaTipoPedido;

	}

	@Transactional
	public List<Conta> getAll() {
		return (List<Conta>) contaRepository.findAll();
	}

	@Transactional
	public Conta getContaWithId(Integer idConta) {
		return contaRepository.getContaWithId(idConta);
	}

	@Transactional
	public void debitarValor(Conta conta, double valor) {
		conta.setSaldo(conta.getSaldo() - valor);
		contaRepository.save(conta);
	}

	@Transactional
	public void acrescentarValor(Conta conta, double valor) {
		conta.setSaldo(conta.getSaldo() + valor);
		contaRepository.save(conta);
	}

}
