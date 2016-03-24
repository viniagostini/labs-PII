package p2_cg;

public class UsuarioNoob extends Usuario{

	public static final double DESCONTO =  0.1; //10% de desconto
	
	
	
	public UsuarioNoob(String nome) throws Exception {
		super(nome);
	}

	@Override
	public boolean realizaCompra(Jogo novoJogo) throws Exception {

		double precoJogo = novoJogo.getPreco();
		
		double precoComDesconto = this.aplicaDesconto(precoJogo);
		
		if(this.getJogos().contains(novoJogo)){
			throw new Exception("O Usuario ja possui o jogo especificado");
			
		}
		
		
		if(this.getSaldo() >= precoJogo){
			
			this.getJogos().add(novoJogo);
			this.abateSaldo(precoJogo);
			
			return true;
			
		}else{
			return false;
		}
	}

	private double aplicaDesconto(double valor){
		
		return valor - valor * DESCONTO;
	}
	
	
		
	
}
