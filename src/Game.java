
import java.util.ArrayList;
import java.util.Random;

/**
 * A jatek kontrollere. Ez inditja el �s fejezi be a jatekot es a koroket.
 */
public class Game {
	/**
	 * Hatralevo jatekosok szama az adott korben
	 */
	private int remaining_player_in_a_round;
	/**
	 * A jatekot jatszo virologusok listaja
	 */
	private ArrayList<Virologist> virologists;
	/**
	 * Egy City attributum, hogy hozzaferjek a palyahoz �s legeneralja azt
	 */
	private City city;

	/**
	 * Konstruktor
	 */
	public Game(){
		remaining_player_in_a_round = 0;
		virologists = new ArrayList<>();
		city = new City(new ArrayList<Field>());
		city = city.generate_City();
	}

	/**
	 * Konstruktor
	 * @param i, adott korben hatralevo jatekosok szama
	 * @param v, jatekot jatszo virologusok listaja
	 * @param c, varost definialo attributum
	 */
	public Game(int i, ArrayList<Virologist> v, City c){
		remaining_player_in_a_round = i;
		virologists = v;
		city = c;
	}

	/**
	 * Getter a hatralevo jatekosok szamat tudjuk lekerdezni vele
	 * @return remaining_player_in_a_round
	 */
	public int getRemainingPlayers() {
		return remaining_player_in_a_round;
	}
	
	/**
	 * Setter a hatralevo jatekosok szamat tudjuk beallitani vele
	 * @param i, a hatralevo jatekosok uj erteke
	 */
	public void setRemainingPlayers(int i) {
		remaining_player_in_a_round = i;
	}
	
	/**
	 * El�lr�l kezdi az els� virol�gus fog k�vetkezni �jra
	 */
	public void next_Round() {
		next_Player(virologists.get(virologists.size() - 1));
		remaining_player_in_a_round = virologists.size() - 1;
	}
	
	/**
	 * A parameterul kapott virologusnak szol, hogy az o kore kovetkezik
	 * @param v, virologus akinek a kore kovetkezik
	 */
	public void next_Player(Virologist v) {
		v.your_Turn();
		remaining_player_in_a_round--;
	}
	
	/**
	 * Uj jatekot hoz letre
	 * @param players, jatekosok szama
	 */
	public void new_Game(int players) {
		/** R = RANDOM SZAM LETREHOZASA (az elerheto palyak szamanak fuggvenyeben)
		 *	FileHandler.loadFile metodus R-edik palya parameterrel
		 *	UJ varos letrehozasa
		 *	CIKLUS a parameterekben kapott szamig:
		 *		Virologus letrehozasa
		 *		Virologus elhelyezese egy kezdomezon
		 *	CIKLUS V�GE
		 */
		//city = city.generate_City();
		//city.addField(new Field(3));		//AZ�RT HOGY LEFUSSON A TESZTESZT.TXT
		//city.addField(new Field(3));
		virologists = new ArrayList<Virologist>();
		for(int i = players; i > 0; i--){		//�gy a lista v�g�n lesz a legkisebb sz�m� j�t�kos, �gy a player1 lesz mindig az els�
			createPlayers(i);
		}
		remaining_player_in_a_round = players-1;
	}
	
	/**
	 * A jatek veget kezelo fuggveny
	 */
	public void game_over() {
		Prototype.results = "Current player: " + Prototype.g.getActualPlayer().getName() + "won!";
		virologists = null;
	}
	
	/**
	 * A jatek v�ros�t adja vissza
	 * @return city, a jatek varosa
	 */
	public City getCity() {
		return city;
	}
	/**
	 * Hozz�ad egy param�terben kapott virologust a j�t�k virologusaihoz
	 * @param v, Virologist, a hozz�adand� Virologus
	 */
	public void addVirologist(Virologist v) {
		virologists.add(v);
	}

	/**
	 * Getter a j�t�k virol�gusaihoz
	 * @return virol�gusok
	 */
	public ArrayList<Virologist> getVirologists() {
		return virologists;
	}

	/**
	 * Visszaadja az aktu�lisan k�r�t j�tsz� vrol�gust
	 * @return virol�gus
	 */
	public Virologist getActualPlayer(){
		return virologists.get(getRemainingPlayers());
	}

	/**
	 * Varoshoz mezoket ado fuggveny
	 * @param f, a varoshoz adni kivant mezo
	 */
	public void addField(Field f){
		city.addField(f);
	}

	/**
	 * Jatekosokat letrehozo fuggveny
	 * @param playernumber, jatekosok szama
	 */
	public void createPlayers(int playernumber){
		Random r = new Random();
		Virologist nV = new Virologist(city.getFields().get(r.nextInt(city.getFields().size())));
		nV.setName(new String("player" + playernumber));
		virologists.add(nV);
	}
}