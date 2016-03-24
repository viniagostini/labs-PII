package jogos;

public class JogoRPG extends Jogo{

	public JogoRPG(String nome, double preco) throws Exception {
		super(nome, preco);
	}


	@Override
	public int registraJogada(int score, boolean zerou) throws Exception {
		
		this.validaRegistaJogada(score);
		
		this.incrementaNJogadas();
		
		if(zerou){
			this.incrementaNZeradas();
		}
		
		int x2p = 10;
		
		return x2p;
	}

	
	
	
	
	
	
	private void validaRegistaJogada(int score) throws Exception{
		
		if( score < 0 ){
			throw new Exception("Nao eh permitido o registro de score negativo");
		}	
	}
	
}
