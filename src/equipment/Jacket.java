package equipment;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : equipment.Jacket.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import effect.AgentProtection;
import player.Virologist;
import skeleton.Main;


public class Jacket extends Equipment {

	/**
	 * Köpeny. mely megvéd más virológusok általi kenéstõl
	 *
	 * @param v - v virológusra hat, nem kenhet rá más ágenst
	 */
	public void apply(Virologist v) {
		Main.printMethod("equipment.Jacket.apply()", true);
		v.addEffect(new AgentProtection());
		Main.printMethod("Return", false);
	}
}
