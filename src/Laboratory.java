import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * A laboratoriumot megvalosito osztalyt
 */
public class Laboratory extends Field {
	/**
	 * A mezon levo genek listaja
	 */
	ArrayList<Gene> gene = new ArrayList<>();
	/**
	 * Konstuktor
	 * @param i, az oldalak szama
	 * @param g, a mezon levo gen
	 */
	public Laboratory(int i, Gene g) {
		super(i);
		colorCode = 1;
		gene = new ArrayList<Gene>();
		gene.add(g);

	}
	/**
	 * Konstruktor
	 * @param i, az oldalak szama
	 */
	public Laboratory(int i) {
		super(i);
		colorCode = 1;
		gene=new ArrayList<Gene>();
	}
	/**
	 * Konstruktor
	 * @param i, az oldalak szama
	 * @param g, felszerelesek a mezon
	 * @param n, hany szomszedja van
	 * @param points, hany pontja van a mezonek
	 */
	public Laboratory(int i, ArrayList<Gene> g, ArrayList<Integer> n, ArrayList<Point> points){
		super(i, n, points);
		colorCode = 1;
		gene.addAll(g);
	}
	
	/**
	 * Getter a laborban levo*
	 * visszat�r a g�nnel, aminek az ID-je megegyezik a f�ggv�ny �ltal kapott ID-vel
	 * @return gene, a laborban levo gen
	 */
	public Gene getGene(int id) {
		/**
		 *�J Gene
*		 *CIKLUS 0-t�l a gene t�mb m�ret�ig
		 *	HA gene ID == id
		 *		g = gene
		 *		VISSZAT�R g-vel
		 *CIKLUS V�GE
		 *VISSZAT�R NULL-al
		 */
		for(int i = 0; i < gene.size(); i++){
			if(gene.get(i).getId() == id){
				Gene g = gene.get(i);
				return g;
			}
		}
		return null;
	}

	/**
	 * F�ggv�ny, mely visszaadja Stringben a mez� nev�t
	 * @return a mez� neve
	 */
	public String toString(){

		/**
		 * ciklus genes.toString
		 */
		return "Laboratory";
	}

	/**
	 * F�ggv�ny, mely hozz�ad a mez� virol�gusaihoz egy param�terben kapott virol�gust
	 * @param v, a hozzaadni kivant virologus
	 */
	public void addVirologist(Virologist v) {
		/**
		 * HA a gene medve
		 * 		virologus megtanulja medv�t
		 * virologust hozz�adjuk a list�hoz
		 */

		p.addIcon(v.getVirologistIcon());

		for(int i = 0; i < gene.size(); i++){		//medv�s labor kezel�se
			if(gene.get(i).getId() == 5){
				try{
					if(!v.getIsInvulnerable()) {
						if (v.getIsCloakActive()) {
							Random r = new Random();
							if (r.nextFloat() > 0.823) {
								v.learn_Gene(new Bear_gene());
								v.craft_Agent(5);
								v.spreading(v.getAgents().remove(5), v);
								v.craft_Agent(5);
								//Eredm�ny �tad�sa a Prototype-nak
								Prototype.results = "Stepped on Bear infected Laboratory, you have been infected with Bear Agent";
							}
						} else {

							v.learn_Gene(new Bear_gene());
							v.craft_Agent(5);
							v.spreading(v.getAgents().remove(5), v);
							v.craft_Agent(5);
							//Eredm�ny �tad�sa a Prototype-nak
							Prototype.results = "Stepped on Bear infected Laboratory, you have been infected with Bear Agent";
						}
					}
				}catch(Exception ex){
				}
			}
		}
		virologists.add(v);
	}

	/**
	 * Ki�rja a mez� tartalm�t sorrendben
	 * @param sor,
	 * @param listVirologists, kiirja e a virologusokat
	 * @param listItems, kiirja e a dolgokat
	 * @return a kimenettel ter vissza
	 */
    public String listThings(int sor, boolean listVirologists, boolean listItems){
        String output = "";
        int sorszam=0;
        if(virologists.size() == 0 && gene.size() == 0)
            //System.out.println();
            output = "\n";
        else if(listItems){
            for(int i = 0; i < gene.size(); ++i) {
                //System.out.println(i + ". " + res.get(i).toString());
                output = output.concat(i + 1 + ". " + gene.get(i).toString() + "\n");
                sorszam = i + 1;
            }
        }
        if(virologists.size() != 0)
            output=output.concat(super.listThings(sorszam, listVirologists, listItems));

        return output;
    }

	/**
	 * Getter a Laborat�rium g�njeihez
	 * @return a g�nek
	 */
	public ArrayList<Gene> getGenes(){
		return gene;
	}

	/**
	 * F�ggv�ny, mely hozz�ad egy param�terben kapott g�nt a g�nekhez
	 * @param g, a hozz�adand� g�n
	 */
	public void addGene(Gene g) {
		gene.add(g);
	}


}
