package code;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : code.ForgetVirusCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import agent.Agent;
import agent.ForgetVirus;
import player.Virologist;
import skeleton.Main;

public class ForgetVirusCode extends GeneticCode {

	/**
	 *L�trehoz egy �j agent.ForgetVirus �genst, amennyiben a param�terk�nt kapott virol�gus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az �j agent.ForgetVirus(Amennyiben nem j�tt l�tre, nullpointerrel t�r vissza)
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
	 *Visszaadja, hogy a agent.ForgetVirus �genshez mennyi aminosavra van sz�ks�g
	 *
	 * @return - A sz�ks�ges aminosavmennyis�g
	 *
	 */
	
	public Integer aminoAcidNeeded() {
		Main.printMethod("code.ForgetVirusCode.aminoAcidNeeded()", true);
		Main.printMethod("Return Integer:13", false);
		return 13;
	}

	/**
	 *Visszaadja, hogy a agent.ForgetVirus �genshez mennyi nukleotidra van sz�ks�g
	 *
	 * @return - A sz�ks�ges nukleotidmennyis�g
	 *
	 */
	
	public Integer nucleotideNeeded() {
		Main.printMethod("code.ForgetVirusCode.nucleotideNeeded() ", true);
		Main.printMethod("Return: Integer: 13 ", false);
		return 13;
	}
}
