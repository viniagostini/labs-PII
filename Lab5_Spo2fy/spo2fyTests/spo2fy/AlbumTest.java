package spo2fy;

import static org.junit.Assert.*;
import org.junit.Test;

public class AlbumTest {

	@Test
	public void testAlbum() {
		
		//teste para verificar a criacao adequada do objeto do tipo Album
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			assertEquals("Dark Side of the Moon", darkSideOfTheMoon.getTitulo());
			assertEquals(1973, darkSideOfTheMoon.getAno());
			assertEquals("Pink Floyd", darkSideOfTheMoon.getArtista());
			assertEquals(0, darkSideOfTheMoon.getDuracaoTotal());
			
			
		}catch(Exception e ){
			fail("Nao deveria lancar exceptions");
		}
	
		
		
		//teste criando album de nome vazio
		try{
		
		Album darkSideOfTheMoon = new Album("", 1973, "Pink Floyd");
		
		fail("Deveria ter lancado uma exception");
		
		}catch(Exception e){
			assertEquals("Nao eh permitida a criacao de musicas sem titulo.",
					e.getMessage());
		}
		
		// teste criando album de nome nulo
		try{
			
			Album darkSideOfTheMoon = new Album(null, 2000, "W. Safadao");
			
			fail("Deveria ter lancado uma exception");
			
			}catch(Exception e){
				assertEquals("Nao eh permitida a criacao de musicas sem titulo.",
						e.getMessage());
		}
		
		// teste criando album com data inferior a 1901
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the moon", 1900, "Pink Floyd");
			
