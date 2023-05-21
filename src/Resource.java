/**
 * A anyagokat megvalosito absztrakt ososztalyt
 */
public abstract class Resource {
    /**
     * Az anyag azonositoja
     */
    int id;
    /** Getter
     * @return Az anyag azonositojaval
     */
	public int getId(){
        return id;
    }/**
     * Abstract Függvény, melynek leszarmazottja visszaadja Stringben a mezõ nevét
     * @return a anyag neve
     */
    public abstract String toString();
}
