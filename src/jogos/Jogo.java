package jogos;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Jogo {
	
	private String nome;
	private double preco;
	private int maiorScore;
	private int qtdZeradas;
	private HashSet<Jogabilidade> jogabilidade;
	
	public Jogo(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
		this.maiorScore = 0;
		this.qtdZeradas = 0;
		this.jogabilidade = new HashSet<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getMaiorScore() {
		return maiorScore;
	}

	public void setMaiorScore(int maiorScore) {
		this.maiorScore = maiorScore;
	}

	public int getQtdZeradas() {
		return qtdZeradas;
	}

	public void zerou() {
		this.qtdZeradas ++;
	}
	
	public boolean adicionaJogabilidade(Jogabilidade jogabilidade) {
		return this.jogabilidade.add(jogabilidade);
	}
	
	public boolean removeJogabilidade(Jogabilidade jogabilidade) {
		Iterator<Jogabilidade> it = this.jogabilidade.iterator();
		while(it.hasNext()) {
			Jogabilidade jogabilidadeAtual = it.next();
			if(jogabilidadeAtual == jogabilidade) {
				return true;
			}
		}
		
		return false;
	}
	
	public HashSet<Jogabilidade> getJogabilidade() {
		return this.jogabilidade;
	}
	
	public abstract int registraJogada(int score, boolean zerou);

	@Override
	public String toString() {
		return "Jogo [nome=" + nome + ", preco=" + preco + ", maiorScore=" + maiorScore + ", qtdZeradas=" + qtdZeradas
				+ ", jogabilidade=" + jogabilidade + "]";
	}
	
	

}
