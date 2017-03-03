package central.games.usuario;

/**
 *  Classe responsavel por criar instancias de Usuario.
 * @author Yuri Silva
 *
 */

public class FactoryDeUsuario {
	

	public Usuario criaUsuario(String nome, String login, String tipo) {
		return new Usuario(nome, login);
		
	}
	

	


}
