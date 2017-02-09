package central.games;

public class Plataforma extends Jogo {

	public Plataforma(String nome, int preco) throws Exception{
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
			return 20;
		}
		if(score > super.getMaiorScore()) {
			this.setMaiorScore(score);
		}
		super.jogou();
		return 0;
	}



}
