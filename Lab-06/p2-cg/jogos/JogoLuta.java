package jogos;


public class JogoLuta extends Jogo{

	public static final int MAIOR_SCORE_POSSIVEL = 100000;
	
	
	public JogoLuta(String nome, double preco) throws Exception {
		
		super(nome, preco);
		
	}

	@Override
	public int registraJogada(int score, boolean zerou) throws Exception {
		
		this.validaRegistaJogada(score);
		
		if(score >= MAIOR_SCORE_POSSIVEL){
			throw new Exception("Tah de hack");
		}
		
		
		
		if(score > this.getMaiorScore()){
			
			this.setMaiorScore(score);
			
			int x2p = score / 1000;
			
			return x2p;
			
			
		}else{
			return 0;
		}
		
		
		
		
		
	}

	
	
	
	
	
	
	
	private void validaRegistaJogada(int score) throws Exception{
		
		if( score < 0 ){
			throw new Exception("Nao eh permitido o registro de score negativo");
		}	
	}
	
	
	
}
