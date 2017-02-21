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

	private LojaController meuSistema;
	
	
	public Loja() {

		meuSistema = new LojaController();
	}
	

	public void adicionaUsuario(String nome, String login) {
		try {
			meuSistema.adicionaUsuario(nome, login);
		}catch(ValidacaoException e) {
			System.out.println("Nome ou login invalido.");
		}catch(MissingResourceException e) {
			System.out.println("Usuario ja cadastrado.");
		}

	}
	
	public Usuario getUsuario(String login) {
		return meuSistema.getUsuario(login);
	}
	
	public void adicionaDinheiro(String login, int valor) {
		meuSistema.addFundos(login, valor);
	}

	public boolean vendeJogo(String login, Jogo jogo) throws ValidacaoException, MissingResourceException {
		return meuSistema.compraJogo(login, jogo);
	}
	
	/**
	 * Transforma o usuario Noob em Veterano.
	 * 
	 * {@link LojaController#upgrade(String)}
	 * 
	 * @param login Login do Usuario
	 * @return true se bem sucedido.
	 * @throws ValidacaoException Se o login for nulo ou invalido ou usuario ja se encontrar como Veterano.
	 * @throws MissingResourceException Se a experiencia do Usuario nao for suficiente para promocao.
	 */

	public boolean upgrade(String login) throws ValidacaoException, MissingResourceException {
		return meuSistema.upgrade(login);
	}
	
	
	public String toString() {
		return meuSistema.toString();
	}




}
