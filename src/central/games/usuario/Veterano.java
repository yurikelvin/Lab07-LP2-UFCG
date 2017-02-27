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


	public int recompensar(Usuario usuario, String nomeJogo) throws Exception{
		Jogo jogo = usuario.getJogo(nomeJogo);
		
		HashSet<Jogabilidade> estilos = jogo.getJogabilidade();
		Iterator<Jogabilidade> it = estilos.iterator();
		int recompensa = 0;
		while(it.hasNext()) {
			Jogabilidade estilo = it.next();
			if(estilo.getValor().equals("online")) {
				recompensa += 10;
				
			} else if(estilo.getValor().equals("cooperativo")) {
				recompensa += 20; 

			}
		}
		
		return recompensa;
		
	}

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
				totalX2p -= 20;
				
			} else if(estilo.getValor().equals("competitivo")) {
				punicao += (totalX2p - 20 >= 0) ? -20: 0; 
				totalX2p -= 20;
				
			}
		
		
		}
		
		return punicao;
	}




	
	

}
