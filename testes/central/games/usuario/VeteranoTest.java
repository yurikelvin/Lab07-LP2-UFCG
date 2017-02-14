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

public class VeteranoTest {
	
	private Veterano veterano;
	
	@Before
	public void setUp() throws Exception {
		veterano = new Veterano("Yuri", "Tsuoka");
		veterano.depositaDinheiro(1000);
	}

	@Test
	public void testCompraJogo() throws Exception{
		RPG gdw = new RPG("God of war", 30);
		assertTrue(veterano.compraJogo(gdw));
		// testando desconto de 20% sobre a compra.
		assertEquals(976.0, veterano.getQtdDinheiroDisponivel(), 0.01);
		assertTrue(gdw.equals(veterano.getJogo("God of war")));
	}
	
	@Test
	public void testeCompraJogoWithExceptions() throws Exception {
		
		veterano.descontaDinheiro(1000);
		
		// Teste comprando jogo com dinheiro insuficiente.
		try {
			
			RPG gdw = new RPG("God of war", 30);
			veterano.depositaDinheiro(20);
			veterano.compraJogo(gdw);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Dinheiro insuficiente", e.getMessage());
		}
		// Teste comprando duas vezes o jogo.
		try {
			veterano.depositaDinheiro(100);
			RPG gdw = new RPG("God of war", 30);
			veterano.compraJogo(gdw);
			veterano.compraJogo(gdw);
		}catch(Exception e) {
			assertEquals("Usuario ja possui este jogo.", e.getMessage());
		}
		
		// Teste jogo nulo.
		
		try {
			veterano.compraJogo(null);
		}catch(Exception e) {
			assertEquals("Jogo nao pode ser nulo", e.getMessage());
		}
		
	}

	@Test
	public void testVeterano() throws Exception {
		Veterano vetera = new Veterano("Jon Snow", "KingInTheN0rth");
		assertEquals(1000, vetera.getX2p());
	}
	
