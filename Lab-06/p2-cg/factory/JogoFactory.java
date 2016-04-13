package factory;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import jogo.Jogo;
import jogo.JogoLuta;
import jogo.JogoPlataforma;
import jogo.JogoRPG;
import jogo.TipoDeJogo;


/* 115110107 - Vinicius Alencar Agostini: LAB 7 - Turma 3 */
/**
 * Classe cuja unica resposabilidade esta na criacao de Jogos
 * 
 * @author Vinicius A. Agostini
 */
public class JogoFactory {

	/**
	 * Metodo que recebe as informacoes do jogo a ser criado
	 * bem como seu tipo, cria um jogo de um tipo especifico 
	 * e retorna uma referencia gererica do tipo Jogo apontando
	 * para o mesmo.
	 * 
	 * @param String - Nome do jogo.
	 * @param double - Preco do jogo.
	 * @param TipoDeJogo - Tipo do jogo.
	 * 
	 * @return Jogo - A referencia para o jogo criado.
	 * 
	 * @throws DadosInvalidosException - Caso algum dado invalido seja inserido.
	 * @throws LogicaDeNegociosExecption - Caso o tipo de jogo seja invalido.
	 */
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
