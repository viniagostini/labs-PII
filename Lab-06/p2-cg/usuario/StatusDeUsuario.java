package usuario;

import jogo.Jogabilidade;
import exceptions.P2CGException;

/* 115110107 - Vinicius Alencar Agostini: LAB 7 - Turma 3 */
public interface StatusDeUsuario {


	public double calculaPrecoCompra(double precoJogo) throws P2CGException;
	
	public int calculaX2PCompra(double precoJogo) throws P2CGException;
	
	public int calculaRecompensa(Jogabilidade jogabilidade)throws P2CGException;
	
	public int calculaPunicao(Jogabilidade jogabilidade)throws P2CGException;
	
	
}
