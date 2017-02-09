package central.games.jogo;

import validacao.Validacao;

public class Plataforma extends Jogo {

	public Plataforma(String nome, int preco) throws Exception{
		super(nome, preco);

	}

	@Override
	public int registraJogada(int score, boolean zerou) throws Exception {

		Validacao.validaInt(score, "Score nao pode ser negativo");
		
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

	@Override
	public String toString() {
		String plataforma = "+ " + super.getNome() + " - Plataforma:" + FIM_DE_LINHA +
				"==> Jogou " + super.getQtdJogadas() + " vez(es)" + FIM_DE_LINHA + 
				"==> Zerou " + super.getQtdZeradas() + " vez(es)" + FIM_DE_LINHA +
				"==> Maior score: " + super.getMaiorScore();
		
		return plataforma;
	}



}
