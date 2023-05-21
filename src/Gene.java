/**
 * A gent megvalosito ososztalyt
 */
public abstract class Gene {
	/**
	 * Id-ja az adott gennek.
	 */
	protected int id;
	
	/**
	 * Getter az id adattaghoz.
	 * @return id
	 */
	public int getId() {

		return id;
	}
	/**
	 * Absztrakt F�ggv�ny, mely visszaadja Stringben a dolog nev�t
	 * @return a gene neve
	 */
	public abstract String toString();
}
