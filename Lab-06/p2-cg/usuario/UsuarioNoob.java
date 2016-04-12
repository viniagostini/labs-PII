package usuario;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;
import jogo.Jogo;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */


/**
 * Classe que herda comportamentos e estados de {@link usuario.Usuario}
 * e implemeta comportamentos especificos de Usuario noob;
 * 
 * @author Vinicius A. Agostini
 */
public class UsuarioNoob extends Usuario{

	public static final double DESCONTO =  0.1; //10% de desconto
	public static final int MULTIPLICADOR_DE_X2P =  10;
	
	/**
	 * Contrutor que chama o super 
	 * @see Usuario
	 * 
	 * @param nome - nome do usuario
	 * @param login - login do usuario
	 * @throws StringInvalidaException - Caso o nome de usuario ou o login
	 * sejam vazios ou nulos
	 */
	public UsuarioNoob(String nome, String login) throws StringInvalidaException {
		super(nome, login);
	}

	
	
	
	private double aplicaDesconto(double valor){
		return valor - valor * DESCONTO;
	}

	
	private int calculaX2pRecebidoEmCompra(double valorJogo){
		
		return (int) valorJogo * MULTIPLICADOR_DE_X2P;
	}
	

}
