package jogos;

import java.util.HashSet;
import java.util.Iterator;

public class Veterano extends Usuario {

	public Veterano(String nome, String login) {
		super(nome, login);

	}
	
	public void imprimeJogos() {
		this.depositaDinheiro(5000);
		RPG meuRpg = new RPG("Ragnarok", 5);
		meuRpg.adicionaJogabilidade(Jogabilidade.ONLINE);
		meuRpg.adicionaJogabilidade(Jogabilidade.OFFLINE);
		compraJogo(meuRpg);
		HashSet<Jogo> teste = this.showGames();
		Iterator<Jogo> it = teste.iterator();
		while(it.hasNext()) {
			Jogo meuJogo = it.next();
			System.out.println(meuJogo);
		}
	}

	@Override
	public boolean compraJogo(Jogo jogoAComprar) {
		if(this.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco() * 0.2)) {
			this.descontaDinheiro(jogoAComprar.getPreco() * 0.2);
			return this.adicionaJogo(jogoAComprar);
		}
		
		return false;
	}
	
	

}
