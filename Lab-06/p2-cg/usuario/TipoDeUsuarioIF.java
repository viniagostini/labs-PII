package usuario;

import jogo.Jogo;
import exceptions.P2CGException;

public interface TipoDeUsuarioIF {

	
	public double calculaCompra(Jogo novoJogo) throws P2CGException;
	
	public int recompensarJogada(String nomeJogo,int scoreObtido,boolean zerou);
	
	public int punirJogada(String nomeJogo,int scoreObtido,boolean zerou);
	
}
