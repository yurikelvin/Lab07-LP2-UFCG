package central.games.usuario;

import java.util.HashSet;

import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.FactoryDeJogo;
import central.games.jogo.Jogabilidade;
import central.games.jogo.Jogo;
import central.games.jogo.RPG;
import exception.ValidacaoException;
import validacao.Validacao;



public class Usuario {
	
	private String nome;
	private String login;
	private double qtdDinheiroDisponivel;
	private int    x2p;

	
	public static final String FIM_DE_LINHA = System.lineSeparator();

	
	private HashSet<Jogo> meusJogos;
	private Categoria minhaCategoria;
	

	
	public Usuario(String nome, String login) {
		
			
		this.nome = nome;
		this.login = login;
		this.qtdDinheiroDisponivel = 0.0;
		this.meusJogos = new HashSet<>();
		this.x2p = 0;

		minhaCategoria = new Noob();


	}
	
	public void setLogin(String login) {
		this.login = login;
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

	public double getQtdDinheiroDisponivel() {
		return qtdDinheiroDisponivel;
	}
	
	/**
	 * Incrementa dinheiro na conta do Usuario, pode incrementar 0 se valor for menor que zero.
	 * @param valor Valor a ser depositado.
	 */

	public void depositaDinheiro(double valor) {
		this.qtdDinheiroDisponivel += (valor < 0) ? 0 : valor;
	}
	
	/**
	 * Decremeta dinheiro na conta do Usuario, pode decrementar 0 se valor for menor que zero.
	 * @param valor Valor a ser descontado.
	 */
	
	public void descontaDinheiro(double valor) {
		this.qtdDinheiroDisponivel -= (valor < 0) ? 0 : valor;
	}
	

	public boolean compraJogo(Jogo jogoAComprar) throws ValidacaoException, MissingResourceException {
		if(getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco() * minhaCategoria.getDesconto())) { // chamada polimorfica
			if(!temJogo(jogoAComprar)) {
				descontaDinheiro(jogoAComprar.getPreco() * minhaCategoria.getDesconto()); // chamada polimorfica
				adicionaX2p(minhaCategoria.bonusNaCompraX2p() * jogoAComprar.getPreco()); // chamada polimorfica
			//	this.upgradeCategoria();
				return adicionaJogo(jogoAComprar);
			}
			throw new ValidacaoException("Usuario ja possui este jogo.");
		}
		
		throw new MissingResourceException("Dinheiro insuficiente", "Usuario", "Preco");
	}


	
	private boolean adicionaJogo(Jogo jogoAAdicionar) {
		
		return meusJogos.add(jogoAAdicionar);
	}
	
	
	public void adicionaX2p(int x2p) {
		this.x2p += x2p;
	}
	
	public int getX2p() {
		return this.x2p;
	}
	
	public void recompensar(String nomeDoJogo, int score, boolean zerou) throws Exception{
		
		Jogo jogoARegistrar = this.getJogo(nomeDoJogo);
		int x2pAcumulada = jogoARegistrar.registraJogada(score, zerou); // chamada polimorfica
		int recompensa = minhaCategoria.recompensar(this, nomeDoJogo); // chamada polimorfica
		this.adicionaX2p( x2pAcumulada + recompensa);
		
	//	this.upgradeCategoria();

	}
	

	
	public void punir(String nomeDoJogo, int score, boolean zerou) throws Exception{
		
		Jogo jogoARegistrar = this.getJogo(nomeDoJogo);
		int x2pAcumulada = jogoARegistrar.registraJogada(score, zerou); // chamada polimorfica
		int punicao = minhaCategoria.punir(this, nomeDoJogo); // chamada polimorfica

		this.adicionaX2p( x2pAcumulada + punicao);
		
		this.downgradeCategoria();

	}
	
	public boolean temJogo(Jogo jogo) {
		return meusJogos.contains(jogo);
	}
	
	/**
	 * Retorna um Jogo com base no nome.
	 * 
	 * @param nomeDoJogo Nome do JOgo
	 * @return O Jogo procurado.
	 * @throws MissingResourceException Se o jogo nao for encontrado.
	 */
	
	public Jogo getJogo(String nomeDoJogo) throws MissingResourceException, ValidacaoException{

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
	
	public void setJogos(HashSet<Jogo> jogos) {
		this.meusJogos = jogos;
	}
	
	public boolean upgradeCategoria() {
		if(this.getX2p() <= 1000) {
			return false;
		}
		
		Noob noobTeste = new Noob();
		if(minhaCategoria.getClass() == noobTeste.getClass()) {
			minhaCategoria = new Veterano();

			return true;
			
		}
		return false;
	}
	
	private boolean downgradeCategoria() {
		
		if(this.getX2p() > 1000) {
			return false;
		}
		
		Veterano veteranoTeste = new Veterano();
		if(minhaCategoria.getClass() == veteranoTeste.getClass()) {
			minhaCategoria = new Noob();
			return true;
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getLogin() == null) ? 0 : this.getLogin().hashCode());
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
		if (this.getLogin() == null) {
			if (other.getLogin() != null)
				return false;
		} else if (!this.getLogin().equals(other.getLogin()))
			return false;
		return true;
	}
	


	@Override
	public String toString() {
		String noob = FIM_DE_LINHA + "Jogador " + minhaCategoria.representacao() + // chamada polimorfica
						": " + getLogin() + FIM_DE_LINHA +
						getNome() + " - " + getX2p() + " x2p"+ FIM_DE_LINHA +
						"Lista de Jogos:";
		int totalPreco = 0;
		
		for(Jogo jogosObtidos: getJogos()) {
			noob += jogosObtidos + FIM_DE_LINHA;
			totalPreco += jogosObtidos.getPreco();
		}
		
		noob += "Total de preco dos jogos: R$ " + totalPreco + ",00" + FIM_DE_LINHA +
				"--------------------------------------------";
		return noob;
	}
	
	
}
