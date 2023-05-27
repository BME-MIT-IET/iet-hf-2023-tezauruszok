package equipment;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : equipment.Sack.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import player.Virologist;
import skeleton.Main;

public class Sack extends Equipment {

	/**
	 * Maximum anyagtároló mennyiséget növeli virológusnál
	 *
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("equipment.Sack.apply()", true);
		v.doubleCapacity();
		Main.printMethod("return", false);
	}
}
