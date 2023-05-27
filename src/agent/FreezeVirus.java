package agent;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : agent.FreezeVirus.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import effect.Frozen;
import player.Virologist;
import skeleton.Main;

public class FreezeVirus extends Agent {

	/**
	 * v virológushoz felveszi az effect.Frozen hatást.
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("agent.FreezeVirus.apply()", true);
		v.addEffect(new Frozen());
		Main.printMethod("Return ", false);
	}
}
