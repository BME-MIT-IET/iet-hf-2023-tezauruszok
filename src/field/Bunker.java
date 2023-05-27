package field;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : field.Bunker.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import equipment.Equipment;
import player.Virologist;
import skeleton.Main;

public class Bunker extends Field {
	private Equipment equipment;

	/**
	 * field.Bunker konstruktor, inicializálja az felszerelést
	 *
	 */
	public Bunker(Equipment e) {
		Main.printMethod("field.Bunker.field.Bunker()", true);
		equipment = e;
		Main.printMethod("return", false);
	}

	///Megj:Felvettem plusz egy Konstruktort(Dávid)
	public Bunker(Equipment e,Integer fieldN){
		super(fieldN);
		equipment=e;
	}

	public Bunker(Integer fieldN) {
		super(fieldN);
	}


	/**
	 * v virológushoz megpróbálja hozzáadni az equipment változó tartalmát
	 *
	 * @param v
	 */
	public void doAction(Virologist v) {
		Main.printMethod("field.Bunker.doAction()", true);
		boolean isNotNull=v.pickUpEquipment(equipment);
		if(isNotNull){
			equipment.apply(v);
		}
		Main.printMethod("return", false);
	}
}
