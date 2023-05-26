 import java.util.ArrayList;

 /**
  * Felszereleseket tarolo heterogen kollekcio
  */
 public class Equipment_s implements ThingList {
	 /**
	  * Felszereleseket tartalmazo lista
	  */
	ArrayList<Equipment> equipment = new ArrayList<>();
	
	/**
	 * Kostruktor
	 */
	public Equipment_s() {
		equipment = new ArrayList<Equipment>();
	}

	 /**
	  * Konstruktor
	  * @param e, felszereleseket tartalmazo lista
	  */
	public Equipment_s(ArrayList<Equipment> e){
		equipment.addAll(e);
	}
	
	/**
	 * Hozzaadja a parameterkent kapott objektumot a listahoz.
	 * @param o, a hozzaadni kivant objektum
	 */
	public void add(Object o) {
		equipment.add((Equipment)o);

	}
	
	/**
	 * Torli a parameterkent kapott objektumot a listabol, majd visszater vele.
	 * @param o, torolni kivant objektum
	 * @return o, torolt objektum
	 */
	public Object remove(Object o) {
		equipment.remove(o);
		return o;
	}
	 /**
	  * Torli a parameterkent kapott felszerelest a listabol, majd visszater vele.
	  * @param id, torolni kivant felszereles azonositoja
	  * @return torolt felszereles utani lista
	  */
	public Equipment remove(int id){
		for(int i = 0; i < equipment.size(); i++){
			if(equipment.get(i).getId() == id){
				return equipment.remove(i);
			}
		}
		return null;
	}

	/**
	 * Visszaadja a parameterkent kapott id-nak megfelelo felszerelest
	 * @param id
	 * @return a megfelelo felszereles (Equipment tipus) vagy Null
	 */
	public Equipment getEquipment(int id){
		/**PSZEUDOKÓD
		 * CIKLUS A FELSZERELÉSEKEN
		 * 		AZ ID-ja megegyezik a paraméterben kapottal?
		 * 			adjuk vissza ezt a felszerelést
		 * 	Adjunk vissza NULL-t
		 */
		for(int j=0; j<equipment.size(); j++){
			if(equipment.get(j).getId()==id){
				return equipment.get(j);
			}
		}
		return null;
	}

	 /**
	  * Getter, ami visszaadja a felszerelesek listajat
	  * @return equipment, felszerelesek listaja
	  */
	public ArrayList<Equipment> getEquipment(){
		return equipment;
	}
}
