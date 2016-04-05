package jogo;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */


/**
 * Classe que herda comportamentos e estados de {@link jogo.Jogo}
 * e implemeta comportamentos especificos de jogos de luta.
 * 
 * @author Vinicius A. Agostini
 */

public class JogoLuta extends Jogo{

	public static final int MAIOR_SCORE_POSSIVEL = 100000;
	
	
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
	public JogoLuta(String nome, double preco) throws DadosInvalidosException {
		super(nome, preco);
	}
	
	
	/**
	 * Sobrescreve o metodo registraJogada da classe mae {@link jogo.Jogo}
	 * adicionando o comportamento especifico de jogos de luta.
	 * 
	 * @exception ValorNumericoInvalidoException - caso o score seja negativo
	 * 
	 * 
	 * @exception LogicaDeNegociosExecption - caso o score esteja acima de 100000
	 */
	@Override
	public int registraJogada(int score, boolean zerou) throws ValorNumericoInvalidoException, LogicaDeNegociosExecption {
		
		this.validaRegistaJogada(score);
		
		if(score > MAIOR_SCORE_POSSIVEL){
			throw new LogicaDeNegociosExecption("Tah de hack");
		}
		
		if(zerou){
			this.incrementaNZeradas();
		}
		
		this.incrementaNJogadas();
		
		int x2p = 0;
		
		if(score > this.getMaiorScore()){
			
			this.setMaiorScore(score);
			x2p = score / 1000;
		}
		
		return x2p;
	}

	
	@Override
	public String toString(){
		String quebraDeLinha = System.lineSeparator();
		
		String retorno = "Jogo de Luta: " + quebraDeLinha;
		
		retorno += super.toString();
		
		return retorno;
	}
	
	
	private void validaRegistaJogada(int score) throws ValorNumericoInvalidoException{
		
		if( score < 0 ){
			throw new ValorNumericoInvalidoException("Nao eh permitido o registro de score negativo");
		}	
	}
	
}
