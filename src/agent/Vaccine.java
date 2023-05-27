package agent;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : agent.Vaccine.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import effect.AgentProtection;
import player.Virologist;
import skeleton.Main;

public class Vaccine extends Agent {
	/**
	 * A v Virológushoz felveszi az agent protection hatást.
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("agent.Vaccine.apply()", true);
		v.addEffect(new AgentProtection());
		Main.printMethod("Return", false);
	}
}
