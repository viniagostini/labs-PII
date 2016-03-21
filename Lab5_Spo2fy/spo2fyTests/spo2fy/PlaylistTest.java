package spo2fy;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlaylistTest {
	

	@Test
	public void testAdcionaMusica() {
		try{
			Playlist playlist = new Playlist("Inspiracao");
			
			Musica eyeOfTheTiger = new Musica("Eye of the Tiger", 4, "Rock");
			Musica dontStopBelivien = new Musica("Dont stop Belivien", 5, "Rock");
			Musica time = new Musica("Time", 6, "Rock");
			Musica fireworks = new Musica("Fireworks", 3, "Pop");
			Musica creep = new Musica("Creep", 5, "Rock");
			

			boolean result = playlist.adcionaMusica(eyeOfTheTiger);
			assertTrue(result);
			
			result = playlist.adcionaMusica(dontStopBelivien);
			assertTrue(result);
			
			result = playlist.adcionaMusica(time);
			assertTrue(result);
			
			result = playlist.adcionaMusica(fireworks);
			assertTrue(result);
			
			result = playlist.adcionaMusica(time);
			assertFalse(result);
			
			
			//verifica se as musicas foram de fato adicionadas
			result = playlist.temMusica(time);
			assertTrue(result);
			
			result = playlist.temMusica(fireworks);
			assertTrue(result);
			
			result = playlist.temMusica(creep);
			assertFalse(result);
			
			
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions");
		}
	}

	@Test
	public void testRemoveMusica() {
		try{
			Playlist playlist = new Playlist("Inspiracao");
			
			Musica eyeOfTheTiger = new Musica("Eye of the Tiger", 4, "Rock");
			Musica dontStopBelivien = new Musica("Dont stop Belivien", 5, "Rock");
			Musica time = new Musica("Time", 6, "Rock");
			Musica fireworks = new Musica("Fireworks", 3, "Pop");
			Musica creep = new Musica("Creep", 5, "Rock");
			

			boolean result = playlist.adcionaMusica(eyeOfTheTiger);
			assertTrue(result);
			
			result = playlist.adcionaMusica(dontStopBelivien);
			assertTrue(result);
			
			result = playlist.adcionaMusica(time);
			assertTrue(result);
			
			result = playlist.adcionaMusica(fireworks);
			assertTrue(result);
			
			result = playlist.adcionaMusica(time);
			assertFalse(result);
			
			
			//verifica se as musicas foram de fato adicionadas
			result = playlist.temMusica(time);
			assertTrue(result);
			
			result = playlist.temMusica(fireworks);
			assertTrue(result);
			
			result = playlist.temMusica(creep);
			assertFalse(result);
			
			
			
			//remove musicas
			
			result = playlist.removeMusica(fireworks);
			assertTrue(result);
			
			//testa se de fato a musica foi removida
			
			result = playlist.temMusica(fireworks);
			assertFalse(result);
			
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions");
		}
	}

	@Test
	public void testGetDuracaoTotal() {
		try{
			Playlist playlist = new Playlist("Inspiracao");
			
			//cria musicas
			Musica eyeOfTheTiger = new Musica("Eye of the Tiger", 4, "Rock");
			Musica dontStopBelivien = new Musica("Dont stop Belivien", 5, "Rock");
			Musica time = new Musica("Time", 6, "Rock");
			Musica fireworks = new Musica("Fireworks", 3, "Pop");
			
			
			//adiciona musicas
			playlist.adcionaMusica(eyeOfTheTiger);
			playlist.adcionaMusica(dontStopBelivien);
			playlist.adcionaMusica(time);
			playlist.adcionaMusica(fireworks);
			
			
			int duracaoTotal = playlist.getDuracaoTotal();
			
			assertEquals(18, duracaoTotal);
			
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions");
		}
	}

}
