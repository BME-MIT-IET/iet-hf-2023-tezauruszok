package field;//
//
//  @ Project : Vak Var�zsl�k F�ldje
//  @ File Name : field.Bunker.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
//
//


import equipment.Equipment;
import player.Virologist;
import skeleton.Main;

public class Bunker extends Field {
	private Equipment equipment;

	/**
	 * field.Bunker konstruktor, inicializ�lja az felszerel�st
	 *
	 */
	public Bunker(Equipment e) {
		Main.printMethod("field.Bunker.field.Bunker()", true);
		equipment = e;
		Main.printMethod("return", false);
	}

	///Megj:Felvettem plusz egy Konstruktort(D�vid)
	public Bunker(Equipment e,Integer fieldN){
		super(fieldN);
		equipment=e;
	}

	public Bunker(Integer fieldN) {
		super(fieldN);
	}


	/**
	 * v virol�gushoz megpr�b�lja hozz�adni az equipment v�ltoz� tartalm�t
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
