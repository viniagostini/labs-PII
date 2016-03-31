package loja;

import java.util.HashSet;

import jogo.Jogo;
import jogo.JogoLuta;
import jogo.JogoPlataforma;
import jogo.JogoRPG;
import jogo.TipoDeJogo;
import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;
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


public class Loja {

	public static final int X2P_MIN_UPGRADE = 1000;
	
	
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
			
			// Jogo copiaJogo = this.copiaJogo(jogoVendido);
			// implementar a interface Clonable em jogo?? http://pt.stackoverflow.com/questions/60813/como-fazer-c%C3%B3pia-de-objetos-em-java
			// usar factory???? http://www.devmedia.com.br/padrao-de-projeto-factory-method-em-java/26348
			// assumir que eu recebo um jogo ja copiado??? (por enquanto fica assim)
			
			Usuario usuarioEncontrado = this.buscaUsuario(loginUsuario);
			
			usuarioEncontrado.realizaCompra(jogoVendido);
			
		}catch(DadosInvalidosException die){
			
			System.out.println(die.getMessage());
			
		}catch(LogicaDeNegociosExecption lne){
			
			System.out.println(lne.getMessage());
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		
	}

	
	/**
	 * Metodo que recebe as informacoes de um jogo a ser vendido, as
	 * usa para criar um jogo e o vende ao usuario.
	 * 
	 * @param Stirng - nome do jogo
	 * @param double - preco do jogo
	 * @param TipoDeJogo - tipo do jogo
	 * @param String - logindo usuario que ira comprar o jogo
	 */
	public void vendeJogo(String nomeJogo, double precoJogo, TipoDeJogo tipoJogo, String loginUsuario){
		
		/*
		 * Nesse caso esta sendo vendido sempre um jogo sem jogabilidades ao usuario (errado ao meu ponto de vista).
		 * 
		 * Alem disso, a classe Loja ira conhecer os tipos especificos de Jogos, o que tira toda a graca do tipo 
		 * generico polimorfico Jogo.
		 * 
		 * Talvez haja tambem um erro de creator, pois nao eh responsabilidade da loja criar jogos.
		 */
		
		try{
			
			Jogo jogoVendido;
			
			switch (tipoJogo) {
			case LUTA:
				
				jogoVendido = new JogoLuta(nomeJogo, precoJogo);
				
				break;
			case RPG:
				
				jogoVendido = new JogoRPG(nomeJogo, precoJogo);
				
				break;
			case PLATAFORMA:
				
				jogoVendido = new JogoPlataforma(nomeJogo, precoJogo);
				
				break;
			default:
				throw new LogicaDeNegociosExecption("Tipo de jogo invalido");
			}
		
			this.vendeJogo(jogoVendido, loginUsuario);
			
		}catch(StringInvalidaException sie){
			
			System.out.println(sie.getMessage());
			
		}catch(ValorNumericoInvalidoException vne){
			
			System.out.println(vne.getMessage());
			
		}catch(LogicaDeNegociosExecption lne){
			
			System.out.println(lne.getMessage());
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
	}
	
	
	/**
	 * Metodo que imprime as informacoes de todos os usuarios
	 * cadastrados.
	 */
	public void imprimeInfoUsuarios(){
		
		System.out.println("== Central P2-CG ==");
		
		for(Usuario usuarioAtual : this.getUsuarios()){
			
			System.out.println(usuarioAtual);
			
		}
		
	}
	
	
	/**
	 * Metodo que faz o upgrade de um usuario nao-veterano a veterano
	 * caso o mesmo possua a quantidade minima de x2p para ser promovido
	 * 
	 * @param String - login do usuario a ser promovido
	 */
	public void upgrade(String loginUsuario){
		
		try{
			
			Usuario usuarioEncontrado = this.buscaUsuario(loginUsuario);
			
			int x2p = usuarioEncontrado.getX2p();
			
			if(x2p < X2P_MIN_UPGRADE){
				throw new LogicaDeNegociosExecption("x2p insuficiente para o upgrade");
			}
			
			if(usuarioEncontrado instanceof UsuarioVeterano){
				throw new LogicaDeNegociosExecption("Impossivel promover um usuario que ja eh veterano.");
			}
			
			/*
			 * Esse passo me soa muito estranho pois a Loja precisa conhecer
			 * tudo que ha em usuario, ou seja, nao tem muita vantagem o usuario
			 * estar encapsulado.
			 */
			
			// gambiarra{
			
			String nome = usuarioEncontrado.getNome();
			String login = usuarioEncontrado.getLogin();
			HashSet<Jogo> jogos = usuarioEncontrado.getJogos();
			double saldo = usuarioEncontrado.getSaldo();
			
			
			Usuario novoVeterano = new UsuarioVeterano(nome, login);
			
			novoVeterano.setJogos(jogos);
			novoVeterano.setSaldo(saldo);
			novoVeterano.setX2p(x2p);
			
			// }
			
			//remove o usuario que era noob da lista
			this.getUsuarios().remove(usuarioEncontrado);
			
			this.getUsuarios().add(novoVeterano);
			
		
		}catch(LogicaDeNegociosExecption lne){
			
			System.out.println(lne.getMessage());
			
		}catch(StringInvalidaException sie){
			
			System.out.println(sie.getMessage());
			
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
