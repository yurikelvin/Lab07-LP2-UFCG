package central.games;

import java.util.HashSet;
import java.util.Iterator;

public class Loja {
	
	private HashSet<Usuario> meusUsuarios;
	
	public Loja() {
		this.meusUsuarios = new HashSet<>();
	}
	
	public boolean vendeJogo(String login ) {
		
	}
	
	public boolean adicionarUsuario(Usuario user) {
		return meusUsuarios.add(user);
	}
	
	public void depositaDinheiro(String login, int valor) {
		
	}
	
	public Usuario getUsuario(String login) throws Exception {
		if(login == null || login.equals("")) {
			throw new Exception("Login nao pode ser nulo ou vazio");
		}
		Iterator<Usuario> it = meusUsuarios.iterator();
		while(it.hasNext()) {
			Usuario usuarioProcurado = it.next();
			if(usuarioProcurado.getLogin().equals(login)) {
				return usuarioProcurado;
			}
		}
		
		throw new Exception("Usuario com este login nao existe");
		
		
	}

}
