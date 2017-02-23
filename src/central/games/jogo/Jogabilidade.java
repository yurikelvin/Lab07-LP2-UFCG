package central.games.jogo;

/**
 * Jogabilidades que podem ser atribuidas ao Jogo.
 * 
 * @author Yuri Silva.
 *
 */
public enum Jogabilidade {
	

	ONLINE("online"),
	OFFLINE("offline"),
	MULTIPLAYER("multiplayer"),
	COOPERATIVO("cooperativo"),
	COMPETITIVO("competitivo");
	
	private final String valor;

	
	Jogabilidade(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
	
	

}
