package code;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : code.CrazyVirusCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//
import agent.Agent;
import agent.CrazyVirus;
import player.Virologist;
import skeleton.Main;

import java.lang.Integer;


public class CrazyVirusCode extends GeneticCode {

	/**
	 *L�trehoz egy �j agent.CrazyVirus �genst, amennyiben a param�terk�nt kapott virol�gus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az �j agent.CrazyVirus(Amennyiben nem j�tt l�tre, nullpointerrel t�r vissza)
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
	 *Visszaadja, hogy a Crazy Virus �genshez mennyi aminosavra van sz�ks�g
	 *
	 * @return - A sz�ks�ges aminosavmennyis�g
	 *
	 */
	
	public Integer aminoAcidNeeded() {
		Main.printMethod("code.CrazyVirusCode.aminoAcidNeeded()",true);
		Main.printMethod( "Return: 12", false);
		return 12;
	}

	/**
	 *Visszaadja, hogy a Crazy Virus �genshez mennyi nukleotidra van sz�ks�g
	 *
	 * @return - A sz�ks�ges nukleotidmennyis�g
	 *
	 */
	
	public Integer nucleotideNeeded() {
		Main.printMethod("code.CrazyVirusCode.nucleotideAcidNeeded() Return: 12", false);
		return 12;
	}
}
