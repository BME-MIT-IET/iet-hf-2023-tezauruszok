/**
 * Ez egy abstract osztaly. Ebbol valosulnak meg a kulonbozo felszerelesek
 */
public abstract class Equipment {
	/**
	 * Megadja, hogy hanyszor tudunk egy adott felszerelest meg hasznalni
	 */
	int durability;
	/**
	 * Egy felszereles sorszama, minden felszerelesnek egyedi
	 */
	int id;
	/**
	 * Az felszereles hatasat aktivalja ez a fuggveny a parameterkent
	 * kapott virologuson.
	 * @param v, akire hatasa lesz
	 */
	public abstract void enable(Virologist v);
	
	/**
	 * Az felszerelest hatastalanitja a parameterkent kapott virologuson.
	 * @param v, akirol levesszuk a hatast
	 */
	public abstract void disable(Virologist v);

	/**
	 * Getter, ami az adott felszereles id-jat adja vissza
	 * @return id, adott felszereles azonositoja
	 */
	public int getId(){
		return id;
	}

	/**
	 * Getter, ami visszaadja, hogy hanyszor tudjuk az adott felszerelest meg hasznalni
	 * @return durability, hanyszor tudjuk meg hasznalni
	 */
	public int getDurability(){
		return durability;
	}

	/**
	 * Setter, ami beallitja, hogy hanyszor tudunk egy adott felszerelest meg hasznalni
	 * @param d, hasznalhatosagi erteke az adott felszerelesnek
	 */
	public void setDurability(int d) {
		durability = d;
	}
	/**
	 * Abstract osztály függvénye.
	 */
	public abstract String toString();

	/**
	 * Konstruktor.
	 * @param dura, megadja, hogy hanyszor tudunk egy adott felszerelest meg hasznalni
	 * @param i, megadja egy felszereles egyedi sorszamat
	 */
	public Equipment(int dura, int i){
		durability = dura;
		id = i;
	}
}
