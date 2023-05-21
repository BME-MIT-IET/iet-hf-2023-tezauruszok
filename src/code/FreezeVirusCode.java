package code;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : code.FreezeVirusCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//

import agent.Agent;
import agent.FreezeVirus;
import player.Virologist;
import skeleton.Main;

import java.lang.Integer;


public class FreezeVirusCode extends GeneticCode {

	/**
	 *L�trehoz egy �j agent.FreezeVirus �genst, amennyiben a param�terk�nt kapott virol�gus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az �j agent.FreezeVirus(Amennyiben nem j�tt l�tre, nullpointerrel t�r vissza)
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
	 *Visszaadja, hogy a agent.FreezeVirus �genshez mennyi aminosavra van sz�ks�g
	 *
	 * @return - A sz�ks�ges aminosavmennyis�g
	 *
	 */

	public Integer aminoAcidNeeded() {
		Main.printMethod("code.FreezeVirusCode.aminoAcidNeeded()", true);
		Main.printMethod("Return: Integer:11", false);
		return 11;
	}

	/**
	 *Visszaadja, hogy a agent.FreezeVirus �genshez mennyi nukleotidra van sz�ks�g
	 *
	 * @return - A sz�ks�ges nukleotidmennyis�g
	 *
	 */

	public Integer nucleotideNeeded() {
		Main.printMethod("code.FreezeVirusCode.nucleotideNeeded()", true);
		Main.printMethod("Return: Integer:11", false);
		return 11;
	}


}
