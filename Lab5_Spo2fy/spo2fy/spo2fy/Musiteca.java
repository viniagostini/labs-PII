package spo2fy;

import java.util.ArrayList;

public class Musiteca {
	
	/*
	 * Essa classe usa o conceito de composicao para
	 * encapsular os albuns e as playlists e o conceito de
	 * forwarding (delegacao) para implementas a maioria 
	 * dos metodos.
	 * 
	 * A grande responsabilidade da musiteca eh relacionar os
	 * albuns com as playlists em algumas situacoes em 
	 * que ambos sao afetados, como ao remover um album,
	 * onde todas as faixas desse album devem tambem ser
	 * removidas da playlist e em mais alguns casos especi-
	 * ficos, alem disso, delegar alguns metodos das classes
	 * que a compoe.
	 * 
	 * */
	
	private GerenciadorDeAlbuns gerenciadorDeAlbuns;
	private GerenciadorDePlaylists gerenciadorDePlaylists;
	
	//construtor
	public Musiteca(){
		
		gerenciadorDeAlbuns = new GerenciadorDeAlbuns();
		gerenciadorDePlaylists = new GerenciadorDePlaylists();
	}

	
	//metodos de GerenciadorDePlaylist
	public boolean adicionaPlaylist(String tituloPlaylist, String tituloAlbum, String artistaAlbum, int faixa)throws Exception{
		/*
		 * Vai retornar falso apenas se a playlist a qual o usuario tentar
		 * inserir a musica ja possuir a musica em questao
		 * */
		
		//se o album especificado nao existir sera lancada uma exception
		//se a faixa for invalida ou se nao existir, sera lancada uma exception
		Musica musicaAtual = this.getGerenciadorDeAlbuns().getFaixa(tituloAlbum, artistaAlbum, faixa);
		
		return this.getGerenciadorDePlaylists().adcionaMusica(tituloPlaylist, musicaAtual);
	}
	
	public boolean criaPlaylist(String titulo)throws Exception{
		return this.getGerenciadorDePlaylists().criaPlaylist(titulo);
	}

	public boolean removePlaylist(String titulo)throws Exception{
		return this.getGerenciadorDePlaylists().removePlaylist(titulo);
	}

	public Playlist buscaPlaylist(String titulo)throws Exception{
		return this.getGerenciadorDePlaylists().buscaPlaylist(titulo);
	}
	
	public boolean temPlaylist(String titulo)throws Exception{
		return this.getGerenciadorDePlaylists().temPlaylist(titulo);
	}

	
	//metodos de Playlist
	public boolean adcionaMusicaPlaylist(String tituloPlaylist, Musica novaMusica)throws Exception{
		return this.gerenciadorDePlaylists.adcionaMusica(tituloPlaylist, novaMusica);	
	}

	public boolean removeMusicaPlaylist(String titulo, Musica musica)throws Exception{
		return this.gerenciadorDePlaylists.removeMusica(titulo, musica);
	}
	
	public boolean temMusicaPlaylist(String titulo, Musica musica)throws Exception{
		return this.gerenciadorDePlaylists.temMusica(titulo, musica);
	}

	public int getDuracaoTotalPlaylist(String titulo)throws Exception{
		return this.gerenciadorDePlaylists.getDuracaoTotal(titulo);
	}

	
	//metodos de GerenciadorDeAlbuns
	public boolean adicionaAlbum(Album novoAlbum)throws Exception{
		return this.getGerenciadorDeAlbuns().adicionaAlbum(novoAlbum);
	}
	
	public boolean favoritaAlbum(String titulo, String artista)throws Exception{
		return this.getGerenciadorDeAlbuns().favoritaAlbum(titulo, artista);
	}
	
	public boolean desfavoritaAlbum(String titulo, String artista)throws Exception{
		return this.getGerenciadorDeAlbuns().desfavoritaAlbum(titulo, artista);
	}
	
	public boolean ehFavorito(String titulo, String artista) throws Exception{
		return this.getGerenciadorDeAlbuns().ehFavorito(titulo, artista);
	}
	
	public boolean temAlbum(String titulo, String artista)throws Exception{
		return this.getGerenciadorDeAlbuns().temAlbum(titulo, artista);
	}
	
	public boolean temAlbum(Album album)throws Exception{
		return this.getGerenciadorDeAlbuns().temAlbum(album);
	}
	
	public Album buscaAlbum(String titulo, String artista)throws Exception{
		return this.getGerenciadorDeAlbuns().buscaAlbum(titulo, artista);
	}
	
