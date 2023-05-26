/**
 * A dolgokat megvalosito interface
 */
public interface ThingList {
	
	/**
	 * Hozzaadja a parameterkent kapott objektumot
	 * @param o, a hozzaadni kivant objektum
	 */
	public void add(Object o);
	
	/**
	 * Torli a parameterkent kapott objektumot, majd visszater vele
	 * @param o, a torolni kivant objektum
	 * @return o, a torolt objektm
	 */
	public Object remove(Object o);
}
