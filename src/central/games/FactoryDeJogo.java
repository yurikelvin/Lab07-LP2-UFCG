package central.games;

import central.games.jogo.Jogo;
import central.games.jogo.Luta;
import central.games.jogo.Plataforma;
import central.games.jogo.RPG;

public class FactoryDeJogo {

	public Jogo criaJogo(String nome, int preco, String tipo, String estilos) throws Exception{
		
		switch(tipo.toLowerCase()) {
			case "rpg":
				return this.criaJogoRpg(nome, preco, estilos);
			case "plataforma":
				return this.criaJogoPlataforma(nome, preco, estilos);
			case "luta":
				return this.criaJogoLuta(nome, preco, estilos);
			
			default:
				throw new Exception("Tipo de jogo nao existente.");
		}
	}

	
	private Jogo criaJogoRpg(String nome, int preco, String estilos) throws Exception{
		
		RPG novoRpg = new RPG(nome, preco);
		
		this.adicionaEstilos(novoRpg, estilos);

		return novoRpg;
	}
	
	private Jogo criaJogoPlataforma(String nome, int preco, String estilos) throws Exception {
		Plataforma novaPlataforma = new Plataforma(nome, preco);
		
		this.adicionaEstilos(novaPlataforma, estilos);
		
		return novaPlataforma;
	}
	
	private Jogo criaJogoLuta(String nome, int preco, String estilos) throws Exception {
		Luta novaLuta = new Luta(nome, preco);
		
		this.adicionaEstilos(novaLuta, estilos);
		
		return novaLuta;
	}
	
	private void adicionaEstilos(Jogo jogoAAdicionarEstilo, String estilos) throws Exception {
		String[] estilo = estilos.trim().split(" ");
		for(String e: estilo) {
			jogoAAdicionarEstilo.adicionaJogabilidade(e);
		}

	}
}
