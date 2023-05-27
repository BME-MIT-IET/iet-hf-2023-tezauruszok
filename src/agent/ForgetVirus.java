package agent;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : agent.ForgetVirus.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import player.Virologist;
import skeleton.Main;

public class ForgetVirus extends Agent {
	/**
	 * A v virol�gusra megh�vja a Forgetvirsucode f�ggv�nyt.
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("agent.ForgetVirus.apply()", true);
		v.forgetGeneticCodes();
		Main.printMethod("return",false);
	}
}
