 import java.awt.*;
 import java.util.ArrayList;
 /**
  * A ovohelyet megvalosito osztalyt
  */
public class Shelter extends Field{
	/**
	 * A mezon levo felszerelesek listaja
	 */
	private ArrayList<Equipment> eq = new ArrayList<>();
	
	/**
	 * Konstuktor
	 * @param i, az oldalak szama
	 * @param e, a mezon levo felszereles
	 */
	public Shelter(int i, Equipment e) {
		super(i);
		colorCode = 2;
		eq = new ArrayList<>();
		eq.add(e);
	}
	/**
	 * Konstruktor
	 * @param i, az oldalak szama
	 */
	public Shelter(int i) {
		super(i);
		colorCode = 2;
		eq = new ArrayList<>();
	}
	/**
	 * Konstruktor
	 * @param i, az oldalak szama
	 * @param g, felszerelesek a mezon
	 * @param n, hany szomszedja van
	 * @param points, hany pontja van a mezonek
	 */
	public Shelter(int i, ArrayList<Equipment> g, ArrayList<Integer> n, ArrayList<Point> points){
		super(i, n, points);
		colorCode = 2;
		eq.addAll(g);
	}
	
	/**
	 * Torli a parameterkent kapott felszerelest, majd visszater vele
	 * @param e, a torolni kivant felszereles
	 * @return e, a torolt felszereles
	 */
	public Equipment removeEquipment(Equipment e) {

		eq.remove(e);
		
		return e;
	}
	
	/**
	 * Hozzaadja a listahoz a parameterkent kapott felszerelest
	 * @param e, a torolni kivant felszereles
	 */
	public void addEquipment(Equipment e) {

		eq.add(e);
		
	}

	/**
	 * Getter a mezõn lévõ felszerelésekhez
	 * @param id,  a visszakapni kívánt felszereleés id-ja
	 * @return res, a mezõn lévõ felszerelés / null ha nincs rajta
	 */
	public Equipment getEq(int id){
		for (int i = 0; i < eq.size(); i++) {
			if(eq.get(i).getId()==id){
				return eq.get(i);
			}
		}
		return null;
	}
	/**
	 * Függvény, mely visszaadja Stringben a mezõ nevét
	 * @return a mezõ neve
	 */
	public String toString(){

		/**
		 * ciklus eq.toString
		 */
		return "Shelter";
	}

	/**
	 * Kiírja a mezõ tartalmát sorrendben
	 * @param sor,
	 * @param listVirologists, kiirja e a virologusokat
	 * @param listItems, kiirja e a dolgokat
	 * @return a kimenettel ter vissza
	 */
	public String listThings(int sor, boolean listVirologists, boolean listItems){
		String output = "";
		int sorszam=0;
		if(virologists.size() == 0 && eq.size() == 0)
			//System.out.println();
			output = "\n";
		else if(listItems){
			for(int i = 0; i < eq.size(); ++i) {
				//System.out.println(i + ". " + res.get(i).toString());
				output = output.concat(i + 1 + ". " + eq.get(i).toString() + "\n");
				sorszam = i + 1;
			}
		}
		if(virologists.size() != 0)
			output=output.concat(super.listThings(sorszam, listVirologists, listItems));

		return output;
	}

	/**
	 * Getter a mezõn lévõ felszerelésekhez
	 * @return a mezõn lévõ felszerelések
	 */
	public ArrayList<Equipment> getEq(){
		return eq;
	}
}
