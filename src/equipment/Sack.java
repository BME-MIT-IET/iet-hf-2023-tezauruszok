package equipment;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : equipment.Sack.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import player.Virologist;
import skeleton.Main;

public class Sack extends Equipment {

	/**
	 * Maximum anyagt�rol� mennyis�get n�veli virol�gusn�l
	 *
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("equipment.Sack.apply()", true);
		v.doubleCapacity();
		Main.printMethod("return", false);
	}
}
