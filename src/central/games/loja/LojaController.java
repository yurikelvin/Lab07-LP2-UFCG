package central.games.loja;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.FactoryDeJogo;
import central.games.jogo.FactoryDeJogoTest;
import central.games.jogo.Jogo;
import central.games.usuario.FactoryDeUsuario;
import central.games.usuario.Usuario;
import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Classe responsavel por gerenciar Usuarios e adminstrar os jogos.
 * 
 * @author Yuri Silva
 *
 */

public class LojaController {
	
	private HashSet<Usuario> meusUsuarios;
	
	private static final String FIM_DE_LINHA = System.lineSeparator();
	private FactoryDeUsuario fUser;
	private FactoryDeJogo fJogo;
	


	public LojaController() {
		
		meusUsuarios = new HashSet<>();
		fUser = new FactoryDeUsuario();
		fJogo = new FactoryDeJogo();

	}
	
	/**
	 *  Cria um novo Usuario ao sistema. Cada usuario eh identificado pelo login.
	 *  Usuario inicializa com a carteira de dinheiro vazia, com a categoria Noob , com uma biblioteca de Jogos vazia e com a quantidade de x2p 0.
	 *  
	 *  
	 * @param nome Nome do usuario a ser criado.
	 * @param login Login do novo usuario a ser criado.
	 * @param tipo Categoria do usuario, se Noob ou Veterano, por convencao, todos comecam Noob.
	 * @throws ValidacaoException Caso os parametros passados sejam nulo ou invalido.
	 */

	public void criaUsuario(String nome, String login, String tipo) throws ValidacaoException {
		
		Validacao.validaString(nome, login, tipo, "Dados para criacao de usuario invalido.");

		Usuario novoUsuario = fUser.criaUsuario(nome, login, tipo);
		
		meusUsuarios.add(novoUsuario);


	}
	
	/**
	 *  Cria um jogo para ser eventualmente vendido ao Usuario. Cada jogo eh identificado pelo nome.
	 *  Jogo so eh criado se o tipo especificado for valido, tipos validos: RPG, PLATAFORMA, LUTA (contem ignore case)
	 *  E se o estilo do jogo (modalidade) for valido(eh comparado com o enum de {@link Jogabilidade} ).
	 * @param nome Nome do Jogo.
	 * @param preco Preco do jogo.
	 * @param tipo Tipo do jogo a ser criado (RPG, LUTA ou PLATAFORMA).
	 * @param estilo Modalidade do jogo, se online, offline, multiplayer, cooperativo, competitivo.
	 * @return
	 * @throws Exception Se o tipo do jogo for diferente de (RPG,LUTA,PLATAFORMA) ou modalidade diferente.
	 */
	
	private Jogo criaJogo(String nome, int preco, String tipo, String estilo) throws Exception {
		
		return fJogo.criaJogo(nome, preco, tipo, estilo);
	}
	

	
	private Usuario getUsuario(String login) throws ValidacaoException, MissingResourceException {
		
		Iterator<Usuario> it = meusUsuarios.iterator();
		while(it.hasNext()) {
			Usuario usuarioProcurado = it.next();
			if(usuarioProcurado.getLogin().equals(login)) {
				return usuarioProcurado;
			}
		}
		
		throw new MissingResourceException("Usuario nao encontrado.", "User not found", "Usuario");
	}
	
	/**
	 * Todo usuario tem uma carteira para comprar Jogos na loja.
	 * Adiciona creditos a carteira do Usuario.
	 * 
	 * @param login Login do Usuario.
	 * @param valor Valor a ser incrementado na conta.
	 * @throws ValidacaoException Se login ou valor for nulo/vazio.
	 * @throws MissingResourceException Se usuario nao for encontrado.
	 */
	
	public void addFundos(String login, int valor) throws ValidacaoException, MissingResourceException {
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo.");
		Validacao.validaInt(valor, "Quantidade a ser depositada precisa ser positiva.");

		this.getUsuario(login).depositaDinheiro(valor);
	
	}
	
	/**
	 * Vende um determinado jogo para um usuario identificado pelo login.
	 * 
	 * @param nome Nome do jogo.
	 * @param preco Preco do jogo.
	 * @param jogabilidade Modalidade do jogo, se online, offline, multiplayer, cooperativo, competitivo.
	 * @param estiloDoJogo Tipo do jogo, se RPG,PLATAFORMA ou LUTA
	 * @param login Login do Usuario que vai comprar o jogo.
	 * @throws ValidacaoException Se nome, preco, jogabilidade e estiloDoJogo forem nulo/vazio/negativo.
	 * @throws Exception Se o usuario nao for encontrado no sistema.
	 */

