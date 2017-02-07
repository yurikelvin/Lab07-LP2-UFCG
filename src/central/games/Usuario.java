package central.games;

import java.util.HashSet;

public abstract class Usuario {
	
	private String nome;
	private String login;
	private double qtdDinheiroDisponivel;

	
	private HashSet<Jogo> meusJogos;
	
	public Usuario(String nome, String login) throws Exception {
		
		if(nome == null || nome.equals("")) {
			throw new Exception("Nome de usuario nao pode ser vazio ou nulo");
		}
		
		if(login == null || login.equals("")) {
			throw new Exception("Login de usuario nao pode ser vazio ou nulo");
		}
		
		this.nome = nome;
		this.login = login;
		this.qtdDinheiroDisponivel = 0.0;
		this.meusJogos = new HashSet<>();

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
	
	public abstract boolean compraJogo(Jogo jogoAComprar);	
	
	public boolean adicionaJogo(Jogo jogoAAdicionar) {
		return meusJogos.add(jogoAAdicionar);
	}
	
	public HashSet<Jogo> showGames() {
		return meusJogos;
	}
	
	
	
	
	

}
