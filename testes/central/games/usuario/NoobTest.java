package central.games.usuario;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import central.games.jogo.Jogo;
import central.games.jogo.Luta;
import central.games.jogo.Plataforma;
import central.games.jogo.RPG;
import exception.ValidacaoException;

public class NoobTest {
	
	private Noob meuNoob;
	
	@Before
	public void setUp() throws Exception{
		meuNoob = new Noob("Thiaguinho", "Aster Griffin");
		meuNoob.depositaDinheiro(1000);
	}

	@Test
	public void testCompraJogo() throws Exception {
		RPG gdw = new RPG("God of war", 30);
		assertTrue(meuNoob.compraJogo(gdw));
		// Testando desconto de 10% sobre a compra.
		assertEquals(973.0, meuNoob.getQtdDinheiroDisponivel(), 0.01);
		assertTrue(gdw.equals(meuNoob.getJogo("God of war")));
		
	}
	
	@Test
	public void testeCompraJogoWithExceptions() throws Exception {
		
		meuNoob.descontaDinheiro(1000);
		
		// Teste comprando jogo com dinheiro insuficiente.
		try {
			
			RPG gdw = new RPG("God of war", 30);
			meuNoob.depositaDinheiro(20);
			meuNoob.compraJogo(gdw);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Dinheiro insuficiente", e.getMessage());
		}
		// Teste comprando duas vezes o jogo.
		try {
			meuNoob.depositaDinheiro(100);
			RPG gdw = new RPG("God of war", 30);
			meuNoob.compraJogo(gdw);
			meuNoob.compraJogo(gdw);
		}catch(Exception e) {
			assertEquals("Usuario ja possui este jogo.", e.getMessage());
		}
		
		// Teste jogo nulo.
		
		try {
			meuNoob.compraJogo(null);
		}catch(Exception e) {
			assertEquals("Jogo nao pode ser nulo", e.getMessage());
		}
		
	}

	@Test
	public void testNoob() throws Exception{
		Noob newbie = new Noob("Jon Snow", "nightWatch's");
		assertEquals(0, newbie.getX2p());
	}
	
