package jogo;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.DadosInvalidosException;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */

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
	
	
	@Test
	public void testAdicionaJogabilidade() {
		
		//verifica se as jogabilidades estao sendo inseridas corretamente
		try{
			JogoRPG wow = new JogoRPG("World of Warcraft", 100);
			
			boolean adicionou = wow.adicionaJogabilidade(Jogabilidade.MULTIPLAYER);
			
			//verifica a resposta
			assertTrue(adicionou);
			
			//verifica se adicionou de fato
			boolean contem = wow.getJogabilidades().contains(Jogabilidade.MULTIPLAYER);
			assertTrue(contem);
			
			
			//adicionando outra jogabilidade
			adicionou = wow.adicionaJogabilidade(Jogabilidade.COMPETITIVO);
			
			//verifica a resposta
			assertTrue(adicionou);
			
			//verifica se adicionou de fato
			contem = wow.getJogabilidades().contains(Jogabilidade.COMPETITIVO);
			assertTrue(contem);
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		//testando inserir duas jogabilidades iguais
		try{
			JogoRPG wow = new JogoRPG("World of Warcraft", 100);
			
			boolean adicionou = wow.adicionaJogabilidade(Jogabilidade.MULTIPLAYER);
			assertTrue(adicionou);
			
			//adicionando a mesma jogabilidade
			adicionou = wow.adicionaJogabilidade(Jogabilidade.MULTIPLAYER);
			
			//verifica a resposta
			assertFalse(adicionou);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		//testando inserir jogabilidade nula
		try{
			JogoRPG wow = new JogoRPG("World of Warcraft", 100);
			
			wow.adicionaJogabilidade(null);
			
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
			JogoRPG wow = new JogoRPG("World of Warcraft", 100);
			JogoRPG outroWow = new JogoRPG("World of Warcraft", 100);
			JogoRPG theKingOfFighter = new JogoRPG("The King of Fighter", 100);
			JogoRPG naruto = new JogoRPG("Naruto", 50);
			
			boolean iguais = wow.equals(outroWow);
			assertTrue(iguais);
			
			iguais = wow.equals(theKingOfFighter);
			assertFalse(iguais);
			
			iguais = wow.equals(naruto);
			assertFalse(iguais);
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
	}
	
	
}
