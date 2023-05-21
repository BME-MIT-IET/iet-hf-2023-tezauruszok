/**
 * Medvetancot okozo agenst megvalosito osztaly
 */
public class Bear_agent extends Agent{
        /**
        *  nucleotid hasznalati koltsege
        */
        private static int nucleo_cost = 0;
        /**
        *  amino sav hasznalati koltsege
        */
        private static int amino_cost = 0;
        /**
        *  agens ervenyessegi ideje
        */
        private int effect_time;
        /**
        *	megfertozott virologus
        */
        private Virologist effected_virologist;
        /**
        *	hasznalva volt e az adott agens
        */
        private boolean is_used;
        /**
         * Konstruktor
         */
        public Bear_agent() {
            super();
            id = 5;
        }

        /**
        * Konstruktor
        * @param a, amino sav hasznalati koltsege
        * @param n, nukleotid hasznalati koltsege
        * @param t, agens ervenyessegi ideje
        * @param name, virologus neve
        * @param u, hasznvalva volt e az adott agens
        */
        public Bear_agent(int a, int n, int t, String name, boolean u){
            super(a, n, t, name, u);
            id = 5;
        }

        /**
         * Getter az agens elkeszitesehez szukseges nukleotid mennyiseghez.
         * @return nucleo_cost, az agens elkeszitesehez szukseges nukleotid.
         */
        public int getNucleoCost() {
            return nucleo_cost;
        }

        /**
         * Getter az agens elkeszitesehez szukseges aminosav mennyiseghez.
         * @return amino_cost, az agens elkeszitesehez szukseges aminosav.
         */
        public int getAminoCost() {
            return amino_cost;
        }

        /**
         * Getter az agens hatasabol hatralevo idohoz.
         * @return effect_time, az agens hatasabol hatralevo ido.
         */
        public int getEffectTime() {
            return effect_time;
        }

        /**
         * Setter az agens hatasabol hatralevo idohoz.
         * @param newtime, az agens hatasanak uj hatralevo ideje.
         */
        public void setEffectTime(int newtime) {

            if(newtime >= 0) {
                effect_time = newtime;
            }else {
                System.out.println("Érvénytelen idõérték");
            }

        }

        /**
         * Getter az agens altal megvaltoztatott virologusra.
         * @return effected_virologist, a megvaltoztatott virologus.
         */
        public Virologist getEffectedVirologist() {
            return effected_virologist;
        }

        /**
         * Setter az agens altal megvaltoztatott virologushoz.
         * @param t, a megvaltoztatott virologus.
         */
        public void setEffectedVirologist(Virologist t) {

            effected_virologist = t;

        }

        /**
         * Getter, amivel lekerdezhetjuk, hogy az agens volt-e mar hasznalva.
         * @return is_used
         */
        public boolean getIsUsed() {
            return is_used;
        }

        /**
         * Setter, amivel beallithatjuk, hogy az agens volt-e mar hasznalva.
         * @param b
         */
        public void setIsUsed(boolean b) {
            is_used = b;
        }

        /**
         * Az agens hatasat aktivalja ez a fuggveny a parameterkent
         * kapott virologuson.
         * @param t, akire hatasa lesz
         */
        public void enable(Virologist t) {

            t.setisBear(true);
        }

        /**
         * Az agenset hatastalanitja a parameterkent kapott virologuson.
         * @param t, akirol levesszuk a hatast
         */
        public void disable(Virologist t) {
            t.setIsChorea(false);
        }

        /**
        * Függvény, mely visszaadja Stringben a medve virus nevet
        * @return a medve virus neve
        */
        public String toString(){
        return "Bear_agent";
    }
}
