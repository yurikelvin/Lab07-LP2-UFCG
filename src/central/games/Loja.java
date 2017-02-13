package central.games;

import java.util.HashSet;

import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogo;
import central.games.usuario.Usuario;
import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Esta classe fornece um acesso Facade a Central Games.
 * 
 * @author Yuri Silva
 *
 */

public class Loja {

	private CentralGames meuSistema;
	
	public Loja() {

		meuSistema = new CentralGames();
	}

	public void adicionaUsuario(Usuario user) throws ValidacaoException{
		meuSistema.adicionaUsuario(user);
	}

	public Usuario getUsuario(String login) throws ValidacaoException, MissingResourceException {
		return meuSistema.getUsuario(login);
	}

	public void adicionaDinheiro(String login, int valor) throws ValidacaoException {
		meuSistema.addFundos(login, valor);
	}

	public boolean vendeJogo(String login, Jogo jogo) throws ValidacaoException {
		return meuSistema.compraJogo(login, jogo);
	}

	public boolean upgrade(String login) throws ValidacaoException, MissingResourceException {
		return meuSistema.upgrade(login);
	}

	public String toString() {
		return meuSistema.toString();
	}




}
