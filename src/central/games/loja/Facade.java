package central.games.loja;

import java.util.MissingResourceException;

import easyaccept.EasyAccept;
import exception.ValidacaoException;


public class Facade {

	private LojaController meuSistema;
	
	
	public Facade() {
		meuSistema = new LojaController();
	}
	
	
	public void criaUsuario(String nome, String login, String tipo) throws Exception {
		meuSistema.criaUsuario(nome, login, tipo);
	}

	public void adicionaCredito(String login, int valor) throws ValidacaoException, MissingResourceException {
		meuSistema.addFundos(login, valor);
	}

	public void vendeJogo(String nome, int preco, String jogabilidade, String estiloDoJogo, String login)
			throws Exception {
		meuSistema.vendeJogo(nome, preco, jogabilidade, estiloDoJogo, login);
	}

	public void upgrade(String login) throws Exception {
		meuSistema.upgrade(login);
	}

	public void punir(String login, String nomeDoJogo, int score, boolean zerou) throws ValidacaoException, Exception {
		meuSistema.punir(login, nomeDoJogo, score, zerou);
	}

	public void recompensar(String login, String nomeDoJogo, int score, boolean zerou) throws Exception {
		meuSistema.recompensar(login, nomeDoJogo, score, zerou);
	}

	public double confereCredito(String loginUsuario) throws ValidacaoException {
		return meuSistema.getSaldo(loginUsuario);
	}

	public int getX2p(String loginUsuario) throws ValidacaoException {
		return meuSistema.getX2p(loginUsuario);
	}


	public static void main(String[] args) {
		args = new String[] {"central.games.loja.Facade", "acceptance_test/us1.txt", "acceptance_test/us2.txt", "acceptance_test/us3.txt"};
		EasyAccept.main(args);
	}
	
}
