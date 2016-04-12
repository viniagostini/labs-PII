package usuario;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.P2CGException;
import exceptions.StringInvalidaException;
import jogo.Jogabilidade;
import jogo.Jogo;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */


/**
 *  Implementa os comportamentos de um tipo de usuario,
 * nesse caso um usuario veterano.
 * 
 * @author Vinicius A. Agostini
 */

public class StatusVeterano implements StatusDeUsuario {

	public static final double DESCONTO =  0.2; //20% de desconto
	public static final int MULTIPLICADOR_DE_X2P =  15;
	
	

	public static final int PUNICAO_OFFLINE = 20;
	public static final int PUNICAO_COMPETITIVO = 20;
	
	public static final int RECONPENSA_ONLINE = 10;
	public static final int RECONPENSA_COOPERATIVO = 20;
	

	@Override
	public double calculaPrecoCompra(double precoJogo) throws P2CGException {
		return precoJogo - precoJogo * DESCONTO;
	}


	@Override
	public int calculaX2PCompra(double precoJogo) throws P2CGException {
		return (int) precoJogo * MULTIPLICADOR_DE_X2P;
	}


	@Override
	public int calculaPunicao (Jogabilidade jogabilidade) throws DadosInvalidosException {
		
		this.validaJogabilidade(jogabilidade);
		
		switch (jogabilidade){
		
			case OFFLINE:
				
				return PUNICAO_OFFLINE;
				
			case COMPETITIVO:
				
				return PUNICAO_COMPETITIVO;
				
			default:
				return 0;
		}
	}


	@Override
	public int calculaReconpensa(Jogabilidade jogabilidade) throws DadosInvalidosException{
		
		this.validaJogabilidade(jogabilidade);
		
		switch (jogabilidade){
		
		case COOPERATIVO:
			
			return RECONPENSA_COOPERATIVO;
			
		case ONLINE:
			
			return RECONPENSA_ONLINE;
			
		default:
			return 0;
		}
	}


	// metodos de validacao
	private void validaJogabilidade(Jogabilidade jogabilidade) throws DadosInvalidosException{
		if(jogabilidade == null){
			throw new DadosInvalidosException("Nao eh permitido jogabilidade nula.");
		}
	}
	


}
