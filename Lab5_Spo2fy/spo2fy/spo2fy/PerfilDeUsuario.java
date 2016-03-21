package spo2fy;

import java.util.ArrayList;
import java.util.TreeSet;

public class PerfilDeUsuario {

	
	private Musiteca musiteca;
	String nome;
	
	public PerfilDeUsuario(String nome) throws Exception{
		
		if(nome == null || nome.trim().isEmpty()){
			throw new Exception("Nao eh permitida a criacao de um perfil com nome nulo ou vazio");
		}
		
		this.nome = nome;
		this.musiteca = new Musiteca();
	}
	
	
	
	public boolean adicionaPlaylist(String tituloPlaylist, String tituloAlbum, String artistaAlbum, int faixa) throws Exception {
		return musiteca.adicionaPlaylist(tituloPlaylist, tituloAlbum, artistaAlbum, faixa);
	}
	
	public boolean criaPlaylist(String titulo) throws Exception {
		return musiteca.criaPlaylist(titulo);
	}
	
	public boolean removePlaylist(String titulo) throws Exception {
		return musiteca.removePlaylist(titulo);
	}
	
	public Playlist buscaPlaylist(String titulo) throws Exception {
		return musiteca.buscaPlaylist(titulo);
	}
	
	public boolean temPlaylist(String titulo) throws Exception {
		return musiteca.temPlaylist(titulo);
	}
	
	public boolean adcionaMusicaPlaylist(String tituloPlaylist, Musica novaMusica) throws Exception {
		return musiteca.adcionaMusicaPlaylist(tituloPlaylist, novaMusica);
	}
	
	public boolean removeMusicaPlaylist(String titulo, Musica musica) throws Exception {
		return musiteca.removeMusicaPlaylist(titulo, musica);
	}
	
	public boolean temMusicaPlaylist(String titulo, Musica musica) throws Exception {
		return musiteca.temMusicaPlaylist(titulo, musica);
	}
	
	public int getDuracaoTotalPlaylist(String titulo) throws Exception {
		return musiteca.getDuracaoTotalPlaylist(titulo);
	}
	
	public boolean adicionaAlbum(Album novoAlbum) throws Exception {
		return musiteca.adicionaAlbum(novoAlbum);
	}
	
	public boolean favoritaAlbum(String titulo, String artista) throws Exception {
		return musiteca.favoritaAlbum(titulo, artista);
	}
	
	public boolean desfavoritaAlbum(String titulo, String artista) throws Exception {
		return musiteca.desfavoritaAlbum(titulo, artista);
	}
	
	public boolean ehFavorito(String titulo, String artista) throws Exception {
		return musiteca.ehFavorito(titulo, artista);
	}
	
	public boolean temAlbum(String titulo, String artista) throws Exception {
		return musiteca.temAlbum(titulo, artista);
	}
	
	public boolean temAlbum(Album album) throws Exception {
		return musiteca.temAlbum(album);
	}
	
	public Album buscaAlbum(String titulo, String artista) throws Exception {
		return musiteca.buscaAlbum(titulo, artista);
	}
	
	public boolean removeAlbum(String titulo, String artista) throws Exception {
		return musiteca.removeAlbum(titulo, artista);
	}
	
	public ArrayList<Album> buscaAlbunsPorTitulo(String titulo) throws Exception {
		return musiteca.buscaAlbunsPorTitulo(titulo);
	}
	
	public ArrayList<Album> buscaAlbunsPorArtista(String artista) throws Exception {
		return musiteca.buscaAlbunsPorArtista(artista);
	}
	
	public ArrayList<Album> buscaAlbunsPorAno(int ano) throws Exception {
		return musiteca.buscaAlbunsPorAno(ano);
	}
	
	public ArrayList<Album> getAlbunsFavoritos() {
		return musiteca.getAlbunsFavoritos();
	}
	
	public boolean adicionaFaixa(String tituloAlbum, String artistaAlbum, Musica novaMusica) throws Exception {
		return musiteca.adicionaFaixa(tituloAlbum, artistaAlbum, novaMusica);
	}
	
	public boolean removeFaixa(String tituloAlbum, String artistaAlbum, String tituloFaixa) throws Exception {
		return musiteca.removeFaixa(tituloAlbum, artistaAlbum, tituloFaixa);
	}
	
	public boolean temFaixa(String tituloAlbum, String artistaAlbum, Musica musica) throws Exception {
		return musiteca.temFaixa(tituloAlbum, artistaAlbum, musica);
	}
	
	public boolean temFaixa(String tituloAlbum, String artistaAlbum, String tituloFaixa) throws Exception {
		return musiteca.temFaixa(tituloAlbum, artistaAlbum, tituloFaixa);
	}
	
	public Musica getFaixa(String tituloAlbum, String artistaAlbum, int indice) throws Exception {
		return musiteca.getFaixa(tituloAlbum, artistaAlbum, indice);
	}
	
	
}
