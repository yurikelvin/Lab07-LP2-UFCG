package central.games.jogo;

/**
 *  Classe responsavel por criar as instancias de Jogo.
 *  
 * @author Yuri Silva
 *
 */

public class FactoryDeJogo {
	
	private static final String RPG = "rpg";
	private static final String PLATAFORMA = "plataforma";
	private static final String LUTA = "luta";

	public Jogo criaJogo(String nome, int preco, String tipo, String estilos) throws Exception{
		
		
		switch(tipo.toLowerCase()) {
			case RPG:
				return this.criaJogoRpg(nome, preco, estilos);
			case PLATAFORMA:
				return this.criaJogoPlataforma(nome, preco, estilos);
			case LUTA:
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
