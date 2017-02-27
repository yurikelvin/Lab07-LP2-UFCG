package central.games.usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogabilidade;
import central.games.jogo.Jogo;
import exception.ValidacaoException;
import validacao.Validacao;


public class Noob implements Categoria {
	



	@Override
	public String representacao() {
		
		return  "Noob";
	}

	public int recompensar(Usuario usuario, String nomeJogo) throws Exception{
		Jogo jogo = usuario.getJogo(nomeJogo);
		
		HashSet<Jogabilidade> estilos = jogo.getJogabilidade();
		Iterator<Jogabilidade> it = estilos.iterator();
		int recompensa = 0;
		while(it.hasNext()) {
			Jogabilidade estilo = it.next();
			if(estilo.getValor().equals("offline")) {
				recompensa += 30;
				
			} else if(estilo.getValor().equals("multiplayer")) {
				recompensa += 10; 

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
			if(estilo.getValor().equals("online")) {
				punicao += (totalX2p - 10 >= 0) ? -10: 0; 
				totalX2p -= 10;
				
			} else if(estilo.getValor().equals("competitivo")) {
				punicao += (totalX2p - 20 >= 0) ? -20: 0; 
				totalX2p -= 20;
				
			} else if(estilo.getValor().equals("cooperativo")) {
				punicao += (totalX2p - 50 >= 0) ? -50: 0;
				totalX2p -= 50;
				
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
