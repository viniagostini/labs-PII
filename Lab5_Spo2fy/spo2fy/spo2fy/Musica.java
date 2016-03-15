package spo2fy;

public class Musica implements Comparable<Musica>{

	private String titulo;
	private int duracao; // em minutos
	private String genero;
	
	public Musica(String titulo, int duracao, String genero) throws Exception{
		
		if(titulo == null || titulo.equals("")){
			throw new Exception("Titulo da musica nao pode ser nulo ou vazio."); 
		}else{
			this.titulo = titulo;
		}
		
		if(duracao < 0){
			throw new Exception("Duracao da musica nao pode ser negativa.");
		}else{
			this.duracao = duracao;
		}
		
		if(genero == null || genero.equals("")){
			throw new Exception("Genero da musica nao pode ser nulo ou vazio."); 
		}else{
			this.genero = genero;
		}
		
	}

	
	
	public int compareTo(Musica outraMusica) {
		
		// implementar compareTo
		
		return 0;
	}
		
	
	public String toString(){
		String retorno = "";
		
		retorno += "Faixa: " + this.getTitulo() + "  ";
		retorno += "Duracao: " + this.getDuracao() + " minutos  ";
		retorno += "Genero: " + this.getGenero() + ".";
 		
		return retorno;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duracao;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	public boolean equals(Object obj){
		
		if(obj instanceof Musica){
			Musica outraMusica = (Musica) obj;
			
			String outroTitulo = outraMusica.getTitulo();
			int outraDuracao = outraMusica.getDuracao();
			
			// se o tituto da classe é igual ao do objeto e se as duracoes sao iguais
			if( this.getTitulo().equals( outroTitulo ) && this.getDuracao() == outraDuracao){
				return true;
			}
		}
		
		return false;
	}

	
}
