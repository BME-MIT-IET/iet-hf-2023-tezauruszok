package player;

//
//  @ Project : Projekt labor
//  @ File Name : player.Virologist.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//

import java.util.ArrayList;
import java.util.List;

import agent.*;
import code.*;
import effect.Effect;
import effect.Frozen;
import equipment.Equipment;
import field.Field;
import game.PlayerV;
import skeleton.Main;


public class Virologist {
	private Integer aminoAcid;
	private Integer nucleotide;
	private Integer maxAminoAcid;
	private Integer maxNucleotide;
	private Boolean iAmBear;
	public PlayerV view;

	public List<GeneticCode> getLearnedCodes() {
		return learnedCodes;
	}

	private List<GeneticCode> learnedCodes;

	public List<Equipment> getMyEquipment() {
		return myEquipment;
	}

	private List<Equipment> myEquipment;

	public List<Agent> getAgentCollection() {
		return agentCollection;
	}

	private List<Agent> agentCollection = new ArrayList<>();

	public List<Effect> getEffects() {
		return effects;
	}

	private List<Effect> effects;
	private Field myField;

	public int getID(){return ID;}
	private int ID;

	/**
	 * player.Virologist konstruktor, inicializálja az értékeket.
	 */
	public Virologist(Field initField) {
		Main.printMethod("player.Virologist.player.Virologist()", true);
		aminoAcid = 0;
		nucleotide = 0;

		maxAminoAcid = 20;
		maxNucleotide = 20;

		learnedCodes = new ArrayList<>();
		myEquipment = new ArrayList<>();
		agentCollection = new ArrayList<>();
		effects = new ArrayList<>();

		myField = initField;
		ID=0;
		Main.printMethod("Return", false);

	}

	public Virologist (String name_) {
		view = new PlayerV(name_);
		aminoAcid = 0;
		nucleotide = 0;

		maxAminoAcid = 20;
		maxNucleotide = 20;

		learnedCodes = new ArrayList<>();
		myEquipment = new ArrayList<>();
		agentCollection = new ArrayList<>();
		effects = new ArrayList<>();

		ID=0;
	}

	///Felvettem egy új konstruktort
	public Virologist(Field initField, int id) {
		Main.printMethod("player.Virologist.player.Virologist()", true);
		aminoAcid = 0;
		nucleotide = 0;

		maxAminoAcid = 20;
		maxNucleotide = 20;

		learnedCodes = new ArrayList<>();
		myEquipment = new ArrayList<>();
		agentCollection = new ArrayList<>();
		effects = new ArrayList<>();

		myField = initField;
		ID=id;
		Main.printMethod("Return", false);

	}

	public Field getMyField() {
		Main.printMethod("player.Virologist.getMyField()", true);
		Main.printMethod("Return myField", false);
		return myField;
	}

	/**
	 * Lépteti a virológust a megadott számú mezõre
	 *
	 * @param fieldNumber - A mezõ száma, ahová lépni akar
	 * @return boolean - lépés sikeressége
	 */
	public boolean move(Integer fieldNumber) {
		Main.printMethod("player.Virologist.move()", true);
		Field nextField = myField.getNeighbour(fieldNumber);
		if (nextField != null) {
			myField = nextField;
			return true;
		}
		Main.printMethod("return", false);
		return false;
	}

	public void setMyField(Field f)  {
		myField = f;
	}

	/**
	 * a ágens felkenése p Virológusra
	 *
	 * @param p - a metódus p-re keni fel az ágenst
	 * @param a- az ágens amit felken
	 */
	public void useAgent(Virologist p, Agent a) {
		Main.printMethod("player.Virologist.useAgent()", true);
		//Ha nem egy mezõn állnak

		if (!p.equals(this)) {
			if ( !p.isSamePos(myField) ) { return; }

			//Ha védett akkor return
			if( p.effectedBy( "effect.AgentProtection" ) ) { return; }
		}

		int idx = -1;
		for(Agent q : agentCollection) {
			if (q.getClass().equals(a.getClass())) {
				idx = agentCollection.indexOf(q);
			}
		}
		if (idx != -1) {
			//Ágens felkenése
			agentCollection.get(idx).apply(p);
			agentCollection.remove(idx);
		}

		Main.printMethod("Return", false);
	}

	/**
	 *
	 */
	public void action() {
		Main.printMethod("player.Virologist.action()", true);
		myField.doAction(this);
		Main.printMethod("Return", false);
	}

	/**
	 * Hozzáad egy eff effektet a virológus effects attribútumához
	 *
	 * @param eff
	 */
	public void addEffect(Effect eff) {
		Main.printMethod("player.Virologist.addEffect()", true);
		effects.add(eff);
		Main.printMethod("Return", false);
	}

