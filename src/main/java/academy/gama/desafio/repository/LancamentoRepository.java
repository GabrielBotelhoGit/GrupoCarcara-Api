package academy.gama.desafio.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.gama.desafio.model.Lancamento;

@Repository
public interface LancamentoRepository extends CrudRepository<Lancamento, Integer> {
	@Query(nativeQuery = true, value = "Select * from lancamento where lancamento.id_conta = :idConta and lancamento.data between :dataInicio and :dataFim ")
	public List<Lancamento> getLancamentosWithIdContaAndDateBetween(@Param("idConta") Integer idConta, @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}
