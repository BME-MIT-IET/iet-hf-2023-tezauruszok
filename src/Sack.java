/**
 * A zsakot megvalosito osztalyt
 */
public class Sack extends Equipment{
	/**
	 * Az elozo merete a zsaknak
	 */
	private int previoussize;
	/**
	 * Konstuktor
	 * @param prevsize, Az elozo merete a zsaknak
	 */
	public Sack(int prevsize){
		super(0, 4);
		previoussize = prevsize;
	}
	/**
	 * Konstuktor, 4 nagysagu zsak
	 */
	public Sack() {
        super(0, 4);
    }

	/**
	 * Az zsak hatasat aktivalja ez a fuggveny a parameterkent
	 * kapott virologuson.
	 * @param v, akire hatasa lesz
	 */
	public void enable(Virologist v) {

		v.setIsSackActive(true);
		previoussize = v.getBackpack().getSize();	//elmentjük a korábbi méretet
		v.getBackpack().setSize(v.getBackpack().getMaxSize());	//átállítjuk a méretet a maximumra
		
	}
	
	/**
	 * Az zsak hatastalanitja a parameterkent kapott virologuson.
	 * @param v, akirol levesszuk a hatast
	 */
	public void disable(Virologist v) {

		v.setIsSackActive(false);
		v.getBackpack().setSize(previoussize);	//átállítjuk a méretet az eredeti méretre
		previoussize = v.getBackpack().getMaxSize();
		
	}
	/**
	 * Függvény, mely visszaadja Stringben a mezõ nevét
	 * @return a felszereles neve
	 */
	public String toString(){return "Sack";}

	/**
	 * Getter az elozo merethez
	 * @return a zsak elozo merete
	 */
	public int getPrevioussize(){
		return previoussize;
	}
}
