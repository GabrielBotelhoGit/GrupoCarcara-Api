package academy.gama.desafio.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.gama.desafio.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{	
	@Query(nativeQuery = true, value = "Select * from usuario where usuario.login = :login")
	Usuario getUsuarioWithLogin(@Param("login") String login);	
	@Query(nativeQuery = true, value = "Select * from usuario where usuario.login = :login and usuario.senha = :senha")
	Usuario getUsuarioWithLoginAndSenha(@Param("login") String login, @Param("senha") String senha);
}
