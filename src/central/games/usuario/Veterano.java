package central.games.usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogabilidade;
import central.games.jogo.Jogo;
import central.games.jogo.RPG;
import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Classe que representa um tipo de Usuario.
 * 
 * 
 * @author Yuri Silva
 *
 */

public class Veterano implements Categoria {
	
	private static final int RECOMPENSA_ONLINE = 10;
	private static final int RECOMPENSA_COOPERATIVO = 20;
	
	private static final int PUNICAO_OFFLINE = 20;
	private static final int PUNICAO_COMPETITIVO = 20;

	
	@Override
	public String representacao() {
		
		return  "Veterano";
	}

	@Override
	public int bonusNaCompraX2p() {

		return 15;
	}

	@Override
	public double getDesconto() {

		return 0.8;
	}

	/**
	 * Recompensa o Usuario Veterano caso o jogo em questao seja online ou cooperativo
	 */

	public int recompensar(Usuario usuario, String nomeJogo) throws Exception{
		Jogo jogo = usuario.getJogo(nomeJogo);
		
		HashSet<Jogabilidade> estilos = jogo.getJogabilidade();
		Iterator<Jogabilidade> it = estilos.iterator();
		int recompensa = 0;
		while(it.hasNext()) {
			Jogabilidade estilo = it.next();
			if(estilo.getValor().equals("online")) {
				recompensa += RECOMPENSA_ONLINE;
				
			} else if(estilo.getValor().equals("cooperativo")) {
				recompensa += RECOMPENSA_COOPERATIVO; 

			}
		}
		
		return recompensa;
		
	}
	
	/**
	 * Pune o usuario veterano caso o Jogo seja offline ou competitivo
	 */

	public int punir(Usuario usuario, String nomeJogo) throws Exception{
		Jogo jogo = usuario.getJogo(nomeJogo);
		
		HashSet<Jogabilidade> estilos = jogo.getJogabilidade();
		Iterator<Jogabilidade> it = estilos.iterator();
		int totalX2p = usuario.getX2p();
		int punicao = 0;
		while(it.hasNext()) {
			Jogabilidade estilo = it.next();
			if(estilo.getValor().equals("offline")) {
				punicao += (totalX2p - 20 >= 0) ? -20: 0; 
				totalX2p -= PUNICAO_OFFLINE;
				
			} else if(estilo.getValor().equals("competitivo")) {
				punicao += (totalX2p - 20 >= 0) ? -20: 0; 
				totalX2p -= PUNICAO_COMPETITIVO;
				
			}
		
		
		}
		
		return punicao;
	}




	
	

}
