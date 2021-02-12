package academy.gama.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.gama.desafio.model.PlanoConta;

@Repository
public interface PlanoContaRepository extends CrudRepository<PlanoConta, Integer> {
	@Query(value="Select planoConta from PlanoConta planoConta where planoConta.id = :idPlanoConta")
	public PlanoConta getPlanoContaWithId(@Param("idPlanoConta") Integer idPlanoConta);
		
	@Query(value="Select planoConta from PlanoConta planoConta where planoConta.login = :loginUsuario and planoConta.ativo = :ativo")
	public List<PlanoConta> getListaPlanoContaByUserAndAtivo(@Param("loginUsuario") String loginUsuario, @Param("ativo") boolean ativo);
}