package central.games;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogo;
import central.games.usuario.Usuario;
import exception.ValidacaoException;
import validacao.Validacao;

public class Loja {
	
	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	private HashSet<Usuario> meusUsuarios;
	
	public Loja() {
		this.meusUsuarios = new HashSet<>();
	}
	
	public boolean adicionarUsuario(Usuario user) {
		return meusUsuarios.add(user);
	}
	
	public Usuario getUsuario(String login) throws ValidacaoException, MissingResourceException {

		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo");
		
		Iterator<Usuario> it = meusUsuarios.iterator();
		while(it.hasNext()) {
			Usuario usuarioProcurado = it.next();
			if(usuarioProcurado.getLogin().equals(login)) {
				return usuarioProcurado;
			}
		}
		
		throw new MissingResourceException("Usuario inexistente", "Usuario", "login");
		
		
	}
	
	public void depositaDinheiro(String login, int valor) throws ValidacaoException {
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo");
		this.getUsuario(login).depositaDinheiro(valor);
	
	}
	
	public boolean vendeJogo(String login, Jogo jogoAComprar) throws ValidacaoException{
		
		return this.getUsuario(login).compraJogo(jogoAComprar);
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
