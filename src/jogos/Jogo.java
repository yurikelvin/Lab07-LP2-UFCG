package jogos;

public abstract class Jogo {
	
	private String nome;
	private double preco;
	private int maiorScore;
	private int qtdZeradas;
	private Jogabilidade jogabilidade;
	
	public Jogo(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
		this.maiorScore = 0;
		this.qtdZeradas = 0;
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
	
	public void setJogabilidade(Jogabilidade jogabilidade) {
		this.jogabilidade = jogabilidade.OFFLINE;
	}
	
	public Jogabilidade getJogabilidade(Jogabilidade jogabilidade) {
		
	}
	
	public abstract int registraJogada(int score, boolean zerou);
	
	

}
