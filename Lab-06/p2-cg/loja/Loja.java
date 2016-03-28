package loja;

import java.util.HashSet;

import jogo.Jogo;
import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;
import usuario.Usuario;
import usuario.UsuarioNoob;

/**
 * Classe "facade" responsavel por gerenciar uma lista de usuarios
 * e as operacoes de venda de jogos, adicao de novos usuarios,
 * adicao de saldo dos usuarios e impressao das informacoes de usuario.
 * 
 * @author Vinicius A. Agostini
 */


public class Loja {


	private HashSet<Usuario> usuarios;	
	
	/**
	 * Contrutor sem parametros que inicializa
	 * um HashSet de Usuarios
	 * @see Usuario
	 */
	public Loja() {
		
		this.usuarios = new HashSet<Usuario>();
	}
	
	/**
	 * Metodo responsavel por adicionar Usuarios a lista de usuarios
	 * e tratar os possiveis erros.
	 * 
	 * @param Usuario - Usuarios a ser adicionado
	 * 
	 * @return boolean - o usuario foi ou nao adicionado
	 */
	public boolean adicionaUsuario(Usuario usuario){
		
		try{
			
			this.validaUsuario(usuario);
			
			return this.getUsuarios().add(usuario);
			
		}catch(DadosInvalidosException die){
			
			System.out.println(die.getMessage());
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		
		return false;
		
	}
	
	
	/**
	 * Metodo responsavel por adicionar dinheiro ao saldo
	 * do usuario com o login passado como parametro.
	 * 
	 * @param String - login do usuario que vai receber o dinheiro
	 * @param double - qtd de dinheiro que o usuario vai receber
	 */
	public void adicionaDinheiro(String loginUsuario, double qtdDinheiro){
		
		try{
			
			Usuario usuarioAtual = this.buscaUsuario(loginUsuario);
			
			usuarioAtual.incrementaSaldo(qtdDinheiro);
			
		}catch(LogicaDeNegociosExecption lne){
			
			System.out.println(lne.getMessage());
			
		}catch(ValorNumericoInvalidoException vne){
			
			System.out.println(vne.getMessage());
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
	}
	
	
	
	/**
	 * Metodo que recebe a copia de um jogo (sera?) e vende a um usuario
	 * especificado por seu login.
	 * 
	 * @param Jogo - jogo a ser vendido
	 * 
	 * @param String - login do usuario
	 */
	public void vendeJogo(Jogo jogoVendido, String loginUsuario){
		
		
		try{
			
			//Jogo copiaJogo = this.copiaJogo(jogoVendido);
			//implementar a interface Clonable em jogo?? http://pt.stackoverflow.com/questions/60813/como-fazer-c%C3%B3pia-de-objetos-em-java
			// usar factory???? http://www.devmedia.com.br/padrao-de-projeto-factory-method-em-java/26348
			
			
			Usuario usuarioEncontrado = this.buscaUsuario(loginUsuario);
			
			usuarioEncontrado.realizaCompra(jogoVendido);
			
		}catch(DadosInvalidosException die){
			
			System.out.println(die.getMessage());
			
		}catch(LogicaDeNegociosExecption lne){
			
			System.out.println(lne.getMessage());
			
		}catch(Exception e){
			
			e.getMessage();
			
		}
		
	}
	
	
	public void imprimeInfoUsuarios(){
		
		System.out.println("== Central P2-CG ==");
		
		for(Usuario usuarioAtual : this.getUsuarios()){
			
			System.out.println(usuarioAtual);
			
		}
		
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
	private Usuario buscaUsuario(String loginUsuario)throws LogicaDeNegociosExecption , DadosInvalidosException{
		
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
	

	

	private Jogo copiaJogo(Jogo jogo) throws DadosInvalidosException{
		
		validaJogo(jogo);
		
		//nao posso instanciar um novo jogo
		
		return null;
	}
	

	
	private void validaUsuario(Usuario usuario)throws DadosInvalidosException{
		if(usuario == null){
			throw new DadosInvalidosException("Usuarios nulos nao sao permitidos.");
		}
	}

	
	private void validaJogo(Jogo jogo)throws DadosInvalidosException{
		if(jogo == null){
			throw new DadosInvalidosException("Jogos nulos nao sao permitidos.");
		}
	}
	
	
	private void validaLogin(String loginUsuario) throws DadosInvalidosException {
		
		if(loginUsuario == null || loginUsuario.trim().isEmpty()){
			throw new DadosInvalidosException("Nao eh permitido login vazio ou nulo");
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
