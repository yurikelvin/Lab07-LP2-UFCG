package central.games;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import central.games.jogo.Luta;
import central.games.jogo.Plataforma;
import central.games.jogo.RPG;
import central.games.usuario.Noob;
import central.games.usuario.Usuario;
import central.games.usuario.Veterano;
import exception.ValidacaoException;
import junit.framework.Assert;

public class CentralGamesTest {
	
	private LojaController meuSistema;

	@Before
	public void setUp() throws Exception {
		meuSistema = new LojaController();
		
		Veterano tsubakker = new Veterano("Yuri", "Tsubakker");
		Noob slnsn = new Noob("Sol", "SLNSN");
		
		RPG ff = new RPG("Final Fantasy", 30);
		RPG gdw = new RPG("God of War", 30);
		
		Luta mortalKombat = new Luta("Mortal Kombat", 30);
		Luta tkf = new Luta("The King of Fighters", 30);
		
		Plataforma crashb = new Plataforma("Crash bandicoot", 30);
		Plataforma sony = new Plataforma("Sony", 30);
		Plataforma mario = new Plataforma("Super mario", 30);
		
		meuSistema.adicionaUsuario(tsubakker);
		meuSistema.adicionaUsuario(slnsn);
		
		meuSistema.addFundos("Tsubakker", 1000);
		meuSistema.addFundos("SLNSN", 1000);
		
		meuSistema.compraJogo("Tsubakker", ff);
		meuSistema.compraJogo("Tsubakker", gdw);
		meuSistema.compraJogo("Tsubakker", tkf);
		meuSistema.compraJogo("Tsubakker", crashb);
		meuSistema.compraJogo("Tsubakker", mortalKombat);
		meuSistema.compraJogo("Tsubakker", sony);
		
		meuSistema.compraJogo("SLNSN", mario);

	}

	@Test
	public void testAdicionaUsuarioEGetUsuario() throws Exception {
		
		Noob novato = new Noob("Tekol", "Tesla");
		Veterano vetera = new Veterano("Morpa", "Mirror");
		meuSistema.adicionaUsuario(vetera);
		meuSistema.adicionaUsuario(novato);
		assertEquals(novato, meuSistema.getUsuario("Tesla"));
		assertEquals(vetera, meuSistema.getUsuario("Mirror"));
		
	}
	
	@Test
	public void testAdicionaUsuarioEGetUsuarioWithExceptions() throws Exception {
		
		// Testando adicionar usuario nulo
		
		try {
			meuSistema.adicionaUsuario(null);
		}catch(Exception e) {
			assertEquals("Usuario nao pode ser nulo.", e.getMessage());
		}
		// Testando pegar usuario nulo/vazio ou ja inexistente.
		try {
			meuSistema.getUsuario(null);
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo.", e.getMessage());
		}
		
		try {
			meuSistema.getUsuario("");
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo.", e.getMessage());
		}
		
		try {
			meuSistema.getUsuario("Fulano");
		}catch(Exception e) {
			assertEquals("Usuario inexistente.", e.getMessage());
		}
	}

	@Test
	public void testAddFundos() throws Exception {
		Noob novato = new Noob("Tekol", "Tesla");
		meuSistema.adicionaUsuario(novato);
		meuSistema.addFundos("Tesla", 1500);
		assertEquals(1500, meuSistema.getSaldo("Tesla"), 0.001);
	}

	@Test
	public void testCompraJogo() throws Exception{
		RPG fallout4 = new RPG("Fallout 4", 30);
		meuSistema.compraJogo("SLNSN", fallout4);
		assertEquals(946, meuSistema.getSaldo("SLNSN"), 0.1);
		assertTrue(meuSistema.getUsuario("SLNSN").temJogo(fallout4));
		
		meuSistema.compraJogo("Tsubakker", fallout4);
		assertEquals(832, meuSistema.getSaldo("Tsubakker"), 0.1);
		assertTrue(meuSistema.getUsuario("Tsubakker").temJogo(fallout4));
		
	}
	
	@Test
	public void testCompraJogoWithException() throws Exception{
		RPG fallout4 = new RPG("Fallout 4", 1500);
		RPG clearWave = new RPG("Clear Wave", 30);
		
		// teste com usuario que nao possui saldo suficiente
		try {
			meuSistema.compraJogo("Tsubakker", fallout4);
			fail();
		}catch(Exception e) {
			assertEquals("Dinheiro insuficiente", e.getMessage());
		}
		// teste com jogo sendo nulo
		try {
			meuSistema.compraJogo("Tsubakker", null);
			fail();
		}catch(Exception e) {
			assertEquals("Jogo nao pode ser nulo", e.getMessage());
		}
		// teste com login do usuario sendo vazio/nulo
		try {
			meuSistema.compraJogo("", clearWave);
			fail();
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo.", e.getMessage());
		}
		
		try {
			meuSistema.compraJogo(null, clearWave);
			fail();
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo.", e.getMessage());
		}
		
	}

	@Test
	public void testUpgrade() throws ValidacaoException {
		
		// Teste parte em que o usuario ainda continua como Noob
		assertEquals(300, meuSistema.getX2p("SLNSN"), 0.1);
		
		Plataforma sonyr = new Plataforma("Sony Run", 69); 
		RPG tw3 = new RPG("The Witcher 3", 100);
		meuSistema.compraJogo("SLNSN", sonyr);
		meuSistema.compraJogo("SLNSN", tw3);
		assertEquals(990, meuSistema.getX2p("SLNSN"), 0.1);
		
		// Teste se usuario noob contem os jogos comprados.
		assertTrue(meuSistema.getUsuario("SLNSN").temJogo(sonyr));
		assertTrue(meuSistema.getUsuario("SLNSN").temJogo(tw3));
		// Teste tentando dar upgrade em usuario com x2p insuficiente.
		try {
			meuSistema.upgrade("SLNSN");
			fail();
		}catch(Exception e) {
			assertEquals("X2p insuficiente.", e.getMessage());
		}
		
		meuSistema.compraJogo("SLNSN", new Plataforma("Mega", 1));
		Noob testeNoob = new Noob("Teste classe", "novonoob");
		assertTrue(meuSistema.getUsuario("SLNSN").getClass() == testeNoob.getClass());
		assertTrue(meuSistema.upgrade("SLNSN")); // Upgrade de Noob para Veterano
		assertFalse(meuSistema.getUsuario("SLNSN").getClass() == testeNoob.getClass());
		// Teste se Usuario virou veterano
		Veterano testeVeterano = new Veterano("Teste veterano", "vetera");
		assertTrue(meuSistema.getUsuario("SLNSN").getClass() == testeVeterano.getClass());
		
		// Teste de upgrade de Usuario que ja se encontra como Veterano
		try {
			meuSistema.upgrade("SLNSN");
			fail();
		}catch(Exception e) {
			assertEquals("Usuario ja se encontra como Veterano.", e.getMessage());
		}
		// Teste se o Usuario quando se transforma em veterano continua com os jogos.
		assertTrue(meuSistema.getUsuario("SLNSN").temJogo(sonyr));
		assertTrue(meuSistema.getUsuario("SLNSN").temJogo(tw3));
	}

	@Test
	public void testRegistraJogadaWithException() throws ValidacaoException{
		try {
			meuSistema.registraJogada(null, "Matrix", 1500, true);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			meuSistema.registraJogada("", "Matrix", 15000, false);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
	}
	

}
