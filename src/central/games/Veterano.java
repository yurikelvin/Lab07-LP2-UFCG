package central.games;

import java.util.HashSet;
import java.util.Iterator;

public class Veterano extends Usuario {

	public Veterano(String nome, String login) throws Exception{
		super(nome, login);
		super.adicionaX2p(1000);

	}
	
	public void imprimeJogos() throws Exception{
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
	public boolean compraJogo(Jogo jogoAComprar) throws Exception {
		if(jogoAComprar == null) {
			throw new Exception("Jogo nao pode ser nulo");
		}

		if(super.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco() * 0.8)) {
			
				super.descontaDinheiro(jogoAComprar.getPreco() * 0.8);
				super.adicionaX2p(15 * jogoAComprar.getPreco());
				return this.adicionaJogo(jogoAComprar);
		}
		
		return false;
	}
	
	

}
