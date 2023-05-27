package code;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : code.GeneticCode.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import agent.Agent;
import player.Virologist;

public abstract class GeneticCode {
	/**
	 *A leszármazottja létrehoz egy új ágenst, amennyiben a paraméterként kapott virológus ezt megteheti
	 *
	 * @param v
	 *
	 * @return - Az új ágens(Amennyiben nem jött létre, nullpointerrel tér vissza)
	 */
	public abstract Agent generateAgent(Virologist v);

	/**
	 *Visszaadja, hogy a leszármazott ágenshez mennyi aminosavra van szükség
	 *
	 * @return - A szükséges aminosavmennyiség
	 *
	 */
	public abstract Integer aminoAcidNeeded();

	/**
	 *Visszaadja, hogy a leszármazott ágenshez mennyi nukleotidra van szükség
	 *
	 * @return - A szükséges nukleotidmennyiség
	 *
	 */

	public abstract Integer nucleotideNeeded();
}
