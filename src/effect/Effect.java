package effect;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : effect.Effect.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import player.Virologist;
import skeleton.Main;

public abstract class Effect {
	private Integer duration;
	private String name;

	public void effect(Virologist v) {

	}

	/**
	 * Visszaadja az effekt nev�t
	 *
	 * @return - az effekt neve
	 */
	public String getName() {

       Main.printMethod("effect.Effect.getName()",true);
	   Main.printMethod("return String", false);
		return this.getClass().toString();
	}
	
	public Boolean tick() {
		Main.printMethod("effect.Effect.tick()",true);
		Main.printMethod("return Boolean", false);
		return null;
	}
}
