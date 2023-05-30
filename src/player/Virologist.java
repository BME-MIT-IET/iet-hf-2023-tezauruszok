package player;

//
//  @ Project : Projekt labor
//  @ File Name : player.Virologist.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly B�lint yo685y
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
import random.RandomContainer;
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
	 * player.Virologist konstruktor, inicializ�lja az �rt�keket.
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

	///Felvettem egy �j konstruktort
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
	 * L�pteti a virol�gust a megadott sz�m� mez�re
	 *
	 * @param fieldNumber - A mez� sz�ma, ahov� l�pni akar
	 * @return boolean - l�p�s sikeress�ge
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
	 * a �gens felken�se p Virol�gusra
	 *
	 * @param p - a met�dus p-re keni fel az �genst
	 * @param a- az �gens amit felken
	 */
	public void useAgent(Virologist p, Agent a) {
		Main.printMethod("player.Virologist.useAgent()", true);
		//Ha nem egy mez�n �llnak

		if (!p.equals(this)) {
			if ( !p.isSamePos(myField) ) { return; }

			//Ha v�dett akkor return
			if( p.effectedBy( "effect.AgentProtection" ) ) { return; }
		}

		int idx = -1;
		for(Agent q : agentCollection) {
			if (q.getClass().equals(a.getClass())) {
				idx = agentCollection.indexOf(q);
			}
		}
		if (idx != -1) {
			//�gens felken�se
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
	 * Hozz�ad egy eff effektet a virol�gus effects attrib�tum�hoz
	 *
	 * @param eff
	 */
	public void addEffect(Effect eff) {
		Main.printMethod("player.Virologist.addEffect()", true);
		effects.add(eff);
		Main.printMethod("Return", false);
	}

	/**
	 * Megn�zi, hogy szerepel e �e� a myEquipment-ben, ha igen akkor igazzal t�r vissza					//SZERINTEM JOBB VOLNA, HA T�BBET IS FEL TUD VENNI
	 * Ha nem, akkor hamissal t�r vissza �s hozz�adja e-t a virol�gus v�d�felszerel�s kollekci�j�hoz
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
		return true; //LEHETNE VOID HA T�BBET FEL TUD VENNI
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
	 * N�veli az aminosav gy�jthet� mennyis�g�t
	 * ez az �rt�k nem mehet 0 al� �s 20 f�l�
	 *
	 * @param value - ezzel az �rt�kkel v�ltoztat az amin�sav mennyis�g�n
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
	 * N�veli a nukleotid gy�jthet� mennyis�g�t
	 * ez az �rt�k nem mehet 0 al� �s 20 f�l�
	 *
	 * @param value - ezzel az �rt�kkel v�ltotzat a nukleotid mennyis�g�n
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
	 * Igazzal t�r vissza, ha a virol�gus f mez�n �ll
	 *
	 * @param f
	 */
	public Boolean isSamePos(Field f) {
		Main.printMethod("player.Virologist.isSamePos()", true);
		Main.printMethod("return Boolean", false);
		return myField.getNumber().equals(f.getNumber());
	}

	/**
	 * Igazzal t�r vissza, ha e effekt hat a virol�gusra
	 *
	 * @param e - a vizsg�land� effekt.
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
	 * A virol�gusra hat� hat�sokat �rv�nyes�ti
	 * A virol�gus l�p�s�nek elej�n h�vja meg a player.GameTable
	 *
	 * @return false - ha a kor kimarad
	 */
	public boolean beforeStep() {
		Main.printMethod("player.Virologist.beforeStep()", true);
		for(Effect e : effects) {
			e.effect(this, RandomContainer.rand);
			if (e.getClass().equals(new Frozen().getClass())) {
				Main.printMethod("return", false);
				return false;
			}
		}

		Main.printMethod("return", false);
		return true;
	}

	/**
	 * Visszat�r a virol�gus genetikai k�d gy�jtem�ny�nek sz�moss�g�val
	 *
	 * @return - az �gens kollekci� m�rete
	 */
	public Integer countOfLearnedCodes() {
		Main.printMethod("player.Virologist.countOfLearnedCodes()", true);
		Main.printMethod("Return: learnedCodes.Size()", false);
		return learnedCodes.size();
	}

	/**
	 * Cs�kkenti a hat�sok hat�sidej�t
	 * Ha valami hat�s�t veszti akkor eldobja azt a hat�st
	 */
	public void afterStep() {
		Main.printMethod("player.Virologist.afterStep()", true);
		effects.removeIf(Effect::tick);
		Main.printMethod("return", false);
	}

	/**
	 *
	 *
	 * @param v - v-nek �tadja a felszerel�seket �s az anyagokat.
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
	 * A kapott k�dot hozz�adja a kollekci�hoz
	 *
	 * @param c - az �j k�d
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
	 * Seg�df�ggv�ny az �gens lek�rdez�s�hez, hogy n�v alapj�n meg lehessen kapni a kollekci� ilyen t�pus� �gens�t
	 * @param aName - �gens neve
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
				throw new Exception("Nem lehet ilyet l�trehozni!");
		}
		for (Agent a : agentCollection) {
			if (a.getClass().equals(gc.getClass())) {
				return learnedCodes.indexOf(a);
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
				throw new Exception("Nem lehet ilyet l�trehozni!");
		}
		for (GeneticCode c : learnedCodes) {
			if (c.getClass().equals(gc.getClass())) {
				return learnedCodes.indexOf(c);
			}
		}
		return -1;
	}

	/**
     * L�trehozza a v�lasztott �genst genetikai k�d alapj�n
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
	 * Kapacit�s megdupl�z�sa az anyagokhoz. EZ NEM SZEREPELT A KOR�BBI DOKSIKBAN
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
	 * elfelejti a virol�gus addig megtanult genetikai k�djait
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
