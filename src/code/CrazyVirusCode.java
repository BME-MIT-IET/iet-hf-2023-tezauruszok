package code;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : code.CrazyVirusCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//
import agent.Agent;
import agent.CrazyVirus;
import player.Virologist;
import skeleton.Main;

import java.lang.Integer;


public class CrazyVirusCode extends GeneticCode {

	/**
	 *Létrehoz egy új agent.CrazyVirus ágenst, amennyiben a paraméterként kapott virológus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az új agent.CrazyVirus(Amennyiben nem jött létre, nullpointerrel tér vissza)
	 */

	public Agent generateAgent(Virologist v) {
		Main.printMethod("code.CrazyVirusCode.generateAgent()", true);
		if(v!=null){
			if(v.getAminoAcid().intValue()>=aminoAcidNeeded().intValue() && v.getNucleotide().intValue()>=nucleotideNeeded().intValue()){
				v.setAminoAcid(v.getAminoAcid().intValue()-aminoAcidNeeded().intValue());
				v.setNucleotide(v.getNucleotide().intValue()-nucleotideNeeded().intValue());
				Main.printMethod("Return: agent.CrazyVirus()", false);
				return new CrazyVirus();
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
	 *Visszaadja, hogy a Crazy Virus ágenshez mennyi aminosavra van szükség
	 *
	 * @return - A szükséges aminosavmennyiség
	 *
	 */
	
	public Integer aminoAcidNeeded() {
		Main.printMethod("code.CrazyVirusCode.aminoAcidNeeded()",true);
		Main.printMethod( "Return: 12", false);
		return 12;
	}

	/**
	 *Visszaadja, hogy a Crazy Virus ágenshez mennyi nukleotidra van szükség
	 *
	 * @return - A szükséges nukleotidmennyiség
	 *
	 */
	
	public Integer nucleotideNeeded() {
		Main.printMethod("code.CrazyVirusCode.nucleotideAcidNeeded() Return: 12", false);
		return 12;
	}
}
