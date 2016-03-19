package spo2fy;

import java.util.ArrayList;

public class Playlist implements Comparable<Playlist>{

	private String titulo;
	private ArrayList<Musica> musicas;
	
	//construtor
	public Playlist(String titulo)throws Exception{
		
		this.valida(titulo);
		
		this.titulo = titulo;
		this.musicas = new ArrayList<Musica>();
	}
	
	
	//funcionalidades
	public boolean adcionaMusica(Musica novaMusica)throws Exception{
		
		this.valida(novaMusica);
		
		//nao eh permitida a adicao de duas musicas iguais na playlist
		if(temMusica(novaMusica)){
			
			return false;
		}else{
		
			this.getMusicas().add(novaMusica);
			return true;
		}
	}

	public boolean removeMusica(Musica musica)throws Exception{
		
		this.valida(musica);
		
		return this.getMusicas().remove(musica);
	}
	
	public boolean temMusica(Musica musica)throws Exception{
		
		this.valida(musica);
		
		return this.getMusicas().contains(musica);
	}

	public int getDuracaoTotal(){
		
		int duracaoTotal = 0;
		
		for( Musica musicaAtual : this.getMusicas() ){
			int duracaoAtual = musicaAtual.getDuracao();
			duracaoTotal += duracaoAtual;
		}

		return duracaoTotal;
	}

	
	
	//metodos triviais
	@Override
	public int compareTo(Playlist outraPlaylist) {
		
		int duracaoAtual = this.getDuracaoTotal();
		int outraDuracao = outraPlaylist.getDuracaoTotal();
		
		//se o resultado da subtacao for positivo, significa que a 
		//playlist atual eh maior que a outra, caso contrario,
		//ela eh menor, e se for 0, elas tem a mesma duracao.
		
		return duracaoAtual - outraDuracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Playlist){
			Playlist outraPlaylist = (Playlist) obj;
			
			String outroTitulo = outraPlaylist.getTitulo();
			
			if(this.getTitulo().equals(outroTitulo)){
				return true;
			}
			
		}
		
		return false;
	}

	public String toString(){
		String quebraDeLinhha = System.lineSeparator();
		
		String retorno = "";
		
		retorno += "Playlist: " + this.getTitulo() + quebraDeLinhha;
		
		retorno += quebraDeLinhha;
		
		int i = 1;
		for( Musica musicaAtual : this.getMusicas() ){
			
			retorno += "    " + i + ". " + musicaAtual.toString();
			retorno += quebraDeLinhha;
		}
		
		retorno += quebraDeLinhha;
		
		retorno += "Duracao total: " + this.getDuracaoTotal() + " minutos";
		
		return retorno;
	}

	
	//getters e setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(ArrayList<Musica> musicas) {
		this.musicas = musicas;
	}
	
	
	//metodos de funcionamento interno
	private void valida(String str)throws Exception{
		
		if(str == null || str.trim().isEmpty()){
			throw new Exception("Nao eh possivel trabalhar com valores vazios ou null.");
		}
	
	}
	
	//metodos de funcionamento interno
	private void valida(Musica musica)throws Exception{
		
		if(musica == null){
			throw new Exception("Nao eh possivel trabalhar com musicas nulas.");
		}
	
	}
	
}