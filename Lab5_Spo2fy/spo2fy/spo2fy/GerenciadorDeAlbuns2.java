package spo2fy;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import comparators.AnoAlbumComparator;


public class GerenciadorDeAlbuns2 {

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
	
	private TreeSet<Album> albunsFavoritos;
	private TreeSet<Album > albuns;
	
	//construtor
	public GerenciadorDeAlbuns2(){
		this.albunsFavoritos = new TreeSet<Album>( new AnoAlbumComparator() );
		this.albuns = new TreeSet< Album >( new AnoAlbumComparator() );
	}
	
	//funcinalidades
	public boolean adicionaAlbum(Album novoAlbum)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		valida(novoAlbum);
		
		return this.getAlbuns().add(novoAlbum);
	}

	public boolean favoritaAlbum(String titulo, String artista)throws Exception{

		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		Album novoFavorito = this.buscaAlbum(titulo, artista);
		
		return this.getAlbunsFavoritos().add(novoFavorito);	
	}
	
	public boolean desfavoritaAlbum(String titulo, String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		Album novoDesfavorito = this.buscaAlbum(titulo, artista);
		
		return this.getAlbunsFavoritos().remove(novoDesfavorito);
	}
	
	public boolean ehFavorito(String titulo, String artista) throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		Album possiveAlbumFavorito = this.buscaAlbum(titulo, artista);
		
		return this.getAlbunsFavoritos().contains(possiveAlbumFavorito);
	}
	
	public boolean temAlbum(String titulo, String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		for (Album albumAtual : this.getAlbuns()){
			
			String tituloAtual = albumAtual.getTitulo();
			String artistaAtual = albumAtual.getArtista();
			
			boolean titulosIguais = tituloAtual.equals(titulo);
			boolean artistasIguais = artistaAtual.equals(artista);
			
			if(titulosIguais && artistasIguais){
				return true;
			}
		}
		return false;
	}
	
	public boolean temAlbum(Album album)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(album);
		
		return this.getAlbuns().contains(album);
	}

	public Album buscaAlbum(String titulo, String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		if(temAlbum(titulo, artista)){
			
			for (Album albumAtual : this.getAlbuns()){
				
				String tituloAtual = albumAtual.getTitulo();
				String artistaAtual = albumAtual.getArtista();
				
				boolean titulosIguais = tituloAtual.equals(titulo);
				boolean artistasIguais = artistaAtual.equals(artista);
				
				if(titulosIguais && artistasIguais){
					return albumAtual;
				}	
			}
			
			throw new Exception("Album nao pertence ao Perfil especificado");
			
		}else{
			throw new Exception("Album nao pertence ao Perfil especificado");
		}
	}

	public boolean removeAlbum(String titulo, String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		this.valida(titulo, artista);
		
		Album albumRemovido = this.buscaAlbum(titulo, artista);
		
		return this.getAlbuns().remove(albumRemovido);
	}

	
	public ArrayList<Album> buscaAlbunsPorTitulo(String titulo)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		valida(titulo);
		
		ArrayList<Album> arrayAlbuns = new ArrayList<Album>();
		
		for (Album albumAtual : this.getAlbuns()){
			
			String tituloAtual = albumAtual.getTitulo();
			
			boolean titulosIguais = tituloAtual.equals(titulo);
			
			if(titulosIguais){
				arrayAlbuns.add(albumAtual);
			}
			
		}
		
		return arrayAlbuns;
	}
	
	public ArrayList<Album> buscaAlbunsPorArtista(String artista)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		valida(artista);
		
		ArrayList<Album> arrayAlbuns = new ArrayList<Album>();
		
		for (Album albumAtual : this.getAlbuns()){
			
			String artistaAtual = albumAtual.getArtista();
			
			boolean artistasIguais = artistaAtual.equals(artista);
			
			if(artistasIguais){
				arrayAlbuns.add(albumAtual);
			}
			
		}
		
		return arrayAlbuns;
	}
	
	public ArrayList<Album> buscaAlbunsPorAno(int ano)throws Exception{
		
		//o valida vai lancar uma exception caso um desses parametros seja invalido
		valida(ano);
		
		ArrayList<Album> arrayAlbuns = new ArrayList<Album>();
		
		for (Album albumAtual : this.getAlbuns()){
			
			int anoAtual = albumAtual.getAno();
			
			boolean anosIguais = anoAtual == ano;
			
			if(anosIguais){
				arrayAlbuns.add(albumAtual);
			}
			
		}
		
		return arrayAlbuns;
	}

	
	public void ordenaAlbunsPorAno(){
		//implementar
	}
	
	public void ordenaAlbunsPorTitulo(){
		//implementar
	}
	
	public void ordenaAlbunsPorArtista(){
		//implementar
	}
	
	//funcionalidades de Album (delegacao)
	public boolean adicionaFaixa(String tituloAlbum, String artistaAlbum, Musica novaMusica) throws Exception{
		
		Album albumAtual = this.buscaAlbum(tituloAlbum, artistaAlbum);
		
		return albumAtual.adicionaFaixa(novaMusica);
	}
	
	public boolean removeFaixa(String tituloAlbum, String artistaAlbum, String tituloFaixa)throws Exception{
		
		Album albumAtual = this.buscaAlbum(tituloAlbum, artistaAlbum);
		
		return albumAtual.removeFaixa(tituloFaixa);
	}
	
	public boolean removeFaixa(String tituloAlbum, String artistaAlbum, int faixa)throws Exception{
		
		Album albumAtual = this.buscaAlbum(tituloAlbum, artistaAlbum);
		
		return albumAtual.removeFaixa(faixa);
	}
	
	public boolean temFaixa(String tituloAlbum, String artistaAlbum, Musica musica)throws Exception{
		
		Album albumAtual = this.buscaAlbum(tituloAlbum, artistaAlbum);
		
		return albumAtual.temFaixa(musica);	
	}
	
	public boolean temFaixa(String tituloAlbum, String artistaAlbum, String tituloFaixa)throws Exception{
		
		Album albumAtual = this.buscaAlbum(tituloAlbum, artistaAlbum);
		
		return albumAtual.temFaixa(tituloFaixa);			
	}
	
	public Musica getFaixa(String tituloAlbum, String artistaAlbum, int indice)throws Exception{
		
		Album albumAtual = this.buscaAlbum(tituloAlbum, artistaAlbum);
		
		return albumAtual.getFaixa(indice);
	}
	
	public Musica getFaixa(String tituloAlbum, String artistaAlbum, String titulo)throws Exception{
		
		Album albumAtual = this.buscaAlbum(tituloAlbum, artistaAlbum);
		
		return albumAtual.getFaixa(titulo);
	}
	
	
	// metodos de funcionamento interno
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

	
	
	//getters e setters
	public TreeSet<Album> getAlbuns() {
		return albuns;
	}

	
	public void setAlbuns(TreeSet<Album> albuns) {
		this.albuns = albuns;
	}
	
	public TreeSet<Album> getAlbunsFavoritos(){
		return this.albunsFavoritos;
	}

	
	public void setAlbunsFavoritos(TreeSet<Album> albunsFavoritos) {
		this.albunsFavoritos = albunsFavoritos;
	}

	
}