			fail("Deveria ter lancado uma exception");
			
			}catch(Exception e){
				assertEquals("Nao eh permitida a criacao de albuns com data inferior a 1900.",
						e.getMessage());
		}
		
	}
	
	@Test
	public void testInserirMusica(){
		
		//teste de insercao, verificando se a classe deixa iserir duas musicas iguais
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			boolean resultado = darkSideOfTheMoon.adicionaFaixa("Time", 300, "Rock");
			assertTrue(resultado);
			
			Musica money = new Musica("Money", 200,"Rock");
			
			resultado = darkSideOfTheMoon.adicionaFaixa(money);
			assertTrue(resultado);
			
			// nao eh permitido inserir a mesma musica duas ou mais vezes
			resultado = darkSideOfTheMoon.adicionaFaixa(money);
			assertFalse(resultado);
			
			Musica time = new Musica("Time", 300, "Rock");
			
			resultado = darkSideOfTheMoon.adicionaFaixa(time);
			assertFalse(resultado);
			
			
			resultado = darkSideOfTheMoon.adicionaFaixa("Money", 200,"Rock");
			assertFalse(resultado);
			
			// asserts que garantem que as musicas foram inseridas:
			
			assertEquals(500, darkSideOfTheMoon.getDuracaoTotal());
			
			assertTrue(darkSideOfTheMoon.temFaixa("Money"));
			assertTrue(darkSideOfTheMoon.temFaixa("Time"));
			assertTrue(darkSideOfTheMoon.temFaixa(money));
			assertTrue(darkSideOfTheMoon.temFaixa(time));
			
			assertFalse(darkSideOfTheMoon.temFaixa("Brain Damage"));
			
		}catch(Exception e){
			System.out.println(e);
			fail("Nao deveria lancar exceptions");
		}
		
		// testes de falha
		
		//inserindo valores invalidos pra musica:
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			//tentando inserir uma musica nula 
			darkSideOfTheMoon.adicionaFaixa(null);
			
			fail("Deveria ter lancado uma exception");
			
		}catch(Exception e){
			assertEquals("Nao eh possivel adicionar faixas vazias", e.getMessage());
		}
		
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			// tentando inserir o titulo da musica nulo
			darkSideOfTheMoon.adicionaFaixa(null, 0 ,"Rock");
			
			fail("Deveria ter lancado uma exception");
			
		}catch(Exception e){
			assertEquals("Titulo da musica nao pode ser nulo ou vazio.", e.getMessage());
		}
		
		
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			// tentando inserir musica com duracao negativa
			darkSideOfTheMoon.adicionaFaixa("Time", -1 ,"Rock");
			
			fail("Deveria ter lancado uma exception");
			
		}catch(Exception e){
			assertEquals("Duracao da musica nao pode ser negativa.", e.getMessage());
		}
		
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			// tentando inserir um genero vazio
			darkSideOfTheMoon.adicionaFaixa("Time", 100 ,"");
			
			fail("Deveria ter lancado uma exception");
			
		}catch(Exception e){
			assertEquals("Genero da musica nao pode ser nulo ou vazio.", e.getMessage());
		}
	
		
		//inserindo musica nula
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			// tentando inserir um genero vazio
			darkSideOfTheMoon.adicionaFaixa(null);
			
			fail("Deveria ter lancado uma exception");
			
		}catch(Exception e){
			assertEquals("Nao eh possivel adicionar faixas vazias", e.getMessage());
		}

	}
	
	@Test
	public void testRemoverMusica(){
		
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			boolean resultado = darkSideOfTheMoon.adicionaFaixa("Time", 300, "Rock");
			assertTrue(resultado);
			
			Musica money = new Musica("Money", 200,"Rock");
			
			resultado = darkSideOfTheMoon.adicionaFaixa(money);
			assertTrue(resultado);
			
			
			resultado = darkSideOfTheMoon.removeFaixa("Time");
			assertTrue(resultado);
			
			
			//garantia que a faixa foi removida
			
			resultado = darkSideOfTheMoon.temFaixa("Time");
			assertFalse(resultado);
			
			int duracao = darkSideOfTheMoon.getDuracaoTotal();
			assertEquals(200, duracao);
			
			
		}catch(Exception e){
			System.out.println(e);
			fail("Nao deveria lancar exceptions");
		}
	
		
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			boolean resultado = darkSideOfTheMoon.adicionaFaixa("Time", 300, "Rock");
			assertTrue(resultado);
			
			Musica money = new Musica("Money", 200,"Rock");
			
			resultado = darkSideOfTheMoon.adicionaFaixa(money);
			assertTrue(resultado);
			
			
			resultado = darkSideOfTheMoon.removeFaixa("");
			
			fail("Deveria lancar exceptions");
			
			
		}catch(Exception e){
			assertEquals("Nao eh permitido pesquisar valores nulos", 
							e.getMessage());
		}
	
	}
	
	@Test
	public void testGetFaixa(){

		try{
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			Musica speakToMe = new Musica("Speak to me", 200, "Rock");
			boolean resultado = darkSideOfTheMoon.adicionaFaixa(speakToMe);
			assertTrue(resultado);
			
			Musica breathe = new Musica("Breathe", 600, "Rock");
			resultado = darkSideOfTheMoon.adicionaFaixa(breathe);
			assertTrue(resultado);
			
			Musica onTheRun = new Musica("On the Run", 300, "Rock");
			resultado = darkSideOfTheMoon.adicionaFaixa(onTheRun);
			assertTrue(resultado);			
			
			Musica time = new Musica("Time", 400, "Rock");
			resultado = darkSideOfTheMoon.adicionaFaixa(time);
			assertTrue(resultado);
			
			// teste consultando faixas 
			
			Musica faixa1 = darkSideOfTheMoon.getFaixa(1);
			Musica faixa2 = darkSideOfTheMoon.getFaixa(2);
			Musica faixa3 = darkSideOfTheMoon.getFaixa(3);
			Musica faixa4 = darkSideOfTheMoon.getFaixa(4);
			
			
			assertEquals(faixa1, speakToMe);
			assertEquals(faixa2, breathe);
			assertEquals(faixa3, onTheRun);
			assertEquals(faixa4, time);

			
		}catch(Exception e){
			fail("Não deveria ter lancado exception");
		}
		
		// testes de falha
		
		try{
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			Musica speakToMe = new Musica("Speak to me", 200, "Rock");
			boolean resultado = darkSideOfTheMoon.adicionaFaixa(speakToMe);
			assertTrue(resultado);
			
			Musica breathe = new Musica("Breathe", 600, "Rock");
			resultado = darkSideOfTheMoon.adicionaFaixa(breathe);
			assertTrue(resultado);
			
			Musica onTheRun = new Musica("On the Run", 300, "Rock");
			resultado = darkSideOfTheMoon.adicionaFaixa(onTheRun);
			assertTrue(resultado);			
			
			Musica time = new Musica("Time", 400, "Rock");
			resultado = darkSideOfTheMoon.adicionaFaixa(time);
			assertTrue(resultado);
		
			//consulta um indice abaixo de 1
			
			try{
				darkSideOfTheMoon.getFaixa(0);
				fail("Deveria ter lancado uma exception");
			}catch(Exception e){
				assertEquals("Nao existem faixas com numero inferiror a 1.", e.getMessage());
			}
			
			
			//consulta um indice acima do numero de faixas
			try{
				darkSideOfTheMoon.getFaixa(5);
				fail("Deveria ter lancado uma exception");
			}catch(Exception e){
				assertEquals("Nao ha musica com esse indice no album.", e.getMessage());
			}
			
		}catch(Exception e){
			fail("Nao deveria ter lancado exception");
		}
	}
	
	

}
