import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
/**
 * A Virologust megvalosito osztalyt
 */
public class Virologist {
	//attrib�tumok
	/**
	 * Virologus neve
	 */
	private String name = "";
	/**
	 * Virologus le van e benulva
	 */
	private boolean isParalyzed = false;
	/**
	 * Virologus vitustancot jar-e
	 */
	private boolean isChorea = false;
	/**
	 * Virologus erinthetetlen-e
	 */
	private boolean isInvulnerable = false;
	/**
	 * Virologus feledes alatt vane
	 */
	private boolean isOblivion = false;
	/**
	 * Virologus zsakja aktiv e
	 */
	private boolean isSackActive = false;
	/**
	 * Virologus kopenye aktiv e
	 */
	private boolean isCloakActive = false;
	/**
	 * Virologus kesztyuje aktiv e
	 */
	private boolean isGlovesActive = false;
	/**
	 * Virologus halott e
	 */
	private boolean isDead = false;
	/**
	 * Virologus baltaja aktiv e
	 */
	private boolean isAxeActive = false;
	/**
	 * Virologus medvetancot jar e
	 */
	private boolean isBear = false;
	/**
	 * Virologus hatralevo lepeseinek a szama
	 */
	private int work_Unit = 4;	//2. K�vetelm�ny, projekt, funkcionalit�s alapj�n 4 a max �rt�k
	/**
	 * Virologus milyen mezon all
	 */
	private Field field;
	/**
	 * Virologus hatizsaka, anyagok vannak benne
	 */
	private Backpack backpack;
	/**
	 * Virologus lecraftolt agensei
	 */
	private Agents agents;
	/**
	 * Virologus megtanult genjei
	 */
	private Genes genes;
	/**
	 * Virologus altal felvett felszerelesek
	 */
	private Equipment_s equipment;


	/**
	 * Virol�gus ikonja
	 */
	private String virologistIcon = "";


	/**
	 * Konstruktor
	 * @param f, a mezo ahol allni fog
	 */
	public Virologist(Field f) {
		virologistIcon = Prototype.generateIcon();
		field = f;
		f.addVirologist(this);
		//ez csak egy default value, adhatunk neki m�st
		backpack = new Backpack(5);
		agents = new Agents();
		genes = new Genes();
		equipment = new Equipment_s();
		//name = randomnev, vagy valami ilyesmi (nincs a bemeneti nyelvn�l olyan opci� hogy ez megv�ltozthat� vagy be�ll�that� lenne, ez�rt random)
	}
	/**
	 * Konstuktor
	 * @param vname, virologus neve
	 * @param visParalyzed, le van e benulva
	 * @param visChorea, eppen choreas-e
	 * @param visInvulnerable, erinthetetlene
	 * @param visOblivion, eppen felejtes alatt all e
	 * @param visSackActive, van e aktiv zsakja
	 * @param visCloakActive, van e aktiv kopenye
	 * @param visGlovesActive, van e aktiv kesztyuje
	 * @param visDead, halott e a virologus
	 * @param visAxeActive, van e aktiv baltaha
	 * @param visBear, medve e
	 * @param vwork_unit, mennyi lepese van meg hatra
	 * @param f, melyik mezon all
	 * @param vbackpack, a virologus hatizsakja, tarolja a megszerzett dolgokat
	 * @param vagents, megszerzett agensek
	 * @param vgenes, megszerzett genek
	 * @param vequipment, megszerzett eszkozok
	 */
	public Virologist(String vname, boolean visParalyzed, boolean visChorea, boolean visInvulnerable, boolean visOblivion,
					  boolean visSackActive, boolean visCloakActive, boolean visGlovesActive, boolean visDead, boolean visAxeActive, boolean visBear,
					  int vwork_unit, Field f, Backpack vbackpack, Agents vagents, Genes vgenes, Equipment_s vequipment) {


		name = vname;
		isParalyzed = visParalyzed;
		isChorea = visChorea;
		isInvulnerable = visInvulnerable;
		isOblivion = visOblivion;
		isSackActive = visSackActive;
		isCloakActive = visCloakActive;
		isGlovesActive = visGlovesActive;
		isDead = visDead;
		isAxeActive = visAxeActive;
		isBear = visBear;
		work_Unit = vwork_unit;
		field = f;
		virologistIcon = Prototype.generateIcon();
		f.addVirologist(this);
		backpack = vbackpack;
		agents = vagents;
		genes = vgenes;
		equipment = vequipment;
	}

