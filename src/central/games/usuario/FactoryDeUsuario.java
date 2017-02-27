package central.games.usuario;

public class FactoryDeUsuario {
	

	public Usuario criaUsuario(String nome, String login, String tipo) {
		return new Usuario(nome, login);
		
	}
	

	


}
