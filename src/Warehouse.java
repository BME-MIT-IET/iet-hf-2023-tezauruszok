 import java.awt.*;
 import java.util.ArrayList;


 /**
  * A raktart megvalosito osztaly
  */
public class Warehouse extends Field{
	/**
	 * A mezon talalhato anyagok listaja
	 */
	private ArrayList<Resource> res = new ArrayList<>();
	
	/**
	 * Konstuktor
	 * @param i, az oldalak szama
	 * @param r, a mezon levo anyag
	 */
	public Warehouse(int i, Resource r) {
		super(i);
		colorCode = 3;
		res = new ArrayList<>();
		res.add(r);
	}
	
	/**
	 * Konstruktor
	 * @param i, az oldalak szama
	 */
	public Warehouse(int i) {
		super(i);
		colorCode = 3;
		res = new ArrayList<>();
	}
	/**
	 * Konstruktor
	 * @param i, az oldalak szama
	 * @param g, az anyagokrol egy lista
	 * @param n, szomszedokrol egy lista
	 * @param points, oldalak szama
	 */
	public Warehouse(int i, ArrayList<Resource> g, ArrayList<Integer> n, ArrayList<Point> points){
		super(i, n, points);
		colorCode = 3;
		res.addAll(g);
	}
	
	/**
	 * Torli a parameterkent kapott anyagot, majd visszater vele
	 * @param r, a torolni kivant anyag
	 * @return r, a torolt anyag
	 */
	public Resource removeResource(Resource r) {
		res.remove(r);
		return r;
	}
	
	/**
	 * Hozzaadja a listahoz a parameterkent kapott anyagot
	 * @param r, a torolni kivant anyag
	 */
	public void addResource(Resource r) {
		
		res.add(r);
	}
	/**
	 * Hozzaadja a virologusokhoz a kapott virologust
	 * @param v, a hozzadni kivant virologus
	 */
	public void addVirologist(Virologist v){
		/**	HA v.getBear
		 * 		destroyResource
		 *	virologust hozzáadjuk a listához
		 *
		 */
		if(v.getisBear()){
			destroyResources();
		}
		virologists.add(v);
		p.addIcon(v.getVirologistIcon());

	}
	/**
	 * Eltavolit egy anyagot a mezo resource listajabol
	 */
	public void destroyResources(){
		/**PSZEUDOKOD
		 * CIKLUS A JELENLEG MEZON LEVO ANYAGOKON:
		 * 		ADOTT ANYAGON DESTROYRESOURCE FV hivasa
		 * KESZ
		 *
		 *
		 */
		for(int i = 0; i < res.size(); i++){
			res.remove(res.get(i));
		}
	}

	/**
	 * Függvény, mely visszaadja Stringben a mezõ nevét
	 * @return a mezõ neve
	 */
	public String toString(){

		/**
		 * ciklus res.toString
		 */
		return "Warehouse";
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
		if(virologists.size() == 0 && res.size() == 0)
			//System.out.println();
			output = "\n";
		else if(listItems){
			for(int i = 0; i < res.size(); ++i) {
				//System.out.println(i + ". " + res.get(i).toString());
				output = output.concat(i + 1 + ". " + res.get(i).toString() + "\n");
				sorszam = i + 1;
			}
		}
		if(virologists.size() != 0)
			output=output.concat(super.listThings(sorszam, listVirologists, listItems));

		return output;
	}

	/**
	 * Getter a mezõn lévõ alapanyagokhoz
	 * @param id,  a visszakapni kívánt alapanyag id-ja
	 * @return res, a mezõn lévõ alapanyag / null ha nincs rajta
	 */
	public Resource getRes(int id){
		for (int i = 0; i < res.size(); i++) {
			if(res.get(i).getId()==id){
				return res.get(i);
			}
		}
		return null;
	}

	/**
	 * Getter a mezõn lévõ alapanyagokhoz
	 * @return a mezõn lévõ alapanyagok
	 */
	public ArrayList<Resource> getRes(){
		return res;
	}

}
