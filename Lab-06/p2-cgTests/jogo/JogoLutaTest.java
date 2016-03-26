package jogo;

import static org.junit.Assert.*;


import org.junit.Test;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

public class JogoLutaTest {

	public static final String NAO_DEVERIA_LANCAR_EXCEPTION = "Nao deveria ter lancado exception";
	
	
	
	@Test
	public void testJogoLuta() {
		//teste do construtor
		
		//verifica o estado inicial do objeto
		try{
			JogoLuta mortalKombat = new JogoLuta("Mortal Kombat", 100);

			//nome
			assertEquals("Mortal Kombat", mortalKombat.getNome());

			//preco
			assertEquals(100.0, mortalKombat.getPreco(), 0.0);
			
			//jogabilidade
			boolean vazio = mortalKombat.getJogabilidades().isEmpty();
			assertTrue(vazio);
			
			//maiorScore
			assertEquals( 0, mortalKombat.getMaiorScore() );
			
			//nJogadas
			assertEquals( 0, mortalKombat.getnJogadas() );
			
			//nZeradas
			assertEquals( 0, mortalKombat.getnZeradas() );
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		
		//verifica insercaco de nome vazio
		try{
			@SuppressWarnings("unused")
			JogoLuta mortalKombat = new JogoLuta("", 100);
				
			fail("Deveria ser lancada uma StringInvalidaException");
			
		}catch(StringInvalidaException sie){
			
			String mensagemException = sie.getMessage();
			String mensagemEsperada = "Nao eh permitida a criacao de Jogos com nome vazio ou nulo.";
			
			assertEquals(mensagemEsperada, mensagemException);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui");
		}
		
		//verificando insercaco de nome nulo
		try{
			@SuppressWarnings("unused")
			JogoLuta mortalKombat = new JogoLuta(null, 100);
			
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
			JogoLuta mortalKombat = new JogoLuta("Mortal Kombat", -1);
			
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
			JogoLuta mortalKombat = new JogoLuta("Mortal Kombat", 100);
			
			int x2pObtido = mortalKombat.registraJogada(5000, false);
			
			//testa atualizacao do maior score
			assertEquals( 5000, mortalKombat.getMaiorScore() );
			
			//testa o incremento do numero de jogadas
			assertEquals( 1, mortalKombat.getnJogadas() );
			
			//verifica o a condicao de incremento de nZeradas
			assertEquals( 0, mortalKombat.getnZeradas() );
			
			//verifica o x2p obtido na jogada
			int x2pEsperado = 5;
			assertEquals(x2pEsperado, x2pObtido);
			
			
			//verifica a situacao em que o recorde nao eh batido e o jogo eh zerado
			
			x2pObtido = mortalKombat.registraJogada(1000, true);
			
			//testa atualizacao do maior score
			assertEquals( 5000, mortalKombat.getMaiorScore() );
			
			//testa o incremento do numero de jogadas
			assertEquals( 2, mortalKombat.getnJogadas() );
			
			//verifica o a condicao de incremento de nZeradas
			assertEquals( 1, mortalKombat.getnZeradas() );
			
			//verifica o x2p obtido na jogada
			x2pEsperado = 0;
			assertEquals(x2pEsperado, x2pObtido);
			
			
			//verifica a situacao em que o recorde eh batido e o jogo eh zerado
			
			x2pObtido = mortalKombat.registraJogada(6000, true);
			
			//testa atualizacao do maior score
			assertEquals( 6000, mortalKombat.getMaiorScore() );
			
			//testa o incremento do numero de jogadas
			assertEquals( 3, mortalKombat.getnJogadas() );
			
			//verifica o a condicao de incremento de nZeradas
			assertEquals( 2, mortalKombat.getnZeradas() );
			
			//verifica o x2p obtido na jogada
			x2pEsperado = 6;
			assertEquals(x2pEsperado, x2pObtido);
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		
		//verifica situacao em que eh passado um valor negativo de score
		try{
			
			JogoLuta mortalKombat = new JogoLuta("Mortal Kombat", 100);
			
			mortalKombat.registraJogada(-1, true);
			
			fail("deveria ter lancado uma ValorNumericoInvalidoException.");
			
		}catch(ValorNumericoInvalidoException vie){
			
			String mensagemRecebida = vie.getMessage();
			String mensagemEsperada = "Nao eh permitido o registro de score negativo";
			
			assertEquals(mensagemEsperada, mensagemRecebida);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		
		//verifica situacao em que eh passado um score maior que o maior permitido
		try{
			
			JogoLuta mortalKombat = new JogoLuta("Mortal Kombat", 100);
			
			int maiorQueOPermitido = JogoLuta.MAIOR_SCORE_POSSIVEL + 1;
			
			mortalKombat.registraJogada(maiorQueOPermitido, true);
			
			fail("deveria ter lancado uma LogicaDeNegociosExecption.");
			
		}catch(LogicaDeNegociosExecption lne){
			
			String mensagemRecebida = lne.getMessage();
			String mensagemEsperada = "Tah de hack";
			
			assertEquals(mensagemEsperada, mensagemRecebida);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}	
	}


	@Test
	public void testAdicionaJogabilidade() {
		
		//verifica se as jogabilidades estao sendo inseridas corretamente
		try{
			JogoLuta mortalKombat = new JogoLuta("Mortal Kombat", 100);
			
			boolean adicionou = mortalKombat.adicionaJogabilidade(Jogabilidade.MULTIPLAYER);
			
			//verifica a resposta
			assertTrue(adicionou);
			
			//verifica se adicionou de fato
			boolean contem = mortalKombat.getJogabilidades().contains(Jogabilidade.MULTIPLAYER);
			assertTrue(contem);
			
			
			//adicionando outra jogabilidade
			adicionou = mortalKombat.adicionaJogabilidade(Jogabilidade.COMPETITIVO);
			
			//verifica a resposta
			assertTrue(adicionou);
			
			//verifica se adicionou de fato
			contem = mortalKombat.getJogabilidades().contains(Jogabilidade.COMPETITIVO);
			assertTrue(contem);
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		//testando inserir duas jogabilidades iguais
		try{
			JogoLuta mortalKombat = new JogoLuta("Mortal Kombat", 100);
			
			boolean adicionou = mortalKombat.adicionaJogabilidade(Jogabilidade.MULTIPLAYER);
			assertTrue(adicionou);
			
			//adicionando a mesma jogabilidade
			adicionou = mortalKombat.adicionaJogabilidade(Jogabilidade.MULTIPLAYER);
			
			//verifica a resposta
			assertFalse(adicionou);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		//testando inserir jogabilidade nula
		try{
			JogoLuta mortalKombat = new JogoLuta("Mortal Kombat", 100);
			
			mortalKombat.adicionaJogabilidade(null);
			
			fail("Deveria ter lancado uma DadosInvalidosException.");
			
		}catch(DadosInvalidosException die){
			
			String mensagemRecebida = die.getMessage();
			String mensagemEsperada = "Nao eh permitida a adicao de jogabilidade nula";
			
			assertEquals(mensagemEsperada, mensagemRecebida);
			
			
		}catch (Exception e) {
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui");
		}
	}

	
	@Test
	public void testEquals() {
		try{
			JogoLuta mortalKombat = new JogoLuta("Mortal Kombat", 100);
			JogoLuta outroMortalKombat = new JogoLuta("Mortal Kombat", 100);
			JogoLuta theKingOfFighter = new JogoLuta("The King of Fighter", 100);
			JogoLuta naruto = new JogoLuta("Naruto", 50);
			
			boolean iguais = mortalKombat.equals(outroMortalKombat);
			assertTrue(iguais);
			
			iguais = mortalKombat.equals(theKingOfFighter);
			assertFalse(iguais);
			
			iguais = mortalKombat.equals(naruto);
			assertFalse(iguais);
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
	}

}
