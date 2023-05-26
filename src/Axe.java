/**
 * A virologus megolhet egy masik virologust a balta hasznalataval
 */
public class Axe extends Equipment{
    /**
     * Konstruktor.
     */
    public Axe(){
            super(1, 1);
        }
        /**
         * Az baltat aktivalja ez a fuggveny a parameterkent
         * kapott virologuson.
         * @param v, akire hatasa lesz
         */
        public void enable(Virologist v) {
            v.setisAxeActive(true);
        }
        /**
         * A baltat hatastalanitja a parameterkent kapott virologuson.
         * @param v, akirol levesszuk a hatast
         */
        public void disable(Virologist v) {

            v.setisAxeActive(false);
        }
        /**
        * Függvény, mely visszaadja Stringben a balta nevét
        * @return balte neve
        */
        public String toString(){return "Axe";}
}
