package central.games.jogo;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import exception.ValidacaoException;

public class FactoryDeJogoTest {

	private FactoryDeJogo fJogo;
	
	@Before
	public void setUp() {
		fJogo = new FactoryDeJogo();
	}
	
	@Test
	public void testCriaJogo() throws Exception {
		// Teste RPG
		
		RPG ragnarok = new RPG("Ragnarok", 30);
		ragnarok.adicionaJogabilidade("online");
		Jogo ragnarokFabricado = fJogo.criaJogo("Ragnarok", 30, "RPG", "ONLINE");
		
		assertTrue(ragnarok.equals(ragnarokFabricado));
		assertTrue(ragnarok.getClass() == ragnarokFabricado.getClass());
		assertTrue(testJogabilidade(ragnarokFabricado.getJogabilidade(), "online"));
		assertEquals("Ragnarok", ragnarokFabricado.getNome());
		assertEquals(30, ragnarokFabricado.getPreco(), 0.001);
		
		// Teste Plataforma
		
		Plataforma mario = new Plataforma("Super Mario", 30);
		Jogo marioCriado = fJogo.criaJogo("Super Mario", 30, "Plataforma", "OFFLINE");
		
		assertTrue(mario.equals(marioCriado));
		assertTrue(mario.getClass() == marioCriado.getClass());
		assertTrue(testJogabilidade(marioCriado.getJogabilidade(), "offline"));
		assertEquals("Super Mario", marioCriado.getNome());
		assertEquals(30, marioCriado.getPreco(), 0.001);
		
		// Teste Luta
		
		Luta streetFighter = new Luta("Street Fighter", 50);
		Jogo streetFighterCriado = fJogo.criaJogo("Street Fighter", 50, "LUTA", "MULTIPLAYER");
		
		assertTrue(streetFighter.equals(streetFighterCriado));
		assertTrue(streetFighter.getClass() == streetFighterCriado.getClass());
		assertTrue(testJogabilidade(streetFighterCriado.getJogabilidade(), "multiplayer"));
		assertEquals("Street Fighter", streetFighterCriado.getNome());
		assertEquals(50, streetFighterCriado.getPreco(), 0.001);
		
		
	}
	
	private boolean testJogabilidade(HashSet<Jogabilidade> e, String jogabilidade) {
		for(Jogabilidade estilo: e) {
			if(estilo.getValor().equals(jogabilidade)) {
				return true;
			}
		}
		
		return false;
	}

}
