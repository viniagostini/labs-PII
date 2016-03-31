package usuario;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;
import jogo.JogoPlataforma;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */

public class UsuarioVeteranoTest {

public static final String NAO_DEVERIA_LANCAR_EXCEPTION = "Nao deveria ter lancado exception";
	
	
	
	@Test
	public void testUsuarioVeterano() {
		//teste do construtor
		
		//verifica o estado inicial do objeto
		try{
			UsuarioVeterano vinicius = new UsuarioVeterano("Vinicius", "vinicius.agostini");
			
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
	
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		//verifica a insercao de nome vazio
		try{
			@SuppressWarnings("unused")
			UsuarioVeterano vinicius = new UsuarioVeterano("", "vinicius.agostini");
			
			fail("Deveria ter lancar uma StringInvalidaException.");
			
		}catch(StringInvalidaException sie){
			
			String mensagemRecebida = sie.getMessage();
			String mensageEsperada = "Nao eh permitida a criacao de usuarios com nome vazio ou nulo";
			
			assertEquals(mensageEsperada, mensagemRecebida);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui.");
		}
		
		//verfica a insercao de login vazio
		try{
			@SuppressWarnings("unused")
			UsuarioVeterano vinicius = new UsuarioVeterano("Vinicius", "");
			
			fail("Deveria ter lancar uma StringInvalidaException.");
			
		}catch(StringInvalidaException sie){
			
			String mensagemRecebida = sie.getMessage();
			String mensageEsperada = "Nao eh permitida a criacao de usuarios com login vazio ou nulo";
			
			assertEquals(mensageEsperada, mensagemRecebida);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui.");
		}
	}
	
	
	@Test
	public void testRealizaCompra() {
		
		try{
			
			UsuarioVeterano vinicius = new UsuarioVeterano("Vinicius", "vinicius.agostini");
			JogoPlataforma mario = new JogoPlataforma("Mario", 100);
			
			vinicius.incrementaSaldo(80);
			
			//lembrando que o jogo recebe 20% de desconto
			vinicius.realizaCompra(mario);
			
			//verifica se o saldo foi abatido corretamente
			double novoSaldo = vinicius.getSaldo();
			assertEquals(0.0, novoSaldo, 0.0);
			
			//verifica se o jogo foi adicionado aos jogos do usuario
			boolean contem = vinicius.getJogos().contains(mario);
			assertTrue(contem);
			
			//verica os x2p gerados pela compra
			int x2p = vinicius.getX2p();
			assertEquals(1500, x2p);
			
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
		
		//teste adicionando um jogo nulo
		try{
			
			UsuarioVeterano vinicius = new UsuarioVeterano("Vinicius", "vinicius.agostini");
			
			vinicius.incrementaSaldo(100);
			
			vinicius.realizaCompra(null);
			
			fail("deveria ter lancar uma DadosInvalidosException");
			
		}catch(DadosInvalidosException die){
		
			String mensagemRecebida = die.getMessage();
			String mensagemEsperada = "Nao sao permitido jogos nulos.";
			
			assertEquals(mensagemEsperada, mensagemRecebida);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui.");
		}


		//teste comprando duas vezes o mesmo jogo
		try{
			
			UsuarioVeterano vinicius = new UsuarioVeterano("Vinicius", "vinicius.agostini");
			JogoPlataforma mario = new JogoPlataforma("Mario", 100);
			JogoPlataforma outroMario = new JogoPlataforma("Mario", 100);
			
			vinicius.incrementaSaldo(160);
			
			//lembrando que o jogo recebe 20% de desconto
			vinicius.realizaCompra(mario);
			
			//comprando o mesmo jogo
			vinicius.realizaCompra(outroMario);
			
			fail("Deveria ter lancado uma LogicaDeNegociosExecption.");
			
		}catch(LogicaDeNegociosExecption lne){
			
			String mensagemRecebida = lne.getMessage();
			String mensagemEsperada = "Nao eh possivel comprar duas vezes o mesmo jogo";
			
			assertEquals(mensagemEsperada, mensagemRecebida);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui");
		}
		
		//teste tentando comprar jogo com saldo insuficiente
		try{
			
			UsuarioVeterano vinicius = new UsuarioVeterano("Vinicius", "vinicius.agostini");
			JogoPlataforma mario = new JogoPlataforma("Mario", 100);
			
			vinicius.incrementaSaldo(79.90);
			
			//lembrando que o jogo recebe 20% de desconto
			vinicius.realizaCompra(mario);
			
			
			fail("Deveria ter lancado uma LogicaDeNegociosExecption.");
			
		}catch(LogicaDeNegociosExecption lne){
			
			String mensagemRecebida = lne.getMessage();
			String mensagemEsperada = "Saldo insuficente para comprar esse jogo";
			
			assertEquals(mensagemEsperada, mensagemRecebida);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION + "aqui");
		}
		
		
	}


	@Test
	public void testRegistraJogada() {
		
		
		try{
			
			UsuarioVeterano vinicius = new UsuarioVeterano("Vinicius", "vinicius.agostini");
			JogoPlataforma mario = new JogoPlataforma("Mario", 100);
			
			vinicius.incrementaSaldo(80);
			
			//lembrando que o jogo recebe 20% de desconto
			vinicius.realizaCompra(mario);
			
			vinicius.registraJogada("Mario", 1000, true);
			
			int x2pObtido = vinicius.getX2p() - 1500; //1000 eh o x2p ganho na compra do jogo
			
			int x2pEsperado = 20;
			
			assertEquals(x2pEsperado, x2pObtido);
			
		}catch(Exception e){
			fail(NAO_DEVERIA_LANCAR_EXCEPTION);
		}
			
	}

}