	/**
	 * Fuggveny genek megtanulasahoz, megtanulja a parameterkent kapott gent
	 * @param g, megtanulni valo gen
	 */
	public void learn_Gene(Gene g) throws Exception{
		/**
		 *PR�B�LD
		 * 		TANULD MEG a megadott g�nt
		 *EGY�BK�NT
		 * 		DOBJ hiba�zenetet
		 *HA megtanult g�nek sz�ma = 4
		 *		J�T�K V�GE h�v�sa
		 */

		Gene new_gene=((Laboratory)field).getGene(g.getId());
		//ELLEN�RZ�S hogy meg van e tanulva kor�bbr�l
		if(genes.hasLearned(new_gene.getId())){
			throw new Exception("Bad gene");
		}
		genes.add(new_gene);
		
		if(genes.getAcquired() == 4) {
			Prototype.g.game_over();
		}
	}
	
	/**
	 * Fuggeny a lepeshez, a parameterkent kapott sorszamu szomszedos mezore lep
	 * @param i, melyik sorszamu mezore lepjen
	 */
	public void move(int i) {
		/***
		 *HA isChorea IGAZ
		 *		random_Move F�GGV�NY
		 *K�L�NBEN
		 * 		K�RD LE a szomsz�dos mez�kb�l az i-ediket
		 * 		l�pj r� a kiv�lasztott szomsz�dra
		 */
		if(isChorea || isBear) {
			random_Move();
		}
		else {
			//Field new_field = field.getNeighbour(i);
			Field new_field = field.getNeighbour(i-1);
			field.removeVirologist(this);
			field.p.removeIcon(virologistIcon);
			field = new_field;
			field.addVirologist(this);
		}
	}
	
	/**
	 * Ha a virologus vitustancot jar, akkor veletlenszeruen lepked, ezt valositja meg a fuggveny
	 */
	public void random_Move() {
		/**
		 *CIKLUS AM�G munkaegys�g > 0
		 *		l�pj egy random szomsz�dra
		 *			HA isBear IGAZ
		 *				a mez�n lev� �sszes virol�gust FERT�ZZE MEG medvev�russal
		 *		cs�kkentsd a munkaegys�get 1-gyel
		 *CIKLUS V�GE
		 *�LL�TSD IsChorea-t HAMIS-ra
		 */
		while(work_Unit > 0) {
			Random r = new Random();
			int i = r.nextInt(field.getNeighbours().size()); //-1, mert a nextInt 1-t�l sz�mol
			Field new_field = field.getNeighbour(i);
			field.removeVirologist(this);
			field.p.removeIcon(virologistIcon);
			field = new_field;
			field.addVirologist(this);

			if(this.getisBear()){
				for(int j = 0; j < field.getVirologists().size(); j++){
					try{
						if(!field.getVirologists().get(j).getisBear()){		//�gy nem lesz feleslegesen kenegetve az, aki m�r eleve medve, bele�rtve ezt az aktu�lis virol�gust is
							spreading(agents.remove(5), field.getVirologists().get(j));
							field.getVirologists().get(j).getGenes().add(new Bear_gene());
							field.getVirologists().get(j).craft_Agent(5);
							craft_Agent(5);
						}
					}catch (Exception ex){
					}
				}
			}
			work_Unit--;
		}
		if(isChorea){
			setIsChorea(false);
			return;
		}
		else{
			return;
		}
	}
	
	/**
	 * Felveszi a parameterkent kapott felszerelest
	 * @param eq, felvenni kivant felszereles
	 */
	public void pickupEq(Equipment eq) {
		/**
		 *ADD HOZZ� az quipmentet az equipment_s-hez
		 *AKTIV�LD az equipmentet
		 */
		((Shelter)field).removeEquipment(eq);
		equipment.add(eq);
		eq.enable(this);
	}
	
	/**
	 * Felveszi a parameterkent kapott anyagot
	 * @param r, felvenni kivant anyag
	 */
	public void pickupRe(Resource r) {
		/**
		 *HA a RemainingSize > 0
		 * 		ADD HOZZ� a resource-ot a backpack-hez
		 */
		if(backpack.getRemainingSize() > 0) {
			((Warehouse)field).removeResource(r);
			backpack.add(r);
		}
	}
	
