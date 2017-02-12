package central.games;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogo;
import central.games.usuario.Usuario;
import central.games.usuario.Veterano;
import exception.ValidacaoException;
import validacao.Validacao;

public class CentralGames {
	
	private HashSet<Usuario> meusUsuarios;
	
	private static final String FIM_DE_LINHA = System.lineSeparator();

	public CentralGames() {
		meusUsuarios = new HashSet<>();
	}
	
	public void adicionaUsuario(Usuario usuario) throws ValidacaoException{
		
		boolean bemSucedido = meusUsuarios.add(usuario);
		if(!bemSucedido) {
			throw new ValidacaoException("Usuario ja cadastrado.");
		}
		
	}
	
	public Usuario getUsuario(String login) throws ValidacaoException, MissingResourceException {

		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo.");
		
		Iterator<Usuario> it = meusUsuarios.iterator();
		while(it.hasNext()) {
			Usuario usuarioProcurado = it.next();
			if(usuarioProcurado.getLogin().equals(login)) {
				return usuarioProcurado;
			}
		}
		
		throw new MissingResourceException("Usuario inexistente.", "Usuario", "login");
		
		
	}
	
	public void addFundos(String login, int valor) throws ValidacaoException {
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo.");
		this.getUsuario(login).depositaDinheiro(valor);
	
	}
	
	public boolean compraJogo(String login, Jogo jogo) throws ValidacaoException{
		
		return this.getUsuario(login).compraJogo(jogo);
	}
	
	public boolean upgrade(String login) throws ValidacaoException, MissingResourceException{
		Validacao.validaString(login);
		
		Veterano novoVeterano = new Veterano("Veterano001", "Veterano01");
		Usuario usuarioASerPromovido = this.getUsuario(login);
		if(usuarioASerPromovido.getClass() != novoVeterano.getClass()) {
			if(usuarioASerPromovido.getX2p() >= 1000) {
				System.out.println(novoVeterano.getLogin());
				novoVeterano.setNome(usuarioASerPromovido.getNome());
				novoVeterano.setLogin(usuarioASerPromovido.getLogin());
				novoVeterano.adicionaX2p(usuarioASerPromovido.getX2p());
				meusUsuarios.remove(usuarioASerPromovido);
				
				System.out.println(novoVeterano.getLogin());
				
				return meusUsuarios.add(novoVeterano);
			}
			
			throw new MissingResourceException("X2p insuficiente.", "Experiencia", "x2p");
		}
		
		throw new ValidacaoException("Usuario ja se encontra como Veterano.");
		
	}
	
	@Override
	public String toString() {
		String loja = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;
		for(Usuario usuarios: meusUsuarios) {
			loja += usuarios;
		}
					
		return loja;
		
	}

}
