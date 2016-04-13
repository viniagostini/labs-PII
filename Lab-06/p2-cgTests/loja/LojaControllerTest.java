package loja;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.P2CGException;
import exceptions.StringInvalidaException;
import jogo.TipoDeJogo;
import usuario.Usuario;

public class LojaControllerTest {


	@Test
	public void testCadastrarUsuario() {
		
		LojaController loja = new LojaController();
		
		String nomeUsuario = "Vinicius";
		String loginUsuario = "viniagostini";
		
		try{
			
			loja.cadastrarUsuario(nomeUsuario, loginUsuario);
			
			Usuario usuarioEncontrado = loja.buscaUsuario(loginUsuario);
			
			assertEquals(nomeUsuario, usuarioEncontrado.getNome());
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions.");
		}
		
		try{
			
			loja.cadastrarUsuario(nomeUsuario, "  ");
			fail("Deveria ter lancar StringInvalidaException.");
		}catch(P2CGException p2e){
			
			//ok
			
		}catch (Exception e) {
			fail("Nao deveria lancar Exception");
		}
		
	}

	@Test
	public void testAdicionaDinheiro() {
		
		LojaController loja = new LojaController();
		
		String nomeUsuario = "Vinicius";
		String loginUsuario = "viniagostini";
		
		try{
			
			loja.cadastrarUsuario(nomeUsuario, loginUsuario);
			
			loja.adicionaDinheiro(loginUsuario, 1000);
			
			
			Usuario usuarioEncontrado = loja.buscaUsuario(loginUsuario);
			
			assertEquals(1000, usuarioEncontrado.getSaldo(), 0);
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions.");
		}
	}

	@Test
	public void testVendeJogo() {
		
		LojaController loja = new LojaController();
		
		String nomeUsuario = "Vinicius";
		String loginUsuario = "viniagostini";
		
		try{
			
			loja.cadastrarUsuario(nomeUsuario, loginUsuario);
			
			loja.adicionaDinheiro(loginUsuario, 1000);
			
			loja.vendeJogo("Rocket League", 23, TipoDeJogo.PLATAFORMA, loginUsuario);
			

			Usuario usuarioEncontrado = loja.buscaUsuario(loginUsuario);
			
			assertTrue( usuarioEncontrado.temJogo("Rocket League") );
			
		}catch(Exception e){
			fail("Nao deveria lancar exceptions.");
		}
	}

}
