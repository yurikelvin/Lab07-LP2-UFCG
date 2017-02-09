package central.games.usuario;

import java.util.HashSet;
import java.util.Iterator;

import central.games.jogo.Jogo;
import validacao.Validacao;

public abstract class Usuario {
	
	private String nome;
	private String login;
	private double qtdDinheiroDisponivel;
	private int x2p;
	
	public static final String FIM_DE_LINHA = System.lineSeparator();

	
	private HashSet<Jogo> meusJogos;
	
	public Usuario(String nome, String login) throws Exception {
		
		Validacao.validaString(nome, "Nome de usuario nao pode ser vazio ou nulo");
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo");
		
		this.nome = nome;
		this.login = login;
		this.qtdDinheiroDisponivel = 0.0;
		this.meusJogos = new HashSet<>();
		this.x2p = 0;

	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		
		Validacao.validaString(nome, "Nome de usuario nao pode ser vazio ou nulo");
		
		this.nome = nome;
	}

	public String getLogin() {
		return login;
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
	
	public boolean compraJogo(Jogo jogoAComprar) throws Exception {

		Validacao.validaObj(jogoAComprar, "Jogo nao pode ser nulo");
		
		if(this.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco())) {
			this.descontaDinheiro(jogoAComprar.getPreco());
			return this.adicionaJogo(jogoAComprar);
		}
		
		return false;
	}
	
	public boolean adicionaJogo(Jogo jogoAAdicionar) {
		
		return meusJogos.add(jogoAAdicionar);
	}
	
	public HashSet<Jogo> showGames() {
		return meusJogos;
	}
	
	public void adicionaX2p(int x2p) {
		this.x2p += (x2p < 0) ? 0 : x2p;
	}
	
	public int getX2p() {
		return this.x2p;
	}
	
	public void registraJogada(String nomeDoJogo, int score, boolean zerou) throws Exception{
		
		Validacao.validaString(nomeDoJogo, "Nome do jogo nao pode ser nulo ou vazio");
		Validacao.validaInt(score, "Score nao pode ser negativo");

		adicionaX2p(this.getJogo(nomeDoJogo).registraJogada(score, zerou));
	}
	
	public Jogo getJogo(String nomeDoJogo) throws Exception{

		Validacao.validaString(nomeDoJogo, "Nome do jogo nao pode ser nulo ou vazio");
		
		Iterator<Jogo> it = meusJogos.iterator();
		while(it.hasNext()) {
			Jogo jogoAProcurar  = it.next();
			if(jogoAProcurar.getNome().equals(nomeDoJogo)) {
				return jogoAProcurar;
			}
		}
		throw new Exception("Jogo nao encontrado");
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
