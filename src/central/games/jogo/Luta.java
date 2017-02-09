package central.games.jogo;

import validacao.Validacao;

public class Luta extends Jogo {
	
	

	public Luta(String nome, int preco) throws Exception{
		super(nome, preco);

	}

	@Override
	public int registraJogada(int score, boolean zerou) throws Exception {

		Validacao.validaInt(score, "Score nao pode ser negativo");
		
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

	@Override
	public String toString() {
		String luta = "+ " + super.getNome() + " - Luta:" + FIM_DE_LINHA +
				"==> Jogou " + super.getQtdJogadas() + " vez(es)" + FIM_DE_LINHA + 
				"==> Zerou " + super.getQtdZeradas() + " vez(es)" + FIM_DE_LINHA +
				"==> Maior score: " + super.getMaiorScore();
		
		return luta;
	}


	
	

}
