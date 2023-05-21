package equipment;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : equipment.Jacket.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import effect.AgentProtection;
import player.Virologist;
import skeleton.Main;


public class Jacket extends Equipment {

	/**
	 * K�peny. mely megv�d m�s virol�gusok �ltali ken�st�l
	 *
	 * @param v - v virol�gusra hat, nem kenhet r� m�s �genst
	 */
	public void apply(Virologist v) {
		Main.printMethod("equipment.Jacket.apply()", true);
		v.addEffect(new AgentProtection());
		Main.printMethod("Return", false);
	}
}
