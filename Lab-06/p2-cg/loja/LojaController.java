package loja;

import java.util.HashSet;

import jogo.Jogo;
import jogo.JogoLuta;
import jogo.JogoPlataforma;
import jogo.JogoRPG;
import jogo.TipoDeJogo;
import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.P2CGException;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;
import factory.JogoFactory;
import factory.UsuarioFactory;
import usuario.TipoDeUsuario;
import usuario.Usuario;
import usuario.UsuarioVeterano;

/* 115110107 - Vinicius Alencar Agostini: LAB 6 - Turma 3 */

/**
 * Classe "facade"/Loja responsavel por gerenciar uma lista de usuarios
 * e as operacoes de venda de jogos, adicao de novos usuarios,
 * adicao de saldo dos usuarios e impressao das informacoes de usuario.
 * 
 * @author Vinicius A. Agostini
 */


public class LojaController {

	public static final int X2P_MIN_UPGRADE = 1000;
	
	
	private HashSet<Usuario> usuarios;
	private JogoFactory fabricaDeJogos;
	private UsuarioFactory fabricaDeUsuarios;
	
	
	
	/**
	 * Contrutor sem parametros que inicializa
	 * um HashSet de Usuarios
	 * @see Usuario
	 */
	public LojaController() {
		
		this.usuarios = new HashSet<Usuario>();
		this.fabricaDeJogos = new JogoFactory();
		this.fabricaDeUsuarios = new UsuarioFactory();
	}
	
	
	
	/**
	 * Metodo responsavel por criar um usuario noob e adiciona-lo a lista de usuarios.
	 * 
	 * @param Usuario - Usuarios a ser adicionado
	 * 
	 * @return boolean - o usuario foi ou nao adicionado
	 * @throws DadosInvalidosException 
	 */
	public boolean cadastrarUsuario(String nomeUsuario, String loginUsuario) throws P2CGException{
		
			this.validaUsuario(nomeUsuario, loginUsuario);
			
			Usuario novoUsuario = this.fabricaDeUsuarios.criaUsuario(nomeUsuario, loginUsuario, TipoDeUsuario.NOOB);
			
			return this.getUsuarios().add(novoUsuario);
	}
	
	
	/**
	 * Metodo responsavel por adicionar dinheiro ao saldo
	 * do usuario com o login passado como parametro.
	 * 
	 * @param String - login do usuario que vai receber o dinheiro
	 * @param double - qtd de dinheiro que o usuario vai receber
	 * @throws LogicaDeNegociosExecption 
	 * @throws StringInvalidaException 
	 * @throws ValorNumericoInvalidoException 
	 */
	public void adicionaDinheiro(String loginUsuario, double qtdDinheiro) throws StringInvalidaException, LogicaDeNegociosExecption, ValorNumericoInvalidoException{
		
			
		Usuario usuarioAtual = this.buscaUsuario(loginUsuario);	
		usuarioAtual.incrementaSaldo(qtdDinheiro);
		
	}
	
	
	
	/**
	 * Metodo privado que recebe a copia do jogo que sera vendido e vende a um usuario
	 * especificado por seu login.
	 * 
	 * @param Jogo - jogo a ser vendido
	 * 
	 * @param String - login do usuario
	 * @throws P2CGException 
	 */
	private void realizaVenda(Jogo jogoVendido, String loginUsuario) throws P2CGException{
		
		Usuario usuarioEncontrado = this.buscaUsuario(loginUsuario);
			
		usuarioEncontrado.realizaCompra(jogoVendido);
	}

	
	/**
	 * Metodo que recebe as informacoes de um jogo a ser vendido, as
	 * usa para criar um jogo e o vende ao usuario.
	 * 
	 * @param Stirng - nome do jogo
	 * @param double - preco do jogo
	 * @param TipoDeJogo - tipo do jogo
	 * @param String - logindo usuario que ira comprar o jogo
	 * @throws P2CGException 
	 */
	public void vendeJogo(String nomeJogo, double precoJogo, TipoDeJogo tipoJogo, String loginUsuario) throws P2CGException{
		
		
		Jogo jogoVendido = this.fabricaDeJogos.criaJogo(nomeJogo, precoJogo, tipoJogo);
		
		this.realizaVenda(jogoVendido, loginUsuario);
		
	}
	
	
	public String toString(){
		
		String info = "";
		
		info += "== Central P2-CG ==";
		
		for(Usuario usuarioAtual : this.getUsuarios()){
			
			info += usuarioAtual.toString();
			
		}
		
		return info;
		
	}
	
	
	/**
	 * Metodo que busca Usuario na lista de usuarios pelo 
	 * login do mesmo
	 * 
	 * @param String - login do usuario a ser buscado
	 * 
	 * @return Usuario - o usuario encontrado com o login especificado
	 * 
	 * @throws LogicaDeNegociosExecption - caso o usuario nao seja encontrado
	 */
	private Usuario buscaUsuario(String loginUsuario)throws LogicaDeNegociosExecption , StringInvalidaException{
		
		this.validaLogin(loginUsuario);
		
		for(Usuario usuarioAtual : this.getUsuarios()){
			
			String loginAtual = usuarioAtual.getLogin();
			
			boolean loginsIguais = loginAtual.equals(loginUsuario);
			
			if(loginsIguais){
				return usuarioAtual;
			}
			
		}
		
		throw new LogicaDeNegociosExecption("Nao existe usuario cadastrado com esse login.");
	}
	

	
	// metodos de validacao
	
	private void validaUsuario(String nomeUsuario, String loginUsuario)throws DadosInvalidosException{
		if(nomeUsuario == null || nomeUsuario.trim().isEmpty()){
			throw new DadosInvalidosException("Usuarios com nome vazio ou nulo nao sao permitidos.");
		}
		
		if(loginUsuario == null || loginUsuario.trim().isEmpty()){
			throw new DadosInvalidosException("Usuarios com login vazio ou nulo nao sao permitidos.");
		}
	}

	
	private void validaLogin(String loginUsuario) throws StringInvalidaException {
		
		if(loginUsuario == null || loginUsuario.trim().isEmpty()){
			throw new StringInvalidaException("Nao eh permitido login vazio ou nulo");
		}
		
		
	}
	
	
	//getters e setters
	public HashSet<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(HashSet<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	

}
