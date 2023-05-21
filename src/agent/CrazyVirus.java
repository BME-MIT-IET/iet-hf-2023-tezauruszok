package agent;

//
//  @ Project : Vak Varázslók Földje
//  @ File Name : agent.CrazyVirus.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//



import effect.RandomMove;
import player.Virologist;
import skeleton.Main;

public class CrazyVirus extends Agent {
	/**
	 * v virológushoz felveszi az Crazy hatást
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("agent.CrazyVirus.apply()", true);
		v.addEffect(new RandomMove());
		Main.printMethod("return", false);
	}
}
