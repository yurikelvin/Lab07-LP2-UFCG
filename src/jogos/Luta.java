package jogos;

public class Luta extends Jogo {
	
	

	public Luta(String nome, double preco) {
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

	@Override
	public void setJogabilidade(Jogabilidade jogabilidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Jogabilidade getJogabilidade(Jogabilidade jogabilidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
