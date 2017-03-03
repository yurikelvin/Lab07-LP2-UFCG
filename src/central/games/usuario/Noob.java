package central.games.usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogabilidade;
import central.games.jogo.Jogo;
import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Classe que representa uma possivel categoria do Usuario.
 * @author Yuri Silva.
 *
 */

public class Noob implements Categoria {
	
	private static final int RECOMPENSA_OFFLINE = 30;
	private static final int RECOMPENSA_MULTIPLAYER = 10;
	
	private static final int PUNICAO_ONLINE = 10;
	private static final int PUNICAO_COMPETITIVO = 20;
	private static final int PUNICAO_COOPERATIVO = 50;


	@Override
	public String representacao() {
		
		return  "Noob";
	}
	
	/**
	 * Recompensa o Usuario Noob caso o jogo em questao seja "offline" ou "multiplayer"
	 */

	public int recompensar(Usuario usuario, String nomeJogo) throws Exception{
		Jogo jogo = usuario.getJogo(nomeJogo);
		
		HashSet<Jogabilidade> estilos = jogo.getJogabilidade();
		Iterator<Jogabilidade> it = estilos.iterator();
		int recompensa = 0;
		while(it.hasNext()) {
			Jogabilidade estilo = it.next();
			if(estilo.getValor().equals("offline")) {
				recompensa += RECOMPENSA_OFFLINE;
				
			} else if(estilo.getValor().equals("multiplayer")) {
				recompensa += RECOMPENSA_MULTIPLAYER; 

			}
		}
		
		return recompensa;
		
	}
	
	/**
	 * Pune um Usuario Noob caso o jogo em questao seja "online", "competitivo" e "cooperativo"
	 */

	public int punir(Usuario usuario, String nomeJogo) throws Exception{
		Jogo jogo = usuario.getJogo(nomeJogo);
		
		HashSet<Jogabilidade> estilos = jogo.getJogabilidade();
		Iterator<Jogabilidade> it = estilos.iterator();
		int totalX2p = usuario.getX2p();
		int punicao = 0;
		while(it.hasNext()) {
			Jogabilidade estilo = it.next();
			if(estilo.getValor().equals("online")) {
				punicao += (totalX2p - 10 >= 0) ? -10: 0; 
				totalX2p -= PUNICAO_ONLINE;
				
			} else if(estilo.getValor().equals("competitivo")) {
				punicao += (totalX2p - 20 >= 0) ? -20: 0; 
				totalX2p -= PUNICAO_COMPETITIVO;
				
			} else if(estilo.getValor().equals("cooperativo")) {
				punicao += (totalX2p - 50 >= 0) ? -50: 0;
				totalX2p -= PUNICAO_COOPERATIVO;
				
			}
		}
		
		return punicao;
	}


	@Override
	public int bonusNaCompraX2p() {
		return 10;
	}

	@Override
	public double getDesconto() {
		return 0.9;
	}


}