	/**
	 * Felveszi a parameterkent kapott agenst
	 * @param a, felvenni kivant agens
	 */
	public void pickupAg(Agent a){
		/**
		 *ADD HOZZ� AZ agent-et az agents-hez
		 */
		agents.add(a);
	}
	
	/**
	 * Felkeni a parameterkent kapott agens a masik parameterkent kapott virologusra
	 * @param a, milyen agenst
	 * @param t, kire keni
	 */
	public void spreading(Agent a, Virologist t) throws Exception{
		/**
		 *HA IsInvulnerable
		 *		DOBJ hiba�zenetet
		 *K�L�NBEN
		 * 		HA IsCloakActive
		 * 			HA RANDOM FLOAT > 0.823
		 * 				CIKLUS az �genseken:
		 * 					HA van ilyen agens:
		 * 						AKTIV�LD az �genst a c�lponton
		 * 						sikeres = igaz
		 * 				HA nem sikeres:
		 * 					DOBJ hiba�zenetet
		 * 			K�L�NBEN
		 * 				DOBJ hiba�zenetet
		 * 		K�L�NBEN
		 * 			CIKLUS az �genseken:
		 * 				HA van ilyen agens:
		 * 					AKTIV�LD az �genst a c�lponton
		 * 					sikeres = igaz
		 * 				HA nem sikeres:
		 * 					DOBJ hiba�zenetet
		 */
		if(t.getIsInvulnerable()) {	//megn�zz�k, hogy invulnerable-e a c�lpont
			throw new Exception("Invulnerable target!");
		}else {	//ha nem invulnerable, akkor lefuthat a t�mad�s
			if(t.getIsCloakActive()) {	//megn�zz�k, hogy van-e a c�lpontnak k�penye
				//ha van k�penye, akkor a megadott val�sz�n�s�ggel lefut a t�mad�s
				Random r = new Random();
				if(r.nextFloat() > 0.823) {	// https://stackoverflow.com/questions/36612890/do-an-action-with-some-probability-in-java ALAPJ�N
					a.enable(t);
					//t.getAgents().getAgents().add(a);
				}else {	//ha a k�peny kiv�dte a t�mad�st, akkor nem t�rt�nik semmi, csak ki�rjuk a k�perny�re, ami t�rt�nt
					throw new Exception("The targeted Player has a cloak, your attack has been deflecteded!");
				}
			}else {	//ha nem invulnerable a c�lpont �s k�penye sincs, akkor csak sim�n lefut a t�mad�s
				a.enable(t);
				//t.getAgents().getAgents().add(a);
			}
		}
	}
	
	/**
	 * Visszakeni a parameterkent kapott agenst a masik parameterkent kapott virologusra
	 * @param a, milyen agenst
	 * @param t, kire keni vissza
	 */
	public void spreadingBack(Agent a, Virologist t){
		/** KESZTY� ELLEN�RZ�S, ANNAK ELLEN�RZ�SE, hogy AKT�V E RAJTUNK AZ ADOTT �GENS hat�sa
		 * 	HA isGlovesActive �s isAgentActive
		 * 		HA IsInvulnerable
		 * 		 		DOBJ hiba�zenetet
		 * 		 K�L�NBEN
		 * 		  		HA IsCloakActive
		 * 		  			HA RANDOM FLOAT > 0.823
		 * 		  				CIKLUS az �genseken:
		 * 		  					HA van ilyen agens:
		 * 		  						AKTIV�LD az �genst a c�lponton
		 * 		  						sikeres = igaz
		 * 		  				HA nem sikeres:
		 * 		  					DOBJ hiba�zenetet
		 * 		  			K�L�NBEN
		 * 		  				DOBJ hiba�zenetet
		 * 		  		K�L�NBEN
		 * 		  			CIKLUS az �genseken:
		 * 		  				HA van ilyen agens:
		 * 		  					AKTIV�LD az �genst a c�lponton
		 * 		  					sikeres = igaz
		 * 		  				HA nem sikeres:
		 * 		  					DOBJ hiba�zenetet
		 * 	K�L�NBEN
		 * 		DOBJ hiba�zenetet
		 */
		if(isGlovesActive) {
			try{
				a.disable(this);
				spreading(a, t);
				a.setEffected_virologist(t);
				if(a.getEffected_virologist() != this){
					a.setIsSpreadedBack(true);
				}
				equipment.getEquipment(3).setDurability(equipment.getEquipment(3).getDurability() - 1);
				if(equipment.getEquipment(3).getDurability() == 0){		//3 haszn�lat ut�n lej�r a keszt��
					equipment.getEquipment(3).disable(this);
				}
			}
			catch (Exception e){

			}

			//a.enable(t);
		}
	}

