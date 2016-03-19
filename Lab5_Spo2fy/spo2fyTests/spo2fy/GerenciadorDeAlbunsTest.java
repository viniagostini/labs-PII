package spo2fy;

import static org.junit.Assert.*;

import org.junit.Test;

public class GerenciadorDeAlbunsTest {

	@Test
	public void testAdicionaAlbum() {
		
		try{
			
			GerenciadorDeAlbuns gerenciadorDeAlbuns2 = new GerenciadorDeAlbuns();
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album darkSideOfTheMoon2 = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album theWall = new Album("The Wall", 1979, "Pink Floyd");
			Album ten = new Album("Ten", 1991, "Pearl Jam");
			Album yield = new Album("Yield", 1998, "Pearl Jam");
			
			boolean resultado = gerenciadorDeAlbuns2.adicionaAlbum(darkSideOfTheMoon);
			assertTrue(resultado);
			
			// teste inserindo o mesmo album
			resultado = gerenciadorDeAlbuns2.adicionaAlbum(darkSideOfTheMoon);
			assertFalse(resultado);
			
			// teste inserindo albuns iguais
			resultado = gerenciadorDeAlbuns2.adicionaAlbum(darkSideOfTheMoon2);
			assertFalse(resultado);

			resultado = gerenciadorDeAlbuns2.adicionaAlbum(theWall);
			assertTrue(resultado);
			
			resultado = gerenciadorDeAlbuns2.adicionaAlbum(ten);
			assertTrue(resultado);
			
			
			//verifica se os albuns foram inseridos

			resultado = gerenciadorDeAlbuns2.temAlbum(darkSideOfTheMoon);
			assertTrue(resultado);
			
			resultado = gerenciadorDeAlbuns2.temAlbum(theWall);
			assertTrue(resultado);
			
			resultado = gerenciadorDeAlbuns2.temAlbum(ten);
			assertTrue(resultado);
			
			resultado = gerenciadorDeAlbuns2.temAlbum(ten);
			assertTrue(resultado);
			
			
			
			resultado = gerenciadorDeAlbuns2.temAlbum(yield);
			assertFalse(resultado);
			
		}catch(Exception e){
			fail("Nao deveria ter lancado exception");
		}
		
		// testes de exception
		try{
			GerenciadorDeAlbuns gerenciadorDeAlbuns2 = new GerenciadorDeAlbuns();
			
			gerenciadorDeAlbuns2.adicionaAlbum(null);
			
		}catch(Exception e){
			String mensagemEsperada = "Nao eh possivel trabalhar com valores de albuns vazios ou null.";
			assertEquals(mensagemEsperada, e.getMessage());
		}
	}

	@Test
	public void testFavoritaAlbum(){
		
		try{
		
		GerenciadorDeAlbuns gerenciadorDeAlbuns2 = getGerenciadorPreenchido();
		
		//favoritando albuns
		boolean result = gerenciadorDeAlbuns2.favoritaAlbum("Dark Side of the Moon", "Pink Floyd");
		assertTrue(result);
		
		result = gerenciadorDeAlbuns2.favoritaAlbum("Ten", "Pearl Jam");
		assertTrue(result);
		
		
		// testando se foram realmente favoritados
		result = gerenciadorDeAlbuns2.ehFavorito("Dark Side of the Moon", "Pink Floyd");
		assertTrue(result);
		
		result = gerenciadorDeAlbuns2.ehFavorito("Yield", "Pearl Jam");
		assertFalse(result);
		
		//testando desfavoritar album
		result = gerenciadorDeAlbuns2.desfavoritaAlbum("Dark Side of the Moon", "Pink Floyd");
		assertTrue(result);
		
		result = gerenciadorDeAlbuns2.ehFavorito("Dark Side of the Moon", "Pink Floyd");
		assertFalse(result);
		
		//desfavoritando um nao favorito
		result = gerenciadorDeAlbuns2.desfavoritaAlbum("Yield", "Pearl Jam");
		assertFalse(result);
		

		}catch(Exception e){
			fail("Nao deveria ter lancado exception");
		}
		
		
		
	}
	
	@Test
	public void testPesquisaAlbum(){
		
		try{
			
			GerenciadorDeAlbuns gerenciadorDeAlbuns2 = getGerenciadorPreenchido();
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album theWall = new Album("The Wall", 1979, "Pink Floyd");
			
			Album albumPesquisado = gerenciadorDeAlbuns2.buscaAlbum("Dark Side of the Moon", "Pink Floyd");
			
			assertEquals(darkSideOfTheMoon, albumPesquisado);
			
			assertFalse(theWall.equals(albumPesquisado));
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions");
		}
	}
	
	@Test
	public void testRemoveAlbum(){
		
		try{
			GerenciadorDeAlbuns gerenciadorDeAlbuns2 = this.getGerenciadorPreenchido();
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			boolean result = gerenciadorDeAlbuns2.temAlbum(darkSideOfTheMoon);
			assertTrue(result);
			
			
			result = gerenciadorDeAlbuns2.removeAlbum("Dark Side of the Moon", "Pink Floyd");
			assertTrue(result);
			
			//verifica se album foi de fato removido
			result = gerenciadorDeAlbuns2.temAlbum(darkSideOfTheMoon);
			assertFalse(result);
			
		}catch(Exception e){
			fail("Nao deveria lancar exception");
		}
		
	}

	
		
	
	private GerenciadorDeAlbuns getGerenciadorPreenchido(){
		GerenciadorDeAlbuns gerenciadorDeAlbuns2 = new GerenciadorDeAlbuns();
		try{
			//criando albuns
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album theWall = new Album("The Wall", 1979, "Pink Floyd");
			Album ten = new Album("Ten", 1991, "Pearl Jam");
			Album yield = new Album("Yield", 1998, "Pearl Jam");
			
			//adicionando albuns
			gerenciadorDeAlbuns2.adicionaAlbum(darkSideOfTheMoon);
			gerenciadorDeAlbuns2.adicionaAlbum(theWall);
			gerenciadorDeAlbuns2.adicionaAlbum(ten);
			gerenciadorDeAlbuns2.adicionaAlbum(yield);
			
			
		}catch(Exception e){
			fail("Nao deveria lancar exception");
		}
		
		return gerenciadorDeAlbuns2;
	}
	
	
	
}