	@Test
	public void testNoobWithException() throws Exception {
		
		// testa login e nome de usuario nulo/vazio
		try {
			Noob newbie = new Noob("Danerys", null);
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
		try {
			Noob newbie = new Noob("Bob Sponja", "");
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
		try {
			Noob newbie = new Noob(null, "loginDeTeste");
		}catch(Exception e) {
			assertEquals("Nome de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
		try {
			Noob newbie = new Noob("", "loginDeTeste");
		}catch(Exception e) {
			assertEquals("Nome de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
	}


	@Test
	public void testUsuario() throws Exception {
		Usuario testeHeranca = new Noob("Arnold", "arn0ld");
		assertEquals(0, testeHeranca.getX2p());
	}

	@Test
	public void testSetLogin() {
		meuNoob.setLogin("l0gin");
		assertEquals("l0gin", meuNoob.getLogin());
	}

	@Test
	public void testGetNome() {
		assertEquals("Thiaguinho", meuNoob.getNome());
	}

	@Test
	public void testSetNome() throws Exception{
		meuNoob.setNome("Marvel");
		assertEquals("Marvel", meuNoob.getNome());
	}
	
	@Test
	public void testSetNomeWithException() throws Exception{
		
		try {
			meuNoob.setNome("");
		}catch(Exception e) {
			assertEquals("Nome de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
		try {
			meuNoob.setNome(null);
		}catch(Exception e) {
			assertEquals("Nome de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
	}

	@Test
	public void testGetLogin() {
		assertEquals("Aster Griffin", meuNoob.getLogin());
	}

	@Test
	public void testGetQtdDinheiroDisponivel() {
		assertEquals(1000.0, meuNoob.getQtdDinheiroDisponivel(), 0.01);
	}

	@Test
	public void testDepositaDinheiro() {
		
		meuNoob.depositaDinheiro(1000.0);
		assertEquals(2000.0, meuNoob.getQtdDinheiroDisponivel(), 0.001);
		
		meuNoob.depositaDinheiro(- 500);
		assertEquals(2000.0, meuNoob.getQtdDinheiroDisponivel(), 0.001);
		
	}
	

	@Test
	public void testDescontaDinheiro() {
		
		meuNoob.descontaDinheiro(500);
		assertEquals(500.0, meuNoob.getQtdDinheiroDisponivel(), 0.001);
		
		meuNoob.descontaDinheiro(300);
		assertEquals(200.0, meuNoob.getQtdDinheiroDisponivel(), 0.001);
		
		meuNoob.descontaDinheiro(-500);
		assertEquals(200.0, meuNoob.getQtdDinheiroDisponivel(), 0.001);		
	}

	@Test
	public void testAdicionaJogo() throws Exception {
		RPG ff = new RPG("Final Fantasy", 30);
		assertTrue(meuNoob.adicionaJogo(ff));
		assertEquals(ff, meuNoob.getJogo("Final Fantasy"));
		
	}

	@Test
	public void testAdicionaX2p() {
		assertEquals(0, meuNoob.getX2p());
		meuNoob.adicionaX2p(500);
		assertEquals(500, meuNoob.getX2p());
		
		meuNoob.adicionaX2p(- 500);
		assertEquals(500, meuNoob.getX2p());
	}

	@Test
	public void testGetX2p() {
		assertEquals(0, meuNoob.getX2p());
		
		meuNoob.adicionaX2p(500);
		
		assertEquals(500, meuNoob.getX2p());
		
		meuNoob.adicionaX2p(- 500);
		assertEquals(500, meuNoob.getX2p());
	}

	@Test
	public void testRegistraJogada() throws Exception {
		
		meuNoob.adicionaJogo(new RPG("Final Fantasy", 30));
		
		meuNoob.registraJogada("Final Fantasy", 500, true);
		assertEquals(10, meuNoob.getX2p());
		
		meuNoob.adicionaJogo(new Plataforma("Crash bandicoot", 30));
		meuNoob.registraJogada("Crash bandicoot", 2000, true);
		
		assertEquals(30, meuNoob.getX2p());
		
		meuNoob.adicionaJogo(new Luta("The King of Fighters", 30));
		meuNoob.registraJogada("The King of Fighters", 2000, true);
		
		assertEquals(32, meuNoob.getX2p());
		
		meuNoob.registraJogada("Crash bandicoot", 3200, false);
		
		assertEquals(32, meuNoob.getX2p());
		
		
		
		
	}

	@Test
	public void testGetJogo() throws Exception {
		RPG ff = new RPG("Final Fantasy", 30);
		meuNoob.adicionaJogo(ff);
		assertEquals(ff, meuNoob.getJogo("Final Fantasy"));
	}

	@Test
	public void testGetJogos() throws Exception{
		RPG ff = new RPG("Final Fantasy", 30);
		RPG gdw = new RPG("God of War", 50);
		RPG residentEvil7 = new RPG("Resident Evil 7", 80);
		meuNoob.adicionaJogo(ff);
		meuNoob.adicionaJogo(residentEvil7);
		meuNoob.adicionaJogo(gdw);
		
		HashSet<Jogo> testeJogosUsuario = meuNoob.getJogos();
		assertTrue(testeJogosUsuario.contains(ff));
		assertTrue(testeJogosUsuario.contains(gdw));
		assertTrue(testeJogosUsuario.contains(residentEvil7));
	}

	@Test
	public void testEquals() throws Exception {
		Noob newbie = new Noob("Temara", "Croste1r");
		assertFalse(meuNoob.equals(newbie));
		Noob novoNoob = new Noob("Crashter", "kopler");
		assertFalse(meuNoob.equals(novoNoob));
		assertFalse(newbie.equals(novoNoob));
		assertFalse(newbie.equals(meuNoob));
		assertFalse(novoNoob.equals(newbie));
		assertTrue(meuNoob.equals(meuNoob));
		assertTrue(novoNoob.equals(novoNoob));
		
		Noob meuNewbie = new Noob("Thiaguinho lima", "Aster Griffin");
		assertTrue(meuNoob.equals(meuNewbie));
		assertFalse(meuNewbie.equals(novoNoob));
		
	}

}
