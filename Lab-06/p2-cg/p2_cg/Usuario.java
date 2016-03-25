package p2_cg;

import java.util.HashSet;

import jogo.Jogo;

public abstract class Usuario {

	private String nome;
	private HashSet<Jogo> jogos;
	private double saldo;
	
	
	public Usuario(String nome)throws Exception{
		
		validaConstrutor(nome);
		
		this.nome = nome;
		this.saldo = 0;
		this.jogos = new HashSet<Jogo>();
	}
	
	
	public abstract boolean realizaCompra(Jogo novoJogo)throws Exception;
	
	//melhorar isso
	public void incrementaSaldo(double valor)throws Exception{
		
		this.validaValorSaldo(valor);
		
		this.saldo = this.saldo + valor;
	}
	
	public void abateSaldo(double valor)throws Exception{
		
		this.validaValorSaldo(valor);
		
		double saldoAtual = this.getSaldo();
		saldoAtual = saldoAtual - valor;
		
		this.setSaldo(saldoAtual);
	}
		
	private void validaConstrutor(String nome)throws Exception{
		
		if(nome == null || nome.trim().isEmpty()){
			throw new Exception("Nao eh permitida a criacao de usuarios com nome vazio ou nulo");
		}
		
	}
	
	private void validaValorSaldo(double valor)throws Exception{
		
		if( valor < 0 ){
			throw new Exception("Nao eh permitido o incremento de saldo negativo");
		}
	}

	
	
	//getters  setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
