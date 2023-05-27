package field;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : field.Storage.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
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

	///Megj:Felvettem plusz egy Konstruktort(D�vid)
	public Storage(Integer fieldN){
		super(fieldN);
		regenerate();

	}

	private void regenerate() {
		Main.printMethod("field.Storage.regenerate()", true);
		Random rand = new Random();

		aminoAcid = rand.nextInt(5) + 1;
		nucleotide = rand.nextInt(5) + 1;
		Main.printMethod("Return", false);
	}

	/**
	 * v virol�gushoz megpr�b�lja hozz�adni az aminoAcid vagy nucleotide mennyis�get,
	 * ameddig ez nem l�pi t�l a megengedett mennyis�get.
	 * Ez az �rt�k mindk�t esetben 20
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

