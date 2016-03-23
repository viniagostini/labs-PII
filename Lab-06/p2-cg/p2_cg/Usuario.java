package p2_cg;

import java.util.HashSet;

public class Usuario {

	private String nome;
	private HashSet<Jogo> jogos;
	private double saldo;
	
	
	public Usuario(String nome)throws Exception{
		
		validaConstrutor(nome);
		
		this.nome = nome;
		this.saldo = 0;
		this.jogos = new HashSet<Jogo>();
	}
	
	
	public boolean realizaCompra(Jogo novoJogo)throws Exception{
		
		double precoJogo = novoJogo.getPreco();
		
		if(this.getJogos().contains(novoJogo)){
			throw new Exception("O Usuario ja possui o jogo especificado");
		}
		
		
		if(this.getSaldo() >= precoJogo){
			
			this.getJogos().add(novoJogo);
			this.abateSaldo(precoJogo);
			
			return true;
			
		}else{
			return false;
		}
	}
	
	
	private void abateSaldo(double valor){
		double saldoAtual = this.getSaldo();
		saldoAtual = saldoAtual - valor;
		
		this.setSaldo(saldoAtual);
	}
	
	


	private void validaConstrutor(String nome)throws Exception{
		
		if(nome == null || nome.trim().isEmpty()){
			throw new Exception("Nao eh permitida a criacao de usuarios com nome vazio ou nulo");
		}
		
	}


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
