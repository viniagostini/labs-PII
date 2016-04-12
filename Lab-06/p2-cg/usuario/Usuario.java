package usuario;

import java.util.HashSet;

import exceptions.LogicaDeNegociosExecption;
import exceptions.P2CGException;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;
import jogo.Jogo;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */

/**
 * Classe abstrata que encapsula todos os comportamentos e estados que um
 * Usuario deve possuir nesse sistema.
 * 
 * @author Vinicius A. Agostini
 */
public abstract class Usuario {

	private String nome;
	private String login;
	private HashSet<Jogo> jogos;
	private double saldo;
	private int x2p;
	
	/**
	 * Construtor
	 * @param String - nome do usuario
	 * @throws StringInvalidaException - Caso seja passado um nome
	 * vazio ou nulo.
	 */
	public Usuario(String nome, String login)throws StringInvalidaException{
		
		validaConstrutor(nome, login);
		
		this.nome = nome;
		this.login = login;
		this.jogos = new HashSet<Jogo>();
	}
	
	/**
	 * Metodo abstrato que diz respeito a um comportamento
	 * essencial a todo usuario: comprar jogos
	 * 
	 * @param Jogo - o jogo que foi comprado
	 * 
	 * @return boolean - se o jogo foi comprado com sucesso
	 * 
	 * @throws Exception - cabe as filhas dessa classe
	 * definirem e tratarem suas proprias exception
	 */
	public abstract void realizaCompra(Jogo novoJogo)throws P2CGException;
	
	
	/**
	 * Metodo que diz respeito a um comportamento
	 * essencial a todo usuario: registrar suas jogadas.
	 * 
	 * @param String - Nome do jogo
	 * @param int - score da partida realizada pelo usuario
	 * @param boolean - o usuario zerou ou nao o jogo nessa partida
	 * 
	 * @throws P2CGException
	 */
	public void registraJogada(String nomeDoJogo, int score, boolean zerou) throws P2CGException {
		
		this.validaRegistaJogada(nomeDoJogo, score);
		
		Jogo jogoJogado = this.buscaJogo(nomeDoJogo);
		
		int x2p = jogoJogado.registraJogada(score, zerou);
		
		this.incrementaX2p(x2p);
	}
	
	/**
	 * Metodo que recebe um valor e incrementa o mesmo
	 * no saldo do usuario
	 * 
	 * @param double - valor a ser incrementado
	 * @throws ValorNumericoInvalidoException - caso o valor passado seja negativo
	 */
	public void incrementaSaldo(double valor)throws ValorNumericoInvalidoException{
		
		this.validaValorSaldo(valor);
		
		this.saldo = this.saldo + valor;
	}
	
	/**
	 * Metodo que recebe um valor e decrementa o mesmo
	 * no saldo do usuario caso o usuario tenha saldo
	 * suficiente.
	 * 
	 * @param double - valor a ser abatido
	 * 
	 * @return boolean - saldo abatido ou nao
	 * 
	 * @throws ValorNumericoInvalidoException - caso o valor passado seja negativo
	 */
	protected boolean abateSaldo(double valor)throws ValorNumericoInvalidoException{
		
		this.validaValorSaldo(valor);
		
		boolean saldoInsuficente = this.getSaldo() < valor;
		
		if(saldoInsuficente){
			return false;
		}

		double saldoAtual = this.getSaldo();
		saldoAtual = saldoAtual - valor;
		
		this.setSaldo(saldoAtual);
		
		return true;
	}
	
	
	/**
	 * Metodo que recebe um valor de x2p e incrementa
	 * no x2p total do usuario
	 * 
	 * @param int - valor de x2p a ser incrementado
	 * @throws ValorNumericoInvalidoException - caso o valor de x2p passado seja negativo
	 */
	protected void incrementaX2p(int x2p)throws ValorNumericoInvalidoException{
		
		this.validaValorX2p(x2p);
		
		int x2pAtual = this.getX2p();
		int novoX2p = x2pAtual + x2p;
		
		this.setX2p(novoX2p);
	}
	
	
	/**
	 * Busca um jogo na lista de jogos de usuario a partir
	 * do seu nome.
	 * 
	 * @param String - Nome do jogo
	 * 
	 * @return Jogo - O jogo que foi buscado
	 * 
	 * @throws StringInvalidaException - Caso o nome do jogo seja vazio ou nulo
	 * @throws LogicaDeNegociosExecption - Caso o jogo nao tenha sido encontrado
	 */
	public Jogo buscaJogo(String nomeJogo) throws LogicaDeNegociosExecption, StringInvalidaException{
		
		validaNomeJogo(nomeJogo);
		
		for(Jogo jogoAtual : this.getJogos()){
			
			String nomeAtual = jogoAtual.getNome();
			
			if(nomeAtual.equals(nomeJogo)){
				return jogoAtual;
			}
			
		}
		
		throw new LogicaDeNegociosExecption("Jogo nao encontrado");
	}
	
