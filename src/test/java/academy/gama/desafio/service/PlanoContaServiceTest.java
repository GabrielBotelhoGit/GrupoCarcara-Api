package academy.gama.desafio.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import academy.gama.desafio.dto.PlanoContaDto;
import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.mapper.PlanoContaMapper;
import academy.gama.desafio.model.PlanoConta;
import academy.gama.desafio.model.Usuario;
import enums.TipoLancamento;

@SpringBootTest
public class PlanoContaServiceTest {
	@TestConfiguration
	static class PlanoContaServiceTestConfiguration{
		@Bean
		public PlanoContaService pcs() {
			return new PlanoContaService();
		}
		@Bean
		public UsuarioService us() {
			return new UsuarioService();
		}
	}
	
	@Autowired
	PlanoContaService pcs;
	
	@Autowired
	UsuarioService us;
	
	@Test
	public void id_invalido_retorna_nulo() {
		PlanoConta pc = pcs.getPlanoContaWithId(-1);
		assertThat(pc).isNull();
	}
	@Test
	public void adiciona_PlanoConta_sem_usuario_cadastrado() {
		PlanoContaDto pcDto = new PlanoContaMapper().getPlanoContaDtoFromEntity(new PlanoConta("teste","teste",TipoLancamento.D,true,false));
		try {
			pcs.addPlanoConta(pcDto);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
		} catch (Exception e) {
			fail("Retornou uma exceção inesperada");
		}
	}
	@Test void adiciona_PlanoConta() {
		UsuarioDto saved = us.addUsuario(new UsuarioDto(new Usuario("12345678910", "mocklogin", "Zé Teste", "123456")));
		
		PlanoContaDto pcDto = new PlanoContaMapper().getPlanoContaDtoFromEntity(new PlanoConta(saved.getLogin(),"Plano Teste",TipoLancamento.D,true,false));
		PlanoContaDto pcDtoSalvo = pcs.addPlanoConta(pcDto);
		
		assertThat(pcDtoSalvo).isNotNull();
	}
}
