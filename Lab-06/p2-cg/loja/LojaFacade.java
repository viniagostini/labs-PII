package loja;

import exceptions.DadosInvalidosException;
import exceptions.LogicaDeNegociosExecption;
import exceptions.StringInvalidaException;
import exceptions.ValorNumericoInvalidoException;

import jogo.TipoDeJogo;
import usuario.TipoDeUsuario;

public class LojaFacade {

	
	private LojaController lojaController;
	
	public LojaFacade(){
		this.lojaController = new LojaController();
	}

	
	public boolean cadastrarUsuario(String nomeUsuario, String loginUsuario, TipoDeUsuario tipoUsuario) {
		
		try{

			return lojaController.cadastrarUsuario(nomeUsuario, loginUsuario);
			
		}catch(DadosInvalidosException die){
			
			System.out.println(die.getMessage());
			
		}catch(LogicaDeNegociosExecption lne){
			
			System.out.println(lne.getMessage());
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		
			return false;
	}

	
	public void adicionaDinheiro(String loginUsuario, double qtdDinheiro) {
		
		try{
			
			lojaController.adicionaDinheiro(loginUsuario, qtdDinheiro);
				
		}catch(LogicaDeNegociosExecption lne){
			
			System.out.println(lne.getMessage());
			
		}catch(ValorNumericoInvalidoException vne){
			
			System.out.println(vne.getMessage());
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		
	}

	
	public void vendeJogo(String nomeJogo, double precoJogo, TipoDeJogo tipoJogo, String loginUsuario) {
		
		try{
			
			lojaController.vendeJogo(nomeJogo, precoJogo, tipoJogo, loginUsuario);
				
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

	
	public void imprimeInfoUsuarios() {
		
		System.out.println(lojaController);
		
	}
	
	
}
