package usuario;

import exceptions.DadosInvalidosException;
import exceptions.P2CGException;

import jogo.Jogabilidade;


/* 115110107 - Vinicius Alencar Agostini: LAB 7 - Turma 3 */
/**
 * Implementa os comportamentos de um tipo de usuario,
 * nesse caso um usuario noob.
 * 
 * @author Vinicius A. Agostini
 */
public class StatusNoob implements StatusDeUsuario{

	public static final double DESCONTO =  0.1; //10% de desconto
	public static final int MULTIPLICADOR_DE_X2P =  10;

	public static final int PUNICAO_ONLINE = 10;
	public static final int PUNICAO_COMPETITIVO = 20;
	public static final int PUNICAO_COOPERATIVO = 50;
	
	public static final int RECOMPENSA_OFFLINE = 30;
	public static final int RECOMPENSA_MULTIPLAYER = 10;
	

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
		
			case ONLINE:
				
				return PUNICAO_ONLINE;
				
			case COMPETITIVO:
				
				return PUNICAO_COMPETITIVO;
				
			case COOPERATIVO:
				
				return PUNICAO_COOPERATIVO;
				
			default:
				return 0;
		}
	}


	@Override
	public int calculaRecompensa(Jogabilidade jogabilidade) throws DadosInvalidosException{
		
		this.validaJogabilidade(jogabilidade);
		
		switch (jogabilidade){
		
		case MULTIPLAYER:
			
			return RECOMPENSA_MULTIPLAYER;
			
		case OFFLINE:
			
			return RECOMPENSA_OFFLINE;
			
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
