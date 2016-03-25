package jogo;

import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

/**
 * 
 * Classe que herda comportamentos e estados de {@link jogo.Jogo}
 * e implemeta comportamentos especificos de jogos de platforma.
 * 
 * @author Vinicius A. Agostini
 */

public class JogoPlataforma extends Jogo{

	/**
	 * Construtor que chama o super
	 * @see Jogo
	 * 
	 * @param nome
	 * @param preco
	 *
	 * @throws StringInvalidaException - caso uma String vazia ou nula
	 * seja passada em nome.
	 * 
	 * @throws ValorNumericoInvalidoException - caso o preco seja negativo.
	 */
	public JogoPlataforma(String nome, double preco) throws Exception {
		super(nome, preco);
	}

	
	/**
	 * Sobrescreve o metodo registraJogada da classe mae {@link jogo.Jogo}
	 * adicionando o comportamento especifico de jogos de plataforma.
	 * 
	 * @exception ValorNumericoInvalidoException - caso o score seja negativo
	 */
	@Override
	public int registraJogada(int score, boolean zerou) throws ValorNumericoInvalidoException {
		
		this.validaRegistaJogada(score);
		
		this.incrementaNJogadas();
		
		int x2p = 0;
		
		if(zerou){
			
			this.incrementaNZeradas();	
			
			x2p = 20;
		}
		
		return x2p;
	}


	private void validaRegistaJogada(int score) throws ValorNumericoInvalidoException{
		
		if( score < 0 ){
			throw new ValorNumericoInvalidoException("Nao eh permitido o registro de score negativo");
		}	
	}

}
