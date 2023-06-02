package field;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : field.Storage.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import player.Virologist;
import random.RandomContainer;
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

		regenerate(RandomContainer.rand);
		Main.printMethod("Return", false);
	}

	///Megj:Felvettem plusz egy Konstruktort(D�vid)
	public Storage(Integer fieldN){
		super(fieldN);
		regenerate(RandomContainer.rand);

	}

	private void regenerate(Random rand) {
		Main.printMethod("field.Storage.regenerate()", true);

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

		regenerate(RandomContainer.rand);
		Main.printMethod("Return", false);
	}
}

