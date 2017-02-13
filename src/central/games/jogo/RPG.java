package central.games.jogo;

import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Tipo especifico de Jogo.
 * 
 * @author Yuri Silva
 *
 */
public class RPG extends Jogo{
	
	/**
	 * Cria um jogo especifico do tipo RPG.
	 * 
	 * @see Jogo#Jogo(String, int)

	 */

	public RPG(String nome, int preco) throws ValidacaoException {
		super(nome, preco);

	}

	@Override
	public int registraJogada(int score, boolean zerou) throws ValidacaoException{

		Validacao.validaInt(score, "Score nao pode ser negativo");
		
		if(zerou) {
			this.zerou();
		}
		if(score > super.getMaiorScore()) {
			this.setMaiorScore(score);
			
		}
		super.jogou();
		return 10;
	}

	@Override
	public String toString() {
		String rpg = "+ " + super.getNome() + " - RPG:" + FIM_DE_LINHA +
						"==> Jogou " + super.getQtdJogadas() + " vez(es)" + FIM_DE_LINHA + 
						"==> Zerou " + super.getQtdZeradas() + " vez(es)" + FIM_DE_LINHA +
						"==> Maior score: " + super.getMaiorScore();
				
		return rpg;
	}

	
	

}
