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
	
	
	public void vendeJogo(Jogo jogoVendido, String loginUsuario){
		
		
		
		
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
	private Usuario buscaUsuario(String loginUsuario)throws LogicaDeNegociosExecption{
		
		for(Usuario usuarioAtual : this.getUsuarios()){
			
			String loginAtual = usuarioAtual.getLogin();
			
			boolean loginsIguais = loginAtual.equals(loginUsuario);
			
			if(loginsIguais){
				return usuarioAtual;
			}
			
		}
		
		throw new LogicaDeNegociosExecption("Nao existe usuario cadastrado com esse login.");
	}
	

	private void validaUsuario(Usuario usuario)throws DadosInvalidosException{
		if(usuario == null){
			throw new DadosInvalidosException("Usuarios nulos nao sao permitidos.");
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
