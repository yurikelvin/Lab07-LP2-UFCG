package central.games.jogo;

import exception.ValidacaoException;

import validacao.Validacao;

/**
 * Tipo especifico de Jogo.
 * 
 * @author Yuri Silva
 *
 */

public class Plataforma extends Jogo {
	
	/**
	 * Cria um jogo especifico do tipo Plataforma.
	 * 
	 * @see Jogo#Jogo(String, int)
	 * @param nome Nome do Jogo.
	 * @param preco Preco do Jogo.
 	 * @throws ValidacaoException Se nome for nulo ou vazio e preco for menor que 0.
	 * 
	 */

	public Plataforma(String nome, int preco) {
		super(nome, preco);

	}
	
	

	@Override
	public int registraJogada(int score, boolean zerou) throws ValidacaoException {

		Validacao.validaInt(score, "Score nao pode ser negativo");
		
		super.jogou();
		
		if(zerou) {
			this.zerou();
			return 20;
		}
		if(score > super.getMaiorScore()) {
			this.setMaiorScore(score);
		}

		return 0;
	}

	@Override
	public String toString() {
		String plataforma =  FIM_DE_LINHA + "+ " + super.getNome() + " - Plataforma:" + FIM_DE_LINHA +
				"==> Jogou " + super.getQtdJogadas() + " vez(es)" + FIM_DE_LINHA + 
				"==> Zerou " + super.getQtdZeradas() + " vez(es)" + FIM_DE_LINHA +
				"==> Maior score: " + super.getMaiorScore();
		
		return plataforma;
	}



}
