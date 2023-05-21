package field;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : field.Field.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//

import game.FieldV;
import player.Virologist;
import skeleton.Main;

import java.util.ArrayList;
import java.util.List;


public class Field {
	private Integer fieldNumber;
	private static Integer fieldNumberCounter = 0;
	private List<Field> neighbours;

	public FieldV getView() {
		return view;
	}

	public FieldV view;

	/**
	 * field.Field konstruktor
	 */
	public Field() {
		Main.printMethod("field.Field.field.Field()", true);
		fieldNumber = fieldNumberCounter++;

		neighbours = new ArrayList<>();

		Main.printMethod("return", false);
	}

	///Megj:Felvettem plusz egy Konstruktort(Dávid)
	public Field(Integer fieldN){
		fieldNumber=fieldN;
	}

	/**
	 * Visszaadja egy adott mezõ szomszédait
	 *
	 * @return - az adott mezõ szomszédai
	 */
	public List<Field> getNeighbours() {
		Main.printMethod("field.Field.getNeighbours()", true);
		Main.printMethod("return <List>field.Field neighbours", false);
		return neighbours;
	}

	/**
	 * Származott osztályokon felül van definiálva, itt hatástalan függvény
	 * A heterogén kollekcióban való eltárolás miatt szükséges, hogy az õsnek is legyen ilyen metódusa
	 *
	 * @param v
	 */
	public void doAction(Virologist v) {
		Main.printMethod("field.Field.doAction()", false);
	}

	/**
	 * Ha a szomszédjai közt van olyan, melynek fieldNumber attribútuma megegyezik a
	 * fieldNumb_ paraméterrel, akkor visszatér annak példányával
	 *
	 * @param fieldNumb_ - a mezõ száma
	 * @return - a szomszédos mezõk
	 */
	public Field getNeighbour(Integer fieldNumb_) {
		Main.printMethod("field.Field.GetNeighbour() ", true);
		for (Field f: neighbours) {
			if (f.getNumber().equals(fieldNumb_)) {
				Main.printMethod("Return: field.Field f", false);
				return f;
			}
		}
		Main.printMethod("Return: null", false);
		return null;
	}

	/**
	 * Ezzel állítható a mezõ szomszéd listája
	 *
	 * @param fields - mezõket tartalmazó lista
	 */
	public void setNeighbours(List<Field> fields) {
		Main.printMethod("field.Field.setNeighbour()", true);
		neighbours = fields;
		Main.printMethod("Return", false);
	}

	/**
	 * Mezõ számának lekérdezése
	 *
	 * @return - a mezõ sorszáma
	 */
	public Integer getNumber() {
		Main.printMethod("field.Field.GetNumber() Return: fieldNumber", true);
		Main.printMethod("return Integer fieldNumber", false);
		return fieldNumber;
	}

	public void setFieldNumber(int n) {
		fieldNumber = n;
	}

	/**
	 * Metódus, hogy hozzá lehessen adni egy fieldet a szomszédok kollekcióhoz
	 * @param f
	 */
	public void addNeighbour(Field f){
		neighbours.add(f);
	}
}
