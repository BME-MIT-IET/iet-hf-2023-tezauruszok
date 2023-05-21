package code;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : code.VaccineCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import agent.Agent;
import agent.Vaccine;
import player.Virologist;
import skeleton.Main;

import java.lang.Integer;

public class VaccineCode extends GeneticCode {

	/**
	 *Létrehoz egy új agent.Vaccine ágenst, amennyiben a paraméterként kapott virológus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az új agent.Vaccine(Amennyiben nem jött létre, nullpointerrel tér vissza)
	 */

	public Agent generateAgent(Virologist v) {
		Main.printMethod("code.VaccineCode.generateAgent()", true);
		if(v!=null){
			if(v.getAminoAcid()>=aminoAcidNeeded() && v.getNucleotide()>=nucleotideNeeded()){
					v.setAminoAcid(v.getAminoAcid()-aminoAcidNeeded());
					v.setNucleotide(v.getNucleotide()-nucleotideNeeded());
					Main.printMethod(" Return: new agent.Vaccine()", false);
					return new Vaccine();
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
	 *Visszaadja, hogy a agent.Vaccine ágenshez mennyi aminosavra van szükség
	 *
	 * @return - A szükséges aminosavmennyiség
	 *
	 */
	
	public Integer aminoAcidNeeded() {
		Main.printMethod("code.VaccineCode.aminoAcidNeeded()", true);
		Main.printMethod("Return Integer:10", false);
		return 10;
	}

	/**
	 *Visszaadja, hogy a agent.Vaccine ágenshez mennyi nukleotidra van szükség
	 *
	 * @return - A szükséges nukleotidmennyiség
	 *
	 */
	
	public Integer nucleotideNeeded() {
		Main.printMethod("code.VaccineCode.nucleotideNeeded()", true);
		Main.printMethod("Return Integer:10", false);
		return 10;
	}
}
