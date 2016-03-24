package jogos;

import java.util.HashSet;

public abstract class Jogo {
	
	//info de jogo
	private String nome;
	private double preco;
	private HashSet<Jogabilidade> jogabilidades;
	
	
	//estatisticas
	private int maiorScore;
	private int nJogadas;
	private int nZeradas;
	
	public Jogo(String nome, double preco)throws Exception{
		//metodo que verifica se os parametros sao validos, caso contrario sera lancada uma exception
		this.validaConstrutor(nome, preco);
		
		this.nome = nome;
		this.preco = preco;
		
		this.jogabilidades = new HashSet<Jogabilidade>();
	}
	
	public abstract int registraJogada(int score, boolean zerou)throws Exception;
	
	public boolean adicionaJogabilidade(Jogabilidade jogabilidade)throws Exception{

		//metodo que verifica se os parametros sao validos, caso contrario sera lancada uma exception
		this.validaAdicionaJogabiliade(jogabilidade);
		
		return this.getJogabilidades().add(jogabilidade);
	}
	
	public void incrementaNJogadas(){
		this.nJogadas ++;
	}
	
	public void incrementaNZeradas(){
		this.nZeradas++;
	}
	
	
	// getters e setters
	public String getNome() {
		/**
		 * retorna o valor do parametro nome
		 */
		return nome;
	}
	
	public void setNome(String nome) {
		/**
		 * modifica o valor do parametro nome
		 */
		this.nome = nome;
	}
	
	public double getPreco() {
		/**
		 * retorna o valor do parametro preco
		 */
		return preco;
	}
	
	public void setPreco(double preco) {
		/**
		 * modifica o valor do parametro preco
		 */
		this.preco = preco;
	}
	
	public int getMaiorScore() {
		/**
		 * retorna o valor do parametro maiorScore
		 */
		return maiorScore;
	}
	
	public void setMaiorScore(int maiorScore) {
		/**
		 * modifica o valor do parametro maiorScore
		 */
		this.maiorScore = maiorScore;
	}
	
	public HashSet<Jogabilidade> getJogabilidades() {
		/**
		 * Retorna um HashSet que contem as jogabilidades do jogo 
		 */
		return jogabilidades;
	}

	public void setJogabilidades(HashSet<Jogabilidade> jogabilidades) {
		/**
		 * Altera o HashSet que contem as jogabilidades do jogo 
		 */
		this.jogabilidades = jogabilidades;
	}
	
	public int getnJogadas() {
		return nJogadas;
	}

	public int getnZeradas() {
		return nZeradas;
	}

	
	//metodos de validacao
	
	private void validaConstrutor(String nome, double preco)throws Exception{
		
		if( nome == null || nome.trim().isEmpty() ){
			throw new Exception("Nao eh permitida a criacao de Jogos com nome vazio ou nulo.");
		}
		
		if( preco < 0 ){
			throw new Exception("Nao eh permitida a criacao de Jogos com preco negativo.");
		}
	}
	
	private void validaAdicionaJogabiliade(Jogabilidade jogabilidade)throws Exception{
		if( jogabilidade == null ){
			throw new Exception("Nao eh permitida a adicao de jogabilidade nula");
		}
	}

	private void validaRegistraJogada(int score)throws Exception{
		
		if(score < 0){
			throw new Exception("Nao eh permitido o registro de score negativo");
		}
		
	}

	
	
}
