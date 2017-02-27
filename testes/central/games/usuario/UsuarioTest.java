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

public class UsuarioTest {
	
	private Usuario meuUsuario;
	
	@Before
	public void setUp() throws Exception{
		meuUsuario = new Usuario("Thiaguinho", "Aster Griffin");
		meuUsuario.depositaDinheiro(1000);
	}

	@Test
	public void testCompraJogo() throws Exception {
		RPG gdw = new RPG("God of war", 30);
		assertTrue(meuUsuario.compraJogo(gdw));
		// Testando desconto de 10% sobre a compra.
		assertEquals(973.0, meuUsuario.getQtdDinheiroDisponivel(), 0.01);
		assertTrue(gdw.equals(meuUsuario.getJogo("God of war")));
		
	}
	
	@Test
	public void testeCompraJogoWithExceptions() throws Exception {
		
		meuUsuario.descontaDinheiro(1000);
		
		// Teste comprando jogo com dinheiro insuficiente.
		try {
			
			RPG gdw = new RPG("God of war", 30);
			meuUsuario.depositaDinheiro(20);
			meuUsuario.compraJogo(gdw);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Dinheiro insuficiente", e.getMessage());
		}
		// Teste comprando duas vezes o jogo.
		try {
			meuUsuario.depositaDinheiro(100);
			RPG gdw = new RPG("God of war", 30);
			meuUsuario.compraJogo(gdw);
			meuUsuario.compraJogo(gdw);
			fail();
		}catch(Exception e) {
			assertEquals("Usuario ja possui este jogo.", e.getMessage());
		}
		
		// Teste jogo nulo.
		
		try {
			meuUsuario.compraJogo(null);
			fail();
		}catch(Exception e) {
			assertEquals("Jogo nao pode ser nulo", e.getMessage());
		}
		
	}

	@Test
	public void testUsuario() throws Exception{
		Usuario user = new Usuario("Jon Snow", "nightWatch's");
		assertEquals(0, user.getX2p());
	}
	

	@Test
	public void testSetLogin() {
		meuUsuario.setLogin("l0gin");
		assertEquals("l0gin", meuUsuario.getLogin());
	}

	@Test
	public void testGetNome() {
		assertEquals("Thiaguinho", meuUsuario.getNome());
	}

	@Test
	public void testSetNome() throws Exception{
		meuUsuario.setNome("Marvel");
		assertEquals("Marvel", meuUsuario.getNome());
	}
	

	@Test
	public void testGetLogin() {
		assertEquals("Aster Griffin", meuUsuario.getLogin());
	}

	@Test
	public void testGetQtdDinheiroDisponivel() {
		assertEquals(1000.0, meuUsuario.getQtdDinheiroDisponivel(), 0.01);
	}

	@Test
	public void testDepositaDinheiro() {
		
		meuUsuario.depositaDinheiro(1000.0);
		assertEquals(2000.0, meuUsuario.getQtdDinheiroDisponivel(), 0.001);
		
		meuUsuario.depositaDinheiro(- 500);
		assertEquals(2000.0, meuUsuario.getQtdDinheiroDisponivel(), 0.001);
		
	}
	

	@Test
	public void testDescontaDinheiro() {
		
		meuUsuario.descontaDinheiro(500);
		assertEquals(500.0, meuUsuario.getQtdDinheiroDisponivel(), 0.001);
		
		meuUsuario.descontaDinheiro(300);
		assertEquals(200.0, meuUsuario.getQtdDinheiroDisponivel(), 0.001);
		
		meuUsuario.descontaDinheiro(-500);
		assertEquals(200.0, meuUsuario.getQtdDinheiroDisponivel(), 0.001);		
	}

	@Test
	public void testAdicionaJogo() throws Exception {
		RPG ff = new RPG("Final Fantasy", 30);
		assertTrue(meuUsuario.compraJogo(ff));
		assertEquals(ff, meuUsuario.getJogo("Final Fantasy"));
		
	}

	@Test
	public void testAdicionaX2p() {
		assertEquals(0, meuUsuario.getX2p());
		meuUsuario.adicionaX2p(500);
		assertEquals(500, meuUsuario.getX2p());
		
		meuUsuario.adicionaX2p(- 500);
		assertEquals(0, meuUsuario.getX2p());
	}

	@Test
	public void testGetX2p() {
		assertEquals(0, meuUsuario.getX2p());
		
		meuUsuario.adicionaX2p(500);
		
		assertEquals(500, meuUsuario.getX2p());
		
		meuUsuario.adicionaX2p(- 500);
		assertEquals(500, meuUsuario.getX2p());
	}

//	@Test
//	public void testRegistraJogada() throws Exception {
//		
//		meuUsuario.adicionaJogo(new RPG("Final Fantasy", 30));
//		
//		meuUsuario.registraJogada("Final Fantasy", 500, true);
//		assertEquals(10, meuUsuario.getX2p());
//		
//		meuUsuario.adicionaJogo(new Plataforma("Crash bandicoot", 30));
//		meuUsuario.registraJogada("Crash bandicoot", 2000, true);
//		
//		assertEquals(30, meuUsuario.getX2p());
//		
//		meuUsuario.adicionaJogo(new Luta("The King of Fighters", 30));
//		meuUsuario.registraJogada("The King of Fighters", 2000, true);
//		
//		assertEquals(32, meuUsuario.getX2p());
//		
//		meuUsuario.registraJogada("Crash bandicoot", 3200, false);
//		
//		assertEquals(32, meuUsuario.getX2p());
//		
//		
//		
//		
//	}

	@Test
	public void testGetJogo() throws Exception {
		RPG ff = new RPG("Final Fantasy", 30);
		meuUsuario.compraJogo(ff);
		assertEquals(ff, meuUsuario.getJogo("Final Fantasy"));
	}

	@Test
	public void testGetJogos() throws Exception{
		RPG ff = new RPG("Final Fantasy", 30);
		RPG gdw = new RPG("God of War", 50);
		RPG residentEvil7 = new RPG("Resident Evil 7", 80);
		meuUsuario.depositaDinheiro(200);
		meuUsuario.compraJogo(residentEvil7);
		meuUsuario.compraJogo(gdw);
		meuUsuario.compraJogo(ff);
		
		HashSet<Jogo> testeJogosUsuario = meuUsuario.getJogos();
		assertTrue(testeJogosUsuario.contains(ff));
		assertTrue(testeJogosUsuario.contains(gdw));
		assertTrue(testeJogosUsuario.contains(residentEvil7));
	}

	@Test
	public void testEquals() throws Exception {
		Usuario user = new Usuario("Temara", "Croste1r");
		assertFalse(meuUsuario.equals(user));
		Usuario novoUsuario = new Usuario("Crashter", "kopler");
		assertFalse(meuUsuario.equals(novoUsuario));
		assertFalse(user.equals(novoUsuario));
		assertFalse(user.equals(meuUsuario));
		assertFalse(novoUsuario.equals(user));
		assertTrue(meuUsuario.equals(meuUsuario));
		assertTrue(novoUsuario.equals(novoUsuario));
		
		Usuario meuUser = new Usuario("Thiaguinho lima", "Aster Griffin");
		assertTrue(meuUsuario.equals(meuUser));
		assertFalse(meuUser.equals(novoUsuario));
		
	}

}
