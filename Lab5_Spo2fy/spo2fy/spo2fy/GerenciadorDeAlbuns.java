package spo2fy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeMap;

public class GerenciadorDeAlbuns {

	/*
	 * 
	 *   A escolha do TreeMap para armazenar os albuns se deu por conta
	 *da eficiencia [ O( n log(n) ) ] e simplicidade na hora de pesquisar, pelo mesmo
	 *ser iteravel e por manter os elementos ordenados (passo 5)
	 *  
	 *   Tomar como paramentro pra maioria das acoes o titulo e artista
	 *do album eh fundamental, pois é isso que torna cada album unico,
	 *de outra forma, as pesquisas seriam inconsistentes, por conta disso,
	 *o Professor Neto deixou que eu fizesse dessa forma
	 * 
	 * */
	
	private HashSet<String> chavesAlbunsFavoritos;
	private TreeMap< String, Album > albuns;
	
	//construtor
	public GerenciadorDeAlbuns(){
		this.chavesAlbunsFavoritos = new HashSet<String>();
		this.albuns = new TreeMap< String, Album >();
	}
	
	//funcinalidades
	public boolean adicionaAlbum(Album novoAlbum)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		valida(novoAlbum);
		
		// essa condicao cuida para que nao sejam inseridos albuns iguais
		if(temAlbum(novoAlbum)){
			return false;
		}
		
		String novoTitulo = novoAlbum.getTitulo();
		String novoArtista = novoAlbum.getArtista();
		
		String chave = this.geraChave(novoTitulo, novoArtista);
		
		this.getAlbuns().put(chave, novoAlbum);
		
