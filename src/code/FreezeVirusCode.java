package code;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : code.FreezeVirusCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//

import agent.Agent;
import agent.FreezeVirus;
import player.Virologist;
import skeleton.Main;

import java.lang.Integer;


public class FreezeVirusCode extends GeneticCode {

	/**
	 *Létrehoz egy új agent.FreezeVirus ágenst, amennyiben a paraméterként kapott virológus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az új agent.FreezeVirus(Amennyiben nem jött létre, nullpointerrel tér vissza)
	 */

	public Agent generateAgent(Virologist v) {
		Main.printMethod("code.FreezeVirusCode.generateAgent()", true);

		if(v!=null){
			if(v.getAminoAcid().intValue()>=aminoAcidNeeded().intValue() && v.getNucleotide().intValue()>=nucleotideNeeded().intValue()){
				v.setAminoAcid(v.getAminoAcid().intValue()-aminoAcidNeeded().intValue());
				v.setNucleotide(v.getNucleotide().intValue()-nucleotideNeeded().intValue());
				Main.printMethod("Return: new agent.FreezeVirus()", false);
				return new FreezeVirus();
			}
			else{
				Main.printMethod("Return: null", false);
				return null;
			}
		}
		else{
			Main.printMethod("Return: null", false);
			return null;
		}
	}

	/**
	 *Visszaadja, hogy a agent.FreezeVirus ágenshez mennyi aminosavra van szükség
	 *
	 * @return - A szükséges aminosavmennyiség
	 *
	 */

	public Integer aminoAcidNeeded() {
		Main.printMethod("code.FreezeVirusCode.aminoAcidNeeded()", true);
		Main.printMethod("Return: Integer:11", false);
		return 11;
	}

	/**
	 *Visszaadja, hogy a agent.FreezeVirus ágenshez mennyi nukleotidra van szükség
	 *
	 * @return - A szükséges nukleotidmennyiség
	 *
	 */

	public Integer nucleotideNeeded() {
		Main.printMethod("code.FreezeVirusCode.nucleotideNeeded()", true);
		Main.printMethod("Return: Integer:11", false);
		return 11;
	}


}
