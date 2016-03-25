package jogo;

import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

/**
 * Classe que herda comportamentos e estados de {@link jogo.Jogo}
 * e implemeta comportamentos especificos de jogos de RPG.
 * 
 * @author Vinicius A. Agostini
 */

public class JogoRPG extends Jogo{

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
	public JogoRPG(String nome, double preco) throws Exception {
		//eh responsabilidade do super validar os dados
		super(nome, preco);
	}

	/**
	 * Sobrescreve o metodo registraJogada da classe mae {@link jogo.Jogo}
	 * adicionando o comportamento especifico de jogos de RPG.
	 * 
	 * @exception ValorNumericoInvalidoException - caso o score seja negativo
	 */
	@Override
	public int registraJogada(int score, boolean zerou) throws ValorNumericoInvalidoException {
		
		this.validaRegistaJogada(score);
		
		this.incrementaNJogadas();
		
		if(zerou){
			this.incrementaNZeradas();
		}
		
		if(score > this.getMaiorScore()){
			this.setMaiorScore(score);
		}
		
		int x2p = 10;
		
		return x2p;
	}

	
	@Override
	public String toString(){
		String quebraDeLinha = System.lineSeparator();
		
		String retorno = "Jogo de RPG: " + quebraDeLinha;
		
		retorno += super.toString();
		
		return retorno;
	}
	
	
	private void validaRegistaJogada(int score) throws ValorNumericoInvalidoException{
		
		if( score < 0 ){
			throw new ValorNumericoInvalidoException("Nao eh permitido o registro de score negativo");
		}	
	}
	
}
