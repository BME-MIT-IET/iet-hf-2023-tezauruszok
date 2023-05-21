package field;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : field.Field.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
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

	///Megj:Felvettem plusz egy Konstruktort(D�vid)
	public Field(Integer fieldN){
		fieldNumber=fieldN;
	}

	/**
	 * Visszaadja egy adott mez� szomsz�dait
	 *
	 * @return - az adott mez� szomsz�dai
	 */
	public List<Field> getNeighbours() {
		Main.printMethod("field.Field.getNeighbours()", true);
		Main.printMethod("return <List>field.Field neighbours", false);
		return neighbours;
	}

	/**
	 * Sz�rmazott oszt�lyokon fel�l van defini�lva, itt hat�stalan f�ggv�ny
	 * A heterog�n kollekci�ban val� elt�rol�s miatt sz�ks�ges, hogy az �snek is legyen ilyen met�dusa
	 *
	 * @param v
	 */
	public void doAction(Virologist v) {
		Main.printMethod("field.Field.doAction()", false);
	}

	/**
	 * Ha a szomsz�djai k�zt van olyan, melynek fieldNumber attrib�tuma megegyezik a
	 * fieldNumb_ param�terrel, akkor visszat�r annak p�ld�ny�val
	 *
	 * @param fieldNumb_ - a mez� sz�ma
	 * @return - a szomsz�dos mez�k
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
	 * Ezzel �ll�that� a mez� szomsz�d list�ja
	 *
	 * @param fields - mez�ket tartalmaz� lista
	 */
	public void setNeighbours(List<Field> fields) {
		Main.printMethod("field.Field.setNeighbour()", true);
		neighbours = fields;
		Main.printMethod("Return", false);
	}

	/**
	 * Mez� sz�m�nak lek�rdez�se
	 *
	 * @return - a mez� sorsz�ma
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
	 * Met�dus, hogy hozz� lehessen adni egy fieldet a szomsz�dok kollekci�hoz
	 * @param f
	 */
	public void addNeighbour(Field f){
		neighbours.add(f);
	}
}
