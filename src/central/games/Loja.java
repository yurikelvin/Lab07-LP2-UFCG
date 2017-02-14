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
	
	/** Adiciona um usuario no sistema.
	 * {@link CentralGames#adicionaUsuario(Usuario)}
	 * @param user Usuario a ser cadastrado no sistema.
	 * @throws ValidacaoException Se usuario for nulo ou ja for cadastrado.
	 */

	public void adicionaUsuario(Usuario user) throws ValidacaoException{
		meuSistema.adicionaUsuario(user);
	}
	
	/**
	 * Retorna um usuario do sistema.
	 * 
	 * {@link CentralGames#getUsuario(String)}
	 * 
	 * @param login Login do usuario
	 * @return true se bem sucedida.
	 * @throws ValidacaoException Se login for nulo.
	 * @throws MissingResourceException Se o Usuario com o login nao for encontrado.
	 */

	public Usuario getUsuario(String login) throws ValidacaoException, MissingResourceException {
		return meuSistema.getUsuario(login);
	}
	
	/**
	 * Deposita dinheiro a conta do usuario. Deposita 0 se valor for menor que zero.
	 * {@link CentralGames#addFundos(String, int)}
	 * @param login Login do usuario.
	 * @param valor Valor a ser depositado.
	 * @throws ValidacaoException Se login for nulo ou vazio.
	 * @throws MissingResourceException Se o login nao tiver associado a nenhum Usuario no sistema.
	 */

	public void adicionaDinheiro(String login, int valor) throws ValidacaoException, MissingResourceException {
		meuSistema.addFundos(login, valor);
	}

	/**
	 * Vende um jogo a um Usuario.
	 * 
	 * {@link CentralGames#compraJogo(String, Jogo)}
	 * @param login Login do usuario.
	 * @param jogo Jogo a ser vendido.
	 * @return true se bem sucedido
	 * @throws ValidacaoException Se login for nulo ou invalido.
	 * @throws MissingResourceException Se usuario nao for encontrado.
	 */
	
	public boolean vendeJogo(String login, Jogo jogo) throws ValidacaoException, MissingResourceException {
		return meuSistema.compraJogo(login, jogo);
	}
	
	/**
	 * Transforma o usuario Noob em Veterano.
	 * 
	 * {@link CentralGames#upgrade(String)}
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
