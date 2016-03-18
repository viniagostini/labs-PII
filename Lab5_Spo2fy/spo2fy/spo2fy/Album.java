package spo2fy;

import java.util.ArrayList;

public class Album implements Comparable<Album> {
	
	public static final int NAO_ENCONTRADO = -1;
	
	
	private String titulo;
	private int ano;
	private String artista;
	private ArrayList<Musica> faixas;
	
	//construtor
	public Album(String titulo, int ano, String artista)throws Exception{
		
		if( titulo == null || titulo.equals("") ){
			throw new Exception("Nao eh permitida a criacao de musicas sem titulo.");
		}else{
			this.titulo = titulo;
			
			
		}
		
		if( ano <= 1900 ){
			throw new Exception("Nao eh permitida a criacao de albuns com data inferior a 1900.");
		}else{
			this.ano = ano;
		}
		
		if( artista == null || artista.equals("") ){
			throw new Exception("Nao eh permitida a criacao de albuns sem artista.");
		}else{
			this.artista = artista;
		}
		
		this.faixas = new ArrayList<Musica>();
	}
	
	//funcionalidades
	public boolean adicionaFaixa(String titulo, int duracao, String genero) throws Exception{
		
		// caso algum dos parametros sejam invalidos o construtor de Musica irï¿½ lanï¿½ar a exception
		Musica novaMusica = new Musica(titulo, duracao, genero);
		
		return this.adicionaFaixa(novaMusica);
	}	
	
	public boolean adicionaFaixa(Musica novaMusica) throws Exception{
	
		if(novaMusica == null){
			throw new Exception("Nao eh possivel adicionar faixas vazias");
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
	
	public Musica getFaixa(int indice)throws Exception{
		
		if(indice < 1){
			throw new Exception("Nao existem faixas com numero inferiror a 1.");
		}
		
		int tamanhoAlbum = this.getFaixas().size();
		
		if(indice > tamanhoAlbum){
			throw new Exception("Nao ha musica com esse indice no album.");
		}
		
		// os indices do arrayList comecam em 0 e as faixas em 1, por isso o "indice - 1" 
		return this.getFaixas().get(indice - 1);
	}
	
	private int buscaFaixa(String titulo)throws Exception{
		
		if(titulo == null || titulo.equals("")){
			throw new Exception("Nao eh permitido pesquisar valores nulos");
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
			throw new Exception("Nï¿½o ï¿½ permitido pesquisar valores nulos");
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

	
	//metodos triviais
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artista == null) ? 0 : artista.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Album){
			
			Album outroAlbum = (Album) obj;
			String tituloOutroAlbum = outroAlbum.getTitulo();
			String artistaOutroAlbum = outroAlbum.getArtista();
			
			
			if(this.getTitulo().equals(tituloOutroAlbum) && 
					this.getArtista().equals(artistaOutroAlbum)){
				
				return true;	
			}
		}
		
		return false;
	}
	
	public String toString(){		
		String quebraDeLinha = System.lineSeparator();
		
		String retorno = "";
		
		retorno += "Titulo: "+this.getTitulo(); 
		retorno += "; Ano: " + this.getAno(); 
		retorno += "; Artista: " + this.getArtista() + quebraDeLinha;
		
		retorno += quebraDeLinha;
		
		int i = 1;
		for(Musica musicaAtual : this.getFaixas()){
			retorno += "        faixa "+i+": ";
			retorno += musicaAtual.toString();
			retorno += quebraDeLinha;
			
			i++;
		}
		
		return retorno;
	}

	@Override
	public int compareTo(Album outroAlbum) {
		
		int anoInterno = this.getAno();
		int anoExterno = outroAlbum.getAno();
		
		// se a subtracao entre o ano da classe atual e a outra classe for positiva,
		// significa que o maior ano é o da classe interna, se for negativa será o contrario,
		// e se for 0, as duas sao iguais...

		return anoInterno - anoExterno;	
	}
	
	
	// getters e setters
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



	//nao faz sentido permitir que qualquer classe externa a essa mude o array
	//public void setFaixas(ArrayList<Musica> faixas) {
	
	
	
}
