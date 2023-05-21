/**
 * A benulast okozo agenst megvalosito osztalyt
 */
public class Paralyze_agent extends Agent{
	/**
	 * Nucleotidbol mennyibe kerul
	 */
	private static int nucleo_cost = 1;
	/**
	 * Aminoacidbol mennyibe kerul
	 */
	private static int amino_cost = 1;
	/**
	 * Mennyi ideig aktiv
	 */
	private int effect_time;
	/**
	 * A virologus akira hatassal van
	 */
	private Virologist effected_virologist = null;
	/**
	 * Hasznalva volt-e
	 */
	private boolean is_used;
	
	/**
	 * Konstruktor.
	 */
	public Paralyze_agent() {
		super();
		id = 1;
	}
	/**
	 * Konstruktor.
	 * @param a, amino sav hasznalati koltseg
	 * @param n, nucleotid hasznalati koltseg
	 * @param t, agens ervenyessegi ideje
	 * @param name, virologus neve
	 * @param u, hasznalva van e az adott agens
	 */
	public Paralyze_agent(int a, int n, int t, String name, boolean u){
		super(a, n, t, name, u);
		id = 1;
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
			//System.out.println("Érvénytelen idõérték");
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
		t.setIsParalyzed(true);
		setIsUsed(true);
		//a paralyze_agent hatásai máshol érvényesülnek, pl egy külön ellenõrzõben a játékos körének elején
		
	}
	
	/**
	 * Az agenset hatastalanitja a parameterkent kapott virologuson.
	 * @param t, akirol levesszuk a hatast
	 */
	public void disable(Virologist t) throws Exception {
		if(t.getIsParalyzed()){
			t.setIsParalyzed(false);
		}else{
			throw new Exception();
		}
	}
	/**
	 * Függvény, mely visszaadja Stringben a dolog nevét
	 * @return a agens neve
	 */
	public String toString(){
		return "Paralyze_agent";
	}
}
