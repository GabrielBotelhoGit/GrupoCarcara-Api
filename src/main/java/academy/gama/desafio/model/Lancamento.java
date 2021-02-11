/**
 * 
 */
package academy.gama.desafio.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author B�rbara Rodrigues, Gabriel Botelho, Guilherme Cruz, Lucas Caputo,
 *         Renan Alencar, Wesley Vicente
 *
 */
@Entity
public class Lancamento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_conta", nullable=false)
	private Conta conta;
	@Column(nullable = false)
	private LocalDateTime data;
	@Column(length = 200)
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "id_plano_conta", nullable=false)
	private PlanoConta planoConta;		
	@Column(nullable = false, precision = 9, scale = 3)
	private double valor;

	public Lancamento(Conta conta, LocalDateTime data, String descricao, PlanoConta planoConta,
			double valor) {
		super();		
		this.conta = conta;
		this.data = data;
		this.descricao = descricao;
		this.planoConta = planoConta;
		this.valor = valor;
	}
	
	public Lancamento(int id, Conta conta, LocalDateTime data, String descricao, PlanoConta planoConta,
			double valor) {
		super();
		this.id = id;
		this.conta = conta;
		this.data = data;
		this.descricao = descricao;
		this.planoConta = planoConta;
		this.valor = valor;
	}

	public Lancamento() { 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PlanoConta getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