	public boolean removeAlbum(String titulo, String artista)throws Exception{
		//antes de remover o album, eh necessario remover todos as
		//musicas pertencentes ao album de todas as playlists
		
		Album albumRemovido = this.getGerenciadorDeAlbuns().buscaAlbum(titulo, artista);
		
		ArrayList<Musica> musicasRemovidas = albumRemovido.getMusicas();
		
		for(Musica musicaRemovida : musicasRemovidas){
			this.getGerenciadorDePlaylists().removeAll(musicaRemovida);
		}
		
		return this.removeAlbum(titulo, artista);
	}

	public ArrayList<Album> buscaAlbunsPorTitulo(String titulo)throws Exception{
		return this.getGerenciadorDeAlbuns().buscaAlbunsPorTitulo(titulo);
	}
	
	public ArrayList<Album> buscaAlbunsPorArtista(String artista)throws Exception{
		return this.getGerenciadorDeAlbuns().buscaAlbunsPorArtista(artista);
	}
	
	public ArrayList<Album> buscaAlbunsPorAno(int ano)throws Exception{
		return this.getGerenciadorDeAlbuns().buscaAlbunsPorAno(ano);
	}
	
	public ArrayList<Album> getAlbunsFavoritos(){
		return this.getGerenciadorDeAlbuns().getAlbunsFavoritos();
	}
	
	
	//metodos de Album
	public boolean adicionaFaixa(String tituloAlbum, String artistaAlbum, Musica novaMusica) throws Exception{
		return this.getGerenciadorDeAlbuns().adicionaFaixa(tituloAlbum, artistaAlbum, novaMusica);
	}
	
	public boolean removeFaixa(String tituloAlbum, String artistaAlbum, String tituloFaixa)throws Exception{
		
		Musica musicaRemovida = this.getGerenciadorDeAlbuns().getFaixa(tituloAlbum, artistaAlbum, tituloFaixa);
		
		this.getGerenciadorDePlaylists().removeAll(musicaRemovida);
		
		return this.getGerenciadorDeAlbuns().removeFaixa(tituloAlbum, artistaAlbum, tituloFaixa);
	}
	
	public boolean temFaixa(String tituloAlbum, String artistaAlbum, Musica musica)throws Exception{
		return this.getGerenciadorDeAlbuns().temFaixa(tituloAlbum, artistaAlbum, musica);
	}
	
	public boolean temFaixa(String tituloAlbum, String artistaAlbum, String tituloFaixa)throws Exception{
		return this.getGerenciadorDeAlbuns().temFaixa(tituloAlbum, artistaAlbum, tituloFaixa);
	}

	public Musica getFaixa(String tituloAlbum, String artistaAlbum, int indice)throws Exception{
		return this.getGerenciadorDeAlbuns().getFaixa(tituloAlbum, artistaAlbum, indice);
	}

	
	//metodos triviais
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gerenciadorDeAlbuns == null) ? 0 : gerenciadorDeAlbuns.hashCode());
		result = prime * result + ((gerenciadorDePlaylists == null) ? 0 : gerenciadorDePlaylists.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		
		if(obj instanceof Musiteca){
			
			Musiteca outraMusiteca = (Musiteca) obj;
			
			GerenciadorDeAlbuns outroGerenciadorDeAlbuns = outraMusiteca.getGerenciadorDeAlbuns();
			GerenciadorDePlaylists outroGerenciadorDePlaylists = outraMusiteca.getGerenciadorDePlaylists();
			
			boolean albunsIguais = this.getGerenciadorDeAlbuns().equals(outroGerenciadorDeAlbuns);
			boolean playlistsIguais = this.getGerenciadorDePlaylists().equals(outroGerenciadorDePlaylists);
			
			if(albunsIguais && playlistsIguais){
				return true;
			}
		}
		
		return false;
	}

	public String toString(){
		String quebraDeLinha = System.lineSeparator();
		
		String retorno = "";
		
		retorno += this.getGerenciadorDeAlbuns().toString();
		
		retorno += quebraDeLinha + quebraDeLinha;
		
		retorno += this.getGerenciadorDePlaylists().toString();
		
		return retorno;
	}

	
	//getters e setters
	public GerenciadorDeAlbuns getGerenciadorDeAlbuns() {
		return gerenciadorDeAlbuns;
	}

	public void setGerenciadorDeAlbuns(GerenciadorDeAlbuns gerenciadorDeAlbuns) {
		this.gerenciadorDeAlbuns = gerenciadorDeAlbuns;
	}

	public GerenciadorDePlaylists getGerenciadorDePlaylists() {
		return gerenciadorDePlaylists;
	}

	public void setGerenciadorDePlaylists(GerenciadorDePlaylists gerenciadorDePlaylists) {
		this.gerenciadorDePlaylists = gerenciadorDePlaylists;
	}
	
	
}