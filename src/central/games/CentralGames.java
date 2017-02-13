package central.games;

import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;

import central.games.jogo.Jogo;
import central.games.usuario.Usuario;
import central.games.usuario.Veterano;
import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Classe responsavel por gerenciar Usuarios e Jogos. 
 * @author Yuri Silva
 *
 */

public class CentralGames {
	
	private HashSet<Usuario> meusUsuarios;
	
	private static final String FIM_DE_LINHA = System.lineSeparator();

	public CentralGames() {
		meusUsuarios = new HashSet<>();
	}
	
	/**
	 *  Adiciona um usuario ao sistema.
	 * @param usuario
	 * @throws ValidacaoException se Usuario ja for cadastrado ou nulo.
	 */
	public void adicionaUsuario(Usuario usuario) throws ValidacaoException{
		
		Validacao.validaObj(usuario);
		
		boolean bemSucedido = meusUsuarios.add(usuario);
		if(!bemSucedido) {
			throw new ValidacaoException("Usuario ja cadastrado.");
		}
		
	}
	
	/**
	 * Retorna um usuario cadastrado no sistema com o login especificado.
	 * @param login
	 * @return
	 * @throws ValidacaoException Se o login for nul oou vazio.
	 * @throws MissingResourceException Se o usuario nao existir.
	 */
	
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
	
	/**
	 * Adiciona dinheiro a conta de um usuario cadastrado no sistema. Adiciona 0 se valor for menor que 0.
	 * @param login
	 * @param valor
	 * @throws ValidacaoException Se login for nulo ou vazio.
	 * @throws MissingResourceException Se o login nao tiver associado a nenhum Usuario no sistema.
	 */
	
	public void addFundos(String login, int valor) throws ValidacaoException, MissingResourceException {
		Validacao.validaString(login, "Login de usuario nao pode ser vazio ou nulo.");

		this.getUsuario(login).depositaDinheiro(valor);
	
	}
	
	/**
	 * Compra um determinado jogo com base em dinheiro disponivel do usuario.
	 * @see Usuario#compraJogo(Jogo)
	 */
	
	public boolean compraJogo(String login, Jogo jogo) throws ValidacaoException{
		
		return this.getUsuario(login).compraJogo(jogo);
	}
	
	/**
	 * Realiza a promocao de um usuario do tipo Noob para o tipo Veterano.
	 * 
	 * @param login
	 * @return true se bem sucedido
	 * @throws ValidacaoException Se o login for nulo ou invalido ou usuario ja se encontrar como Veterano.
	 * @throws MissingResourceException Se a experiencia do Usuario nao for suficiente para promocao.
	 */
	
	
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
