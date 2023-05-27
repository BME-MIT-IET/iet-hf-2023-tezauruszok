package agent;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : agent.ForgetVirus.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import player.Virologist;
import skeleton.Main;

public class ForgetVirus extends Agent {
	/**
	 * A v virológusra meghívja a Forgetvirsucode függvényt.
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("agent.ForgetVirus.apply()", true);
		v.forgetGeneticCodes();
		Main.printMethod("return",false);
	}
}
