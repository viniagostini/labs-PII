package jogo;

import exceptions.DadosInvalidosException;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;


/* 115110107 - Vinicius Alencar Agostini: LAB 7 - Turma 3 */
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
	public JogoPlataforma(String nome, double preco) throws DadosInvalidosException {
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
		
		if(score > this.getMaiorScore()){
			this.setMaiorScore(score);
		}
		
		return x2p;
	}

	
	@Override
	public String toString(){
		String quebraDeLinha = System.lineSeparator();
		
		String retorno = "Jogo de Plataforma: " + quebraDeLinha;
		
		retorno += super.toString();
		
		return retorno;
	}

	
	private void validaRegistaJogada(int score) throws ValorNumericoInvalidoException{
		
		if( score < 0 ){
			throw new ValorNumericoInvalidoException("Nao eh permitido o registro de score negativo");
		}	
	}

}
