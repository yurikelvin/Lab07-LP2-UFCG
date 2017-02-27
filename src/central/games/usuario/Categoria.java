package central.games.usuario;

import central.games.jogo.Jogo;

public interface Categoria {
	
	int recompensar(Usuario usuario, String nomeJogo) throws Exception;
	int punir(Usuario usuario, String nomeJogo) throws Exception;
	String representacao();
	int bonusNaCompraX2p();
	double getDesconto();


}
