package factory;

import jogo.Jogo;
import jogo.JogoLuta;
import jogo.JogoPlataforma;
import jogo.JogoRPG;
import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import usuario.TipoDeUsuario;
import usuario.Usuario;
import usuario.UsuarioNoob;
import usuario.UsuarioVeterano;

public class UsuarioFactory {

	
	public Usuario criaUsuario(String nomeUsuario, String loginUsuario, TipoDeUsuario tipoUsuario)throws DadosInvalidosException, LogicaDeNegociosExecption{
		
		Usuario novoUsuario;
		
		switch (tipoUsuario) {
		case NOOB:
			
			novoUsuario = new UsuarioNoob(nomeUsuario, loginUsuario);
			
			break;
		case VETERANO:
			
			novoUsuario = new UsuarioVeterano(nomeUsuario, loginUsuario);
			
			break;
		default:
			throw new LogicaDeNegociosExecption("Tipo de usuario invalido");
		}
		
		return novoUsuario;
	}
	
	
}
