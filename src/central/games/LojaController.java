package central.games;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogo;
import central.games.usuario.Usuario;
import central.games.usuario.Veterano;
import exception.ValidacaoException;
import validacao.Validacao;



public class LojaController {
	
	private HashSet<Usuario> meusUsuarios;
	
	private static final String FIM_DE_LINHA = System.lineSeparator();

	public LojaController() {
		meusUsuarios = new HashSet<>();
	}
	

	public void adicionaUsuario(String nome, String login) throws ValidacaoException{
			


	}
	

	
	public Usuario getUsuario(String login) throws ValidacaoException, MissingResourceException {

	}
	

	
	public void addFundos(String login, int valor) throws ValidacaoException, MissingResourceException {
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo.");

		this.getUsuario(login).depositaDinheiro(valor);
	
	}
	

	public boolean compraJogo(String login, Jogo jogo) throws ValidacaoException, MissingResourceException{
		
		return this.getUsuario(login).compraJogo(jogo);
	}
	

	
	public boolean upgrade(String login) throws ValidacaoException, MissingResourceException{
		Validacao.validaString(login);
		
		Veterano novoVeterano = new Veterano("Veterano001", "Veterano01");
		Usuario usuarioASerPromovido = this.getUsuario(login);
		if(usuarioASerPromovido.getClass() != novoVeterano.getClass()) {
			if(getX2p(login) >= 1000) {

				novoVeterano.setNome(usuarioASerPromovido.getNome());
				novoVeterano.setLogin(usuarioASerPromovido.getLogin());
				novoVeterano.adicionaX2p(usuarioASerPromovido.getX2p());
				novoVeterano.setJogos(usuarioASerPromovido.getJogos());
				novoVeterano.depositaDinheiro(usuarioASerPromovido.getQtdDinheiroDisponivel());
				meusUsuarios.remove(usuarioASerPromovido);

				
				return meusUsuarios.add(novoVeterano);
			}
			
			throw new MissingResourceException("X2p insuficiente.", "Experiencia", "x2p");
		}
		
		throw new ValidacaoException("Usuario ja se encontra como Veterano.");
		
	}
	

	public void registraJogada(String loginUsuario, String nomeDoJogo, int score, boolean zerou) throws ValidacaoException, MissingResourceException {
		Usuario usuarioARegistrar = this.getUsuario(loginUsuario);
		
		usuarioARegistrar.registraJogada(nomeDoJogo, score, zerou);
		
		
	}
	
	public double getSaldo(String loginUsuario) throws ValidacaoException{
		return getUsuario(loginUsuario).getQtdDinheiroDisponivel();
	}
	
	public double getX2p(String loginUsuario) throws ValidacaoException {
		
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
