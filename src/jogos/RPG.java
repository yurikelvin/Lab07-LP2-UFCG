package jogos;

public class RPG extends Jogo{
	
	

	public RPG(String nome, double preco) {
		super(nome, preco);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int registraJogada(int score, boolean zerou) {
		if(zerou) {
			this.zerou();
		}
		if(score > this.getMaiorScore()) {
			this.setMaiorScore(score);
		}
		return 0;
	}

	
	

}
