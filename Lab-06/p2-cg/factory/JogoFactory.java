package factory;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import jogo.Jogo;
import jogo.JogoLuta;
import jogo.JogoPlataforma;
import jogo.JogoRPG;
import jogo.TipoDeJogo;

public class JogoFactory {

	
	public Jogo criaJogo(String nomeJogo, double precoJogo, TipoDeJogo tipoJogo) 
			throws DadosInvalidosException, LogicaDeNegociosExecption{
		
		Jogo novoJogo;
		
		switch (tipoJogo) {
		case LUTA:
			
			novoJogo = new JogoLuta(nomeJogo, precoJogo);
			
			break;
		case RPG:
			
			novoJogo = new JogoRPG(nomeJogo, precoJogo);
			
			break;
		case PLATAFORMA:
			
			novoJogo = new JogoPlataforma(nomeJogo, precoJogo);
			
			break;
		default:
			throw new LogicaDeNegociosExecption("Tipo de jogo invalido");
		}
		
		return novoJogo;
	}
	
}
