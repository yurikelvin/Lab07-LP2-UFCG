package central.games.loja;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.FactoryDeJogo;
import central.games.jogo.Jogo;
import central.games.usuario.FactoryDeUsuario;
import central.games.usuario.Usuario;
import exception.ValidacaoException;
import validacao.Validacao;



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
	

	public void criaUsuario(String nome, String login, String tipo) throws ValidacaoException {
		
		Validacao.validaString(nome, login, tipo, "Dados para criacao de usuario invalido.");

		meusUsuarios.add(fUser.criaUsuario(nome, login, tipo));


	}
	
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
	

	
	public void addFundos(String login, int valor) throws ValidacaoException, MissingResourceException {
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo.");

		this.getUsuario(login).depositaDinheiro(valor);
	
	}
	

	public void vendeJogo(String nome, int preco, String jogabilidade, String estiloDoJogo, String login) throws Exception{

		Validacao.validaString(nome, jogabilidade, estiloDoJogo, login, "Dados para venda de jogo invalido.");
		Validacao.validaInt(preco, "Venda de jogo com preco invalido.");
		
		
		Jogo jogoASerVendido = this.criaJogo(nome, preco, estiloDoJogo, jogabilidade);
		 this.getUsuario(login).compraJogo(jogoASerVendido);
	}
	
	public void upgrade(String login) throws Exception {
		
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo.");
		
		this.getUsuario(login).upgradeCategoria();
	}
	
	public void punir(String login, String nomeDoJogo, int score, boolean zerou) throws ValidacaoException, Exception{
		
		Validacao.validaString(login, nomeDoJogo, "Login ou Nome do Jogo invalido.");
		
		
		this.getUsuario(login).punir(nomeDoJogo, score, zerou);
	}
	
	public void recompensar(String login, String nomeDoJogo, int score, boolean zerou) throws Exception{
		
		Validacao.validaString(login, nomeDoJogo, "Login ou Nome do Jogo invalido.");
		
		this.getUsuario(login).recompensar(nomeDoJogo, score, zerou);
	}

	public double getSaldo(String loginUsuario) throws ValidacaoException{
		
		Validacao.validaString(loginUsuario);
		
		return getUsuario(loginUsuario).getQtdDinheiroDisponivel();
	}
	
	public int getX2p(String loginUsuario) throws ValidacaoException {
		
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
