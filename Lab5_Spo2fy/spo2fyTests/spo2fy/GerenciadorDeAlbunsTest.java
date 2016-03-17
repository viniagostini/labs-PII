package spo2fy;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

public class GerenciadorDeAlbunsTest {

	@Test
	public void testIserirAlbuns() {
		
		try{
			
			GerenciadorDeAlbuns gerenciadorDeAlbuns = new GerenciadorDeAlbuns();
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album darkSideOfTheMoon2 = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album theWall = new Album("The Wall", 1979, "Pink Floyd");
			Album ten = new Album("Ten", 1991, "Pearl Jam");
			

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
			
			
		}catch(Exception e){
			fail("Not yet implemented");
		}
		
	}
	
	@Test
	public void testPesquisarAlbum(){
		
		try{
			
			GerenciadorDeAlbuns gerenciadorDeAlbuns = new GerenciadorDeAlbuns();
			
			Album darkSideOfTheMoon = new Album("Dark Side of the Moon", 1973, "Pink Floyd");
			Album theWall = new Album("The Wall", 1979, "Pink Floyd");
			Album ten = new Album("Ten", 1991, "Pearl Jam");
			

			boolean resultado = gerenciadorDeAlbuns.adicionaAlbum(darkSideOfTheMoon);
			assertTrue(resultado);
		
			resultado = gerenciadorDeAlbuns.adicionaAlbum(theWall);
			assertTrue(resultado);
			
			resultado = gerenciadorDeAlbuns.adicionaAlbum(ten);
			assertTrue(resultado);
			
			
			Album albumPesquisado = gerenciadorDeAlbuns.pesquisaAlbum("Dark Side of the Moon");
			assertEquals(darkSideOfTheMoon, albumPesquisado);
			
			albumPesquisado = gerenciadorDeAlbuns.pesquisaAlbum("The Wall");
			assertEquals(theWall, albumPesquisado);
			
			//pesquisando album que nao existe
			albumPesquisado = gerenciadorDeAlbuns.pesquisaAlbum("Nevermind");
			assertEquals(null, albumPesquisado);
			
			//UUID.randomUUID();
			
			//UUID id = UUID.fromString("asdf");
			
			///System.out.println(id.randomUUID());
			
			gerenciadorDeAlbuns.favoritaAlbum("Dark Side of the Moon");
			
			System.out.println(gerenciadorDeAlbuns);
			
		}catch(Exception e){
			fail("Nao deveria lancar exception");
		}
		
		
	}

}
