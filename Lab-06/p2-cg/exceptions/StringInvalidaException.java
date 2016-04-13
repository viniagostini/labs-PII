package exceptions;

/* 115110107 - Vinicius Alencar Agostini: LAB 7 - Turma 3 */

/**
 * 
 * @author Vinicius A. Agostini
 *
 * Classe responsavel por encapsular todos os erros/exceptions relacionados a Strings invalidas
 * que possam ocorrer nesse projeto.
 */

public class StringInvalidaException extends DadosInvalidosException{

	public StringInvalidaException(){
		/**
		 * construtor com uma mensagem padrao
		 */
		super("Um dado invalido foi inserido");
	}
	
	public StringInvalidaException(String mensagem){
		/**
		 * construtor que recebe uma mensagem personalizada
		 */
		super(mensagem);
	}

}
