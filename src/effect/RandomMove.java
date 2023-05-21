package effect;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : effect.RandomMove.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import player.Virologist;
import skeleton.Main;

import java.util.Random;

public class RandomMove extends Effect {
	/**
	 * A v virológus véletlenszerõ mozgást fog végezni a pályán két körön át
	 * @param v
	 */
	public void effect(Virologist v) {
		Main.printMethod("effect.RandomMove.effect()", true);

		Random rand = new Random();

		for (int i = 0; i < 5; i++) {
			Integer neighbourCount = v.getMyField().getNeighbours().size();
			Integer direction = rand.nextInt(neighbourCount);
			v.move(v.getMyField().getNeighbours().get(direction).getNumber());
		}

		Main.printMethod("return", false);
	}
}
