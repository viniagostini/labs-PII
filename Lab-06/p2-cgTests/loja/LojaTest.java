package loja;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.StringInvalidaException;
import jogo.Jogo;
import usuario.Usuario;
import usuario.UsuarioNoob;
import usuario.UsuarioVeterano;

public class LojaTest {

	@Test
	public void testAdicionaUsuario() {
		try {
			Loja loja = new Loja();
			
			UsuarioNoob usuario1 = new UsuarioNoob("Noob1", "N1");
			UsuarioNoob usuario2 = new UsuarioNoob("Noob2", "N2");
			UsuarioNoob usuario3 = new UsuarioNoob("Noob3", "N3");
			UsuarioNoob usuario4 = new UsuarioNoob("Noob4", "N4"); // Igual ao usuario1
			
			UsuarioVeterano usuario5 = new UsuarioVeterano("Veterano1", "V1");
			UsuarioVeterano usuario6 = new UsuarioVeterano("Veterano2", "V2");
			UsuarioVeterano usuario7 = new UsuarioVeterano("Veterano3", "V3");

			loja.adicionaUsuario(usuario1);
			loja.adicionaUsuario(usuario2);
			loja.adicionaUsuario(usuario3);
			
			loja.adicionaUsuario(usuario5);
			loja.adicionaUsuario(usuario6);
			loja.adicionaUsuario(usuario7);
			
			boolean contem = loja.getUsuarios().contains(usuario1);
			assertTrue(contem);

			contem = loja.getUsuarios().contains(usuario2);
			assertTrue(contem);

			contem = loja.getUsuarios().contains(usuario3);
			assertTrue(contem);
			
			contem = loja.getUsuarios().contains(usuario4);
			assertFalse(contem);
			
			contem = loja.getUsuarios().contains(usuario5);
			assertTrue(contem);
			
			contem = loja.getUsuarios().contains(usuario6);
			assertTrue(contem);
			
			contem = loja.getUsuarios().contains(usuario7);
			assertTrue(contem);
			
			
			
		} catch (StringInvalidaException e) {
			fail("Nao deveria lancar Exceptions");
		}
	}

	@Test
	public void testAdicionaDinheiro() {
		//fail("Not yet implemented");
	}

	@Test
	public void testVendeJogo() {
		//fail("Not yet implemented");
	}

}