	/**
	 * Megn�zi, hogy az adott �gens hat�ssal  van e a virologusra
	 * @param a, milyen agens
	 * @return
	 */
	public boolean isAgentActive(Agent a){
		return false;
	}
	
	/**
	 * Getter az elfelejtett genekhez
	 * @return elfelejtett genek
	 */
	public ArrayList<Gene> forget_Genes() {
		/**
		 *�j LISTA forgotten_genes = genes tartalma
		 *T�R�LD KI az �sszes gene-t a genes-b�l
		 *VISSZAT�R a forgotten_genes-el
		 */

		ArrayList<Gene> forgotten_genes = genes.getGenes(); //az�rt, hogyha visszakenj�k eml�kezzen r� az �gens, hogy miket felejtett el vel�nk
		genes.clear();

		return forgotten_genes;
	}
	
	/**
	 * Eldobja a paramterkent kapott anyagot a msik parameterkent kapott virologusnak es visszater az anyaggal
	 * @param id, milyen anyag
	 * @param v2, kinek dobja oda
	 * @return
	 */
	public void dropRe (int id, Virologist v2) throws NullPointerException {
		/**
		 *HA isParalyzed
		 * 		PR�B�LD
		 * 			VEDD KI a resource-ot a backpack-b�l
		 * 			VEGYE FEL a m�sik virol�gus a resource-ot
		 * 		EGY�BK�NT
		 * 			DOBJ hiba�zenetet
		 *VISSZAT�R a resource-al
		 */
		if(isParalyzed) {
			v2.pickupRe(backpack.remove(id));
		}
	}
	
	/**
	 * Eldobja a parameterkent kapott felszerelest a masik parameterkent kapott virologusnak es visszater a felszerelessel
	 * @param id, milyen felszereles
	 * @param v2, kinek dobja oda
	 * @return
	 */
	public void dropEq(int id, Virologist v2) throws NullPointerException {
		/**
		 *HA isParalyzed
		 * 		VEDD KI az equipment-et a backpack-b�l
		 * 		DEAKTIV�LD az equipment-et a virol�guson
		 * 		VEGYE FEL a m�sik virol�gus az equipmentet
		 *VISSZAT�R az equipment-el
		 */
		if(isParalyzed) {
			Equipment temp = equipment.remove(id);
			temp.disable(this);
			v2.equipment.add(temp);
		}
	}
	
	/**
	 * Eldobja a parameterkent kapott agenst a masik parameterkent kapott virologusnak es visszater az agenssel
	 * @param id, milyen agens
	 * @param v2, kinek dobja oda
	 * @return
	 */
	//public Agent dropAg(Agent a, Virologist v2) {
	public void dropAg(int id, Virologist v2) throws NullPointerException {
		/**
		 *HA isParalyzed
		 * 		VEDD KI az agent-et az agents-b�l
		 * 		VEGYE FEL a m�sik virol�gus az agent-et
		 *VISSZAT�R az agent-el
		 */
		if(isParalyzed) {

			v2.pickupAg(agents.remove(id));
		}
	}
	
