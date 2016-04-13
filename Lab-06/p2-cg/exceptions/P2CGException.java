package exceptions;

/* 115110107 - Vinicius Alencar Agostini: LAB 7 - Turma 3 */


/**
 * @author Vinicius A. Agostini
 *
 * Classe responsavel por encapsular todos os erros/exceptions que possam ocorrer
 * nesse projeto.
 */

public class P2CGException extends Exception{

	public P2CGException(){
		/**
		 * construtor com uma mensagem padrao
		 */
		super("Existe um erro no programa");
	}
	
	public P2CGException(String mensagem){
		/**
		 * construtor que recebe uma mensagem personalizada
		 */
		super(mensagem);
	}
	
}
