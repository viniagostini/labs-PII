package jogo;

import java.util.HashSet;

import exceptions.DadosInvalidosException;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

/**
 * Classe abstrata que encapsula todos os comportamentos e estados que um
 * jogo deve possuir nesse sistema.
 * 
 * @author Vinicius A. Agostini
 */

public abstract class Jogo {
	
	//info de jogo
	private String nome;
	private double preco;
	private HashSet<Jogabilidade> jogabilidades;
	
	
	//estatisticas
	private int maiorScore;
	private int nJogadas;
	private int nZeradas;
	
	
	/**
	 * Construtor
	 * 
	 * @param String - nome do jogo 
	 * @param String - preco do jogo
	 * 
	 * @throws StringInvalidaException - caso uma String vazia ou nula
	 * seja passada em nome.
	 * 
	 * @throws ValorNumericoInvalidoException - caso o preco seja negativo.
	 */
	public Jogo(String nome, double preco)throws StringInvalidaException , ValorNumericoInvalidoException{
		
		this.validaConstrutor(nome, preco);
		
		this.nome = nome;
		this.preco = preco;
		
		this.jogabilidades = new HashSet<Jogabilidade>();
	}
	
	
	/**
	 * Metodo que registra cada jogada realizada pelo usuario.
	 * 
	 * @param int - score da jogada
	 * @param boolean - zerou ou nao o jogo
	 * 
	 * @return int - x2p gerado por cada jogada
	 * 
	 * @throws Exception - eh responsabilidade de quem implementar esse metodo
	 * definir e tratar suas exceptions.
	 */
	public abstract int registraJogada(int score, boolean zerou)throws Exception;
	
	
	/**
	 * Metodo que permite a adicao de diversos tipos de jogabilidade
	 * ao jogo.
	 * 
	 * @param jogabilidade
	 * @return boolean - jogabilidade foi adicionada ou nao.
	 * @throws DadosInvalidosException - caso receba null como paramentro.
	 */
	public boolean adicionaJogabilidade(Jogabilidade jogabilidade)throws DadosInvalidosException{

		this.validaAdicionaJogabiliade(jogabilidade);
		
		return this.getJogabilidades().add(jogabilidade);
	}
	
	
	/**
	 * Incrementa em 1 o numero de vezes que o usuario jogou o jogo.
	 */
	public void incrementaNJogadas(){
		this.nJogadas++;
	}

	
	/**
	 * Incrementa em 1 o numero de vezes que o usuario zerou o jogo.
	 */
	public void incrementaNZeradas(){
		this.nZeradas++;
	}
	
	
	// getters e setters
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getMaiorScore() {
		return maiorScore;
	}
	
	public void setMaiorScore(int maiorScore) {
		this.maiorScore = maiorScore;
	}
	
	public HashSet<Jogabilidade> getJogabilidades() {
		return jogabilidades;
	}

	public void setJogabilidades(HashSet<Jogabilidade> jogabilidades) {
		this.jogabilidades = jogabilidades;
	}
	
	public int getnJogadas() {
		return nJogadas;
	}

	public int getnZeradas() {
		return nZeradas;
	}

	
	//metodos de validacao
	private void validaConstrutor(String nome, double preco)throws StringInvalidaException , ValorNumericoInvalidoException{
		
		if( nome == null || nome.trim().isEmpty() ){
			throw new StringInvalidaException("Nao eh permitida a criacao de Jogos com nome vazio ou nulo.");
		}
		
		if( preco < 0 ){
			throw new ValorNumericoInvalidoException("Nao eh permitida a criacao de Jogos com preco negativo.");
		}
	}
	
	private void validaAdicionaJogabiliade(Jogabilidade jogabilidade)throws DadosInvalidosException{
		
		if( jogabilidade == null ){
			throw new DadosInvalidosException("Nao eh permitida a adicao de jogabilidade nula");
		}
		
	}
		
}
