package equipment;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : equipment.Glove.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//

import player.Virologist;
import skeleton.Main;


public class Glove extends Equipment {

	/**
	 * Visszakeni a kapott ágenst
	 *
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("equipment.Glove.apply()", true);
		Main.printMethod("Return", false);
	}
}