	/**
	 * Verifica se o usuario possui um jogo com o nome passado
	 * 
	 * @param String - Nome do jogo
	 * 
	 * @return boolean - Se o jogo foi ou nao encontrado
	 * 
	 * @throws StringInvalidaException - Caso o nome passado deja vazio ou nulo.
	 */
	public boolean temJogo(String nomeJogo) throws StringInvalidaException{
		
		validaNomeJogo(nomeJogo);
		
		for(Jogo jogoAtual : this.getJogos()){
			
			String nomeAtual = jogoAtual.getNome();
			
			if(nomeAtual.equals(nomeJogo)){
				return true;
			}
			
		}
		
		return false;
	}
	
	//metodos de validacao
	private void validaConstrutor(String nome, String login)throws StringInvalidaException{
		
		if(nome == null || nome.trim().isEmpty()){
			throw new StringInvalidaException("Nao eh permitida a criacao de usuarios com nome vazio ou nulo");
		}
		
		if(login == null || login.trim().isEmpty()){
			throw new StringInvalidaException("Nao eh permitida a criacao de usuarios com login vazio ou nulo");
		}
		
	}
	
	private void validaValorSaldo(double valor)throws ValorNumericoInvalidoException{
		if( valor < 0 ){
			throw new ValorNumericoInvalidoException("Nao eh permitido o incremento de saldo negativo");
		}
	}
	
	private void validaValorX2p(int x2p)throws ValorNumericoInvalidoException{
		if( x2p < 0 ){
			throw new ValorNumericoInvalidoException("Nao eh permitido o incremento de x2p negativo");
		}
	}

	protected void validaNomeJogo(String nomeJogo) throws StringInvalidaException{
		
		if(nomeJogo == null || nomeJogo.trim().isEmpty()){
			throw new StringInvalidaException("O nome do jogo nao deve ser nulo ou vazio.");
		}
	}
	
	private void validaRegistaJogada(String nomeDoJogo, int score) throws ValorNumericoInvalidoException, StringInvalidaException{
		
		this.validaNomeJogo(nomeDoJogo);
		
		if( score < 0 ){
			throw new ValorNumericoInvalidoException("Nao eh permitido o registro de score negativo");
		}

	}
	
	//metodos triviais

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Usuario){
			
			Usuario outroUsuario = (Usuario) obj;
			
			String outroNome = outroUsuario.getNome();
			String outroLogin = outroUsuario.getLogin();
			
			boolean nomesIguais = this.getNome().equals(outroNome);
			boolean loginsIguais = this.getLogin().equals(outroLogin);
			
			if(nomesIguais && loginsIguais){
				return true;
			}			
		}

		return false;
	}

	public String toString(){
		String quebraDeLinha = System.lineSeparator();
		
		String retorno = "";
		
		retorno += "Nome: " + this.getNome() + quebraDeLinha;
		
		retorno += "Login: " + this.getLogin() + quebraDeLinha;
		
		retorno += "Saldo: R$" + this.getSaldo() + quebraDeLinha;
		
		retorno += "x2p: " + this.getX2p()+ quebraDeLinha;
		
		retorno += "Lista de Jogos: " + quebraDeLinha;
		
		double precoTotal = 0;
		
		for(Jogo jogoAtual : this.getJogos()){
			retorno += jogoAtual.toString();
			double precoAtual = jogoAtual.getPreco();
			precoTotal = precoTotal + precoAtual;
		}
		
		retorno += "Total de preco dos jogos: R$ " + precoTotal + quebraDeLinha;
		
		retorno += "-----------------------------------";
		
		return retorno;
	}

	
	//getters  setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getX2p() {
		return x2p;
	}

	public void setX2p(int x2p) {
		this.x2p = x2p;
	}

	public HashSet<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(HashSet<Jogo> jogos) {
		this.jogos = jogos;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
}
