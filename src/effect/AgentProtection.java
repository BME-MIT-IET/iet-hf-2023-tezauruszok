package effect;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : effect.AgentProtection.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import player.Virologist;
import skeleton.Main;

public class AgentProtection extends Effect {
	/**
	 * A v virológusra alkamazva megvédi õt két körig a rá alkalmazott ágensek hatásától.
	 * @param v
	 */
	public void effect(Virologist v) {
		Main.printMethod("effect.AgentProtection.effect()", true);
		Main.printMethod("return", false);
	}
}
