package field;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : field.Laboratory.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import code.GeneticCode;
import player.Virologist;
import skeleton.Main;

public class Laboratory extends Field {
	private static Integer laborCounter = 0;
	private GeneticCode geneticCode;

	/**
	 *
	 *field.Laboratory konstruktor, inicializálja a genetikai kódot
	 */
	public Laboratory(GeneticCode gc) {
		Main.printMethod("field.Laboratory.field.Laboratory()", true);
		geneticCode = gc;
		Main.printMethod("Return", false);
	}

	///Megj:Felvettem plusz egy Konstruktort(Dávid)
	public Laboratory(GeneticCode gc, Integer fieldN){
		super(fieldN);
		geneticCode = gc;

	}
	public Laboratory(int fn) {
		super(fn);
	}

	/*
	public field.Laboratory() {
		switch (laborCounter++ % 4) {
			case 0:
				geneticCode = new code.CrazyVirusCode();
				break;
			case 1:
				geneticCode = new code.ForgetVirusCode();
				break;
			case 2:
				geneticCode = new code.FreezeVirusCode();
				break;
			case 3:
				geneticCode = new code.VaccineCode();
				break;
		}
	}
	*/

	/**
	 * v virológushoz megpróbálja hozzáadni az agent változó tartalmát.
	 * A „próbálja” arra vonatkozik, hogy ha már van egy ilyen
	 *  genetikai kódja a virológusnak, akkor nem történik semmi
	 *
	 * @param v
	 */
	public void doAction(Virologist v) {
		Main.printMethod("field.Laboratory.doAction()", true);
		v.learnCode(geneticCode);
		Main.printMethod("Return", false);
	}
}
