package central.games.loja;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import central.games.jogo.Luta;
import central.games.jogo.Plataforma;
import central.games.jogo.RPG;
import central.games.loja.LojaController;
import central.games.usuario.Noob;
import central.games.usuario.Usuario;
import central.games.usuario.Veterano;
import exception.ValidacaoException;
import junit.framework.Assert;

public class LojaControllerTest {
	
	private LojaController meuSistema;

	@Before
	public void setUp() throws Exception {
		meuSistema = new LojaController();
		

		meuSistema.criaUsuario("Yuri", "Tsubakker", "Noob");
		meuSistema.criaUsuario("Sol", "SLNSN", "Noob");
		
		meuSistema.addFundos("Tsubakker", 1000);
		meuSistema.addFundos("SLNSN", 1000);


	}
	
	@Test
	public void testcriaUsuario() throws Exception {
		
		
		meuSistema.criaUsuario("Ram", "ram.kop", "Noob");
		meuSistema.criaUsuario("Kaop", "kaop.ope", "Noob");
		
		try {
			meuSistema.getSaldo("ram.kop");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals(0.0, meuSistema.getSaldo("ram.kop"), 0.001);
		assertEquals(0.0, meuSistema.getSaldo("kaop.ope"), 0.001);
		
	}
	
	@Test
	public void testcriaUsuarioWithExceptions() throws Exception {
		
		// Testando criar usuario nulo / vazio
		
		try {
			meuSistema.criaUsuario(null, null, null);
		}catch(Exception e) {
			assertEquals("Dados para criacao de usuario invalido.", e.getMessage());
		}
		
		try {
			meuSistema.criaUsuario("", "", "");
		}catch(Exception e) {
			assertEquals("Dados para criacao de usuario invalido.", e.getMessage());
		}
		
		try {
			meuSistema.criaUsuario("     ", "     ", "   ");
		}catch(Exception e) {
			assertEquals("Dados para criacao de usuario invalido.", e.getMessage());
		}
		
		try {
			meuSistema.criaUsuario(null, "koqp.qt", "Noob");
		}catch(Exception e) {
			assertEquals("Dados para criacao de usuario invalido.", e.getMessage());
		}
		
		try {
			meuSistema.criaUsuario("Karm", null, "Noob");
		}catch(Exception e) {
			assertEquals("Dados para criacao de usuario invalido.", e.getMessage());
		}
		
		try {
			meuSistema.criaUsuario("Keppo", "ke.ppo", null);
		}catch(Exception e) {
			assertEquals("Dados para criacao de usuario invalido.", e.getMessage());
		}



	}

	@Test
	public void testAddFundos() throws Exception {
		// adicionando credito para Noob 
		meuSistema.criaUsuario("Tekol", "Tesla", "Noob");
		meuSistema.addFundos("Tesla", 1500);
		assertEquals(1500, meuSistema.getSaldo("Tesla"), 0.001);
		
		// fazendo usuario virar Veterano e testando adicionar credito para Veterano
		meuSistema.vendeJogo("The king", 300, "ONLINE", "RPG", "Tesla");
		assertEquals(3000, meuSistema.getX2p("Tesla"));
		meuSistema.upgrade("Tesla");
		meuSistema.addFundos("Tesla", 300);
		assertEquals(1530, meuSistema.getSaldo("Tesla"), 0.001);
		
		
	}
	
	@Test
	public void testAddFundosWithException() throws Exception {
		// Testando adicionar credito para usuario inexistente.
		try {
			meuSistema.addFundos("nunca.te.vi", 5000);
			Assert.fail();
		}catch(Exception e) {
			assertEquals("Usuario nao encontrado.",e.getMessage());
		}
		// Testando adicionar credito com parametros invalidos
		try {
			meuSistema.addFundos("", 3000);
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo.",e.getMessage());
		}
		
		try {
			meuSistema.addFundos("		", 3000);
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo.",e.getMessage());
		}
		
		try {
			meuSistema.addFundos(null, 3000);
		}catch(Exception e) {
			assertEquals("Login de usuario nao pode ser vazio ou nulo.",e.getMessage());
		}
		
		try {
			meuSistema.addFundos("Ketchup", - 3000);
		}catch(Exception e) {
			assertEquals("Quantidade a ser depositada precisa ser positiva.",e.getMessage());
		}
	}
	
	@Test
	public void testVendeJogo() throws Exception {
		
		meuSistema.vendeJogo("The king of figthers", 30, "MULTIPLAYER", "LUTA", "Tsubakker");
		assertEquals(973.0, meuSistema.getSaldo("Tsubakker"), 0.001);
		meuSistema.vendeJogo("Ragnarok", 30, "MULTIPLAYER ONLINE", "RPG", "Tsubakker");
		meuSistema.vendeJogo("Crash bandicoot", 30, "OFFLINE", "PLATAFORMA", "Tsubakker");
		assertEquals(919.0, meuSistema.getSaldo("Tsubakker"), 0.001);
		
		
	}
	
	@Test
	public void testVendeJogoWithException() throws Exception {
		// Testando dados invalidos ao vender Jogo
		try {
			meuSistema.vendeJogo("", 3000, "MULTIPLAYER", "RPG", "Tsubakker");
			fail();
		}catch(Exception e) {
			assertEquals("Dados para venda de jogo invalido.", e.getMessage());
		}
		
		try {
			meuSistema.vendeJogo("	", 3000, "MULTIPLAYER", "RPG", "Tsubakker");
			fail();
		}catch(Exception e) {
			assertEquals("Dados para venda de jogo invalido.", e.getMessage());
		}
		
		try {
			meuSistema.vendeJogo("	", 3000, "		", "", "");
			fail();
		}catch(Exception e) {
			assertEquals("Dados para venda de jogo invalido.", e.getMessage());
		}
		
		try {
			meuSistema.vendeJogo("", 3000, "", "", "");
			fail();
		}catch(Exception e) {
			
			assertEquals("Dados para venda de jogo invalido.", e.getMessage());
		}
		
		try {
			meuSistema.vendeJogo(null, 3000, null, null, null);
			fail();
		}catch(Exception e) {
			
			assertEquals("Dados para venda de jogo invalido.", e.getMessage());
		}
		
		try {
			meuSistema.vendeJogo("Teste", 3000, "Online", "RPG", null);
			fail();
		}catch(Exception e) {
			
			assertEquals("Dados para venda de jogo invalido.", e.getMessage());
		}
		
		try {
			meuSistema.vendeJogo(null, 3000, "Online", "RPG", "Tsubakker");
			fail();
		}catch(Exception e) {
			
			assertEquals("Dados para venda de jogo invalido.", e.getMessage());
		}
		
		try {
			meuSistema.vendeJogo("Teste", 3000, "Online", null, "Tsubakker");
			fail();
		}catch(Exception e) {
			
			assertEquals("Dados para venda de jogo invalido.", e.getMessage());
		}
		
		try {
			meuSistema.vendeJogo("Teste", 3000, null, "RPG", "Tsubakker");
			fail();
		}catch(Exception e) {
			
			assertEquals("Dados para venda de jogo invalido.", e.getMessage());
		}
		
		
		try {
			meuSistema.vendeJogo("Dota", - 3000, "ONLINE MULTIPLAYER", "RPG", "Tsubakker");
			fail();
		}catch(Exception e) {
			
			assertEquals("Venda de jogo com preco invalido.", e.getMessage());
		}
		
		// Testando tipo de jogo inexistente
		
		try {
			meuSistema.vendeJogo("Dota", 300, "ONLINE MULTIPLAYER", "MMORPG", "Tsubakker");
			fail();
		}catch(Exception e) {
			
			assertEquals("Tipo de jogo nao existente.", e.getMessage());
		}
		
		// Testando jogabilidade do jogo inexistente
		
		try {
			meuSistema.vendeJogo("Dota", 300, "MOBA", "RPG", "Tsubakker");
			fail();
		}catch(Exception e) {
			
			assertEquals("Estilo nao valido.", e.getMessage());
		}
		
		
	}
	
	@Test
	public void testUpgrade() throws Exception {
		meuSistema.vendeJogo("Ragnarok", 300, "ONLINE MULTIPLAYER", "RPG", "Tsubakker");
		meuSistema.upgrade("Tsubakker"); // Usuario ja tem 3000 x2p devido a compra do jogo
		meuSistema.punir("Tsubakker", "Ragnarok", 300, false);
		assertEquals(3010, meuSistema.getX2p("Tsubakker")); // nao deve perder nada pois eh veterano
	}
	
	@Test
	public void testNotUpgrade() throws Exception {
		meuSistema.upgrade("Tsubakker");
		meuSistema.vendeJogo("Ragnarok", 300, "ONLINE MULTIPLAYER", "RPG", "Tsubakker");
		// Usuario ja tem 3000 x2p devido a compra do jogo
		meuSistema.punir("Tsubakker", "Ragnarok", 300, false); // Usuario deve ser Noob
		assertEquals(3000, meuSistema.getX2p("Tsubakker")); // perdeu 10 de x2p por jogo ser online
	}

	@Test
	public void testPunirNoob() throws Exception {
		
		
		
		meuSistema.vendeJogo("Final Fantasy", 30, "COOPERATIVO ONLINE", "RPG", "Tsubakker");
		assertEquals(300.0, meuSistema.getX2p("Tsubakker"), 0.001);
		
		meuSistema.punir("Tsubakker", "Final Fantasy", 30000, false);
		assertEquals(250, meuSistema.getX2p("Tsubakker"));
		
		meuSistema.punir("Tsubakker", "Final Fantasy", 30000, false);
		assertEquals(200, meuSistema.getX2p("Tsubakker"));
		
		
		
		
	}
	
	@Test
	public void testPunirVeterano() throws Exception {
		
		
		meuSistema.vendeJogo("Final Fantasy", 300, "COMPETITIVO OFFLINE", "RPG", "Tsubakker");
		
		assertEquals(3000, meuSistema.getX2p("Tsubakker"));
		meuSistema.upgrade("Tsubakker");
		
		meuSistema.punir("Tsubakker","Final Fantasy", 30, false);
		assertEquals(2970, meuSistema.getX2p("Tsubakker"));
		
		
	}
	
	@Test
	public void testRecompensarNoob() throws Exception {
		
		meuSistema.vendeJogo("Final Fantasy", 30, "OFFLINE MULTIPLAYER COMPETITIVO", "RPG", "Tsubakker");
		assertEquals(300.0,  meuSistema.getX2p("Tsubakker"), 0.001);
		
		meuSistema.recompensar("Tsubakker", "Final Fantasy", 30000, false); // vai receber 10 por ter jogado
		assertEquals(350,  meuSistema.getX2p("Tsubakker"));
		
		meuSistema.recompensar("Tsubakker", "Final Fantasy", 30000, false); // vai receber 10 por ter jogado
		assertEquals(400,  meuSistema.getX2p("Tsubakker"));
		
	}
	
	@Test
	public void testRecompensarVeterano() throws Exception {


		meuSistema.vendeJogo("Final Fantasy", 300, "ONLINE COOPERATIVO", "RPG", "Tsubakker");
		assertEquals(3000.0, meuSistema.getX2p("Tsubakker"), 0.001);
		meuSistema.upgrade("Tsubakker");
		
		
		meuSistema.recompensar("Tsubakker", "Final Fantasy", 30000, false); // vai receber 10 por ter jogado
		assertEquals(3040, meuSistema.getX2p("Tsubakker"));
		
		meuSistema.recompensar("Tsubakker", "Final Fantasy", 30000, false); // vai receber 10 por ter jogado
		assertEquals(3080, meuSistema.getX2p("Tsubakker"));
	}
}
