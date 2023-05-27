package agent;

//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : agent.CrazyVirus.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//



import effect.RandomMove;
import player.Virologist;
import skeleton.Main;

public class CrazyVirus extends Agent {
	/**
	 * v virol�gushoz felveszi az Crazy hat�st
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("agent.CrazyVirus.apply()", true);
		v.addEffect(new RandomMove());
		Main.printMethod("return", false);
	}
}
