package exceptions;


/**
 * 
 * @author Vinicius A. Agostini
 *
 * Classe responsavel por encapsular todos os erros/exceptions relacionados a Strings invalidas
 * que possam ocorrer nesse projeto.
 */

public class ValorNumericoInvalidoException extends DadosInvalidosException{

	public ValorNumericoInvalidoException(){
		/**
		 * construtor com uma mensagem padrao
		 */
		super("Um dado invalido foi inserido");
	}
	
	public ValorNumericoInvalidoException(String mensagem){
		/**
		 * construtor que recebe uma mensagem personalizada
		 */
		super(mensagem);
	}

}
