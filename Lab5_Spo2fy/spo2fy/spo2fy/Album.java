package spo2fy;

import java.util.ArrayList;

public class Album {
	
	public static final int NAO_ENCONTRADO = -1;
	
	private String titulo;
	private int ano;
	private String artista; //!!!!!!!!!!!!!!!!!SE DER TEMPO, PENSAR EM ENCAPSULAR ARTISTA(INTEGRANTES, INFO, ETC..)
	private ArrayList<Musica> faixas;
	
	public Album(String titulo, int ano, String artista)throws Exception{
		
		if( titulo == null || titulo.equals("") ){
			throw new Exception("Não é permitida a criação de albuns sem titulo.");
		}else{
			this.titulo = titulo;
		}
		
		if( ano < 1900 ){
			throw new Exception("Não é permitida a criação de albuns com data inferior a 1900.");
		}else{
			this.ano = ano;
		}
		
		if( artista == null || artista.equals("") ){
			throw new Exception("Não é permitida a criação de albuns sem artista.");
		}else{
			this.artista = artista;
		}
		
		this.faixas = new ArrayList<Musica>();
	}
	
	
	public boolean adicionaFaixa(String titulo, int duracao, String genero) throws Exception{
		
		// caso algum dos parametros sejam invalidos o construtor de Musica irá lançar a exception
		Musica novaMusica = new Musica(titulo, duracao, genero);
		
		return this.adicionaFaixa(novaMusica);
	}	
	
	public boolean adicionaFaixa(Musica novaMusica) throws Exception{
	
		if(novaMusica == null){
			throw new Exception("Não é possivel adicionar faixas vazias");
		}
		
		if(temFaixa(novaMusica)){
			return false;
		}else{
			this.faixas.add(novaMusica);
			return true;	
		}
	}
	
	public boolean removeFaixa(String titulo)throws Exception{
		
		int result = this.buscaFaixa(titulo);
		
		if(result == NAO_ENCONTRADO){
			return false;
			
		}else{
			int indice = result;
			
			this.getFaixas().remove(indice);
			
			return true;
		}
	}
	
	public boolean temFaixa(Musica musica)throws Exception{
		
		int result = this.buscaFaixa(musica);
		
		if(result >= 0){
			return true;
		}else{
			return false;
		}
			
	}
	
	public boolean temFaixa(String titulo)throws Exception{
		
		int result = this.buscaFaixa(titulo);
		
		if(result >= 0){
			return true;
		}else{
			return false;
		}
			
	}
	
	
	private int buscaFaixa(String titulo)throws Exception{
		
		if(titulo == null || titulo.equals("")){
			throw new Exception("Não é permitido pesquisar valores nulos");
		}
		
		int len = this.getFaixas().size();
		
		for(int i =0; i < len; i++){
			Musica musicaAtual = this.getFaixas().get(i);
			String tituloAtual = musicaAtual.getTitulo();
			
			if(tituloAtual.equals(titulo)){
				return i;
			}
		}
		
		return NAO_ENCONTRADO;
		
	}
	
	private int buscaFaixa(Musica musica) throws Exception{
		
		if(musica == null){
			throw new Exception("Não é permitido pesquisar valores nulos");
		}
		
		int len = this.getFaixas().size();
		
		for(int i =0; i < len; i++){
			Musica musicaAtual = this.getFaixas().get(i);
			
			if(musicaAtual.equals(musica)){
				return i;
			}
			
		}
		
		return NAO_ENCONTRADO;
		
	}


	
	
	public int getAno() {
		return ano;
	}


	
	public void setAno(int ano) {
		this.ano = ano;
	}


	
	public String getArtista() {
		return artista;
	}


	
	public void setArtista(String artista) {
		this.artista = artista;
	}
	
	public int getDuracaoTotal(){
		
		int duracaoTotal = 0;
		
		if(this.getFaixas().isEmpty()){
			return 0;
		}else{
			
			for( Musica musicaAtual : this.getFaixas() ){
				int duracaoAtual = musicaAtual.getDuracao();
				duracaoTotal += duracaoAtual;
			}
		}
		
		return duracaoTotal;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	private ArrayList<Musica> getFaixas() {	
		return this.faixas;
	}

	
	//não faz sentido permitir que qualquer classe externa a essa mude o array
	/*
	public void setFaixas(ArrayList<Musica> faixas) {
		this.faixas = faixas;
	}
	*/
	
	
}
