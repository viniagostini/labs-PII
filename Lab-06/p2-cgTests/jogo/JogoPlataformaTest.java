package jogo;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

public class JogoPlataformaTest {

public static final String NAO_DEVERIA_LANCAR_EXCEPTION = "Nao deveria ter lancado exception";
	
	
	@Test
	public void testJogoPlataforma() {
		//teste do construtor
		
		//verifica o estado inicial do objeto
		try{
			JogoPlataforma limbo = new JogoPlataforma("Limbo", 100);

			//nome
			assertEquals("Limbo", limbo.getNome());

			//preco
			assertEquals(100.0, limbo.getPreco(), 0.0);
			
			//jogabilidade
			boolean vazio = limbo.getJogabilidades().isEmpty();
			assertTrue(vazio);
			
			//maiorScore
			assertEquals( 0, limbo.getMaiorScore() );
			
			//nJogadas
			assertEquals( 0, limbo.getnJogadas() );
			
			//nZeradas
			assertEquals( 0, limbo.getnZeradas() );
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		
		//verificando insercao de nome vazio
		try{
			@SuppressWarnings("unused")
			JogoPlataforma limbo = new JogoPlataforma("", 100);
				
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
			JogoPlataforma limbo = new JogoPlataforma(null, 100);
			
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
			JogoPlataforma limbo = new JogoPlataforma("Limbo", -1);
			
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
			JogoPlataforma limbo = new JogoPlataforma("limbo", 100);
			
			int x2pObtido = limbo.registraJogada(5000, false);
			
			//testa atualizacao do maior score
			assertEquals( 5000, limbo.getMaiorScore() );
			
			//testa o incremento do numero de jogadas
			assertEquals( 1, limbo.getnJogadas() );
			
			//verifica o a condicao de incremento de nZeradas
			assertEquals( 0, limbo.getnZeradas() );
			
			//verifica o x2p obtido na jogada
			int x2pEsperado = 0;
			assertEquals(x2pEsperado, x2pObtido);
			
			
			//verifica a situacao em que o recorde nao eh batido e o jogo eh zerado
			
			x2pObtido = limbo.registraJogada(1000, true);
			
			//testa atualizacao do maior score
			assertEquals( 5000, limbo.getMaiorScore() );
			
			//testa o incremento do numero de jogadas
			assertEquals( 2, limbo.getnJogadas() );
			
			//verifica o a condicao de incremento de nZeradas
			assertEquals( 1, limbo.getnZeradas() );
			
			//verifica o x2p obtido na jogada
			x2pEsperado = 20;
			assertEquals(x2pEsperado, x2pObtido);
			
			
			//verifica a situacao em que o recorde eh batido e o jogo eh zerado
			
			x2pObtido = limbo.registraJogada(6000, true);
			
			//testa atualizacao do maior score
			assertEquals( 6000, limbo.getMaiorScore() );
			
			//testa o incremento do numero de jogadas
			assertEquals( 3, limbo.getnJogadas() );
			
			//verifica o a condicao de incremento de nZeradas
			assertEquals( 2, limbo.getnZeradas() );
			
			//verifica o x2p obtido na jogada
			x2pEsperado = 20;
			assertEquals(x2pEsperado, x2pObtido);
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		
		//verifica situacao em que eh passado um valor negativo de score
		try{
			
			JogoPlataforma limbo = new JogoPlataforma("limbo", 100);
			
			limbo.registraJogada(-1, true);
			
			fail("deveria ter lancado uma ValorNumericoInvalidoException.");
			
		}catch(ValorNumericoInvalidoException vie){
			
			String mensagemRecebida = vie.getMessage();
			String mensagemEsperada = "Nao eh permitido o registro de score negativo";
			
			assertEquals(mensagemEsperada, mensagemRecebida);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
			
	}
	
	
	@Test
	public void testEquals() {
		try{
			JogoPlataforma limbo = new JogoPlataforma("Limbo", 100);
			JogoPlataforma outroLimbo = new JogoPlataforma("Limbo", 100);
			JogoPlataforma theKingOfFighter = new JogoPlataforma("The King of Fighter", 100);
			JogoPlataforma naruto = new JogoPlataforma("Naruto", 50);
			
			boolean iguais = limbo.equals(outroLimbo);
			assertTrue(iguais);
			
			iguais = limbo.equals(theKingOfFighter);
			assertFalse(iguais);
			
			iguais = limbo.equals(naruto);
			assertFalse(iguais);
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
	}

	
}
