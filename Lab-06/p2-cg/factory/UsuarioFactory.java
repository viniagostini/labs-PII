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

/**
 * Classe cuja unica resposabilidade esta na criacao de Usuarios
 * 
 * @author Vinicius A. Agostini
 */
public class UsuarioFactory {

	/**
	 * Metodo que recebe as informacoes do usuario a ser criado
	 * bem como seu tipo, cria um usuario de um tipo especifico 
	 * e retorna uma referencia gererica do tipo usuario apontando
	 * para o mesmo.
	 * 
	 * @param String - Nome do usuario.
	 * @param String - Login do usuario.
	 * @param TipoDeUsuario - Tipo do usuario.
	 * 
	 * @return Jogo - A referencia para o usuario criado.
	 * 
	 * @throws DadosInvalidosException - Caso algum dado invalido seja inserido.
	 * @throws LogicaDeNegociosExecption - Caso o tipo de usuario seja invalido.
	 */
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
