package academy.gama.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.gama.desafio.dto.UsuarioDto;
import academy.gama.desafio.model.Conta;
import academy.gama.desafio.model.Usuario;
import enums.TipoConta;

@Service
public class CadastroService {
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	ContaService contaService;
	public String Cadastrar(UsuarioDto usuarioDto) {		

		Usuario usuario = new Usuario(usuarioDto);

		if (validaSenha(usuario.getSenha())) {
			boolean sucesso = usuarioService.addUsuario(usuario);
			if (sucesso) {
				// Primeiro temos que criar a conta de d�bito
				Conta contaDebito = new Conta("Conta D�bito", usuario, TipoConta.CB);
				contaService.addConta(contaDebito);

				// Agora criamos a conta de cr�dito
				Conta contaCredito = new Conta("Conta Cr�dito", usuario, TipoConta.CC);
				contaService.addConta(contaCredito);
			} else {
				System.out.println("Já existe usuário com esse login");
				return "Já existe usuário com esse login";
			}
		}else{
			return "Senha inválida: A senha deve conter 6 ou mais caracteres";
		}
		return "Cadastrado com sucesso";
	}

	private boolean validaSenha(String senha) {
		return senha.length() >= 6;
	}
}
