package code;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : code.ForgetVirusCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import agent.Agent;
import agent.ForgetVirus;
import player.Virologist;
import skeleton.Main;

public class ForgetVirusCode extends GeneticCode {

	/**
	 *Létrehoz egy új agent.ForgetVirus ágenst, amennyiben a paraméterként kapott virológus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az új agent.ForgetVirus(Amennyiben nem jött létre, nullpointerrel tér vissza)
	 */

	public Agent generateAgent(Virologist v) {
		Main.printMethod("code.ForgetVirusCode.generateAgent()",true);
		if(v!=null){
			if(v.getAminoAcid().intValue()>=aminoAcidNeeded().intValue() && v.getNucleotide().intValue()>=nucleotideNeeded().intValue()){
				v.setAminoAcid(v.getAminoAcid().intValue()-aminoAcidNeeded().intValue());
				v.setNucleotide(v.getNucleotide().intValue()-nucleotideNeeded().intValue());
				Main.printMethod("Return: new agent.ForgetVirus()", false);
				return new ForgetVirus();
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
	 *Visszaadja, hogy a agent.ForgetVirus ágenshez mennyi aminosavra van szükség
	 *
	 * @return - A szükséges aminosavmennyiség
	 *
	 */
	
	public Integer aminoAcidNeeded() {
		Main.printMethod("code.ForgetVirusCode.aminoAcidNeeded()", true);
		Main.printMethod("Return Integer:13", false);
		return 13;
	}

	/**
	 *Visszaadja, hogy a agent.ForgetVirus ágenshez mennyi nukleotidra van szükség
	 *
	 * @return - A szükséges nukleotidmennyiség
	 *
	 */
	
	public Integer nucleotideNeeded() {
		Main.printMethod("code.ForgetVirusCode.nucleotideNeeded() ", true);
		Main.printMethod("Return: Integer: 13 ", false);
		return 13;
	}
}
