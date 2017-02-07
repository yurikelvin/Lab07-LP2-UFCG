package central.games;

public class Plataforma extends Jogo {

	public Plataforma(String nome, double preco) throws Exception{
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
		if(score > this.getMaiorScore()) {
			this.setMaiorScore(score);
		}
		return 0;
	}



}