	/**
	 * Csinal egy agenst a parameterkent kapott sorszam alapjan donti el melyiket
	 * @param ch, milyen agenst craftol
	 * @return
	 */
	public Agent craft_Agent(int ch) throws Exception{
		/**
		 *HA meg van tanulva az adott g�n
		 *	1. eset:
		 *		HOZZ L�TRE �j Paralyze_agent-et
		 *			HA van elegend� Resource
		 *				ADD HOZZ� az �j �genst az agents-hez
		 *			EGY�BK�NT
		 *				DOBJ hiba�zenetet
		 *		VISSZAT�R az �j agent-el
		 *	2. eset:
		 *		HOZZ L�TRE �j Oblivion_agent-et
		 *			HA van elegend� Resource
		 *				ADD HOZZ� az �j �genst az agents-hez
		 *			EGY�BK�NT
		 *				DOBJ hiba�zenetet
		 *		VISSZAT�R az �j agent-el
		 *	3. eset:
		 *		HOZZ L�TRE �j Invulnerability_agent-et
		 *			HA van elegend� Resource
		 *				ADD HOZZ� az �j �genst az agents-hez
		 *			EGY�BK�NT
		 *				DOBJ hiba�zenetet
		 *		VISSZAT�R az �j agent-el
		 *	4. eset:
		 *		HOZZ L�TRE �j Chorea_agent-et
		 *			HA van elegend� Resource
		 *				ADD HOZZ� az �j �genst az agents-hez
		 *			EGY�BK�NT
		 *				DOBJ hiba�zenetet
		 *		VISSZAT�R az �j agent-el
		 *EGY�BK�NT
		 *		DOBJ hiba�zenetet
		 */
		if(this.genes.hasLearned(ch)) {
			switch(ch) {
			case 1:
				Paralyze_agent p = new Paralyze_agent();
				if(this.backpack.consume(p)) {
					this.agents.add(p);
				}
				else{
					throw new Exception("Kev�s cucc.");
				}
				return p;
			case 2:
				Oblivion_agent o = new Oblivion_agent();
				if(this.backpack.consume(o)) {
					this.agents.add(o);
				}
				else{
					throw new Exception("Kev�s cucc.");
				}
				return o;
			case 3:
				Invulnerability_agent i = new Invulnerability_agent();
				if(this.backpack.consume(i)) {
					this.agents.add(i);
				}
				else{
					throw new Exception("Kev�s cucc.");
				}
				return i;
			case 4:
				Chorea_agent c = new Chorea_agent();
				if(this.backpack.consume(c)) {
					this.agents.add(c);
				}
				else{
					throw new Exception("Kev�s cucc.");
				}
				return c;
			case 5:
				Bear_agent b = new Bear_agent();
				//if(this.backpack.consume(b)) {
					this.agents.add(b);
				//}
				return b;
			}
		}
		else{
			throw new Exception("Nemjo");
		}
		return null;
	}
	
	/**
	 * A virologus megkapja a lepes jogat
	 */
	public void your_Turn() {
		/**
		 *FRISS�TSD az �sszes �genst
		 */
		agents.update_Agents();
	}
	/**
	 * A virologus megtamadja a parameterul kapott virologust
	 * @param v, megtamadni kivant virologus
	 */
	public void attack(Virologist v) throws Exception{
		/**
		 *HA isAxeActive IGAZ
		 * 	�LL�TSD az isDead-et IGAZ-ra a c�lponton
		 * 	DEAKTIV�LD a Axe-et
		 *EGY�BK�NT
		 * 	DOBJ hiba�zenetet
		 */

		if(isAxeActive && equipment.getEquipment(1).getDurability() > 0){
			v.setisDead(true);
			equipment.getEquipment(1).setDurability(0);
			this.setisAxeActive(false);
		}
		else{
			throw new Exception("Nemjo");
		}
	}
	/**
	 * F�ggv�ny, mely visszaadja Stringben a mez� nev�t
	 * @return a mez� neve
	 */

	public String toString(){
		return name;
	}

	/*GETTEREK �S SETTEREK*/
	/*******************************************************************************************************************/

	/**
	 * Setter ami beallitja, hogy a virologus le van-e benitva
	 * @param b
	 */
	public void setIsParalyzed(boolean b) {
		isParalyzed = b;
	}
	
	/**
	 * Getter, ami megmondja, hogy a virologus le van-e benitva
	 * @return
	 */
	public boolean getIsParalyzed() {
		return isParalyzed;
	}
	
	/**
	 * Setter ami beallitja, hogy a virologus vitustancot jar-r
	 * @param b
	 */
	public void setIsChorea(boolean b) {
		isChorea = b;
	}
	
	/**
	 * Getter, ami megmondja, hogy a virologus vitustancot jar-e
	 * @return
	 */
	public boolean getIsChorea() {
		return isChorea;
	}
	
	/**
	 * Setter, ami beallitja, hogy a virologus sebezheto-e
	 * @param b
	 */
	public void setIsInvulnerable(boolean b) {
		isInvulnerable = b;
	}
	
	/**
	 * Getter, ami megmondja, hogy a virologus sebezheto-e
	 * @return
	 */
	public boolean getIsInvulnerable() {
		return isInvulnerable;
	}
	
