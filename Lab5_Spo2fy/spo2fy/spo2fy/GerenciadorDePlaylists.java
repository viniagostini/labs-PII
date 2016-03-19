package spo2fy;

import java.util.TreeMap;

public class GerenciadorDePlaylists {
	
	/*
	 * 
	 * Para armazenar as playlists foi usado um TreeMap,
	 * que eh iteravel e por ser um mapa, torna muito facil
	 * e efeciente a pesquisa no mesmo
	 * 
	 * 
	 * As playlists ficam sempre ordenadas por duracao
	 * 
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!talvez a playlist nao tenha titulo como atributo (pensar nisso depois)
	 * 
	 * */
	
	private TreeMap<String, Playlist> playlists;
	
	//construtor
	public GerenciadorDePlaylists(){
		
		this.playlists = new TreeMap<String, Playlist>();
	}
	
	//funcionalidades de gerenciador
	public boolean criaPlaylist(String titulo)throws Exception{
		
		Playlist novaPlayList = new Playlist(titulo);
		
		if(temPlaylist(titulo)){
			return false;
		}else{
			
			this.getPlaylists().put(titulo, novaPlayList);
			return true;
		}
	}

	public boolean removePlaylist(String titulo)throws Exception{
		
		this.valida(titulo);
		
		if(temPlaylist(titulo)){
			this.getPlaylists().remove(titulo);
			
			return true;
		}else{
			
			return false;	
		}
	}

	public Playlist buscaPlaylist(String titulo)throws Exception{
		
		this.valida(titulo);
		
		return this.getPlaylists().get(titulo);
	}
	
	public boolean temPlaylist(String titulo)throws Exception{
		
		this.valida(titulo);
		
		return this.getPlaylists().containsKey(titulo);
	}
	
	public void removeAll(Musica musica)throws Exception{
		
		//vai ser garantido que a musica em questao sera removida de todas as playlists
		
		for( String chave : this.getPlaylists().keySet() ){
			
			Playlist playlistAtual = this.getPlaylists().get(chave);
			
			//aqui vai ser lancada uma exception caso a musica seja null
			playlistAtual.removeMusica(musica);
	
		}
		
	}
	
	
	//funcionalidades de playlist(delegacao)
	public boolean adcionaMusica(String titulo, Musica novaMusica)throws Exception{
		
		this.valida(titulo);
		
		if(! temPlaylist(titulo)){
			this.criaPlaylist(titulo);
		}
		
		Playlist playlistAtual = this.getPlaylists().get(titulo);
		
		//se a musica for invalida sera lancada uma exception
		return playlistAtual.adcionaMusica(novaMusica);
		
	}

	public boolean removeMusica(String titulo, Musica musica)throws Exception{
		
		this.valida(titulo);
		
		Playlist playlistAtual = this.getPlaylists().get(titulo);
		
		return playlistAtual.removeMusica(musica);
	}
	
	public boolean temMusica(String titulo, Musica musica)throws Exception{
		
		this.valida(titulo);
		
		Playlist playlistAtual = this.getPlaylists().get(titulo);
		
		return playlistAtual.temMusica(musica);
	}

	public int getDuracaoTotal(String titulo)throws Exception{
		
		this.valida(titulo);
		
		Playlist playlistAtual = this.getPlaylists().get(titulo);
		
		return playlistAtual.getDuracaoTotal();	
	}

	
	
	
	
	//getters e setters
	public TreeMap<String, Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(TreeMap<String, Playlist> playlists) {
		this.playlists = playlists;
	}
	
	
	
	//metodos de funcionamento interno
		private void valida(String str)throws Exception{
			
			if(str == null || str.trim().isEmpty()){
				throw new Exception("Nao eh possivel trabalhar com valores vazios ou null.");
			}
		
		}
		
}
