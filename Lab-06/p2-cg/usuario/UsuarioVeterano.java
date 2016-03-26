package usuario;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;
import jogo.Jogo;


/**
 * Classe que herda comportamentos e estados de {@link usuario.Usuario}
 * e implemeta comportamentos especificos de Usuario veterano;
 * 
 * @author Vinicius A. Agostini
 */

public class UsuarioVeterano extends Usuario {

	public static final double DESCONTO =  0.2; //20% de desconto
	public static final int MULTIPLICADOR_DE_X2P =  15;
	
	
	/**
	 * Contrutor que chama o super 
	 * @see Usuario
	 * 
	 * @param nome - nome do usuario
	 * @param login - login do usuario
	 * @throws StringInvalidaException - Caso o nome de usuario ou o login
	 * sejam vazios ou nulos
	 */
	public UsuarioVeterano(String nome, String login) throws StringInvalidaException {
		super(nome, login);
	}

	
	
	/**
	 * Realiza uma compra aplicando o desconto especifico
	 * para usuarios veterano
	 */
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
		
		this.getJogos().add(novoJogo);
		
		int x2pRecebido = this.calculaX2pRecebidoEmCompra(precoJogo);
		
		this.incrementaX2p(x2pRecebido);
	}
	
	
	private double aplicaDesconto(double valor){
		return valor - valor * DESCONTO;
	}

	
	private int calculaX2pRecebidoEmCompra(double valorJogo){
		
		return (int) valorJogo * MULTIPLICADOR_DE_X2P;
	}
	
	
	public String toString(){
		String quebraDeLinha = System.lineSeparator();
		
		String retorno = "-----------Jogador Veterano-------------" + quebraDeLinha;
		
		retorno += super.toString();
		
		return retorno;
	}

	
	private void validaJogo(Jogo jogo) throws DadosInvalidosException{
		
		if(jogo == null){
			throw new DadosInvalidosException("Nao sao permitido jogos nulos.");
		}
		
	}

}
