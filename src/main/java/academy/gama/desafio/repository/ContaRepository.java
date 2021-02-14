package academy.gama.desafio.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.gama.desafio.model.Conta;
import enums.TipoConta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{			
	@Query(value = "Select conta from Conta conta Left Join Fetch conta.usuario usuario Left Join Fetch conta.lancamentos lancamentos Left Join Fetch lancamentos.planoConta where conta.usuario.login = :loginUsuario")	
	List<Conta> getContasWithLogin(@Param("loginUsuario") String login);
	
	@Query(value = "Select conta from Conta conta Left Join Fetch conta.usuario usuario Left Join Fetch conta.lancamentos lancamentos Left Join Fetch lancamentos.planoConta where conta.usuario.login = :loginUsuario and ((lancamentos.data between :inicio and :fim) or lancamentos.data is null)")
	List<Conta> getContasWithLoginAndDateBetween(@Param("loginUsuario") String login, @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
	
	@Query(value = "Select conta from Conta conta Left Join Fetch conta.usuario usuario Left Join Fetch conta.lancamentos lancamentos Left Join Fetch lancamentos.planoConta where conta.usuario.login = :loginUsuario and conta.tipoConta = :tipoConta")
	Conta getContaWithLoginAndTipoConta(@Param("loginUsuario") String login, @Param("tipoConta") TipoConta tipoConta);
	
	@Query(value="Select conta from Conta conta where conta.id = idConta")
	Conta getContaWithId(@Param("idConta") Integer idConta);
}
