package spo2fy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class GerenciadorDeAlbuns {

	/*
	 * implementar esquema para conseguir uma chave unica
	 * 
	 * professor sugeriu UUID
	 * 
	 * 
	 * Atualmente chavesAlbunsFavoritos nao estao em chevaesAlbunsComuns, pensar 
	 * se deveria ser assim...
	 * 
	 * */
	
	private HashSet<String> chavesAlbunsComuns;
	private HashSet<String> chavesAlbunsFavoritos;
	private HashMap< String, Album > albuns;
	
	public GerenciadorDeAlbuns(){
		this.chavesAlbunsComuns = new HashSet<String>();
		this.chavesAlbunsFavoritos = new HashSet<String>();
		this.albuns = new HashMap< String, Album >();
		
	}
	
	
	public boolean adicionaAlbum(Album novoAlbum)throws Exception{
		
		if(temAlbum(novoAlbum)){
			return false;
		}
		
		String chave = novoAlbum.getTitulo();
		this.getChavesAlbunsComuns().add(chave);
		
		this.getAlbuns().put(chave, novoAlbum);
		
		return true;
	}

	public boolean favoritaAlbum(String tituloAlbum)throws Exception{
		
		if(temAlbum(tituloAlbum)){

			this.getChavesAlbunsComuns().remove(tituloAlbum);
			this.getChavesAlbunsFavoritos().add(tituloAlbum);
			
			return true;
		}else{
			return false;
		}
	}
	
	public boolean desfavoritaAlbum(String tituloAlbum)throws Exception{
		
		if(temAlbum(tituloAlbum)){

			this.getChavesAlbunsComuns().add(tituloAlbum);
			this.getChavesAlbunsFavoritos().remove(tituloAlbum);
			
			return true;
		}else{
			return false;
		}
	}
	
	public boolean temAlbum(String titulo)throws Exception{
		
		if(titulo == null || titulo.equals("")){
			throw new Exception("Nao eh possivel trabalhar com valores vazios ou null");
		}
		
		if(this.getAlbuns().containsKey(titulo)){
			return true;
		}else{
			return false;
		}

	}

	public boolean temAlbum(Album album)throws Exception{
		
		if(album == null){
			throw new Exception("Nao eh possivel trabalhar com valores vazios ou null");
		}
		
		if(this.getAlbuns().containsValue(album)){
			return true;
		}else{
			return false;
		}

	}
	
	public Album pesquisaAlbum(String titulo)throws Exception{
		
		if(temAlbum(titulo)){
			return this.getAlbuns().get(titulo);
		}
		
		return null;
	}
	
	public boolean removeAlbum(String titulo)throws Exception{
		
		if(temAlbum(titulo)){
			
			//remove do mapa
			this.getAlbuns().remove(titulo);
			
			//remove do set de chaves, porem, ele pode estas dentre as chaves comuns ou favoritas
			if(this.getChavesAlbunsComuns().contains(titulo)){
				this.getChavesAlbunsComuns().remove(titulo);
			}else{
				this.getChavesAlbunsFavoritos().remove(titulo);
			}	
		}
		return false;
	}
	
	
	public String toString(){
		String quebraDeLinha = System.lineSeparator();
		
		String retorno = "";
		
		retorno += "Albuns: ";
		
		retorno += quebraDeLinha + quebraDeLinha;
		
		retorno += "   Albuns favoritos: " + quebraDeLinha;
		
		for(String chaveAtual : this.getChavesAlbunsFavoritos()){
			Album albumAtual = this.getAlbuns().get(chaveAtual);
			retorno += "      " + albumAtual.getTitulo() + quebraDeLinha;
		}
		
		retorno += quebraDeLinha + quebraDeLinha;
		
		
		retorno += "   Albuns comuns: " + quebraDeLinha;
		
		for(String chaveAtual : this.getChavesAlbunsComuns()){
			Album albumAtual = this.getAlbuns().get(chaveAtual);
			retorno += "      " + albumAtual.getTitulo() + quebraDeLinha;
		}
		
		
		
		return retorno;
	}
	
	
	// getters e setters
	public HashSet<String> getChavesAlbunsComuns() {
		return chavesAlbunsComuns;
	}

	public void setChavesAlbunsComuns(HashSet<String> chavesAlbunsComuns) {
		this.chavesAlbunsComuns = chavesAlbunsComuns;
	}

	public HashSet<String> getChavesAlbunsFavoritos() {
		return chavesAlbunsFavoritos;
	}

	public void setChavesAlbunsFavoritos(HashSet<String> chavesAlbunsFavoritos) {
		this.chavesAlbunsFavoritos = chavesAlbunsFavoritos;
	}

	public HashMap<String, Album> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(HashMap<String, Album> albuns) {
		this.albuns = albuns;
	}
	
}