	public void vendeJogo(String nome, int preco, String jogabilidade, String estiloDoJogo, String login) throws Exception, ValidacaoException{

		Validacao.validaString(nome, jogabilidade, estiloDoJogo, login, "Dados para venda de jogo invalido.");
		Validacao.validaInt(preco, "Venda de jogo com preco invalido.");
		
		
		Jogo jogoASerVendido = this.criaJogo(nome, preco, estiloDoJogo, jogabilidade);
		 this.getUsuario(login).compraJogo(jogoASerVendido);
	}
	
	/**
	 * Altera a categoria do usuario caso ele tenha mais de 1000 x2p. De noob para Veterano.
	 * Usuario passa a ganhar bonus de x2p na compra de jogo, descontos na hora da compra e outras vantagens.
	 * @param login Login do Usuario a ser realizado a promocao.
	 * @throws ValidacaoException Se login for nulo/vazio
	 * @throws Exception Se o usuario nao for encontrado no sistema.
	 */
	
	public void upgrade(String login) throws Exception, ValidacaoException {
		
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo.");
		
		this.getUsuario(login).upgradeCategoria();
	}
	
	/**
	 * Registra uma jogada do Usuario no jogo em questao. Pune de acordo com as modalidades do jogo e da categoria do Usuario.
	 * {@link Usuario#punir(String, int, boolean)}
	 * @param login Login do Usuario
	 * @param nomeDoJogo Nome do jogo a registrar a jogada.
	 * @param score Score obitdo.
	 * @param zerou Se zerou.
	 * @throws ValidacaoException Se login, nomeDoJogo, score for nulo/negativo
	 * @throws Exception Se Usuario nao for encontrado no sistema.
	 */
	
	public void punir(String login, String nomeDoJogo, int score, boolean zerou) throws ValidacaoException, Exception{
		
		Validacao.validaString(login, nomeDoJogo, "Login ou Nome do Jogo invalido.");
		
		
		this.getUsuario(login).punir(nomeDoJogo, score, zerou);
	}
	
	/**
	 * Registra uma jogada do Usuario no jogo em questao. Recompensa de acordo com as modalidades do jogo e da categoria do Usuario.
	 * {@link Usuario#recompensar(String, int, boolean)}
	 * @param login Login do Usuario
	 * @param nomeDoJogo Nome do jogo a registrar a jogada.
	 * @param score Score obitdo.
	 * @param zerou Se zerou.
	 * @throws ValidacaoException Se login, nomeDoJogo, score for nulo/negativo
	 * @throws Exception Se Usuario nao for encontrado no sistema.
	 */
	
	public void recompensar(String login, String nomeDoJogo, int score, boolean zerou) throws Exception, ValidacaoException{
		
		Validacao.validaString(login, nomeDoJogo, "Login ou Nome do Jogo invalido.");
		
		this.getUsuario(login).recompensar(nomeDoJogo, score, zerou);
	}
	
	/**
	 * Retorna a quantidade de dinheiro disponivel na carteira do Usuario.
	 * 
	 * @param loginUsuario Login do Usuario
	 * @return A quantidade de dinheiro disponivel na carteira do usuario.
	 * @throws ValidacaoException Se loginUsuario for nulo/vazio.
	 * @throws Exception Se o usuario nao for encontrado.
	 */

	public double getSaldo(String loginUsuario) throws ValidacaoException, Exception{
		
		Validacao.validaString(loginUsuario);
		
		return getUsuario(loginUsuario).getQtdDinheiroDisponivel();
	}
	
	/**
	 * X2p significa experiencia dentro do jogo.
	 * A cada forma que o usuario compra um jogo, pune/recompensa de modo a registrar uma jogada, o jogador ganha experiencia que podem ser utilizadas para evoluir a categoria do Usuario.
	 * Evoluindo a categoria do Usuario de forma que ele ganhe beneficios por ter uma quantidade de experiencia alta.
	 * @param loginUsuario Login do Usuario.
	 * @return A quantidade de experiencia do Usuario.
	 * @throws ValidacaoException Se loginUsuario for nulo/vazio
	 * @throws Exception Se o usuario nao for encontrado no sistema.
	 */
	
	public int getX2p(String loginUsuario) throws ValidacaoException, Exception {
		
		Validacao.validaString(loginUsuario);
		
		return getUsuario(loginUsuario).getX2p();
	}
	
	@Override
	public String toString() {
		String loja = "=== Central P2-CG ===" + FIM_DE_LINHA;
		for(Usuario usuarios: meusUsuarios) {
			loja += usuarios;
		}
					
		return loja;
		
	}

}
