package academy.gama.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.gama.desafio.model.PlanoConta;
import enums.TipoLancamento;

@Repository
public interface PlanoContaRepository extends JpaRepository<PlanoConta, Integer> {
	@Query(value="Select planoConta from PlanoConta planoConta where planoConta.id = :idPlanoConta")
	public PlanoConta getPlanoContaWithId(@Param("idPlanoConta") Integer idPlanoConta);
		
	@Query(value="Select planoConta from PlanoConta planoConta where planoConta.login = :loginUsuario and planoConta.ativo = :ativo")
	public List<PlanoConta> getListaPlanoContaByUserAndAtivo(@Param("loginUsuario") String loginUsuario, @Param("ativo") boolean ativo);
	
	@Query(value="Select planoConta from PlanoConta planoConta where planoConta.login = :loginUsuario and planoConta.ativo = :ativo and planoConta.tipoLancamento in :tiposLancamento")
	public List<PlanoConta> getListaPlanoContaByUserAndAtivoAndTiposLancamento(@Param("loginUsuario") String loginUsuario, @Param("ativo") boolean ativo, @Param("tiposLancamento") List<TipoLancamento> tiposLancamento);
}