	/**
	 * Setter, ami beallitja, hogy a zsak aktive-e
	 * @param b
	 */
	public void setIsSackActive(boolean b) {
		isSackActive = b;
	}
	
	/**
	 * Getter, ami megmondja, hogy a zsak aktiv-e
	 * @return
	 */
	public boolean getIsSackActive() {
		return isSackActive;
	}
	
	/**
	 * Setter, ami beallitja, hogy a kopeny aktive-e
	 * @param b
	 */
	public void setIsCloakActive(boolean b) {
		isCloakActive = b;
	}
	
	/**
	 * Getterm, ami megmondja, hogy a kopeny aktiv-e
	 * @return
	 */
	public boolean getIsCloakActive() {
		return isCloakActive;
	}
	
	/**
	 * Setter, ami beallitja, hogy a kesztyu aktiv-e
	 * @param b
	 */
	public void setIsGlovesActive(boolean b) {
		isGlovesActive = b;
	}
	
	/**
	 * Getter, ami megmondja, hogy a kesztyu aktiv-e
	 * @return
	 */
	public boolean getIsGlovesActive() {
		return isGlovesActive;
	}
	
	/**
	 * Setter, ami beallitja, hogy a virologusnak hany lepese van meg
	 * @param i
	 */
	public void setWork_Unit(int i) {
		work_Unit = i;
	}
	
	/**
	 * Getter, ami beallitja, hogy a virologusnak hany lepese van meg
	 * @return
	 */
	public int getWork_Unit() {
		return work_Unit;
	}
	/**
	 * Getter, ami visszaadja, hogy a virologusnak aktiv e a baltaja
	 * @return isAxeActive
	 */
	public boolean getisAxeActive(){
		return isAxeActive;
	}

	/**
	 * Setter, ami beallitja, hogy a virologusnak aktiv e a baltaja
	 */
	public void setisAxeActive(boolean b){
		isAxeActive = b;
	}

	/**
	 * Getter, ami visszaadja, hogy a virologus halott e
	 * @return isDead
	 */
	public boolean getisDead(){
		return isDead;
	}

	/**
	 * Setter, ami beallitja, hogy a virologus halott e
	 */
	public void setisDead(boolean b){
		isDead = b;
	}

	/**
	 * Getter, ami visszaadja, hogy a virologuson akt�v-e az Oblivion agent
	 * @return isOblivion
	 */
	public boolean getisOblivion(){
		return isOblivion;
	}

	/**
	 * Setter, ami beallitja, hogy a virologus halott e
	 */
	public void setisOblivion(boolean b){
		isOblivion = b;
	}

	/**
	 * Getter, ami visszaadja, hogy a virologus medvevirussal fertozott e
	 * @return isBear
	 */
	public boolean getisBear(){
		return isBear;
	}

	/**
	 * Setter, ami beallitja, hogy a virologus medvevirussal fertozott e
	 */
	public void setisBear(boolean b){
		isBear = b;
	}


	
	/**
	 * Setter, ami beallitja, hogy melyik mezon allunk
	 * @param f
	 */
	public void setField(Field f) {
		field = f;
	}
	
	/**
	 * Getter, ami megmondja melyik mezon allunk
	 * @return
	 */
	public Field getField() {
		return field;
	}
	
	/**
	 * Setter, ami beallitja a haizsakot
	 * @param bp
	 */
	public void setBackpack(Backpack bp) {
		backpack = bp;
	}
	
	/**
	 * Getter, ami visszaadja a hatizsakot
	 * @return
	 */
	public Backpack getBackpack() {
		return backpack;
	}
	
	/**
	 * Setter, ami beallitja az agenseket
	 * @param as
	 */
	public void setAgents(Agents as) {
		agents = as;
	}
	
	/**
	 * Getter, ami visszaadja az agenseket
	 * @return
	 */
	public Agents getAgents() {
		return agents;
	}
	
	/**
	 * Setter, ami beallitja a geneket
	 * @param gs
	 */
	public void setGenes(Genes gs) {
		genes = gs;
	}
	
	/**
	 * Getter, ami visszaadja a geneket
	 * @return
	 */
	public Genes getGenes() {
		return genes;
	}
	
	/**
	 * Setter, ami beallitja a felszereleseket
	 * @param eq
	 */
	public void setEquipment(Equipment_s eq) {
		equipment = eq;
	}
	
