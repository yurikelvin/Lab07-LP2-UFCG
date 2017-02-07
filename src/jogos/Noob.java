package jogos;

public class Noob extends Usuario {

	public Noob(String nome, String login) {
		super(nome, login);

	}

	@Override
	public boolean compraJogo(Jogo jogoAComprar) {
		if(this.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco() * 0.1)) {
			this.descontaDinheiro(jogoAComprar.getPreco() * 0.1);
			return this.adicionaJogo(jogoAComprar);
		}
		
		return false;
	}

}
