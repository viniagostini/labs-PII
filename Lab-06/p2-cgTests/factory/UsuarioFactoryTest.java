package factory;

import static org.junit.Assert.*;

import org.junit.Test;

import usuario.TipoDeUsuario;
import usuario.Usuario;
import usuario.UsuarioNoob;

public class UsuarioFactoryTest {

	@Test
	public void testCriaUsuario() {
		
		try{
			
			UsuarioFactory usuarioFactory = new UsuarioFactory();
			
			Usuario vinicius = usuarioFactory.criaUsuario("Vinicius", "vinicius.agostini", TipoDeUsuario.NOOB);
			
			//verificando o estado inicial do objeto
			
			String nome = vinicius.getNome();
			String login = vinicius.getLogin();
			double saldo = vinicius.getSaldo();
			int x2p = vinicius.getX2p();
			boolean semJogos = vinicius.getJogos().isEmpty();
			
			assertEquals("Vinicius", nome);
			assertEquals("vinicius.agostini", login);
			assertEquals(0.0, saldo, 0.0);
			assertEquals(0, x2p);
			assertTrue(semJogos);
			
			
			//verificando o tipo criado
			
			@SuppressWarnings("unused")
			UsuarioNoob noob = (UsuarioNoob) vinicius;
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions.");
		}
		
		
	}

}
