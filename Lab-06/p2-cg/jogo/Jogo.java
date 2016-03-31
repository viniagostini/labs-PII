package jogo;

import java.util.HashSet;

import exceptions.DadosInvalidosException;
import exceptions.P2CGException;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */

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
	public abstract int registraJogada(int score, boolean zerou)throws P2CGException;
	
	
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
	
	//metodos triviais
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Jogo){
			Jogo outroJogo = (Jogo) obj;
			
			String outroNome = outroJogo.getNome();
			double outroPreco = outroJogo.getPreco();
			
			boolean nomesIguais = this.getNome().equals(outroNome);
			boolean precosIguais = this.getPreco() == outroPreco;
			
			if(nomesIguais && precosIguais){
				return true;
			}
		}
		
		return false;
	}
	
	
	@Override
	public String toString() {
		String quebraDeLinha = System.lineSeparator();
		String retorno = "";
		
		retorno += "    Titulo: " + this.getNome() + quebraDeLinha; 
		
		retorno += "    Preco: R$" + this.getPreco() + quebraDeLinha;
		
		retorno += "    Jogabilidades: " + this.getJogabilidades() + quebraDeLinha;
		
		retorno += "    Recorde: " + this.getMaiorScore() + quebraDeLinha;
		
		retorno += "    Jogado " + this.getnJogadas() + " vezes." + quebraDeLinha;
		
		retorno += "    Zerado " + this.getnZeradas() + " vezes." + quebraDeLinha;
		
		return retorno;
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
