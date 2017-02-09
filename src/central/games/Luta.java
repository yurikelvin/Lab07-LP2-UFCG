package central.games;

public class Luta extends Jogo {
	
	

	public Luta(String nome, int preco) throws Exception{
		super(nome, preco);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int registraJogada(int score, boolean zerou) throws Exception {
		if(score < 0) {
			throw new Exception("Score nao pode ser negativo");
		}
		if(zerou) {
			this.zerou();
		}
		if(score > super.getMaiorScore()) {
			this.setMaiorScore(score);
			return score / 1000;
		}
		
		super.jogou();
		
		return 0;

	}


	
	

}
