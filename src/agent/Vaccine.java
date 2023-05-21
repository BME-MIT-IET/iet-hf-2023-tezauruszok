package agent;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : agent.Vaccine.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import effect.AgentProtection;
import player.Virologist;
import skeleton.Main;

public class Vaccine extends Agent {
	/**
	 * A v Virol�gushoz felveszi az agent protection hat�st.
	 * @param v
	 */
	public void apply(Virologist v) {
		Main.printMethod("agent.Vaccine.apply()", true);
		v.addEffect(new AgentProtection());
		Main.printMethod("Return", false);
	}
}
