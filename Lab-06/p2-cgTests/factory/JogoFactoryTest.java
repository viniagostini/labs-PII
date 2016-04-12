package factory;

import static org.junit.Assert.*;

import org.junit.Test;

import jogo.Jogo;
import jogo.JogoLuta;
import jogo.TipoDeJogo;

public class JogoFactoryTest {

	@Test
	public void testCriaJogo() {
		try{
			
			JogoFactory jogoFactory = new JogoFactory();
			
			Jogo mortalKombat = jogoFactory.criaJogo("Mortal Kombat", 100, TipoDeJogo.LUTA);
			
			//verificando o estado inicial do objeto
			
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
			
			
			//verificando se o tipo do jogo foi criado corretamente
			
			@SuppressWarnings("unused")
			JogoLuta mk = (JogoLuta) mortalKombat;
			
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions");
		}
	}

}
