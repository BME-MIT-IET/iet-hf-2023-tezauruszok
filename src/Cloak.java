/**
 * Vedelmet biztosit a virologusnak, aki eppen hasznalja ezt a felszerelest
 */
public class Cloak extends Equipment{

	/**
	 * Konstruktor.
	 */
	public Cloak(){
		super(0,2);
	}
	/**
	 * Az kopeny hatasat aktivalja ez a fuggveny a parameterkent
	 * kapott virologuson.
	 * @param v, akire hatasa lesz
	 */
	public void enable(Virologist v) {

		v.setIsCloakActive(true);
		//ennek hatásai a virológus spread függvényében érvényesülnek
		
	}
	
	/**
	 * Az kopenyet hatastalanitja a parameterkent kapott virologuson.
	 * @param v, akirol levesszuk a hatast
	 */
	public void disable(Virologist v) {

		v.setIsCloakActive(false);
		
	}
	/**
	 * Függvény, mely visszaadja Stringben a kopeny nevet
	 * @return a kopeny neve
	 */
	public String toString(){return "Cloak";}
}
