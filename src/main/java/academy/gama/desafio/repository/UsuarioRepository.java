package academy.gama.desafio.repository;

import java.util.List;

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
	
	@Query(nativeQuery = true, value = "SELECT * FROM USUARIO\r\n"
			+ "INNER JOIN CONTA\r\n"
			+ "ON (USUARIO.LOGIN = CONTA.LOGIN_USUARIO) WHERE usuario.id= :id")
	List<Usuario> findUsuarioAndConta(@Param("id") Integer id);	
}
