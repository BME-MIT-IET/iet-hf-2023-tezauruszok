package field;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : field.Storage.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import player.Virologist;
import skeleton.Main;

import java.util.Random;

public class Storage extends Field {
	public Integer aminoAcid;
	public Integer nucleotide;

	/**
	 * field.Storage konstruktor
	 */
	public Storage(){
		super(); // Counter miatt
		Main.printMethod("field.Storage.field.Storage()", true);

		regenerate();
		Main.printMethod("Return", false);
	}

	///Megj:Felvettem plusz egy Konstruktort(Dávid)
	public Storage(Integer fieldN){
		super(fieldN);
		regenerate();

	}

	private void regenerateRandom() {
		Main.printMethod("field.Storage.regenerate()", true);
		Random rand = new Random();

		aminoAcid = rand.nextInt(5) + 1;
		nucleotide = rand.nextInt(5) + 1;
		Main.printMethod("Return", false);
	}

	private void regenerate() {
		Main.printMethod("field.Storage.regenerate()", true);
		aminoAcid = 21;
		nucleotide = 21;
		Main.printMethod("Return", false);
	}

	/**
	 * v virológushoz megpróbálja hozzáadni az aminoAcid vagy nucleotide mennyiséget,
	 * ameddig ez nem lépi túl a megengedett mennyiséget.
	 * Ez az érték mindkét esetben 20
	 *
	 * @param v
	 */
	public void doAction(Virologist v) {
		Main.printMethod("field.Storage.doAction()", true);
		v.increaseNucleotide(nucleotide);
		v.increaseAminoAcid(aminoAcid);

		regenerate();
		Main.printMethod("Return", false);
	}
}

