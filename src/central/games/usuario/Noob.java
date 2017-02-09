package central.games.usuario;

import central.games.jogo.Jogo;
import validacao.Validacao;

public class Noob extends Usuario {

	public Noob(String nome, String login) throws Exception{
		
		super(nome, login);

	}

	@Override
	public boolean compraJogo(Jogo jogoAComprar) throws Exception{
		
		Validacao.validaObj(jogoAComprar, "Jogo nao pode ser nulo");
		
		if(super.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco() * 0.9)) {
			super.descontaDinheiro(jogoAComprar.getPreco() * 0.9);
			super.adicionaX2p(10 * jogoAComprar.getPreco());
			return super.adicionaJogo(jogoAComprar);
		}
		
		return false;
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
		
		noob += FIM_DE_LINHA + "Total de preco dos jogos: R$ " + totalPreco + ",00" + FIM_DE_LINHA +
				"--------------------------------------------";
		return noob;
	}

}
