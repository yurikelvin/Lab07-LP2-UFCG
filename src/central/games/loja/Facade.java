package central.games.loja;

import java.util.MissingResourceException;

import easyaccept.EasyAccept;
import exception.ValidacaoException;

/**
 * Classe responsavel por servir de Facade entre o Usuario e a implementacao.
 * 
 * @author Yuri Silva
 *
 */

public class Facade {

	private LojaController meuSistema;
	
	
	public Facade() {
		meuSistema = new LojaController();
	}
	
	/**
	 * Cria um Usuario identificando o seu nome, seu login e sua categoria.
	 * {@link LojaController#criaUsuario(String, String, String)}
	 * @param nome Nome do usuario.
	 * @param login Login do Usuario.
	 * @param tipo Categoria do usuario, se Noob ou Veterano. (IMPORTANTE: TODOS COMECAM COMO NOOB)
	 * @throws Exception Caso nao consiga criar o usuario.
	 */
	
	public void criaUsuario(String nome, String login, String tipo) throws Exception {
		meuSistema.criaUsuario(nome, login, tipo);
	}
	
	/**
	 *  Adiciona dinheiro a carteira do Usuario, identificando seu login.
	 *  {@link LojaController#addFundos(String, int)}
	 * @param login Login do usuario.
	 * @param valor Valor a ser creditado.
	 * @throws ValidacaoException Caso nao seja possivel validar o usuario ou valor.
	 * @throws MissingResourceException Caso nao seja possivel encontrar o usuario.
	 */

	public void adicionaCredito(String login, int valor) throws ValidacaoException, MissingResourceException {
		meuSistema.addFundos(login, valor);
	}
	
	/**
	 * Vende um determinado jogo dado nome, preco, jogabilidade (ONLINE, MULTIPLAYER, COMPETITIVO, COOPERATIVO, OFFLINE) e estilo RPG/PLATAFORMA ou LUTA.
	 * Caso nao siga os pre-requesitos acima nao eh possivel vender este jogo.
	 * {@link LojaController#vendeJogo(String, int, String, String, String)}
	 * @param nome Nome do Jogo.
	 * @param preco Preco do jogo a ser vendido.
	 * @param jogabilidade Modalidade do jogo: (ONLINE, MULTIPLAYER, COMPETITIVO, COOPERATIVO, OFFLINE).
	 * @param estiloDoJogo Se o jogo eh do tipo RPG/PLATAFORMA ou LUTA
	 * @param login Login do Usuario que manifesta o interesse de comprar o jogo.
	 * @throws Exception Caso nao seja possivel vender o jogo ao usuario ou jogo invalido.
	 */

	public void vendeJogo(String nome, int preco, String jogabilidade, String estiloDoJogo, String login)
			throws Exception {
		meuSistema.vendeJogo(nome, preco, jogabilidade, estiloDoJogo, login);
	}
	
	/**
	 * Existem duas categorias de Usuario: Noob ou veterano. 
	 * Realiza a promocao de categoria de um Usuario de Noob para Veterano, caso o usuario tenha mais de 1000 x2p necessaria.
	 * {@link Facade#getX2p(String)}
	 * {@link LojaController#upgrade(String)}
	 * @param login Login do Usuario.
	 * @throws Exception Caso o usuario nao tenha os requesitos ou nao seja encontrado.
	 */

	public void upgrade(String login) throws Exception {
		meuSistema.upgrade(login);
	}
	
	/**
	 * Registra uma jogada feita no Jogo. Pune de acordo com a modalidade do Jogo baseado na categoria do Usuario.
	 * Pode adicionar experiencia extra de acordo com o registraJogada de cada jogo.
	 * {@link LojaController#punir(String, String, int, boolean)}
	 * @param login Login do Usuario.
	 * @param nomeDoJogo Nome do Jogo a ser feito o registro.
	 * @param score Score obtido.
	 * @param zerou Se zerou.
	 * @throws ValidacaoException Caso as informacoes passadas estejam invalidas.
	 * @throws Exception Caso o jogo nao seja encontrado com o Usuario ou o usuario nao seja encontrado.
	 */

	public void punir(String login, String nomeDoJogo, int score, boolean zerou) throws ValidacaoException, Exception {
		meuSistema.punir(login, nomeDoJogo, score, zerou);
	}
	
	/**
	 * Registra uma jogada feita no Jogo. Recompensa de acordo com a modalidade do Jogo baseado na categoria do Usuario.
	 * Pode adicionar experiencia extra de acordo com o registraJogada de cada jogo.
	 * @param login Login do Usuario.
	 * @param nomeDoJogo Nome do Jogo a ser feito o registro.
	 * @param score Score obtido.
	 * @param zerou Se zerou.
	 * @throws ValidacaoException Caso as informacoes passadas estejam invalidas.
	 * @throws Exception Caso o jogo nao seja encontrado com o Usuario ou o usuario nao seja encontrado.
	 */

	public void recompensar(String login, String nomeDoJogo, int score, boolean zerou) throws Exception, ValidacaoException {
		meuSistema.recompensar(login, nomeDoJogo, score, zerou);
	}
	
	/**
	 * Retorna a quantidade de dinheiro disponivel na carteira do Usuario.
	 * {@link LojaController#getSaldo(String)}
	 * @param loginUsuario Login do Usuario.
	 * @return A quantidade de dinheiro disponivel na carteira do Usuario.
	 * @throws ValidacaoException Caso o login passado seja invalido.
	 * @throws Exception Se o usuario nao for encontrado.
	 */

	public double confereCredito(String loginUsuario) throws ValidacaoException, Exception {
		return meuSistema.getSaldo(loginUsuario);
	}
	
	/**
	 * X2p eh uma experiencia dada aos Usuarios quando os mesmos realizam uma compra ou registram uma jogada.
	 * Dependendo do tipo de Categoria sao dadas x2p diferentes.
	 * Retorna a quantidade de x2p (experiencia) do usuario.
	 * 
	 * {@link LojaController#getX2p(String)}
	 * @param loginUsuario Login do Usuario.
	 * @return A quantidade de x2p do usuario.
	 * @throws ValidacaoException Caso o login passado seja nulo/vazio.
	 * @throws Exception Caso o login do usuario nao esteja cadastrado no sistema.
	 */

	public int getX2p(String loginUsuario) throws ValidacaoException, Exception {
		return meuSistema.getX2p(loginUsuario);
	}
	
	/**
	 * Representacao em String de todos usuarios cadastrados com seus respectivos jogos / experiencia acumulada e categoria associada ao usuario.
	 * @return Representacao em string de todo o sistema.
	 */
	
	public String mostraSistema() {
		return meuSistema.toString();
	}


	public static void main(String[] args) {
		args = new String[] {"central.games.loja.Facade", "acceptance_test/us1.txt", "acceptance_test/us2.txt", "acceptance_test/us3.txt"};
		EasyAccept.main(args);
	}
	
}
