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
		RPG gdw = new RPG("God of war", 300);
		assertTrue(meuUsuario.compraJogo(gdw));
		// Testando desconto de 10% sobre a compra. NOOB
		assertEquals(730.0, meuUsuario.getQtdDinheiroDisponivel(), 0.01);
		assertEquals(3000, meuUsuario.getX2p()); // Testando bonus de x2p para Noob
		assertTrue(gdw.equals(meuUsuario.getJogo("God of war")));
		// Testando desconto de 20% sobre a compra. VETERANO
		meuUsuario.upgradeCategoria();
		RPG rpg = new RPG("Final Fantasy", 300);
		assertTrue(meuUsuario.compraJogo(rpg));
		assertEquals(490.0, meuUsuario.getQtdDinheiroDisponivel(), 0.001);
		assertEquals(7500, meuUsuario.getX2p()); // Testando bonus de x2p para Veterano
		assertTrue(rpg.equals(meuUsuario.getJogo("Final Fantasy")));
		
		
		
	}
	
	@Test
	public void testeCompraJogoWithExceptions() throws Exception {
		
		meuUsuario.descontaDinheiro(1000); // tira o dinheiro existente na conta
		
		// Teste comprando jogo com dinheiro insuficiente. NOOB
		try {
			
			RPG gdw = new RPG("God of war", 30);
			meuUsuario.compraJogo(gdw);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Dinheiro insuficiente", e.getMessage());
		}
		// Teste comprando jogo com dinheiro insuficiente. VETERANO
		try {
			RPG gdw = new RPG("God of war", 300);
			assertEquals(0, meuUsuario.getQtdDinheiroDisponivel(), 0.001);
			meuUsuario.depositaDinheiro(270);
			assertEquals(270.0, meuUsuario.getQtdDinheiroDisponivel(), 0.001);
			meuUsuario.compraJogo(gdw);
			assertEquals(0, meuUsuario.getQtdDinheiroDisponivel(), 0.001);
			meuUsuario.upgradeCategoria();
			RPG tk = new RPG("The K", 30);
			meuUsuario.compraJogo(tk);
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
		assertEquals(0, meuUsuario.getX2p());
	}
	

	
	@Test
	public void testPunirNoob() throws Exception {
		
		
		RPG rpg = new RPG("Final Fantasy", 30);
		rpg.adicionaJogabilidade("cooperativo");
		rpg.adicionaJogabilidade("online");
		
		meuUsuario.compraJogo(rpg);
		assertEquals(300.0, meuUsuario.getX2p(), 0.001);
		
		meuUsuario.punir("Final Fantasy", 30000, false);
		assertEquals(250, meuUsuario.getX2p());
		
		meuUsuario.punir("Final Fantasy", 30000, false);
		assertEquals(200, meuUsuario.getX2p());
		
		
		
		
	}
	
	@Test
	public void testPunirVeterano() throws Exception {
		
		
		RPG rpg = new RPG("Final Fantasy", 300);
		rpg.adicionaJogabilidade("competitivo");
		rpg.adicionaJogabilidade("offline");
		
		meuUsuario.compraJogo(rpg);
		
		assertEquals(3000, meuUsuario.getX2p());
		assertTrue(meuUsuario.upgradeCategoria());
		
		meuUsuario.punir("Final Fantasy", 30, false);
		assertEquals(2970, meuUsuario.getX2p());
		
		
	}
	
	@Test
	public void testRecompensarNoob() throws Exception {
		RPG rpg = new RPG("Final Fantasy", 30);
		rpg.adicionaJogabilidade("offline");
		rpg.adicionaJogabilidade("multiplayer");
		rpg.adicionaJogabilidade("competitivo");
		
		meuUsuario.compraJogo(rpg);
		assertEquals(300.0, meuUsuario.getX2p(), 0.001);
		
		meuUsuario.recompensar("Final Fantasy", 30000, false); // vai receber 10 por ter jogado
		assertEquals(350, meuUsuario.getX2p());
		
		meuUsuario.recompensar("Final Fantasy", 30000, false); // vai receber 10 por ter jogado
		assertEquals(400, meuUsuario.getX2p());
		
	}

	@Test
	public void testRecompensarVeterano() throws Exception {
		RPG rpg = new RPG("Final Fantasy", 300);
		rpg.adicionaJogabilidade("online");
		rpg.adicionaJogabilidade("cooperativo");

		meuUsuario.compraJogo(rpg);
		assertEquals(3000.0, meuUsuario.getX2p(), 0.001);
		meuUsuario.upgradeCategoria();
		
		
		meuUsuario.recompensar("Final Fantasy", 30000, false); // vai receber 10 por ter jogado
		assertEquals(3040, meuUsuario.getX2p());
		
		meuUsuario.recompensar("Final Fantasy", 30000, false); // vai receber 10 por ter jogado
		assertEquals(3080, meuUsuario.getX2p());
	}
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
