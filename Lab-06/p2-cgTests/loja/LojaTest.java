package loja;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.StringInvalidaException;
import jogo.JogoLuta;
import jogo.JogoPlataforma;
import jogo.JogoRPG;
import usuario.UsuarioNoob;
import usuario.UsuarioVeterano;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */

public class LojaTest {

	
	
	@Test
	public void testAdicionaUsuario() {
		try {
			LojaController loja = new LojaController();
			
			UsuarioNoob usuario1 = new UsuarioNoob("Noob1", "N1");
			UsuarioNoob usuario2 = new UsuarioNoob("Noob2", "N2");
			UsuarioNoob usuario3 = new UsuarioNoob("Noob3", "N3");
			UsuarioNoob usuario4 = new UsuarioNoob("Noob4", "N4"); // nao sera inserido
			
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
			
			contem = loja.getUsuarios().contains(usuario5);
			assertTrue(contem);
			
			contem = loja.getUsuarios().contains(usuario6);
			assertTrue(contem);
			
			contem = loja.getUsuarios().contains(usuario7);
			assertTrue(contem);
			
			
			//testando usuario nao inserido
			contem = loja.getUsuarios().contains(usuario4);
			assertFalse(contem);
			
			//testanto tentativa de inserir usuario nulo
			loja.adicionaUsuario(null);
			
			contem = loja.getUsuarios().contains(null);
			assertFalse(contem);
			
			
			//loja.imprimeInfoUsuarios();
			
			
		} catch (StringInvalidaException e) {
			fail("Nao deveria lancar Exceptions");
		}
	}

	@Test
	public void testAdicionaDinheiro() {
		try {
			LojaController loja = new LojaController();
			
			UsuarioNoob usuario1 = new UsuarioNoob("Noob1", "N1");			
			UsuarioVeterano usuario5 = new UsuarioVeterano("Veterano1", "V1");

			loja.adicionaUsuario(usuario1);
			loja.adicionaUsuario(usuario5);
			
			loja.adicionaDinheiro("V1", 100);			
			loja.adicionaDinheiro("N1", 50);			

			double saldo = usuario5.getSaldo();
			double saldoEsperado = 100;
			assertEquals(saldoEsperado, saldo, 0.0);
			
			saldo = usuario1.getSaldo();
			saldoEsperado = 50;
			assertEquals(saldoEsperado, saldo, 0.0);
			
			//testando inserir saldo negativo
			loja.adicionaDinheiro("V1", -100);
			
			//verifica se o saldo foi modificado
			saldo = usuario5.getSaldo();
			saldoEsperado = 100;
			assertEquals(saldoEsperado, saldo, 0.0);
			
			
		} catch (Exception e) {
			fail("Nao deveria lancar Exceptions");
		}
	}

	@Test
	public void testVendeJogo() {
		try {
			LojaController loja = new LojaController();
			
			UsuarioNoob usuario1 = new UsuarioNoob("Noob1", "N1");			
			UsuarioVeterano usuario5 = new UsuarioVeterano("Veterano1", "V1");

			loja.adicionaUsuario(usuario1);
			loja.adicionaUsuario(usuario5);
			
			loja.adicionaDinheiro("V1", 200);			
			loja.adicionaDinheiro("N1", 500);			

			JogoLuta jogoLuta = new JogoLuta("Jogo de Luta", 100);
			JogoRPG jogoRPG = new JogoRPG("Jogo de RPG", 200);
			JogoPlataforma jogoPlataforma = new JogoPlataforma("Jogo de Plataforma", 1000);

			loja.vendeJogo(jogoLuta, "V1");
			loja.vendeJogo(jogoRPG, "N1");
			
			//testando se o saldo foi abatido
			double saldo = usuario5.getSaldo();
			double saldoEsperado = 200 - (100 * 0.8); //saldo inicial - preco do jogo com 20% de desconto
			assertEquals(saldoEsperado, saldo, 0.0);
			//testando se tem o jogo
			boolean temJogo = usuario5.temJogo(jogoLuta.getNome());
			assertTrue(temJogo);
			
			//testando se o saldo foi abatido
			saldo = usuario1.getSaldo();
			saldoEsperado = 500 - (200 * 0.9); //saldo inicial - preco do jogo com 20% de desconto
			assertEquals(saldoEsperado, saldo, 0.0);
			//testando se tem o jogo
			temJogo = usuario1.temJogo(jogoRPG.getNome());
			assertTrue(temJogo);
			
			
			
			
			//testando vender um jogo que o usuario nao tem saldo pra comprar
			loja.vendeJogo(jogoPlataforma, "V2");
			
			//verificando se o saldo foi abatido
			saldo = usuario5.getSaldo();
			saldoEsperado = 200 - (100 * 0.8); //saldo inicial - preco do jogo com 20% de desconto
			assertEquals(saldoEsperado, saldo, 0.0);
			
			//testando se tem o jogo
			temJogo = usuario5.temJogo(jogoPlataforma.getNome());
			assertFalse(temJogo);
			
			
		} catch (Exception e) {
			fail("Nao deveria lancar Exceptions");
		}
	}

	
	
}
