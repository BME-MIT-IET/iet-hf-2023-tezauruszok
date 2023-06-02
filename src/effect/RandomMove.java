package effect;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : effect.RandomMove.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import player.Virologist;
import skeleton.Main;

import java.util.Random;

public class RandomMove extends Effect {
	/**
	 * A v virol�gus v�letlenszer� mozg�st fog v�gezni a p�ly�n k�t k�r�n �t
	 * @param v
	 */
	public void effect(Virologist v, Random rand) {
		Main.printMethod("effect.RandomMove.effect()", true);

		for (int i = 0; i < 5; i++) {
			Integer neighbourCount = v.getMyField().getNeighbours().size();
			Integer direction = rand.nextInt(neighbourCount);
			v.move(v.getMyField().getNeighbours().get(direction).getNumber());
		}

		Main.printMethod("return", false);
	}
}
