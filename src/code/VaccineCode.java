package code;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : code.VaccineCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import agent.Agent;
import agent.Vaccine;
import player.Virologist;
import skeleton.Main;

import java.lang.Integer;

public class VaccineCode extends GeneticCode {

	/**
	 *L�trehoz egy �j agent.Vaccine �genst, amennyiben a param�terk�nt kapott virol�gus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az �j agent.Vaccine(Amennyiben nem j�tt l�tre, nullpointerrel t�r vissza)
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
	 *Visszaadja, hogy a agent.Vaccine �genshez mennyi aminosavra van sz�ks�g
	 *
	 * @return - A sz�ks�ges aminosavmennyis�g
	 *
	 */
	
	public Integer aminoAcidNeeded() {
		Main.printMethod("code.VaccineCode.aminoAcidNeeded()", true);
		Main.printMethod("Return Integer:10", false);
		return 10;
	}

	/**
	 *Visszaadja, hogy a agent.Vaccine �genshez mennyi nukleotidra van sz�ks�g
	 *
	 * @return - A sz�ks�ges nukleotidmennyis�g
	 *
	 */
	
	public Integer nucleotideNeeded() {
		Main.printMethod("code.VaccineCode.nucleotideNeeded()", true);
		Main.printMethod("Return Integer:10", false);
		return 10;
	}
}
