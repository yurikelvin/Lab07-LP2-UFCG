package central.games;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Classe responsavel por moldar os tipos de Jogo.
 *
 * @author Yuri Kelvin Moura Sousa e Silva
 */

public abstract class Jogo {
	
	private String nome;
	private double preco;
	private int maiorScore;
	private int qtdZeradas;
	private HashSet<Jogabilidade> jogabilidade;
	
	/** 
	 * Cria um novo Jogo com o nome e preco especificado.
	 * @param nome  o nome do Jogo.
	 * @param preco  o preco do Jogo.
	 * @throws Exception  Se nome for nulo ou vazio ou preco for menor ou igual a 0.
	 */
	
	public Jogo(String nome, double preco) throws Exception{
		if(nome == null || nome.equals("")) {
			throw new Exception("Nome do jogo nao pode ser nulo ou vazio");
		}
		if(preco <= 0) {
			throw new Exception("Preco nao pode ser negativo");
		}
		
		this.nome = nome;
		this.preco = preco;
		this.maiorScore = 0;
		this.qtdZeradas = 0;
		this.jogabilidade = new HashSet<>();
	}
	/**
	 * Retorna o nome deste Jogo.
	 * @return o nome do Jogo.
	 */

	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna o preco deste Jogo.
	 * @return o preco do Jogo
	 */
	public double getPreco() {
		return this.preco;
	}
	
	/**
	 * Modifica o preco atual do Jogo.
	 * @param preco  novo preco a ser definido.
	 * @throws Exception  Se preco for menor que zero.
	 */

	public void setPreco(double preco) throws Exception {
		if(preco < 0) {
			throw new Exception("Preco tem que ser maior que zero");
		}
		
		this.preco = preco;
	}
	
	/**
	 * Retorna a maior pontuacao atual feita pelos jogadores dentro do Jogo.
	 * 
	 * @return a maior pontuacao.
	 */

	public int getMaiorScore() {
		return this.maiorScore;
	}
	
	/**
	 * Modifica a maior pontuacao atual feita pelos jogadores dentro do Jogo.
	 * 
	 * @param maiorScore  maior pontuacao feita.
	 * @throws Exception  se maiorScore for menor que zero.
	 */

	public void setMaiorScore(int maiorScore) throws Exception{
		if(maiorScore < 0) {
			throw new Exception("Score precisa ser maior que 0");
		}
		
		this.maiorScore = maiorScore;
		
	}
	
	/**
	 * Retorna a quantidade de vezes que este Jogo ja foi concluido.
	 * 
	 * @return a quantidade de vezes que este Jogo ja foi concluido.
	 */

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
	
	/**
	 * Cada Jogo possui um agrupamento que descreve sua jogabilidade.
	 *  Adiciona jogabilidade ao Jogo.
	 * 
	 * @param jogabilidade  Estilo de jogo.
	 * @return Se a jogabilidade foi adicionada com sucesso ao Jogo.
	 * @throws Exception  Se jogabilidade for nulo.
	 */
	
	public boolean adicionaJogabilidade(Jogabilidade jogabilidade) throws Exception {
		if(jogabilidade == null) {
			throw new Exception("Tipo de jogabilidade nao pode ser nulo");
		}
		return this.jogabilidade.add(jogabilidade);
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
	 * @throws Exception Se score for menor que zero.
	 */
	
	public abstract int registraJogada(int score, boolean zerou) throws Exception;

	@Override
	public String toString() {
		return "Jogo [nome=" + nome + ", preco=" + preco + ", maiorScore=" + maiorScore + ", qtdZeradas=" + qtdZeradas
				+ ", jogabilidade=" + jogabilidade + "]";
	}
	
	

}
