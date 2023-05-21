package agent;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : agent.Agent.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//
import player.Virologist;
import skeleton.Main;


public abstract class Agent {
	private Virologist myMagics;

	public void apply(Virologist v) {
		Main.printMethod("agent.Agent.apply()", true);
		Main.printMethod("return", false);

	}
}
