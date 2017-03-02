package central.games.jogo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.ValidacaoException;

public class RPGTest {
	
	RPG ragnarok;

	@Before
	public void setUp() throws Exception {
		ragnarok = new RPG("Ragnarok", 30);
		
	}

	@Test
	public void testJogo() throws Exception {
		Jogo testeHeranca = new RPG("The Witcher 3", 100);
	}
	
	@Test
	public void testRPG() throws Exception{
		RPG theWitcher = new RPG("The Witcher 3", 100);
		RPG finalFantasy = new RPG("Final Fantasy", 140);
		RPG darkSouls = new RPG("Dark Souls", 200);
	}

	@Test
	public void testRegistraJogada() throws Exception{

		
		assertEquals(10, ragnarok.registraJogada(500, true));
		assertEquals(10, ragnarok.registraJogada(1000, false));
		
		assertEquals(1, ragnarok.getQtdZeradas());
		assertEquals(1000, ragnarok.getMaiorScore());
		assertEquals(2, ragnarok.getQtdJogadas());
		
		
	}
	
	@Test
	public void testRegistraJogadaWithException() throws Exception{

		
		try {
			ragnarok.registraJogada(- 5, true);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
		
		
	}

/*	@Test
	public void testToString() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testGetNome() {
		assertEquals("Ragnarok", ragnarok.getNome());
	}

	@Test
	public void testGetPreco() {
		assertEquals(30, ragnarok.getPreco());
	}

	@Test
	public void testSetPreco() throws Exception {
		
		ragnarok.setPreco(50);
		assertEquals(50, ragnarok.getPreco());
	}

	@Test
	public void testGetMaiorScore() throws Exception {
		ragnarok.setMaiorScore(500);
		assertEquals(500, ragnarok.getMaiorScore());
		
		
	}
	

	@Test
	public void testSetMaiorScore() throws Exception {
		ragnarok.setMaiorScore(500);
		assertEquals(500, ragnarok.getMaiorScore());
	}

	@Test
	public void testGetQtdZeradas() throws Exception{
		assertEquals(0, ragnarok.getQtdZeradas());
		ragnarok.registraJogada(10, true);
		assertEquals(1, ragnarok.getQtdZeradas());
		ragnarok.registraJogada(10, true);
		assertEquals(2, ragnarok.getQtdZeradas());
	}

	@Test
	public void testZerou() {
		assertEquals(0, ragnarok.getQtdZeradas());
		ragnarok.zerou();
		assertEquals(1, ragnarok.getQtdZeradas());
		ragnarok.zerou();
		assertEquals(2, ragnarok.getQtdZeradas());
	}

	@Test
	public void testGetQtdJogadas() throws Exception {
		assertEquals(0, ragnarok.getQtdJogadas());
		ragnarok.registraJogada(10, true);
		assertEquals(1, ragnarok.getQtdJogadas());
		ragnarok.registraJogada(11, false);
		assertEquals(2, ragnarok.getQtdJogadas());
		
	}

	@Test
	public void testJogou() {
		assertEquals(0, ragnarok.getQtdJogadas());
		ragnarok.jogou();
		assertEquals(1, ragnarok.getQtdJogadas());
		ragnarok.jogou();
		assertEquals(2, ragnarok.getQtdJogadas());
	}

	@Test
	public void testAdicionaEGetJogabilidade() throws Exception{
		
		assertTrue(ragnarok.adicionaJogabilidade("MULTIPLAYER"));
		assertTrue(ragnarok.adicionaJogabilidade("ONLINE"));
		
		HashSet<Jogabilidade> ragnarokJogabilidade = ragnarok.getJogabilidade();
		Iterator<Jogabilidade> it = ragnarokJogabilidade.iterator();
		while(it.hasNext()) {
			Jogabilidade nova = it.next();
			assertTrue(nova == Jogabilidade.MULTIPLAYER || nova == Jogabilidade.ONLINE);
		}
		
		
	}


}
