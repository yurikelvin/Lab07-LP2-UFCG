package central.games.jogo;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlataformaTest {
	
	private Plataforma minecraft;

	@Before
	public void setUp() throws Exception {
		minecraft = new Plataforma("Minecraft", 100);
	}

	@Test
	public void testRegistraJogada() throws Exception{
	

		assertEquals(0, minecraft.registraJogada(10, false));
		assertEquals(1, minecraft.getQtdJogadas());
		assertEquals(0, minecraft.getQtdZeradas());
		assertEquals(20, minecraft.registraJogada(10, true));
		assertEquals(2, minecraft.getQtdJogadas());
		assertEquals(1, minecraft.getQtdZeradas());
		assertEquals(0, minecraft.registraJogada(10000, false));
		assertEquals(10000, minecraft.getMaiorScore());


	}
	
	public void testRegistraJogadaWithException() throws Exception {
		try {
			minecraft.registraJogada(-5, false);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
		
		try {
			minecraft.registraJogada(- 1, false);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
	}
	
	@Test
	public void testLuta() throws Exception { 
		Plataforma mario = new Plataforma("Mario", 80);
		Plataforma sony = new Plataforma("Sony", 30);
		Plataforma crashBandicoot = new Plataforma("Crash Bandicoot", 20);

	}

	@Test
	public void testLutaWithExceptions() {
		// Teste de nome nulo, vazio e preco negativo
		
		try {
			Plataforma nomeNulo = new Plataforma(null, 100);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Nome do jogo nao pode ser nulo ou vazio", e.getMessage());
			}
		
		try {
			Plataforma nomeVazio = new Plataforma("", 100);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Nome do jogo nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			Plataforma precoNegativo = new Plataforma("Preco Negativo", -5);
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
		Jogo testeHeranca = new Plataforma("Crash Bandicoot", 80);
	}

	@Test
	public void testGetNome() {
		assertEquals("Minecraft", minecraft.getNome());
	}

	@Test
	public void testGetPreco() {
		assertEquals(100, minecraft.getPreco());
	}

	@Test
	public void testSetPreco() throws Exception{
		minecraft.setPreco(105);
		assertEquals(105, minecraft.getPreco());
	}
	
	@Test
	public void testSetPrecoWithException() {
		
		try {
			minecraft.setPreco(- 100);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Preco do jogo nao pode ser negativo", e.getMessage());
		}
	}

	@Test
	public void testGetMaiorScore() throws Exception{
		minecraft.setMaiorScore(500);
		assertEquals(500, minecraft.getMaiorScore());
		
	}

	@Test
	public void testSetMaiorScore() throws Exception{
		minecraft.setMaiorScore(500);
		assertEquals(500, minecraft.getMaiorScore());
	}
	
	@Test
	public void testSetMaiorScoreWithException() throws Exception {
		try {
			minecraft.setMaiorScore(- 500);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Score precisa ser maior que 0", e.getMessage());
		}
		
	
	}

	@Test
	public void testGetQtdZeradas() throws Exception{
		assertEquals(0, minecraft.getQtdZeradas());
		minecraft.registraJogada(10, true);
		assertEquals(1, minecraft.getQtdZeradas());
		minecraft.registraJogada(10, true);
		assertEquals(2, minecraft.getQtdZeradas());
	}

	@Test
	public void testZerou() {
		assertEquals(0, minecraft.getQtdZeradas());
		minecraft.zerou();
		assertEquals(1, minecraft.getQtdZeradas());
		minecraft.zerou();
		assertEquals(2, minecraft.getQtdZeradas());
	}

	@Test
	public void testGetQtdJogadas() throws Exception{
		assertEquals(0, minecraft.getQtdJogadas());
		minecraft.registraJogada(10, true);
		assertEquals(1, minecraft.getQtdJogadas());
		minecraft.registraJogada(11, false);
		assertEquals(2, minecraft.getQtdJogadas());
	}

	@Test
	public void testJogou() {
		assertEquals(0, minecraft.getQtdJogadas());
		minecraft.jogou();
		assertEquals(1, minecraft.getQtdJogadas());
		minecraft.jogou();
		assertEquals(2, minecraft.getQtdJogadas());
	}

	@Test
	public void testAdicionaEGetJogabilidade() throws Exception{
		assertTrue(minecraft.adicionaJogabilidade("MULTIPLAYER"));
		assertTrue(minecraft.adicionaJogabilidade("OFFLINE"));
		assertTrue(minecraft.adicionaJogabilidade("ONLINE"));
		
		HashSet<Jogabilidade> minecraftJogabilidade = minecraft.getJogabilidade();
		Iterator<Jogabilidade> it = minecraftJogabilidade.iterator();
		while(it.hasNext()) {
			Jogabilidade nova = it.next();
			assertTrue(nova == Jogabilidade.MULTIPLAYER || nova == Jogabilidade.OFFLINE || nova == Jogabilidade.ONLINE);
		}
		
		
	}



}