	@Test
	public void testVeteranoWithException() throws Exception {
		
		// testa login e nome de usuario nulo/vazio
		try {
			Veterano vetera = new Veterano("Danerys", null);
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
		try {
			Veterano vetera = new Veterano("Bob Sponja", "");
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
		try {
			Veterano vetera = new Veterano(null, "loginDeTeste");
		}catch(Exception e) {
			assertEquals("Nome de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
		try {
			Veterano vetera = new Veterano("", "loginDeTeste");
		}catch(Exception e) {
			assertEquals("Nome de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
	}

	@Test
	public void testUsuario() throws Exception {
		Usuario testeHeranca = new Veterano("Arnold", "arn0ld");
		assertEquals(1000, testeHeranca.getX2p());
	}

	@Test
	public void testSetLogin() {
		veterano.setLogin("l0gin");
		assertEquals("l0gin", veterano.getLogin());;
	}

	@Test
	public void testGetNome() {
		assertEquals("Yuri", veterano.getNome());
	}


	@Test
	public void testSetNome() throws Exception{
		veterano.setNome("Marvel");
		assertEquals("Marvel", veterano.getNome());
	}
	
	@Test
	public void testSetNomeWithException() throws Exception{
		
		try {
			veterano.setNome("");
		}catch(Exception e) {
			assertEquals("Nome de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
		try {
			veterano.setNome(null);
		}catch(Exception e) {
			assertEquals("Nome de usuario nao pode ser vazio ou nulo", e.getMessage());
		}
		
	}

	@Test
	public void testGetLogin() {
		assertEquals("Tsuoka", veterano.getLogin());
	}

	@Test
	public void testGetQtdDinheiroDisponivel() {
		assertEquals(1000.0, veterano.getQtdDinheiroDisponivel(), 0.01);
	}

	@Test
	public void testDepositaDinheiro() {
		
		veterano.depositaDinheiro(1000.0);
		assertEquals(2000.0, veterano.getQtdDinheiroDisponivel(), 0.001);
		
		veterano.depositaDinheiro(- 500);
		assertEquals(2000.0, veterano.getQtdDinheiroDisponivel(), 0.001);
		
	}
	

	@Test
	public void testDescontaDinheiro() {
		
		veterano.descontaDinheiro(500);
		assertEquals(500.0, veterano.getQtdDinheiroDisponivel(), 0.001);
		
		veterano.descontaDinheiro(300);
		assertEquals(200.0, veterano.getQtdDinheiroDisponivel(), 0.001);
		
		veterano.descontaDinheiro(-500);
		assertEquals(200.0, veterano.getQtdDinheiroDisponivel(), 0.001);		
	}

	@Test
	public void testAdicionaJogo() throws Exception {
		RPG ff = new RPG("Final Fantasy", 30);
		assertTrue(veterano.adicionaJogo(ff));
		assertEquals(ff, veterano.getJogo("Final Fantasy"));
		
	}

	@Test
	public void testAdicionaX2p() {
		assertEquals(1000, veterano.getX2p());
		veterano.adicionaX2p(500);
		assertEquals(1500, veterano.getX2p());
		
		veterano.adicionaX2p(- 500);
		assertEquals(1500, veterano.getX2p());
	}

	@Test
	public void testGetX2p() {
		assertEquals(1000, veterano.getX2p());
		
		veterano.adicionaX2p(500);
		
		assertEquals(1500, veterano.getX2p());
		
		veterano.adicionaX2p(- 500);
		assertEquals(1500, veterano.getX2p());
	}


	@Test
	public void testRegistraJogada() throws Exception {
		
		veterano.adicionaJogo(new RPG("Final Fantasy", 30));
		
		veterano.registraJogada("Final Fantasy", 500, true);
		assertEquals(1010, veterano.getX2p());
		
		veterano.adicionaJogo(new Plataforma("Crash bandicoot", 30));
		veterano.registraJogada("Crash bandicoot", 2000, true);
		
		assertEquals(1030, veterano.getX2p());
		
		veterano.adicionaJogo(new Luta("The King of Fighters", 30));
		veterano.registraJogada("The King of Fighters", 2000, true);
		
		assertEquals(1032, veterano.getX2p());
		
		veterano.registraJogada("Crash bandicoot", 3200, false);
		
		assertEquals(1032, veterano.getX2p());
		
		
		
		
	}
	@Test
	public void testGetJogo() throws Exception {
		RPG ff = new RPG("Final Fantasy", 30);
		veterano.adicionaJogo(ff);
		assertEquals(ff, veterano.getJogo("Final Fantasy"));
	}

	@Test
	public void testGetJogos() throws Exception{
		RPG ff = new RPG("Final Fantasy", 30);
		RPG gdw = new RPG("God of War", 50);
		RPG residentEvil7 = new RPG("Resident Evil 7", 80);
		veterano.adicionaJogo(ff);
		veterano.adicionaJogo(residentEvil7);
		veterano.adicionaJogo(gdw);
		
		HashSet<Jogo> testeJogosUsuario = veterano.getJogos();
		assertTrue(testeJogosUsuario.contains(ff));
		assertTrue(testeJogosUsuario.contains(gdw));
		assertTrue(testeJogosUsuario.contains(residentEvil7));
	}

	@Test
	public void testEquals() throws Exception {
		Veterano vetera = new Veterano("Temara", "Croste1r");
		assertFalse(veterano.equals(vetera));
		Veterano novoVeterano = new Veterano("Crashter", "kopler");
		assertFalse(veterano.equals(novoVeterano));
		assertFalse(vetera.equals(novoVeterano));
		assertFalse(vetera.equals(veterano));
		assertFalse(novoVeterano.equals(vetera));
		assertTrue(veterano.equals(veterano));
		assertTrue(novoVeterano.equals(novoVeterano));
		
		Veterano meuvetera = new Veterano("Yuri", "Tsuoka");
		assertTrue(veterano.equals(meuvetera));
		assertFalse(meuvetera.equals(novoVeterano));
		
	}

}
