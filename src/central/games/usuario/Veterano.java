package central.games.usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogabilidade;
import central.games.jogo.Jogo;
import central.games.jogo.RPG;
import exception.ValidacaoException;
import validacao.Validacao;

public class Veterano extends Usuario {

	public Veterano(String nome, String login) throws ValidacaoException{
		
		super(nome, login);
		super.adicionaX2p(1000);

	}
	
	@Override
	public boolean compraJogo(Jogo jogoAComprar) throws ValidacaoException, MissingResourceException {

		Validacao.validaObj(jogoAComprar, "Jogo nao pode ser nulo");
		
		if(super.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco() * 0.8)) {
			
				super.descontaDinheiro(jogoAComprar.getPreco() * 0.8);
				super.adicionaX2p(15 * jogoAComprar.getPreco());
				return super.adicionaJogo(jogoAComprar);
		}
		
		throw new MissingResourceException("Dinheiro insuficiente", "Usuario", "Preco");
	}

	@Override
	public String toString() {
		String veterano = super.getLogin() + FIM_DE_LINHA +
						super.getNome() + " - Jogador Veterano" + FIM_DE_LINHA +
						"Lista de Jogos:" + FIM_DE_LINHA;
		int totalPreco = 0;
		
		for(Jogo jogosObtidos: super.getJogos()) {
			veterano += jogosObtidos + FIM_DE_LINHA;
			totalPreco += jogosObtidos.getPreco();
		}
		
		veterano += "Total de preco dos jogos: R$ " + totalPreco + ",00" + FIM_DE_LINHA +
				"--------------------------------------------";
		return veterano;
	}
	
	

}
