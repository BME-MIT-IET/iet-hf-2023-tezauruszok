import java.util.ArrayList;

/**
 * Agenseket tarolo heterogen kollekcio
 */
public class Agents implements ThingList {
	/**
	 *	Agensek listaja
	 */
	private ArrayList<Agent> agents = new ArrayList<>();
	
	/**
	 * Konstruktor.
	 */
	public Agents() {
		agents = new ArrayList<Agent>();
	}

	/**
	 * Konstruktor.
	 * @param a, Agensek listaja
	 */
	public Agents(ArrayList<Agent> a){
		agents.addAll(a);
	}
	
	/**
	 * Hozzaadja a parameterkent kapott objektumot a tagvaltozokent 
	 * tarolt listahoz.
	 * @param o, amit hozza akarunk adni
	 */
	public void add(Object o) {

		agents.add((Agent)o);
	}

	/**
	 * Eltavolitja a parameterkent kapott objektumot
	 * @param o, amit el akarunk tavolitani
	 * @return a kitorolt objektum
	 */
	public Object remove(Object o) {

		agents.remove(o);
		

		return o;
	}

	/**
	 * Eltavolitja a parameterkent kapott agenst
	 * @param id, amit el akarunk tavolitani
	 * @return a kitorolt agens
	 */
	public Agent remove(int id){
		for(int i = 0; i < agents.size(); i++){
			if(agents.get(i).getId() == id ){
				return agents.remove(i);
			}
		}
		return null;
	}
	
	/**
	 * A listaban levo osszes agensnek csokkenti a hatralevo idejet.
	 */
	public void update_Agents() {
		for(int i=0; i<agents.size(); i++) {
			agents.get(i).decrease_Eft();
			if(agents.get(i).getEffect_time()==0){
				try{
					agents.get(i).disable(agents.get(i).getEffected_virologist());
					agents.remove(agents.get(i));
				}
				catch(Exception ex){
				}

			}
		}
	}

	/**
	 * Getter, ami vissza adja az agensek listajat
	 * @return agents, agensek listaja
	 */
	public ArrayList<Agent> getAgents(){
		return agents;
	}

}
