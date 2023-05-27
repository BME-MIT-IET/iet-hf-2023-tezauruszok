package agent;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : agent.FreezeVirus.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import effect.Frozen;
import player.Virologist;
import skeleton.Main;

public class FreezeVirus extends Agent {

	/**
	 * v virol�gushoz felveszi az effect.Frozen hat�st.
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("agent.FreezeVirus.apply()", true);
		v.addEffect(new Frozen());
		Main.printMethod("Return ", false);
	}
}
