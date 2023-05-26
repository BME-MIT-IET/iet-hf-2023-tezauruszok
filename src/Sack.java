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
		previoussize = v.getBackpack().getSize();	//elmentj�k a kor�bbi m�retet
		v.getBackpack().setSize(v.getBackpack().getMaxSize());	//�t�ll�tjuk a m�retet a maximumra
		
	}
	
	/**
	 * Az zsak hatastalanitja a parameterkent kapott virologuson.
	 * @param v, akirol levesszuk a hatast
	 */
	public void disable(Virologist v) {

		v.setIsSackActive(false);
		v.getBackpack().setSize(previoussize);	//�t�ll�tjuk a m�retet az eredeti m�retre
		previoussize = v.getBackpack().getMaxSize();
		
	}
	/**
	 * F�ggv�ny, mely visszaadja Stringben a mez� nev�t
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