	/**
	 * Megnézi, hogy szerepel e ‘e’ a myEquipment-ben, ha igen akkor igazzal tér vissza					//SZERINTEM JOBB VOLNA, HA TÖBBET IS FEL TUD VENNI
	 * Ha nem, akkor hamissal tér vissza és hozzáadja e-t a virológus védõfelszerelés kollekciójához
	 *
	 * @param e
	 *
	 * @return - false, ha nem tudja felvenni
	 */
	public Boolean pickUpEquipment(Equipment e) {		//MOST TOBBET IS FEL TUD VENNI
		Main.printMethod("player.Virologist.pickUpEquipment()", true);
		System.out.println(" Return: true");
		myEquipment.add(e);

		Main.printMethod("return", false);
		return true; //LEHETNE VOID HA TÖBBET FEL TUD VENNI
	}

	/**
	 *
	 * @param otherVirologist
	 */
	public void robVirologist(Virologist otherVirologist) {
		Main.printMethod("player.Virologist.robVirologist()", true);
		//Ha nem egy mezon allnak akkor return
		if ( !otherVirologist.isSamePos(myField) ) {
			Main.printMethod("return", false);
			return;
		}

		if ( otherVirologist.effectedBy("effect.Frozen") ) {
			iAmRobbed(this);
		}

		Main.printMethod("return", false);
	}


	/**
	 * Növeli az aminosav gyûjthetõ mennyiségét
	 * ez az érték nem mehet 0 alá és 20 fölé
	 *
	 * @param value - ezzel az értékkel változtat az aminósav mennyiségén
	 */
	public void increaseAminoAcid(Integer value) {
		Main.printMethod("player.Virologist.increaseAminoAcid()", true);
		if (value == 0) {
			Main.printMethod("return", false);
			return;
		}

		if (value > 0) {
			while (value > 0 && aminoAcid < maxAminoAcid) {
				aminoAcid++;
				value--;
			}
		} else {
			while (value < 0 && aminoAcid > 0) {
				aminoAcid--;
				value++;
			}
		}

		Main.printMethod("return", false);
	}

	/**
	 * Növeli a nukleotid gyûjthetõ mennyiségét
	 * ez az érték nem mehet 0 alá és 20 fölé
	 *
	 * @param value - ezzel az értékkel váltotzat a nukleotid mennyiségén
	 */
	public void increaseNucleotide(Integer value) {
		Main.printMethod("player.Virologist.increaseNucleotide()", true);
		if (value == 0) {
			Main.printMethod("return", false);
			return;
		}

		if (value > 0) {
			while (value > 0 && nucleotide < maxNucleotide) {
				nucleotide++;
				value--;
			}
		} else {
			while (value < 0 && nucleotide > 0) {
				nucleotide--;
				value++;
			}
		}

		Main.printMethod("return", false);
	}

	/**
	 * Igazzal tér vissza, ha a virológus f mezõn áll
	 *
	 * @param f
	 */
	public Boolean isSamePos(Field f) {
		Main.printMethod("player.Virologist.isSamePos()", true);
		Main.printMethod("return Boolean", false);
		return myField.getNumber().equals(f.getNumber());
	}

	/**
	 * Igazzal tér vissza, ha e effekt hat a virológusra
	 *
	 * @param e - a vizsgálandó effekt.
	 */
	public Boolean effectedBy(String e ) {
		Main.printMethod("player.Virologist.effectedBy()", true);

		for (Effect myEffect : effects) {
			if (myEffect.getName().equals(e)) {
				Main.printMethod("Return: true", false);
				return true;
			}
		}
		Main.printMethod("Return: false", false);
		return false;
	}

	/**
	 * A virológusra ható hatásokat érvényesíti
	 * A virológus lépésének elején hívja meg a player.GameTable
	 *
	 * @return false - ha a kor kimarad
	 */
	public boolean beforeStep() {
		Main.printMethod("player.Virologist.beforeStep()", true);
		for(Effect e : effects) {
			e.effect(this);
			if (e.getClass().equals(new Frozen().getClass())) {
				Main.printMethod("return", false);
				return false;
			}
		}

		Main.printMethod("return", false);
		return true;
	}

	/**
	 * Visszatér a virológus genetikai kód gyûjteményének számosságával
	 *
	 * @return - az ágens kollekció mérete
	 */
	public Integer countOfLearnedCodes() {
		Main.printMethod("player.Virologist.countOfLearnedCodes()", true);
		Main.printMethod("Return: learnedCodes.Size()", false);
		return learnedCodes.size();
	}

	/**
	 * Csökkenti a hatások hatásidejét
	 * Ha valami hatását veszti akkor eldobja azt a hatást
	 */
	public void afterStep() {
		Main.printMethod("player.Virologist.afterStep()", true);
		effects.removeIf(Effect::tick);
		Main.printMethod("return", false);
	}

	/**
	 *
	 *
	 * @param v - v-nek átadja a felszereléseket és az anyagokat.
	 */
	public void iAmRobbed(Virologist v) {
		Main.printMethod("player.Virologist.iAmRobbed()", true);
		v.increaseAminoAcid(aminoAcid);
		v.increaseNucleotide(nucleotide);

		for(Equipment e : myEquipment) {
			v.pickUpEquipment(e);
		}
		Main.printMethod("return ", false);
	}

