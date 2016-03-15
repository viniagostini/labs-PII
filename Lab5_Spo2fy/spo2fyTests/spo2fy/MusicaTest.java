package spo2fy;

import static org.junit.Assert.*;
import org.junit.Test;

public class MusicaTest {

	@Test
	public void testMusica() {
		try {
			Musica chandelier = new Musica("Chandelier", 3, "Pop");
			Musica elasticHeart = new Musica("Elastic Heart", 3, "Pop");
			Musica cellophane = new Musica("Cellophane", 4, "Pop");

			assertEquals("Chandelier", chandelier.getTitulo());
			assertEquals(3, chandelier.getDuracao());
			assertEquals("Pop", chandelier.getGenero());

			assertEquals("Elastic Heart", elasticHeart.getTitulo());
			assertEquals(3, elasticHeart.getDuracao());
			assertEquals("Pop", elasticHeart.getGenero());

			assertEquals("Cellophane", cellophane.getTitulo());
			assertEquals(4, cellophane.getDuracao());
			assertEquals("Pop", cellophane.getGenero());

			Musica chandelierRemix = new Musica("Chandelier", 3, "Pop");
			assertEquals(chandelier, chandelierRemix);
			assertFalse(chandelier.equals(cellophane));

		} catch (Exception e) {
			fail();// nao deveria ter lancado nenhuma Exception nesse teste.
		}
	}

	@Test
	public void testEquals() {
		Musica chandelier;
		try {
			chandelier = new Musica("Chandelier", 3, "Pop");
			Musica elasticHeart = new Musica("Elastic Heart", 3, "Pop");
			Musica cellophane = new Musica("Cellophane", 4, "Pop");

			assertEquals(chandelier, new Musica("Chandelier", 3, "Pop"));
			assertEquals(chandelier, new Musica("Chandelier", 3, "Pop-rock"));
			assertNotEquals(elasticHeart, chandelier);
			assertNotEquals(elasticHeart, cellophane);
		} catch (Exception e) {
			fail("não deve lançar exceção aqui.");
		}
	}

	@Test
	public void testMusicaInvalida() {
		try {
			Musica tituloInvalido = new Musica("", 3, "Pop");
			fail(); // se chegar aqui da erro, pois deveria lancar exception.
		} catch (Exception e) {
			assertEquals("Titulo da musica nao pode ser nulo ou vazio.",
					e.getMessage());
		}

		try {
			Musica tituloInvalido = new Musica(null, 3, "Pop");
			fail(); // se chegar aqui da erro, pois deveria lancar exception.
		} catch (Exception e) {
			assertEquals("Titulo da musica nao pode ser nulo ou vazio.",
					e.getMessage());
		}

		try {
			Musica duracaoInvalida = new Musica("Elastic Heart", -5, "Pop");
			fail(); // se chegar aqui da erro, pois deveria lancar exception.
		} catch (Exception e) {
			assertEquals("Duracao da musica nao pode ser negativa.",
					e.getMessage());
		}

		try {
			Musica tipoInvalido = new Musica("Cellophane", 4, "");
			fail(); // se chegar aqui da erro, pois deveria lancar exception.
		} catch (Exception e) {
			assertEquals("Genero da musica nao pode ser nulo ou vazio.",
					e.getMessage());
		}

		try {
			Musica tipoInvalido = new Musica("Cellophane", 4, null);
			fail(); // se chegar aqui da erro, pois deveria lancar exception.
		} catch (Exception e) {
			assertEquals("Genero da musica nao pode ser nulo ou vazio.",
					e.getMessage());
		}

	}
}
