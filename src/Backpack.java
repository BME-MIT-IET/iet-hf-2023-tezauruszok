 import java.util.ArrayList;
 import java.util.Collections;

 /**
  * Az agensekhez szukseges anyagokat tarolo heterogen kollekcio
  */
 public class Backpack implements ThingList {
	/**
	 * hatizsak merete
	 */
	private int size;
	 /**
	  * hatizsak maximum merete
	  */
	private static int maxsize;
	 /**
	  * anyagok listaja
	  */
	private ArrayList<Resource> resources = new ArrayList<>();
	
	/**
	 * Konstruktor.
	 * @param i, hatizsak merete
	 */
	public Backpack(int i) {
		size = i;
		resources = new ArrayList<Resource>();
	}
	 /**
	  * Konstruktor.
	  * @param i, hatizsak merete
	  * @param j, hatizsak maximum merete
	  * @param re, anyagok listaja
	  */
	public Backpack(int i, int j, ArrayList<Resource> re){
		size = i;
		maxsize = j;
		resources.addAll(re);
	}
	
	/**
	 * Getter a merethez.
	 * @return size, a hatizsak merete
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Getter a maradek merethez.
	 * @return resources.size() - size, a hatizsak maradek merete
	 */
	public int getRemainingSize(){
		return size - resources.size();
	}
	
	/**
	 * Setter a merethez.
	 * @param newsize, a hatizsak uj merete.
	 */
	public void setSize(int newsize) {

		size = newsize;
		
	}
	
	/**
	 * Getter a maximalis merethez.
	 * @return maxsize, a hatizsak maximalis merete.
	 */
	public int getMaxSize() {

		return maxsize;
	}
	
	/**
	 * Beteszi a hatizsak listajaba a parameterkent kapott objektumot.
	 * @param o, a betenni kivant objektum.
	 */
	public void add(Object o) {

		resources.add((Resource) o);
	}
	
	/**
	 * Kiveszi a listabol a parameterkent kapott objektumot.
	 * @param o, a kivenni kivant objetktum.
	 * @return o, a kivett objektum.
	 */
	public Resource remove(Object o) {

		resources.remove(o);
		
		return (Resource) o;
	}
	 /**
	  * Kiveszi a listabol a parameterkent kapott anyagot.
	  * @param id, a kivenni kivant anyag.
	  * @return a kivett anyag utani lista.
	  */
	public Resource remove(int id){
		for(int i = 0; i < resources.size(); i++){
			if(resources.get(i).getId() == id){
				return resources.remove(i);
			}
		}
		return null;
	}
	
//needs implementation
	/**
	 * A parameterkent kapott agens elkeszitesehez szukseges mennyisegu anyagot kivesz a listabol.
	 * @param a, az agens amit el akarunk kesziteni.
	 * @return hogy sikerult-e.
	 */
	public boolean consume(Agent a) {
		ArrayList<Resource> tmp1 = new ArrayList<Resource>();
		ArrayList<Resource> tmp2 = new ArrayList<Resource>();
		for(int i = 0; i < a.getNucleo_cost(); i++){
			for(int j = 0; j < resources.size(); j++){
				if(resources.get(j).getId() == 2){
					tmp1.add(resources.remove(j));
					break;
				}
			}
		}
		for(int i = 0; i < a.getAmino_cost(); i++){
			for(int j = 0; j < resources.size(); j++){
				if(resources.get(j).getId() == 1){
					tmp2.add(resources.remove(j));
					break;
				}
			}
		}

		if(tmp1.size() != a.getNucleo_cost() || tmp2.size() != a.getAmino_cost()){
			resources.addAll(tmp1);
			resources.addAll(tmp2);
			return false;
		}

		return true;
	}
	 /**
	  * Getter, amivel lekerdezhetjuk, az anyagokat a hatizsakban
	  * @return resources, anyagok listaja
	  */
	public ArrayList<Resource> getResources(){
		return resources;
	}
}
