package factory;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;

import usuario.Usuario;

/**
 * Classe cuja unica resposabilidade esta na criacao de Usuarios
 * 
 * @author Vinicius A. Agostini
 */
public class UsuarioFactory {

	/**
	 * Metodo que cria um usuario baseado nas informacoes
	 * recebidas.
	 * 
	 * @param String - Nome do usuario.
	 * @param String - Login do usuario.
	 * 
	 * @return Jogo - A referencia para o usuario criado.
	 * 
	 * @throws DadosInvalidosException - Caso algum dado invalido seja inserido.
	 * @throws LogicaDeNegociosExecption - Caso o tipo de usuario seja invalido.
	 */
	public Usuario criaUsuario(String nomeUsuario, String loginUsuario)throws StringInvalidaException{
		
		return new Usuario(nomeUsuario, loginUsuario);
	}
	
	
	
}
