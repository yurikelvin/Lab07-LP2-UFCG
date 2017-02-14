package central.games.usuario;

import java.util.MissingResourceException;


import central.games.jogo.Jogo;
import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Classe que representa um tipo de Usuario.
 * 
 * @author Yuri Silva
 */

public class Noob extends Usuario {
	
	/**
	 * Cria um tipo de Usuario Noob.
	 * 
	 * @see Usuario#Usuario(String, String)
	 * 
	 * @param nome Nome do Usuario.
	 * @param login Login do Usuario.
	 * 
	 * @throws ValidacaoException Se nome ou login for nulo ou vazio. 
	 */

	public Noob(String nome, String login) throws ValidacaoException{
		
		super(nome, login);

	}

	@Override
	public boolean compraJogo(Jogo jogoAComprar) throws ValidacaoException, MissingResourceException{
		
		Validacao.validaObj(jogoAComprar, "Jogo nao pode ser nulo");
		
		if(super.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco() * 0.9)) {
			if(!super.temJogo(jogoAComprar)) {
				super.descontaDinheiro(jogoAComprar.getPreco() * 0.9);
				super.adicionaX2p(10 * jogoAComprar.getPreco());
				return super.adicionaJogo(jogoAComprar);
			}
			throw new ValidacaoException("Usuario ja possui este jogo.");
		}
		
		throw new MissingResourceException("Dinheiro insuficiente", "Usuario", "Preco");
	}

	@Override
	public String toString() {
		String noob = super.getLogin() + FIM_DE_LINHA +
						super.getNome() + " - Jogador Noob" + FIM_DE_LINHA +
						"Lista de Jogos:" + FIM_DE_LINHA;
		int totalPreco = 0;
		
		for(Jogo jogosObtidos: super.getJogos()) {
			noob += jogosObtidos + FIM_DE_LINHA;
			totalPreco += jogosObtidos.getPreco();
		}
		
		noob += "Total de preco dos jogos: R$ " + totalPreco + ",00" + FIM_DE_LINHA +
				"--------------------------------------------";
		return noob;
	}

}