		return true;
	}

	public boolean favoritaAlbum(String titulo, String artista)throws Exception{

		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		if(temAlbum(titulo, artista)){
			String chave = this.geraChave(titulo, artista);
			return this.getChavesAlbunsFavoritos().add(chave);
			
		}else{
			return false;
		}
	}
	
	public boolean desfavoritaAlbum(String titulo, String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		String chave = this.geraChave(titulo, artista);
		
		return this.getChavesAlbunsFavoritos().remove(chave);
	}
	
	public boolean ehFavorito(String titulo, String artista) throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		String chave = this.geraChave(titulo, artista);
		
		return this.getChavesAlbunsFavoritos().contains(chave);
	}
	
	public boolean temAlbum(String titulo, String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		String chave = this.geraChave(titulo, artista);
		
		return this.getAlbuns().containsKey(chave);
	}
	
	public boolean temAlbum(Album album)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(album);
		
		return this.getAlbuns().containsValue(album);

	}

	public Album buscaAlbum(String titulo, String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		if(temAlbum(titulo, artista)){
			
			String chave = this.geraChave(titulo, artista);
			
			return this.getAlbuns().get(chave);
		}else{
			throw new Exception("Album nao pertence ao Perfil especificado");
		}
	}
	
	public boolean removeAlbum(String titulo, String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		if(temAlbum(titulo, artista)){
			
			String chave = this.geraChave(titulo, artista);
			
			//remove do mapa
			this.getAlbuns().remove(chave);
			
			// caso ele seja favorito, ele eh removido tambem do Set de chave de favoritos
			if(this.getChavesAlbunsFavoritos().contains(chave)){
				
				this.getChavesAlbunsFavoritos().remove(chave);
			
			}	
			
			return true;
		}
		
		return false;
	}
	
	public ArrayList<Album> buscaAlbunsPorTitulo(String titulo)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		valida(titulo);
		
		ArrayList<Album> arrayAlbuns = new ArrayList<Album>();
		
		for( String chave : this.getAlbuns().keySet() ){
			Album albumAtual = this.getAlbuns().get(chave);
			
			String tituloAtual = albumAtual.getTitulo();
			
			if(tituloAtual.equals(titulo)){
				arrayAlbuns.add(albumAtual);
			}

		}
		
		return arrayAlbuns;
	}
	
	public ArrayList<Album> buscaAlbunsPorArtista(String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		valida(artista);
		
		ArrayList<Album> arrayAlbuns = new ArrayList<Album>();
		
		for( String chave : this.getAlbuns().keySet() ){
			Album albumAtual = this.getAlbuns().get(chave);
			
			String artistaAtual = albumAtual.getArtista();
			
			if(artistaAtual.equals(artista)){
				arrayAlbuns.add(albumAtual);
			}

		}
		
		return arrayAlbuns;
	}
	
	public ArrayList<Album> buscaAlbunsPorAno(int ano)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		valida(ano);
		
		ArrayList<Album> arrayAlbuns = new ArrayList<Album>();
		
		for( String chave : this.getAlbuns().keySet() ){
			Album albumAtual = this.getAlbuns().get(chave);
			
			int anoAtual = albumAtual.getAno();
			
			if(anoAtual == ano){
				arrayAlbuns.add(albumAtual);
			}

		}
		
		return arrayAlbuns;
	}
	
	public ArrayList<Album> getAlbunsFavoritos(){
		
		ArrayList<Album> albunsFavoritos = new ArrayList<Album>(); 
		
		for(String chave : this.getChavesAlbunsFavoritos()){
			Album albumAtual = this.getAlbuns().get(chave);
			albunsFavoritos.add(albumAtual);
		}
		
		return albunsFavoritos;
	}
	
	//funcionalidades de Album (delegacao)
	public boolean adicionaFaixa(String tituloAlbum, String artistaAlbum, Musica novaMusica) throws Exception{
		
		String chave = this.geraChave(tituloAlbum, artistaAlbum);
		
		if(temAlbum(tituloAlbum, artistaAlbum)){
			Album albumAtual = this.getAlbuns().get(chave);
			
			return albumAtual.adicionaFaixa(novaMusica);
		}else{
			throw new Exception("Album nao pertence ao Perfil especificado");
		}
		
	}
	
	public boolean removeFaixa(String tituloAlbum, String artistaAlbum, String tituloFaixa)throws Exception{
		
		String chave = this.geraChave(tituloAlbum, artistaAlbum);
		
		if(temAlbum(tituloAlbum, artistaAlbum)){
			Album albumAtual = this.getAlbuns().get(chave);
			
			return albumAtual.removeFaixa(tituloFaixa);
		}else{
			throw new Exception("Album nao pertence ao Perfil especificado");
		}
	}
	
	public boolean removeFaixa(String tituloAlbum, String artistaAlbum, int faixa)throws Exception{
		
		String chave = this.geraChave(tituloAlbum, artistaAlbum);
		
		if(temAlbum(tituloAlbum, artistaAlbum)){
			Album albumAtual = this.getAlbuns().get(chave);
			
			return albumAtual.removeFaixa(faixa);
		}else{
			throw new Exception("Album nao pertence ao Perfil especificado");
		}
	}
	
	public boolean temFaixa(String tituloAlbum, String artistaAlbum, Musica musica)throws Exception{
		
		String chave = this.geraChave(tituloAlbum, artistaAlbum);
		
		if(temAlbum(tituloAlbum, artistaAlbum)){
			Album albumAtual = this.getAlbuns().get(chave);
			
			return albumAtual.temFaixa(musica);
		}else{
			throw new Exception("Album nao pertence ao Perfil especificado");
		}
		
			
	}
	
	public boolean temFaixa(String tituloAlbum, String artistaAlbum, String tituloFaixa)throws Exception{
		
		String chave = this.geraChave(tituloAlbum, artistaAlbum);
		
		if(temAlbum(tituloAlbum, artistaAlbum)){
			Album albumAtual = this.getAlbuns().get(chave);
			
			return albumAtual.temFaixa(tituloFaixa);
		}else{
			throw new Exception("Album nao pertence ao Perfil especificado");
		}
			
	}
	
	public Musica getFaixa(String tituloAlbum, String artistaAlbum, int indice)throws Exception{
		
		String chave = this.geraChave(tituloAlbum, artistaAlbum);
		
		if(temAlbum(tituloAlbum, artistaAlbum)){
			Album albumAtual = this.getAlbuns().get(chave);
			
			return albumAtual.getFaixa(indice);
		}else{
			throw new Exception("Album nao pertence ao Perfil especificado");
		}

	}
	
	public Musica getFaixa(String tituloAlbum, String artistaAlbum, String titulo)throws Exception{
		
		String chave = this.geraChave(tituloAlbum, artistaAlbum);
		
		if(temAlbum(tituloAlbum, artistaAlbum)){
			Album albumAtual = this.getAlbuns().get(chave);
			
			return albumAtual.getFaixa(titulo);
		}else{
			throw new Exception("Album nao pertence ao Perfil especificado");
		}
	}
	
	//metodos triviais
	public String toString(){
		String quebraDeLinha = System.lineSeparator();
		
		String retorno = "Albuns: ";
		
		retorno += quebraDeLinha + quebraDeLinha;
		
		for( String chave : this.getAlbuns().keySet() ){
			Album albumAtual = this.getAlbuns().get(chave);
			
			retorno += "    ";
			
			if(this.getChavesAlbunsFavoritos().contains(chave)){
				retorno += "(Favorito)";
			}
			
			retorno += albumAtual.toString();
			
			retorno += quebraDeLinha;
		}
		
		return retorno;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albuns == null) ? 0 : albuns.hashCode());
		result = prime * result + ((chavesAlbunsFavoritos == null) ? 0 : chavesAlbunsFavoritos.hashCode());
		return result;
	}
	
	//equals gerado pelo eclipse, tendo em vista que ele basicamente nao tera utilidade nesse projeto
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GerenciadorDeAlbuns))
			return false;
		GerenciadorDeAlbuns other = (GerenciadorDeAlbuns) obj;
		if (albuns == null) {
			if (other.albuns != null)
				return false;
		} else if (!albuns.equals(other.albuns))
			return false;
		if (chavesAlbunsFavoritos == null) {
			if (other.chavesAlbunsFavoritos != null)
				return false;
		} else if (!chavesAlbunsFavoritos.equals(other.chavesAlbunsFavoritos))
			return false;
		return true;
	}
	
	
	// getters e setters
	public HashSet<String> getChavesAlbunsFavoritos() {
		return chavesAlbunsFavoritos;
	}

	public void setChavesAlbunsFavoritos(HashSet<String> chavesAlbunsFavoritos) {
		this.chavesAlbunsFavoritos = chavesAlbunsFavoritos;
	}

	public TreeMap<String, Album> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(TreeMap<String, Album> albuns) {
		this.albuns = albuns;
	}
	
	
	
	// metodos de funcionamento interno
	private String geraChave(String titulo, String artista)throws Exception{
		// a chave consiste em dois atributos que tornam um album unico: titulo e artista
		// ela sempre sera do formato "titulo-artista"
		
		this.valida(titulo, artista);
		
		return titulo+"-"+artista;
	}

	private void valida(String titulo, String artista)throws Exception{
		
		if(titulo == null || titulo.trim().isEmpty()){
			throw new Exception("Nao eh possivel trabalhar com valores de titulos vazios ou null.");
		}
		
		if(artista == null || artista.equals("")){
			throw new Exception("Nao eh possivel trabalhar com valores de artistas vazios ou null.");
		}
		
	}
	
	private void valida(String str)throws Exception{
		
		if(str == null || str.trim().isEmpty()){
			throw new Exception("Nao eh possivel trabalhar com valores vazios ou null.");
		}
	
	}
	
	private void valida(Album album)throws Exception{
		
		if(album == null){
			throw new Exception("Nao eh possivel trabalhar com valores de albuns vazios ou null.");
		}
	}
	
	private void valida(int ano)throws Exception{
		
		int dataAtual = Calendar.getInstance().get(Calendar.YEAR);
	
		if(ano < 1901){
			throw new Exception("Nao eh possivel trabalhar com valores de anos abaixo de 1901.");
		}else if(ano > dataAtual){
			throw new Exception("Impossivel trabalhar com albuns do futuro.");
		}
		
		
	}

	

}