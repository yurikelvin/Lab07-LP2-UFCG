package central.games.jogo;

import java.util.HashSet;

import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Classe responsavel por moldar os tipos de Jogo.
 *
 * @author Yuri Silva
 */

public abstract class Jogo {
	
	private String nome;
	private int preco;
	private int maiorScore;
	private int qtdZeradas;
	private int qtdJogadas;
	private HashSet<Jogabilidade> jogabilidade;
	
	public static final String FIM_DE_LINHA = System.lineSeparator();
	
	
	/** 
	 * Construtor que serve de base para as subclasses criarem um Jogo com os parametros especificados..
	 * @param nome  o nome do Jogo.
	 * @param preco  o preco do Jogo.
	 * @throws ValidacaoException  Se nome for nulo ou vazio ou preco for menor ou igual a 0.
	 */
	
	public Jogo(String nome, int preco) throws ValidacaoException{
		
		Validacao.validaString(nome, "Nome do jogo nao pode ser nulo ou vazio");
		Validacao.validaInt(preco, "Preco do jogo nao pode ser negativo");

		this.nome = nome;
		this.preco = preco;
		this.maiorScore = 0;
		this.qtdZeradas = 0;
		this.jogabilidade = new HashSet<>();
	}

	public String getNome() {
		return this.nome;
	}

	public int getPreco() {
		return this.preco;
	}
	
	/**
	 * Modifica o preco atual do Jogo.
	 * @param preco  novo preco a ser definido.
	 * @throws ValidacaoException  Se preco for menor que zero.
	 */

	public void setPreco(int preco) throws ValidacaoException {
		
		Validacao.validaInt(preco, "Preco do jogo nao pode ser negativo");
		
		this.preco = preco;
	}
	

	public int getMaiorScore() {
		return this.maiorScore;
	}
	
	/**
	 * Modifica a maior pontuacao atual feita pelos jogadores dentro do Jogo.
	 * 
	 * @param maiorScore  maior pontuacao feita.
	 * @throws ValidacaoException  se maiorScore for menor que zero.
	 */

	public void setMaiorScore(int maiorScore) throws ValidacaoException{

		Validacao.validaInt(maiorScore, "Score precisa ser maior que 0");
		
		this.maiorScore = maiorScore;
		
	}
	

	public int getQtdZeradas() {
		return this.qtdZeradas;
	}
	
	/**
	 * Incrementa um a quantidade de vezes que o jogo foi zerado.
	 *
	 */

	public void zerou() {
		this.qtdZeradas ++;
	}
	
	public int getQtdJogadas() {
		return qtdJogadas;
	}
	public void jogou() {
		this.qtdJogadas ++;
	}

	
	public boolean adicionaJogabilidade(String estilo) throws ValidacaoException {
		
		for(Jogabilidade j: Jogabilidade.values()) {
			if(estilo.equalsIgnoreCase(j.getValor())) {
				jogabilidade.add(j);
				return true;
			}
		}
		
		throw new ValidacaoException("Estilo nao valido.");
	}

	/**
	 * Cada Jogo possui um agrupamento que descreve sua jogabilidade.
	 * Retorna um os tipos de jogabilidade do Jogo.
	 * @return Os tipos de jogabilidade do Jogo.
	 */
	
	public HashSet<Jogabilidade> getJogabilidade() {
		return this.jogabilidade;
	}
	/**
	 * Registra uma jogada feita pelo jogador no Jogo.
	 * Retorna uma bonificacao pelo jogador ter Jogado o jogo.
	 * 
	 * @param score pontuacao feita pelo Jogador.
	 * @param zerou caso o jogador tenha finalizado o Jogo.
	 * @return Uma bonificacao pelo jogador ter Jogado o jogo.
	 * @throws ValidacaoException Se score for menor que zero.
	 */
	
	public abstract int registraJogada(int score, boolean zerou) throws ValidacaoException;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getNome() == null) ? 0 : this.getNome().hashCode());
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
		Jogo other = (Jogo) obj;
		if (this.getNome() == null) {
			if (other.getNome() != null)
				return false;
		} else if (!this.getNome().equals(other.getNome()))
			return false;
		return true;
	}


	
	

}
