package central.games;

import java.util.MissingResourceException;

import central.games.jogo.Luta;
import central.games.jogo.Plataforma;
import central.games.jogo.RPG;
import central.games.usuario.Noob;

public class Teste {
	
	public static void main(String[] args) throws Exception {

		
		String nome1 = "Super Mario World";
		String nome2 = "Guilty Gears";
		
		Loja minhaLoja = new Loja();
		
		Noob meuNoob = new Noob("TesteNoob", "TesteNoob1");
		Plataforma teste = new Plataforma(nome1, 30);
		Luta testeLuta = new Luta(nome2, 80);
		RPG meuRPG = new RPG("Paper Mario", 75);
		meuNoob.depositaDinheiro(2000);
		meuNoob.compraJogo(teste);
		
		System.out.println(meuNoob.getX2p());
		minhaLoja.adicionaUsuario(meuNoob);

		meuNoob.compraJogo(testeLuta);
		
		System.out.println(meuNoob.getX2p());
		minhaLoja.upgrade("TesteNoob1");
				
		meuNoob.registraJogada(nome1, 1000, true);
		meuNoob.registraJogada(nome1, 1000, true);
		meuNoob.registraJogada(nome1, 1000, true);
		meuNoob.registraJogada(nome1, 0, false);
		meuNoob.registraJogada(nome1, 2000, false);
		
		meuNoob.registraJogada(nome2, 80000, true);

		System.out.println(meuNoob.getX2p());
		
		meuNoob.compraJogo(new Luta("Guilty Gears", 80));
		meuNoob.compraJogo(new RPG("Paper Mario", 75));
		
		System.out.println(meuNoob.getX2p());
		
	

		//System.out.println(minhaLoja);

	}
	
/*	public void imprimeJogos() throws Exception{
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
*/

}
