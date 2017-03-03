package central.games.usuario;

import central.games.jogo.Jogo;

/**
 * Interface responsavel por fazer a caracterizacao de Tipos de Usuario.
 * 
 * @author Yuri Silva
 *
 */

public interface Categoria {
	
	/**
	 * Recompensa o Usuario com base nas modalidades do Jogo. Retorna a quantidade a ser recompensada.
	 * 
	 * @param usuario Usuario.
	 * @param nomeJogo Nome do Jogo.
	 * @return A quantidade a ser recompensada
	 * @throws Exception Caso haja erro na validacao.
	 */
	int recompensar(Usuario usuario, String nomeJogo) throws Exception;
	
	/**
	 * Pune o Usuario com base nas modalidades do Jogo. Retorna a quantidade a ser punida.
	 * @param usuario Usuario.
	 * @param nomeJogo Nome do Jogo.
	 * @return A quantidade a ser punida.
	 * @throws Exception Caso haja erro na validacao.
	 */
	
	int punir(Usuario usuario, String nomeJogo) throws Exception;
	
	/**
	 * Representacao em String do tipo implementado de Usuario. Ex: Noob
	 * @return
	 */
	String representacao();
	
	
	int bonusNaCompraX2p();
	
	/**
	 * Cada usuario tem um desconto diferente, este desconto deve ser aplicado pelo valor * (desconto). Por exemplo valor * 0.9 (caso queira dar 10% de desconto).
	 * @return
	 */
	double getDesconto();


}
