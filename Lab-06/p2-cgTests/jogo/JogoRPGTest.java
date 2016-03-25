package jogo;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

public class JogoRPGTest {

	public static final String NAO_DEVERIA_LANCAR_EXCEPTION = "Nao deveria ter lancado exception";
	
	
	@Test
	public void testJogoRPG() {
		//teste do construtor
		
		//verifica o estado inicial do objeto
		try{
			JogoRPG wow = new JogoRPG("World of Warcraft", 100);

			//nome
			assertEquals("World of Warcraft", wow.getNome());

			//preco
			assertEquals(100.0, wow.getPreco(), 0.0);
			
			//jogabilidade
			boolean vazio = wow.getJogabilidades().isEmpty();
			assertTrue(vazio);
			
			//maiorScore
			assertEquals( 0, wow.getMaiorScore() );
			
			//nJogadas
			assertEquals( 0, wow.getnJogadas() );
			
			//nZeradas
			assertEquals( 0, wow.getnZeradas() );
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		
		//verificando insercao de nome vazio
		try{
			@SuppressWarnings("unused")
			JogoRPG wow = new JogoRPG("", 100);
				
			fail("Deveria ser lancada uma StringInvalidaException");
			
		}catch(StringInvalidaException sie){
			
			String mensagemException = sie.getMessage();
			String mensagemEsperada = "Nao eh permitida a criacao de Jogos com nome vazio ou nulo.";
			
			assertEquals(mensagemEsperada, mensagemException);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui");
		}
		
		//verificando insercao de nome nulo
		try{
			@SuppressWarnings("unused")
			JogoRPG wow = new JogoRPG(null, 100);
			
			fail("Deveria ser lancada uma StringInvalidaException");
			
		}catch(StringInvalidaException sie){
			
			String mensagemException = sie.getMessage();
			String mensagemEsperada = "Nao eh permitida a criacao de Jogos com nome vazio ou nulo.";
			
			assertEquals(mensagemEsperada, mensagemException);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui");
		}
		
		
		//verificando insercaco de preco negativo
		try{
			@SuppressWarnings("unused")
			JogoRPG wow = new JogoRPG("World of Warcraft", -1);
			
			fail("Deveria ser lancada uma ValorNumericoInvalidoException");
			
		}catch(ValorNumericoInvalidoException vie){
			
			String mensagemException = vie.getMessage();
			String mensagemEsperada = "Nao eh permitida a criacao de Jogos com preco negativo.";
			
			assertEquals(mensagemEsperada, mensagemException);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui");
		}
	}

	
	@Test
	public void testRegistraJogada() {
		
		try{
			JogoRPG wow = new JogoRPG("World of Warcraft", 100);
			
			int x2pObtido = wow.registraJogada(5000, false);
			
			//testa atualizacao do maior score
			assertEquals( 5000, wow.getMaiorScore() );
			
			//testa o incremento do numero de jogadas
			assertEquals( 1, wow.getnJogadas() );
			
			//verifica o a condicao de incremento de nZeradas
			assertEquals( 0, wow.getnZeradas() );
			
			//verifica o x2p obtido na jogada
			int x2pEsperado = 10;
			assertEquals(x2pEsperado, x2pObtido);
			
			
			//verifica a situacao em que o recorde nao eh batido e o jogo eh zerado
			
			x2pObtido = wow.registraJogada(1000, true);
			
			//testa atualizacao do maior score
			assertEquals( 5000, wow.getMaiorScore() );
			
			//testa o incremento do numero de jogadas
			assertEquals( 2, wow.getnJogadas() );
			
			//verifica o a condicao de incremento de nZeradas
			assertEquals( 1, wow.getnZeradas() );
			
			//verifica o x2p obtido na jogada
			x2pEsperado = 10;
			assertEquals(x2pEsperado, x2pObtido);
			
			
			//verifica a situacao em que o recorde eh batido e o jogo eh zerado
			
			x2pObtido = wow.registraJogada(6000, true);
			
			//testa atualizacao do maior score
			assertEquals( 6000, wow.getMaiorScore() );
			
			//testa o incremento do numero de jogadas
			assertEquals( 3, wow.getnJogadas() );
			
			//verifica o a condicao de incremento de nZeradas
			assertEquals( 2, wow.getnZeradas() );
			
			//verifica o x2p obtido na jogada
			x2pEsperado = 10;
			assertEquals(x2pEsperado, x2pObtido);
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		
		//verifica situacao em que eh passado um valor negativo de score
		try{
			
			JogoRPG wow = new JogoRPG("World of Warcraft", 100);
			
			wow.registraJogada(-1, true);
			
			fail("deveria ter lancado uma ValorNumericoInvalidoException.");
			
		}catch(ValorNumericoInvalidoException vie){
			
			String mensagemRecebida = vie.getMessage();
			String mensagemEsperada = "Nao eh permitido o registro de score negativo";
			
			assertEquals(mensagemEsperada, mensagemRecebida);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
