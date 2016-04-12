package usuario;

import exceptions.P2CGException;

public interface TipoDeUsuarioIF {


	public double calculaPrecoCompra(double precoJogo) throws P2CGException;
	
	public int calculaX2PCompra(double precoJogo) throws P2CGException;
	
	public int recompensarJogada(String nomeJogo,int scoreObtido,boolean zerou);
	
	public int punirJogada(String nomeJogo,int scoreObtido,boolean zerou);
	
	
}
