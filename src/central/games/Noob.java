package central.games;

public class Noob extends Usuario {

	public Noob(String nome, String login) throws Exception{
		super(nome, login);

	}

	@Override
	public boolean compraJogo(Jogo jogoAComprar) {
		if(super.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco() * 0.9)) {
			super.descontaDinheiro(jogoAComprar.getPreco() * 0.9);
			super.adicionaX2p(10 * jogoAComprar.getPreco());
			return this.adicionaJogo(jogoAComprar);
		}
		
		return false;
	}

}
