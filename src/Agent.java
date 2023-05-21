/**
 * Ez egy abstract ososztaly amibol a kulonbozo agensek szarmaznak
 */
public abstract class Agent {
	/**
	 *  amino sav hasznalati koltsege
	 */
	private static int amino_cost;
	/**
	 *  nucleotid hasznalati koltsege
	 */
	private static int nucleo_cost;
	/**
	 *  agens ervenyessegi ideje
	 */
	private int effect_time;
	/**
	 *	megfertozott virologus
	 */
	private Virologist effected_virologist;
	/**
	 *	megfertozott virologus neve
	 */
	private String effected_virologist_name;
	/**
	 *	hasznalva volt e az adott agens
	 */
	private boolean is_used;
	/**
	 *	vissza tudja e kenni az agenst a virologus
	 */
	private boolean isspreadedback = false;
	/**
	 * agens azonositoja
	 */
	int id;
	//át kell állítania az effektált virologust
	
	/**
	 * Konstruktor.
	 */
	public Agent() {
		amino_cost=1;
		nucleo_cost=1;
		effect_time=1;
		effected_virologist=null;
		is_used=false;
	}

	/**
	 * Konstruktor.
	 * @param a, amino sav hasznalati koltseg
	 * @param n, nucleotid hasznalati koltseg
	 * @param t, agens ervenyessegi ideje
	 * @param name, virologus neve
	 * @param u, hasznalva van e az adott agens
	 */
	public Agent(int a, int n, int t, String name, boolean u){
		amino_cost = a;
		nucleo_cost = n;
		effect_time = t;
		effected_virologist_name = name;
		is_used = u;
	}
	/**
	 * Az agens hatasat aktivalja ez a fuggveny a parameterkent
	 * kapott virologuson.
	 * @param t, akire hatasa lesz
	 */
	public abstract void enable(Virologist t);
	
	/**
	 * Az agenset hatastalanitja a parameterkent kapott virologuson.
	 * @param t, akirol levesszuk a hatast
	 */
	public abstract void disable(Virologist t)throws Exception;
	
	/**
	 * Csokkenti az agens hatasanak hatralevo idejet.
	 */
	public void decrease_Eft() {
		if(is_used && effect_time > 0) {
			effect_time--;
			try{
				if(effect_time == 0)
					disable(effected_virologist);
			}catch (Exception ex){
				
			}
		}
		
	}

	/** NEWNEWNEW
	 * Getter ami visszaadja az ágens id-ját
	 */
	public int getId(){
		return id;
	}

	/**
	 * Getter az agens elkeszitesehez szukseges nukleotid mennyiseghez.
	 * @return nucleo_cost, az agens elkeszitesehez szukseges nukleotid.
	 */
	public int getNucleo_cost() {
		return nucleo_cost;
	}

	/**
	 * Getter az agens elkeszitesehez szukseges aminosav mennyiseghez.
	 * @return amino_cost, az agens elkeszitesehez szukseges aminosav.
	 */
	public int getAmino_cost() {
		return amino_cost;
	}

	/**
	 * Getter az agens hatasabol hatralevo idohoz.
	 * @return effect_time, az agens hatasabol hatralevo ido.
	 */
	public int getEffect_time() {
		return effect_time;
	}

	/**
	 * Setter az agens hatasabol hatralevo idohoz.
	 * @param newtime, az agens hatasanak uj hatralevo ideje.
	 */
	public void setEffect_time(int newtime) {

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
	public Virologist getEffected_virologist() {
		return effected_virologist;
	}

	/**
	 * Setter az agens altal megvaltoztatott virologushoz.
	 * @param t, a megvaltoztatott virologus.
	 */
	public void setEffected_virologist(Virologist t) {

		effected_virologist = t;

	}

	/**
	 * Getter, amivel lekerdezhetjuk, hogy az agens volt-e mar hasznalva.
	 * @return is_used, hasznalva volt e az adott agens
	 */
	public boolean getIs_used() {
		return is_used;
	}

	/**
	 * Setter, amivel beallithatjuk, hogy az agens volt-e mar hasznalva.
	 * @param b, haszalva volt e az adott agens
	 */
	public void setIs_used(boolean b) {
		is_used = b;
	}

	/**
	 * Getter, amivel lekerdezhetjuk, hogy az agens volt-e mar visszakenve.
	 * @return isspreadedback
	 */
	public boolean getIsSpreadedBack() {
		return isspreadedback;
	}

	/**
	 * Setter, amivel beallithatjuk, hogy az agens volt-e mar visszakenve.
	 * @param b,visszakenheto e az adott agens
	 */
	public void setIsSpreadedBack(boolean b) {
		isspreadedback = b;
	}

	/**
	 * Getter, amivel lekerdezhetjuk a megfertozott virologus nevet
	 * @return effected_virologist_name, a megfertoztt virolus neve
	 */
	public String getEffected_virologist_name() {
		return effected_virologist_name;
	}

	/**
	 * Abstract osztály függvénye.
	 */
	public abstract String toString();
}
