import java.awt.*;
import java.util.ArrayList;

/**
 * A mezoket megvalosito osztaly
 */
public class Field {


	/**
	 * Színezéshez szükséges tulajdonság
	 */
	int colorCode = 0;

	/**
	 * Getter a színkódhoz
	 * @return a színkód
	 */
	public int getColorCode() {
		return colorCode;
	}
	/**
	 * Megadja, hogy hany oldalu a sokszog az adott mezon
	 */
	int side;
	/**
	 * Az adott mezovel szomszedos mezoket tartalmazo lista
	 */
	ArrayList<Field> neighbours;
	/**
	 * Az adott mezon talalhato virologusok listaja
	 */
	ArrayList<Virologist> virologists;
	/**
	 * Mezo egyedi azonosito sorszama
	 */
	int id;
	/**
	 * Szomszedos mezok egyedi azonosito sorszamainak listaja
	 */
	ArrayList<Integer> neighbourId = new ArrayList<>();
	/**
	 * Polygon osztalybeli attributum
	 */
	Polygon p;
	
	/**
	 * Kostruktor
	 * @param i, az oldalak szama
	 */
	public Field(int i) {
		side = i;
		neighbours = new ArrayList<Field>();
		virologists = new ArrayList<Virologist>();
	}

	/**
	 * Konstruktor.
	 * @param i, megadja, hogy hany oldalu a sokszog az adott mezon
	 * @param n, szomszedos mezok egyedi azonosito sorszamainak listaja
	 * @param points, megadja, hogy hany oldalo az adott mezo lista
	 */
	public Field(int i, ArrayList<Integer> n, ArrayList<Point> points){
		side = i;
		neighbourId.addAll(n);
		virologists = new ArrayList<Virologist>();
		neighbours = new ArrayList<Field>();
		if(points.size() == 3)
			p = new Triangle(points);
		else
			p = new Square(points);
		p.field = this;
	}
	
	/**
	 * Getter a szomszedokhoz, visszaadja az i. szomszedot
	 * @param i, a szomszed sorszama
	 * @return az i. szomszed
	 */
	public Field getNeighbour(int i) {
		
		if(neighbours.size()>i && i>=0) {
			return neighbours.get(i);
		}
		else {
			return null;
		}
	}
	/**
	 * Hozzaad szomszedkent egy mezot
	 * @param f, a hozzaadni kivant mezo
	 */
	public void addNeighbour2(Field f){
		neighbours.add(f);
	}
	
	/**
	 * Lekerdezi a mezot, hogy szomszedos-e a masik mezovel.
	 * @param f, a masik mezo
	 * @return szomszedsag igaz-e
	 */
	public boolean isNeighbour(Field f) {
		
		return neighbours.contains(f);
	}
	/**
	 * Hozzaadja a mezohoz a parameterkent kapott virologust
	 * @param v, a hozzaadni kivant virologus
	 */
	public void addVirologist(Virologist v) {
		virologists.add(v);
		p.addIcon(v.getVirologistIcon());
		
	}
	
	/**
	 * Eltavolitja a mezorol a parameterkent kapott virologust.
	 * @param v az eltavolitani kivant virologus
	 */
	public void removeVirologist(Virologist v) {
		virologists.remove(v);
		p.removeIcon(v.getVirologistIcon());
	}
	
	/**
	 * Hozzaad szomszedkent egy mezot
	 * @param f, a hozzaadni kivant mezo
	 */
	public void addNeighbour(Field f) {
		neighbours.add(f);
		f.neighbours.add(this);
	}
	
	/**
	 * Getter a mezo oldalainak szamahoz
	 * @return side, a mezo oldalainak szama
	 */
	public int getSide() {
		
		return side;
	}

	/**
	 * Getter a mezõ szomszédjaihoz
	 * @return neighbours a mezo szomszédjai
	 */
	public ArrayList<Field> getNeighbours() {
		return neighbours;
	}

	/**
	 * Függvény, mely visszaadja Stringben a mezo nevet
	 * @return a mezo neve
	 */
	public String toString(){
		/**
		 * print side, virologists
		 */

		return "Field";

	}

	/**
	 * Kiírja a mezõ tartalmát sorrendben
	 */
	public String listThings(int sor, boolean listVirologists, boolean listItems){
		String output ="";
		if(virologists.size() == 0) {
			//System.out.println("");
			output = "\n";
		}
		else if(listVirologists){
			for (int i = 0; i < virologists.size(); ++i) {
				//System.out.println(i + ". " + virologists.get(i).toString());
				output= output.concat(sor+i+1 +". " + virologists.get(i).toString() + "\n");
			}
		}
		return output;
	}

	/**
	 * Getter a mezõ virológusaihoz
	 * @return a virológusok
	 */
	public ArrayList<Virologist> getVirologists(){
		return virologists;
	}

	/**
	 * Getter a mezõ id-jához
	 * @return id, az adott mezo azonositoja
	 */
	public int getId(){
		return id;
	}

	/**
	 * Setter a mezõ id-jához
	 * @param value, mezo azonositoja
	 */
	public void setId(int value){
		id = value;
	}

	public void setPolygon(ArrayList<Point> polygonpoints){
		if(polygonpoints.size() == 4)
			p = new Square(polygonpoints);
		else
			p = new Triangle(polygonpoints);
		p.field = this;
	}

	/**
	 * Mezok poziciojat megvalosito fuggveny
	 * @return res.toString
	 */
	public String polygonposes(){
		StringBuilder res = new StringBuilder();
		res.append(p.points.get(0).x).append(",").append(p.points.get(0).y);
		for(int i = 1; i < side; ++i){
			res.append(",").append(p.points.get(i).x).append(",").append(p.points.get(i).y);
		}
		return res.toString();
	}
}
