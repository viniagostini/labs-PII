package spo2fy;

public class Musiteca {
	
	/*
	 * Essa classe usa o conceito de composicao para
	 * encapsular os albuns e a playlist
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
		Album albumAtual = this.getGerenciadorDeAlbuns().buscaAlbum(tituloAlbum, artistaAlbum);
		
		//se a faixa for invalida ou se nao existir, sera lancada uma exception
		Musica musicaAtual = albumAtual.getFaixa(faixa);
		
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
