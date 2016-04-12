package usuario;

import jogo.Jogabilidade;
import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.P2CGException;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */


/**
 * Implementa os comportamentos de um tipo de usuario,
 * nesse caso um usuario noob.
 * 
 * @author Vinicius A. Agostini
 */
public class Noob implements TipoDeUsuarioIF{

	public static final double DESCONTO =  0.1; //10% de desconto
	public static final int MULTIPLICADOR_DE_X2P =  10;

	public static final int PUNICAO_ONLINE = 10;
	public static final int PUNICAO_COMPETITIVO = 20;
	public static final int PUNICAO_COOPERATIVO = 50;
	

	public static final int RECONPENSA_OFFLINE = 30;
	public static final int RECONPENSA_MULTIPLAYER = 10;
	

	@Override
	public double calculaPrecoCompra(double precoJogo) throws P2CGException {
		return precoJogo - precoJogo * DESCONTO;
	}


	@Override
	public int calculaX2PCompra(double precoJogo) throws P2CGException {
		return (int) precoJogo * MULTIPLICADOR_DE_X2P;
	}


	@Override
	public int calculaPunicao (Jogabilidade jogabilidade) throws P2CGException {
		
		this.verificaJogabilidade(jogabilidade);
		
		switch (jogabilidade){
		
			case ONLINE:
				
				return PUNICAO_ONLINE;
				
			case COMPETITIVO:
				
				return PUNICAO_COMPETITIVO;
				
			case COOPERATIVO:
				
				return PUNICAO_COOPERATIVO;
				
			default:
				throw new LogicaDeNegociosExecption("Nao existe punicao para esse tipo de jogada.");
		}
	}


	@Override
	public int calculaReconpensa(Jogabilidade jogabilidade) throws LogicaDeNegociosExecption{
		switch (jogabilidade){
		
		case ONLINE:
			
			return PUNICAO_ONLINE;
			
		case COMPETITIVO:
			
			return PUNICAO_COMPETITIVO;
			
		default:
			throw new LogicaDeNegociosExecption("Nao existe punicao para esse tipo de jogada.");
		}
	}


	// metodos de verificacao
	private void verificaJogabilidade(Jogabilidade jogabilidade) throws DadosInvalidosException{
		if(jogabilidade == null){
			throw new DadosInvalidosException("Nao eh permitido jogabilidade nula.");
		}
	}

}
