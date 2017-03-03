package central.games.jogo;

import exception.ValidacaoException;
import validacao.Validacao;

/**
 * 
 * Tipo especifico de Jogo.
 * 
 * @author Yuri Silva
 *
 */
public class Luta extends Jogo {
	
	/**
	 * Cria um jogo especifico do tipo Luta.
	 * 
	 * @see Jogo#Jogo(String, int)
	 * @param nome Nome do Jogo.
	 * @param preco Preco do Jogo.
	 * @throws ValidacaoException Se nome for nulo ou vazio e preco for menor que 0.
	 */
	
	public Luta(String nome, int preco) {
		super(nome, preco);

	}

	@Override
	public int registraJogada(int score, boolean zerou) throws ValidacaoException {

		Validacao.validaInt(score, "Score nao pode ser negativo");
		
		super.jogou();
		
		if(zerou) {
			this.zerou();
		}
		if(score >= super.getMaiorScore()) {
			super.setMaiorScore(score);
			return score / 1000;
		}
		

		
		return 0;

	}

	@Override
	public String toString() {
		String luta =  FIM_DE_LINHA + "+ " + super.getNome() + " - Luta:" + FIM_DE_LINHA +
				"==> Jogou " + super.getQtdJogadas() + " vez(es)" + FIM_DE_LINHA + 
				"==> Zerou " + super.getQtdZeradas() + " vez(es)" + FIM_DE_LINHA +
				"==> Maior score: " + super.getMaiorScore() + FIM_DE_LINHA;
		
		return luta;
	}


	
	

}
