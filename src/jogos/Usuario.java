package jogos;

import java.util.HashSet;

public class Usuario {
	
	private String nome;
	private String login;
	private double qtdDinheiroDisponivel;
	
	private HashSet<Jogo> meusJogos;
	
	public Usuario(String nome, String login) {
		
		this.nome = nome;
		this.login = login;
		this.qtdDinheiroDisponivel = 0.0;
		this.meusJogos = new HashSet<>();
	}
	
	public boolean compraJogo(Jogo jogoAComprar) {
		if(this.getQtdDinheiroDisponivel() >= jogoAComprar.getPreco()) {
			this.descontaDinheiro(jogoAComprar.getPreco());
			return meusJogos.add(jogoAComprar);
		}
		
		return false;
		
		
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public double getQtdDinheiroDisponivel() {
		return qtdDinheiroDisponivel;
	}

	public void depositaDinheiro(double valor) {
		this.qtdDinheiroDisponivel += (valor < 0) ? 0 : valor;
	}
	
	public void descontaDinheiro(double valor) {
		this.qtdDinheiroDisponivel -= (valor < 0) ? 0 : valor;
	}
	
	
	
	
	
	
	

}
