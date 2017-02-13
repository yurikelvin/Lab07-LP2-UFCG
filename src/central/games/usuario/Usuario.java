package central.games.usuario;

import java.util.HashSet;

import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogo;
import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Classe responsavel para servir de base para Tipos de Usuario.
 * 
 * @author Yuri Kelvin Moura Sousa e Silva
 *
 */

public abstract class Usuario {
	
	private String nome;
	private String login;
	private double qtdDinheiroDisponivel;
	private int x2p;
	
	public static final String FIM_DE_LINHA = System.lineSeparator();

	
	private HashSet<Jogo> meusJogos;
	
	/**
	 *  Construtor que serve de base para as subclasses criarem um novo tipo de Usuario.
	 * @param nome Nome do Usuario.
	 * @param login Login do Usuario.
	 * @throws ValidacaoException Se nome ou login forem nulo ou vazios 
	 */
	
	public Usuario(String nome, String login) throws ValidacaoException {
		
		Validacao.validaString(nome, "Nome de usuario nao pode ser vazio ou nulo");
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo");
		
		this.nome = nome;
		this.login = login;
		this.qtdDinheiroDisponivel = 0.0;
		this.meusJogos = new HashSet<>();
		this.x2p = 0;

	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}
	
	/**
	 * Define um novo nome para o Usuario.
	 * 
	 * @param nome
	 * @throws ValidacaoException Se usuario for vazio ou nulo.
	 */

	public void setNome(String nome) throws ValidacaoException {
		
		Validacao.validaString(nome, "Nome de usuario nao pode ser vazio ou nulo");
		
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public double getQtdDinheiroDisponivel() {
		return qtdDinheiroDisponivel;
	}
	
	/**
	 * Incrementa dinheiro na conta do Usuario, pode incrementar 0 se valor for menor que zero.
	 * @param valor
	 */

	public void depositaDinheiro(double valor) {
		this.qtdDinheiroDisponivel += (valor < 0) ? 0 : valor;
	}
	
	/**
	 * Decremeta dinheiro na conta do Usuario, pode decrementar 0 se valor for menor que zero.
	 * @param valor
	 */
	
	public void descontaDinheiro(double valor) {
		this.qtdDinheiroDisponivel -= (valor < 0) ? 0 : valor;
	}
	
	/**
	 *  Compra um determinado jogo, se o Usuario tiver dinheiro disponinvel.
	 * @param jogoAComprar
	 * @return true se a compra for bem sucedida.
	 * @throws ValidacaoException Se jogoAComprar for nulo.
	 * @throws MissingResourceException Se dinheiro para a compra for insuficiente.
	 */
	
	public boolean compraJogo(Jogo jogoAComprar) throws ValidacaoException, MissingResourceException {

		Validacao.validaObj(jogoAComprar, "Jogo nao pode ser nulo");
		
		if(this.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco())) {
			this.descontaDinheiro(jogoAComprar.getPreco());
			return this.adicionaJogo(jogoAComprar);
		}
		
		throw new MissingResourceException("Dinheiro insuficiente", "Usuario", "Preco");
	}
	
	public boolean adicionaJogo(Jogo jogoAAdicionar) {
		
		return meusJogos.add(jogoAAdicionar);
	}
	
	public HashSet<Jogo> showGames() {
		return meusJogos;
	}
	
	/**
	 * Soma experiencia ao Usuario, pode somar 0 se x2p for menor que zero.
	 * @param x2p experiencia a ser somada
	 */
	
	public void adicionaX2p(int x2p) {
		this.x2p += (x2p < 0) ? 0 : x2p;
	}
	
	public int getX2p() {
		return this.x2p;
	}
	
	/**
	 * @see Jogo#registraJogada(int, boolean)
	 * 
	 * @param nomeDoJogo
	 * @throws ValidacaoException Se nome do jogo for nulo ou vazio.
	 */
	
	public void registraJogada(String nomeDoJogo, int score, boolean zerou) throws ValidacaoException{
		
		Validacao.validaString(nomeDoJogo, "Nome do jogo nao pode ser nulo ou vazio");


		adicionaX2p(this.getJogo(nomeDoJogo).registraJogada(score, zerou));
	}
	
	/**
	 * Retorna um Jogo com base no nome.
	 * 
	 * @param nomeDoJogo
	 * @return O Jogo procurado.
	 * @throws MissingResourceException Se o jogo nao for encontrado.
	 * @throws ValidacaoException Se o nome do jogo for nulo ou vazio.
	 */
	
	public Jogo getJogo(String nomeDoJogo) throws MissingResourceException, ValidacaoException{

		Validacao.validaString(nomeDoJogo, "Nome do jogo nao pode ser nulo ou vazio");
		
		Iterator<Jogo> it = meusJogos.iterator();
		while(it.hasNext()) {
			Jogo jogoAProcurar  = it.next();
			if(jogoAProcurar.getNome().equals(nomeDoJogo)) {
				return jogoAProcurar;
			}
		}
		throw new MissingResourceException("Jogo nao encontrado", "Usuario", "Jogo");
	}
	
	public HashSet<Jogo> getJogos() {
		return this.meusJogos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	


	
	
	

}
