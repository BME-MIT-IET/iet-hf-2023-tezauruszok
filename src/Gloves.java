/**
 * A kesztyut megvalosito osztalyt
 */
public class Gloves extends Equipment{

	/**
	 * Konstruktor
	 */
	public Gloves(){
		super(3, 3);
	}

	/**
	 * Az kesztyu hatasat aktivalja ez a fuggveny a parameterkent
	 * kapott virologuson.
	 * @param v, akire hatasa lesz
	 */
	public void enable(Virologist v) {

		v.setIsGlovesActive(true);
		//ezt lehetne a kör elején valami külön függvényben alkalmazni
		
	}
	
	/**
	 * Az kesztyu hatastalanitja a parameterkent kapott virologuson.
	 * @param v, akirol levesszuk a hatast
	 */
	public void disable(Virologist v) {

		v.setIsGlovesActive(false);
		
	}

	/**
	 * Függvény, mely visszaadja Stringben a dolog nevét
	 * @return a felszereles  neve
	 */
	public String toString(){return "Gloves";}
}
