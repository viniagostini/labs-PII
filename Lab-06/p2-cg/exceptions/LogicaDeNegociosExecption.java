package exceptions;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */


/**
 * 
 * @author Vinicius A. Agostini
 *
 * Classe responsavel por encapsular todos os erros/exceptions relacionados a Logica de Negocios
 * que possam ocorrer nesse projeto.
 */

public class LogicaDeNegociosExecption extends P2CGException{

	public LogicaDeNegociosExecption(){
		/**
		 * construtor com uma mensagem padrao
		 */
		super("Existe um erro de logica de negocios");
	}
	
	public LogicaDeNegociosExecption(String mensagem){
		/**
		 * construtor que recebe uma mensagem personalizada
		 */
		super(mensagem);
	}
	
}
