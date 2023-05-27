package field;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : field.Laboratory.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
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
	 *field.Laboratory konstruktor, inicializ�lja a genetikai k�dot
	 */
	public Laboratory(GeneticCode gc) {
		Main.printMethod("field.Laboratory.field.Laboratory()", true);
		geneticCode = gc;
		Main.printMethod("Return", false);
	}

	///Megj:Felvettem plusz egy Konstruktort(D�vid)
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
	 * v virol�gushoz megpr�b�lja hozz�adni az agent v�ltoz� tartalm�t.
	 * A �pr�b�lja� arra vonatkozik, hogy ha m�r van egy ilyen
	 *  genetikai k�dja a virol�gusnak, akkor nem t�rt�nik semmi
	 *
	 * @param v
	 */
	public void doAction(Virologist v) {
		Main.printMethod("field.Laboratory.doAction()", true);
		v.learnCode(geneticCode);
		Main.printMethod("Return", false);
	}
}
