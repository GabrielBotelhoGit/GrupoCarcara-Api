package academy.gama.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.gama.desafio.model.Conta;
import enums.TipoConta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Integer>{			
	@Query(nativeQuery = true, value = "Select * from conta where conta.login_usuario = :loginUsuario")
	List<Conta> getContaWithLogin(@Param("loginUsuario") String login);
	@Query(nativeQuery = true, value = "Select * from conta where conta.login_usuario = :loginUsuario and conta.tipo_conta = :tipoConta")
	Conta getContaWithLoginAndTipoConta(@Param("loginUsuario") String login, @Param("tipoConta") TipoConta tipoConta);
}
