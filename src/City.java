import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ez a palyat megvalosito osztaly. Field mezoket tartalmaz
 */
public class City {
	private ArrayList<Field> fields;
	/**
	 * A létrehozandó város konstruktora
	 * @param
	 */
	public City generate_City() {
		/**CIKLUS a betoltott palya mezoin:
		 *		mezo letrehozasa
		 *		elemek letrehozasa
		 *		mezore elemek lerakasa
		 *		mezo berakasa a City-be
		 *	CIKLUS 	VÉGE
		 *
		 */
		fields = new ArrayList<Field>();
		ArrayList<Point> points = new ArrayList<>();
		ArrayList<Point> points2 = new ArrayList<>();



		return this;
	}

	/**
	 * Konstruktor.
	 * @param f, mezok listaja
	 */
	public City(ArrayList<Field> f){
		fields = f;
	}


	/**
	 * Hozzaad a játék városához egy paraméterben kapott mezõt
	 * @param f, a hozzaadni kivant mezo
	 */
	public void addField(Field f) {
		fields.add(f);
		f.setId(fields.size() - 1);
	}

	/**
	 * Hozzaad a jatek varosahoz parameterben kapott mezoket
	 * @param f, a hozzaadni kivant mezok listaja
	 */
	public void addAll(ArrayList<Field> f){
		for(Field field: f)
			addField(field);
	}

	/**
	 * Getter, ami visszaadja a mezoket a palyan
	 * @return fields, mezok a palyan
	 */
	public ArrayList<Field> getFields(){
		return fields;
	}

	public ArrayList<Point> calcpoints(Point p1, Point p2, int n){
		ArrayList<Point> points = new ArrayList<>();
		Point avg = new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
		Point vec2 = new Point((avg.x - p1.x), (avg.y - p1.y));
		Point vec = new Point(-vec2.y, vec2.x);

		float x = (float)vec.x / 40;
		float y = (float)vec.y / 40;

		if(n == 3){
			Point p3 = new Point((int)(avg.x - x * (int)(Math.sqrt(3.0) * 40)), (int)(avg.y - y * (int)(Math.sqrt(3.0) * 40)));
			points.add(p3);
		}
		else{
			Point p3 = new Point((int)(p2.x - x * 80),  (int)(p2.y - y * 80));
			Point p4 = new Point((int)(p1.x - x * 80), (int)(p1.y - y * 80));
			points.add(p4);
			points.add(p3);
		}
		points.add(p2);
		points.add(p1);
		return points;
	}
}