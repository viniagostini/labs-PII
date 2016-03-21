package spo2fy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

public class GerenciadorDeAlbunsTest {

	@Before
	public void qualquerCoisa(){
		//executa antes de cada teste
	}
	
	@Test
	public void testAdicionaAlbum() {
		
		try{
			
			GerenciadorDeAlbuns gerenciadorDeAlbuns = new GerenciadorDeAlbuns();
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album darkSideOfTheMoon2 = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album theWall = new Album("The Wall", 1979, "Pink Floyd");
			Album ten = new Album("Ten", 1991, "Pearl Jam");
			Album yield = new Album("Yield", 1998, "Pearl Jam");
			
			boolean resultado = gerenciadorDeAlbuns.adicionaAlbum(darkSideOfTheMoon);
			assertTrue(resultado);
			
			// teste inserindo o mesmo album
			resultado = gerenciadorDeAlbuns.adicionaAlbum(darkSideOfTheMoon);
			assertFalse(resultado);
			
			// teste inserindo albuns iguais
			resultado = gerenciadorDeAlbuns.adicionaAlbum(darkSideOfTheMoon2);
			assertFalse(resultado);

			resultado = gerenciadorDeAlbuns.adicionaAlbum(theWall);
			assertTrue(resultado);
			
			resultado = gerenciadorDeAlbuns.adicionaAlbum(ten);
			assertTrue(resultado);
			
			
			//verifica se os albuns foram inseridos

			resultado = gerenciadorDeAlbuns.temAlbum(darkSideOfTheMoon);
			assertTrue(resultado);
			
			resultado = gerenciadorDeAlbuns.temAlbum(theWall);
			assertTrue(resultado);
			
			resultado = gerenciadorDeAlbuns.temAlbum(ten);
			assertTrue(resultado);
			
			resultado = gerenciadorDeAlbuns.temAlbum(ten);
			assertTrue(resultado);
			
			
			
			resultado = gerenciadorDeAlbuns.temAlbum(yield);
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
		
		GerenciadorDeAlbuns gerenciadorDeAlbuns = getGerenciadorPreenchido();
		
		//favoritando albuns
		boolean result = gerenciadorDeAlbuns.favoritaAlbum("Dark Side of the Moon", "Pink Floyd");
		assertTrue(result);
		
		result = gerenciadorDeAlbuns.favoritaAlbum("Ten", "Pearl Jam");
		assertTrue(result);
		
		
		// testando se foram realmente favoritados
		result = gerenciadorDeAlbuns.ehFavorito("Dark Side of the Moon", "Pink Floyd");
		assertTrue(result);
		
		result = gerenciadorDeAlbuns.ehFavorito("Yield", "Pearl Jam");
		assertFalse(result);
		
		//testando desfavoritar album
		result = gerenciadorDeAlbuns.desfavoritaAlbum("Dark Side of the Moon", "Pink Floyd");
		assertTrue(result);
		
		result = gerenciadorDeAlbuns.ehFavorito("Dark Side of the Moon", "Pink Floyd");
		assertFalse(result);
		
		//desfavoritando um nao favorito
		result = gerenciadorDeAlbuns.desfavoritaAlbum("Yield", "Pearl Jam");
		assertFalse(result);
		

		}catch(Exception e){
			fail("Nao deveria ter lancado exception");
		}
		
		
		
	}
	
	@Test
	public void testBuscaAlbum(){
		
		try{
			
			GerenciadorDeAlbuns gerenciadorDeAlbuns = getGerenciadorPreenchido();
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album theWall = new Album("The Wall", 1979, "Pink Floyd");
			
			Album albumPesquisado = gerenciadorDeAlbuns.buscaAlbum("Dark Side of the Moon", "Pink Floyd");
			
			assertEquals(darkSideOfTheMoon, albumPesquisado);
			
			assertFalse(theWall.equals(albumPesquisado));
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions");
		}
	}
	
