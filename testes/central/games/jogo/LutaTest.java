package central.games.jogo;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LutaTest {

	private Luta streetFighter;
	
	@Before
	public void setUp() throws Exception {
		streetFighter = new Luta("Street Fighter", 70);
	}

	@Test
	public void testRegistraJogada() throws Exception{

		assertEquals(2, streetFighter.registraJogada(2000, false));
		assertEquals(2000, streetFighter.getMaiorScore());
		assertEquals(1, streetFighter.getQtdJogadas());
		assertEquals(0, streetFighter.getQtdZeradas());
		assertEquals(20, streetFighter.registraJogada(20000, true));
		assertEquals(2, streetFighter.getQtdJogadas());
		assertEquals(1, streetFighter.getQtdZeradas());
		assertEquals(20000, streetFighter.getMaiorScore());
		assertEquals(0, streetFighter.registraJogada(150, false));
		assertEquals(3, streetFighter.getQtdJogadas());
	}
	
	public void testRegistraJogadaWithException() throws Exception {
		try {
			streetFighter.registraJogada(-5, false);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
		
		try {
			streetFighter.registraJogada(- 1, false);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
	}
	
	@Test
	public void testLuta() throws Exception { 
		Luta theKingOfFighters = new Luta("The King of Fighters", 80);
		Luta mortalKombat = new Luta("Mortal Kombat", 30);

	}

	@Test
	public void testLutaWithExceptions() {
		// Teste de nome nulo, vazio e preco negativo
		
		try {
			Luta nomeNulo = new Luta(null, 100);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Nome do jogo nao pode ser nulo ou vazio", e.getMessage());
			}
		
		try {
			Luta nomeVazio = new Luta("", 100);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Nome do jogo nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			Luta precoNegativo = new Luta("Preco Negativo", -5);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Preco do jogo nao pode ser negativo", e.getMessage());
		}
	}

/*	@Test
	public void testToString() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testJogo() throws Exception {
		Jogo testeHeranca = new Luta("The King of Fighters", 80);
	}

	@Test
	public void testGetNome() {
		assertEquals("Street Fighter", streetFighter.getNome());
	}

	@Test
	public void testGetPreco() {
		assertEquals(70, streetFighter.getPreco());
	}

	@Test
	public void testSetPreco() throws Exception{
		streetFighter.setPreco(100);
		assertEquals(100, streetFighter.getPreco());
	}
	
	@Test
	public void testSetPrecoWithException() {
		
		try {
			streetFighter.setPreco(- 100);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Preco do jogo nao pode ser negativo", e.getMessage());
		}
	}

	@Test
	public void testGetMaiorScore() throws Exception{
		streetFighter.setMaiorScore(500);
		assertEquals(500, streetFighter.getMaiorScore());
		
	}

	@Test
	public void testSetMaiorScore() throws Exception{
		streetFighter.setMaiorScore(500);
		assertEquals(500, streetFighter.getMaiorScore());
	}
	
	@Test
	public void testSetMaiorScoreWithException() throws Exception {
		try {
			streetFighter.setMaiorScore(- 500);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Score precisa ser maior que 0", e.getMessage());
		}
		
	
	}

	@Test
	public void testGetQtdZeradas() throws Exception{
		assertEquals(0, streetFighter.getQtdZeradas());
		streetFighter.registraJogada(10, true);
		assertEquals(1, streetFighter.getQtdZeradas());
		streetFighter.registraJogada(10, true);
		assertEquals(2, streetFighter.getQtdZeradas());
	}

	@Test
	public void testZerou() {
		assertEquals(0, streetFighter.getQtdZeradas());
		streetFighter.zerou();
		assertEquals(1, streetFighter.getQtdZeradas());
		streetFighter.zerou();
		assertEquals(2, streetFighter.getQtdZeradas());
	}

	@Test
	public void testGetQtdJogadas() throws Exception{
		assertEquals(0, streetFighter.getQtdJogadas());
		streetFighter.registraJogada(10, true);
		assertEquals(1, streetFighter.getQtdJogadas());
		streetFighter.registraJogada(11, false);
		assertEquals(2, streetFighter.getQtdJogadas());
	}

	@Test
	public void testJogou() {
		assertEquals(0, streetFighter.getQtdJogadas());
		streetFighter.jogou();
		assertEquals(1, streetFighter.getQtdJogadas());
		streetFighter.jogou();
		assertEquals(2, streetFighter.getQtdJogadas());
	}

	@Test
	public void testAdicionaEGetJogabilidade() throws Exception{
		assertTrue(streetFighter.adicionaJogabilidade("MULTIPLAYER"));
		assertTrue(streetFighter.adicionaJogabilidade("OFFLINE"));
		
		HashSet<Jogabilidade> streetFighterJogabilidade = streetFighter.getJogabilidade();
		Iterator<Jogabilidade> it = streetFighterJogabilidade.iterator();
		while(it.hasNext()) {
			Jogabilidade nova = it.next();
			assertTrue(nova == Jogabilidade.MULTIPLAYER || nova == Jogabilidade.OFFLINE);
		}
		
		
	}


}
