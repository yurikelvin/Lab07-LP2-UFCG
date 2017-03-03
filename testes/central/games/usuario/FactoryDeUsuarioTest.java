package central.games.usuario;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FactoryDeUsuarioTest {
	
	private FactoryDeUsuario fUser;

	
	@Before
	public void setUp() {
		fUser = new FactoryDeUsuario();
	}
	
	@Test
	public void testCriaUsuario() {
		Usuario userF = fUser.criaUsuario("Yuri", "yuri.silva", "Noob");
		Usuario usuario = new Usuario("Yuri", "yuri.silva");
		
		assertTrue(usuario.equals(userF));
		assertEquals("Yuri", userF.getNome());
		assertEquals("yuri.silva", userF.getLogin());
		assertEquals(0, userF.getX2p(), 0.001);
		
		
	}

}
