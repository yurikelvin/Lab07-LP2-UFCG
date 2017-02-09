package central.games.usuario;

import java.util.HashSet;
import java.util.Iterator;

import central.games.jogo.Jogabilidade;
import central.games.jogo.Jogo;
import central.games.jogo.RPG;
import validacao.Validacao;

public class Veterano extends Usuario {

	public Veterano(String nome, String login) throws Exception{
		
		super(nome, login);
		super.adicionaX2p(1000);

	}
	
	@Override
	public boolean compraJogo(Jogo jogoAComprar) throws Exception {

		Validacao.validaObj(jogoAComprar, "Jogo nao pode ser nulo");
		
		if(super.getQtdDinheiroDisponivel() >= (jogoAComprar.getPreco() * 0.8)) {
			
				super.descontaDinheiro(jogoAComprar.getPreco() * 0.8);
				super.adicionaX2p(15 * jogoAComprar.getPreco());
				return super.adicionaJogo(jogoAComprar);
		}
		
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
