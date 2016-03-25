package usuario;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.P2CGException;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;
import jogo.Jogo;

public class UsuarioNoob extends Usuario{

	public static final double DESCONTO =  0.1; //10% de desconto
	public static final int MULTIPLICADOR_DE_X2P =  10;
	
	
	
	public UsuarioNoob(String nome, String login) throws StringInvalidaException {
		super(nome, login);
	}

	@Override
	public void realizaCompra(Jogo novoJogo) throws DadosInvalidosException, LogicaDeNegociosExecption {
		
		this.validaJogo(novoJogo);
		
		boolean contemJogo = this.getJogos().contains(novoJogo);
		
		if(contemJogo){
			throw new LogicaDeNegociosExecption("Nao eh possivel comprar duas vezes o mesmo jogo");
		}
		
		double precoJogo = novoJogo.getPreco();
		double precoPago = this.aplicaDesconto(precoJogo);
		
		
		if( ! this.abateSaldo(precoPago) ){
			throw new LogicaDeNegociosExecption("Saldo insuficente para comprar esse jogo");
		}
		
		
		int x2pRecebido = this.calculaX2pRecebidoEmCompra(precoJogo);
		
		this.incrementaX2p(x2pRecebido);
		
	}
	
	
	@Override
	public void registraJogada(String nomeDoJogo, int score, boolean zerou) throws P2CGException {
		
		this.validaRegistaJogada(nomeDoJogo, score);
		
		Jogo jogoJogado = this.buscaJogo(nomeDoJogo);
		
		int x2p = jogoJogado.registraJogada(score, zerou);
		
		this.incrementaX2p(x2p);
	}

	
	private double aplicaDesconto(double valor){
		return valor - valor * DESCONTO;
	}

	private int calculaX2pRecebidoEmCompra(double valorJogo){
		
		return (int) valorJogo * MULTIPLICADOR_DE_X2P;
	}
	
	private void validaRegistaJogada(String nomeDoJogo, int score) throws ValorNumericoInvalidoException, StringInvalidaException{
		
		this.validaNomeJogo(nomeDoJogo);
		
		if( score < 0 ){
			throw new ValorNumericoInvalidoException("Nao eh permitido o registro de score negativo");
		}

	}
	
	private void validaJogo(Jogo jogo) throws DadosInvalidosException{
		
		if(jogo == null){
			throw new DadosInvalidosException("Nao eh permitido jogos nulos.");
		}
		
	}

	
}
