package exceptions;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */

/**
 * @author Vinicius A. Agostini
 *
 * Classe responsavel por encapsular todos os erros/exceptions relacionados a dados invalidos
 * que possam ocorrer nesse projeto.
 */ 

public class DadosInvalidosException extends P2CGException{

	public DadosInvalidosException(){
		/**
		 * construtor com uma mensagem padrao
		 */
		super("Um dado invalido foi inserido");
	}
	
	public DadosInvalidosException(String mensagem){
		/**
		 * construtor que recebe uma mensagem personalizada
		 */
		super(mensagem);
	}

	
}