	@Test
	public void testRemoveAlbum(){
		
		try{
			GerenciadorDeAlbuns gerenciadorDeAlbuns = this.getGerenciadorPreenchido();
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			
			boolean result = gerenciadorDeAlbuns.temAlbum(darkSideOfTheMoon);
			assertTrue(result);
			
			
			result = gerenciadorDeAlbuns.removeAlbum("Dark Side of the Moon", "Pink Floyd");
			assertTrue(result);
			
			//verifica se album foi de fato removido
			result = gerenciadorDeAlbuns.temAlbum(darkSideOfTheMoon);
			assertFalse(result);
			
		}catch(Exception e){
			fail("Nao deveria lancar exception");
		}
		
	}

	@Test
	public void testBuscaAlbumPorX(){
		try{
			GerenciadorDeAlbuns gerenciadorDeAlbuns = new GerenciadorDeAlbuns();
			
			//criando albuns
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album theWall = new Album("The Wall", 1979, "Pink Floyd");
			Album ten = new Album("Ten", 1991, "Pearl Jam");
			Album yield = new Album("Yield", 1998, "Pearl Jam");
			Album darkSideOfTheMoon2 = new Album("Dark Side of the Moon", 1973, "Outro Artista");
			
			
			//adicionando albuns
			gerenciadorDeAlbuns.adicionaAlbum(darkSideOfTheMoon);
			gerenciadorDeAlbuns.adicionaAlbum(theWall);
			gerenciadorDeAlbuns.adicionaAlbum(ten);
			gerenciadorDeAlbuns.adicionaAlbum(yield);	
			gerenciadorDeAlbuns.adicionaAlbum(darkSideOfTheMoon2);
			
			//teste busca por titulo
			String titulo = "Dark Side of the Moon";
			ArrayList<Album> listaPorTitulo = gerenciadorDeAlbuns.buscaAlbunsPorTitulo(titulo);
			
			boolean result = listaPorTitulo.contains(darkSideOfTheMoon);
			assertTrue(result);
			
			result = listaPorTitulo.contains(darkSideOfTheMoon2);
			assertTrue(result);
			
			result = listaPorTitulo.contains(theWall);
			assertFalse(result);
			
			//teste busca por Artita
			String artista = "Pearl Jam";
			ArrayList<Album> listaPorArtista = gerenciadorDeAlbuns.buscaAlbunsPorArtista(artista);
			
			result = listaPorArtista.contains(yield);
			assertTrue(result);
			
			result = listaPorArtista.contains(ten);
			assertTrue(result);
			
			result = listaPorArtista.contains(theWall);
			assertFalse(result);
			
			//teste busca album por ano
			int ano = 1973;
			ArrayList<Album> listaPorAno = gerenciadorDeAlbuns.buscaAlbunsPorAno(ano);
			
			result = listaPorAno.contains(darkSideOfTheMoon);
			assertTrue(result);
			
			result = listaPorAno.contains(darkSideOfTheMoon2);
			assertTrue(result);
			
			result = listaPorAno.contains(theWall);
			assertFalse(result);
			
			
			
		}catch(Exception e){
			fail();
		}
	}
	
	
	
	private GerenciadorDeAlbuns getGerenciadorPreenchido(){
		GerenciadorDeAlbuns gerenciadorDeAlbuns = new GerenciadorDeAlbuns();
		try{
			//criando albuns
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album theWall = new Album("The Wall", 1979, "Pink Floyd");
			Album ten = new Album("Ten", 1991, "Pearl Jam");
			Album yield = new Album("Yield", 1998, "Pearl Jam");
			
			//adicionando albuns
			gerenciadorDeAlbuns.adicionaAlbum(darkSideOfTheMoon);
			gerenciadorDeAlbuns.adicionaAlbum(theWall);
			gerenciadorDeAlbuns.adicionaAlbum(ten);
			gerenciadorDeAlbuns.adicionaAlbum(yield);
			
			
		}catch(Exception e){
			fail("Nao deveria lancar exception");
		}
		
		return gerenciadorDeAlbuns;
	}
	
	
	
}