	/**
	 * A kapott kódot hozzáadja a kollekcióhoz
	 *
	 * @param c - az új kód
	 */
	public void learnCode(GeneticCode c) {
		Main.printMethod("player.Virologist.learnCode()", true);
		boolean nincs = true;

		for(GeneticCode code : learnedCodes){
			if (code.getClass().equals(c.getClass())) nincs = false;
		}
		if (nincs) {
			learnedCodes.add(c);
		}
		Main.printMethod("return", false);
	}

	/**
	 * Segédfüggvény az ágens lekérdezéséhez, hogy név alapján meg lehessen kapni a kollekció ilyen típusú ágensét
	 * @param aName - ágens neve
	 * @return - learnedCodes indexe
	 */
	public Integer getAgentIdxByName(String aName) throws Exception {
		Agent gc = null;
		switch (aName) {
			case "crazyvirus":
				gc = new CrazyVirus();
				break;
			case "forgetvirus":
				gc = new ForgetVirus();
				break;
			case "freezevirus":
				gc = new FreezeVirus();
				break;
			case "vaccine":
			case "vaccineagent":
				gc = new Vaccine();
				break;
			default:
				throw new Exception("Nem lehet ilyet létrehozni!");
		}
		for (Agent a : agentCollection) {
			if (a.getClass().equals(gc.getClass())) {
				return agentCollection.indexOf(a);
			}
		}
		return -1;
	}

	public Integer getCodeIdxByAgentName(String aName) throws Exception {
		GeneticCode gc = null;
		switch (aName) {
			case "crazyvirus":
			case "crazyvirusagent":
				gc = new CrazyVirusCode();
				break;
			case "forgetvirus":
			case "forgetvirusagent":
				gc = new ForgetVirusCode();
				break;
			case "freezevirus":
			case "freezevirusagent":
				gc = new FreezeVirusCode();
				break;
			case "vaccine":
			case "vaccineagent":
				gc = new VaccineCode();
				break;
			default:
				throw new Exception("Nem lehet ilyet létrehozni!");
		}
		for (GeneticCode c : learnedCodes) {
			if (c.getClass().equals(gc.getClass())) {
				return learnedCodes.indexOf(c);
			}
		}
		return -1;
	}

	/**
     * Létrehozza a választott ágenst genetikai kód alapján
     *
     * @param idx
	 */
	public void createAgent(Integer idx) {
		Main.printMethod("player.Virologist.CreateAgent", true);
		GeneticCode gc;
		try {
			gc = learnedCodes.get(idx);
		} catch (Exception e) {
			Main.printMethod("return ", false);
			return;
		}

		if ( gc.aminoAcidNeeded() < aminoAcid && gc.nucleotideNeeded() < nucleotide) {
			Agent agent = gc.generateAgent(this);
			if (agent!= null) {
				agentCollection.add(agent);
			}
		}
		Main.printMethod("return ", false);
	}

	public void addAgent (Agent a) {
		agentCollection.add(a);
	}

	public Agent getAgentByIdx(Integer idx) {
		try {
			Agent a;
			return agentCollection.get(idx-1);
		} catch (Exception e) {
			System.out.println("Nincs ilyen agens");
		}
		return null;
	}

	/**
	 * Kapacitás megduplázása az anyagokhoz. EZ NEM SZEREPELT A KORÁBBI DOKSIKBAN
	 */
	public void doubleCapacity() {
		Main.printMethod("player.Virologist.doubleCapacity()", true);
		maxAminoAcid *= 2;
		maxNucleotide *= 2;
		Main.printMethod("return", false);
	}

	public Integer getAminoAcid(){
		Main.printMethod("player.Virologist.getAminoAcid()", true);
		Main.printMethod("return: Integer aminoAcid", false);
		return aminoAcid;
	}
	public Integer getNucleotide(){
		Main.printMethod("player.Virologist.getNucleotide()", true);
		Main.printMethod("return: Integer nucleotide", false);
		return nucleotide;
	}

	public void setAminoAcid(Integer aminoAcid) {
		Main.printMethod("player.Virologist.setAminoAcid()", true);
		this.aminoAcid = aminoAcid;
		Main.printMethod("return", false);
	}

	public void setNucleotide(Integer nucleotide){
		Main.printMethod("player.Virologist.setNucleotide()", true);
		this.nucleotide=nucleotide;
		Main.printMethod("return ", false);
	}

	/**
	 * elfelejti a virológus addig megtanult genetikai kódjait
	 */
	public void forgetGeneticCodes() {
		Main.printMethod("player.Virologist.forgetGeneticCodes()", true);
		learnedCodes = new ArrayList<>();
		Main.printMethod("return ", false);
	}

	public String getName() {
		return view.name;
	}
}
