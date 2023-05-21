package code;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : code.GeneticCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import agent.Agent;
import player.Virologist;

public abstract class GeneticCode {
	/**
	 *A lesz�rmazottja l�trehoz egy �j �genst, amennyiben a param�terk�nt kapott virol�gus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az �j �gens(Amennyiben nem j�tt l�tre, nullpointerrel t�r vissza)
	 */
	public abstract Agent generateAgent(Virologist v);

	/**
	 *Visszaadja, hogy a lesz�rmazott �genshez mennyi aminosavra van sz�ks�g
	 *
	 * @return - A sz�ks�ges aminosavmennyis�g
	 *
	 */
	public abstract Integer aminoAcidNeeded();

	/**
	 *Visszaadja, hogy a lesz�rmazott �genshez mennyi nukleotidra van sz�ks�g
	 *
	 * @return - A sz�ks�ges nukleotidmennyis�g
	 *
	 */

	public abstract Integer nucleotideNeeded();
}
