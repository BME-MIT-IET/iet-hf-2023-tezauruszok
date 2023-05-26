
import java.util.ArrayList;
import java.util.Random;

/**
 * A jatek kontrollere. Ez inditja el és fejezi be a jatekot es a koroket.
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
	 * Egy City attributum, hogy hozzaferjek a palyahoz és legeneralja azt
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
	 * Elölrõl kezdi az elsõ virológus fog következni újra
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
		 *	CIKLUS VÉGE
		 */
		//city = city.generate_City();
		//city.addField(new Field(3));		//AZÉRT HOGY LEFUSSON A TESZTESZT.TXT
		//city.addField(new Field(3));
		virologists = new ArrayList<Virologist>();
		for(int i = players; i > 0; i--){		//így a lista végén lesz a legkisebb számú játékos, így a player1 lesz mindig az elsõ
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
	 * A jatek városát adja vissza
	 * @return city, a jatek varosa
	 */
	public City getCity() {
		return city;
	}
	/**
	 * Hozzáad egy paraméterben kapott virologust a játék virologusaihoz
	 * @param v, Virologist, a hozzáadandó Virologus
	 */
	public void addVirologist(Virologist v) {
		virologists.add(v);
	}

	/**
	 * Getter a játék virológusaihoz
	 * @return virológusok
	 */
	public ArrayList<Virologist> getVirologists() {
		return virologists;
	}

	/**
	 * Visszaadja az aktuálisan körét játszó vrológust
	 * @return virológus
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