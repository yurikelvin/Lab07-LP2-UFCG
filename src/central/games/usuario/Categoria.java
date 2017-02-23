package central.games.usuario;

import central.games.jogo.Jogo;

public interface Categoria {
	
	boolean compraJogo(Jogo jogoAComprar) throws Exception;
	String toString();

}
