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
			fail("Não deveria lançar exceptions");
		}
	
		
		
		//teste criando album de nome vazio
				try{
				
				Album darkSideOfTheMoon = new Album("", 1973, "Pink Floyd");
				
				fail("Deveria ter lançado uma exception");
				
				}catch(Exception e){
					assertEquals("Não é permitida a criação de musicas sem titulo.",
							e.getMessage());
				}
				
				// teste criando album de nome nulo
				try{
					
					//Album darkSideOfTheMoon = new Album(null);
					
					fail("Deveria ter lançado uma exception");
					
					}catch(Exception e){
						assertEquals("Não é permitida a criação de musicas sem titulo.",
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
			
			// não é permitido inserir a mesma musica duas ou mais vezes
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
			fail("Não deveria lançar exceptions");
		}
		
		// testes de falha
		
		//inserindo valores invalidos pra musica:
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			//tentando inserir uma musica nula 
			darkSideOfTheMoon.adicionaFaixa(null);
			
			fail("Deveria ter lançado uma exception");
			
		}catch(Exception e){
			assertEquals("Não é possivel adicionar faixas vazias", e.getMessage());
		}
		
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			// tentando inserir o titulo da musica nulo
			darkSideOfTheMoon.adicionaFaixa(null, 0 ,"Rock");
			
			fail("Deveria ter lançado uma exception");
			
		}catch(Exception e){
			assertEquals("Titulo da musica nao pode ser nulo ou vazio.", e.getMessage());
		}
		
		
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			// tentando inserir musica com duração negativa
			darkSideOfTheMoon.adicionaFaixa("Time", -1 ,"Rock");
			
			fail("Deveria ter lançado uma exception");
			
		}catch(Exception e){
			assertEquals("Duracao da musica nao pode ser negativa.", e.getMessage());
		}
		
		try{
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			// tentando inserir um genero vazio
			darkSideOfTheMoon.adicionaFaixa("Time", 100 ,"");
			
			fail("Deveria ter lançado uma exception");
			
		}catch(Exception e){
			assertEquals("Genero da musica nao pode ser nulo ou vazio.", e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	//!!!!!!!!!!!!!!!!SE DER TEMPO, COLOCAR ESSAS MENSAGENS DE EXCEPTION EM CONSTANTES
	
	

}