	/**
	 * Getter, ami visszaadja a felszereleseket
	 * @return
	 */
	public Equipment_s getEquipment() {
		return equipment;
	}

	/**
	 * Getter a Virologus azonos�t�j�hoz
	 * @return name, a Virol�gus azonos�t�ja
	 */
	public String getName(){
		return name;
	}

	/**
	 * Setter a Virologus azonos�t�j�hoz
	 */
	public void setName(String n) { name = n; }

	/**
	 * F�ggv�ny, amely kilist�zza a Virol�gus dolgait, �s azok mennyis�g�t soronk�nt
	 */
	public String listThings(){
		String output = "";
		ArrayList<String> keys = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();

		//ki�rja az �genseket �s a darabsz�mukat
		keys.add("Paralyze agent");
		keys.add("Oblivion agent");
		keys.add("Invulnerability agent");
		keys.add("Chorea agent");
		keys.add("Bear agent");
		for(int i = 0; i < keys.size(); ++i) {
			map.put(keys.get(i), 0);
			for(Agent a: agents.getAgents())
				if(a.getId() - 1 == i)
					map.replace(keys.get(i), map.get(keys.get(i)) + 1);
		}
		//for (String key : keys) System.out.println(key + " " + map.get(key));
		for (String key : keys) output = output.concat(key + " " + map.get(key)+"\n");

		keys.clear();
		map.clear();

		//ki�rja az g�neket �s a darabsz�mukat
		keys.add("Paralyze gene");
		keys.add("Oblivion gene");
		keys.add("Invulnerability gene");
		keys.add("Chorea gene");
		keys.add("Bear gene");
		for(int i = 0; i < keys.size(); ++i) {
			map.put(keys.get(i), 0);
			for(Gene g: genes.getGenes())
				if(g.getId() - 1 == i)
					map.replace(keys.get(i), map.get(keys.get(i)) + 1);
		}

		//for (String key : keys) System.out.println(key + " " + map.get(key));
		for (String key : keys) output = output.concat(key + " " + map.get(key)+"\n");

		keys.clear();
		map.clear();

		//ki�rja az anyagokat �s a darabsz�mukat
		keys.add("Amino acid");
		keys.add("Nukleotid");
		for(int i = 0; i < keys.size(); ++i) {
			map.put(keys.get(i), 0);
			for(Resource r: backpack.getResources())
				if(r.getId() - 1 == i)
					map.replace(keys.get(i), map.get(keys.get(i)) + 1);
		}
		//for (String key : keys) System.out.println(key + " " + map.get(key));
		for (String key : keys) output = output.concat(key + " " + map.get(key)+"\n");

		keys.clear();
		map.clear();

		//ki�rja a t�rgyakat �s a darabsz�mukat
		keys.add("Axe");
		keys.add("Cloak");
		keys.add("Gloves");
		keys.add("Sack");
		for(int i = 0; i < keys.size(); ++i) {
			map.put(keys.get(i), 0);
			for(Equipment e: equipment.getEquipment())
				if(e.getId() - 1 == i)
					map.replace(keys.get(i), map.get(keys.get(i)) + 1);
		}
		//for (String key : keys) System.out.println(key + " " + map.get(key));
		for (String key : keys) output = output.concat(key + " " + map.get(key)+"\n");
		return output;
	}
	/**
	 * F�ggv�ny amivel ujratanulja a megadott geneket a virologus
	 * @param newGenes,  megtanulni kivant genek
	 */
	public void relearn(ArrayList<Gene> newGenes)throws Exception{
		for(int i = 0; i < newGenes.size(); ++i)
			learn_Gene(newGenes.get(i));
	}

	/**
	 * Getter a virol�gus ikonj�hoz
	 * @return
	 */
	public String getVirologistIcon() {
		return virologistIcon;
	}

	/**
	 * Setter a virol�gus ikonj�hoz
	 * @param virologistIcon
	 */
	public void setVirologistIcon(String virologistIcon) {
		this.virologistIcon = virologistIcon;
	}

	/**
	 * ONLY FOR GENERATING TEST FILES
	 */
	public void addEq(Equipment e){
		this.equipment.add(e);
	}

	public void addAq(Agent e){
		this.agents.add(e);
	}
	public void addRe(Resource e){
		this.backpack.add(e);
	}



}