package loja;

import java.util.HashSet;

import exceptions.StringInvalidaException;
import usuario.Usuario;
import usuario.UsuarioNoob;

/**
 * Classe "façade" responsavel por gerenciar uma lista de usuarios
 * e as operacoes de venda de jogos, adicao de novos usuarios,
 * adicao de saldo dos usuarios e impressao das informacoes de usuario.
 * 
 * @author Vinicius A. Agostini
 */


public class Loja {


	private HashSet<Usuario> usuarios;	
	
	public Loja() {
		
		this.usuarios = new HashSet<Usuario>();
	}
	
	
	public boolean adicionaUsuario(Usuario usuario){
		
		
		
		return this.getUsuarios().add(usuario);
		
	}

	
	//getters e setters
	public HashSet<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(HashSet<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	

}
