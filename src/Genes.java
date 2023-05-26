import java.util.ArrayList;
/**
 * A geneket tarolo osztaly
 */
public class Genes implements ThingList{
	/**
	 * Hany db gent szerzett meg eddig
	 */
	private int acquired;
	/**
	 * Lista a genekbol
	 */
	private ArrayList<Gene> genes = new ArrayList<>();
	
	/**
	 * Konstuktor
	 */
	public Genes() {
		acquired = 0; 
		genes = new ArrayList<Gene>();
	}
	/**
	 * Konstuktor
	 * @param i , hanyat szerzett meg
	 * @param g , lista a megszerzett genekbol
	 */
	public Genes(int i, ArrayList<Gene> g){
		acquired = i;
		genes.addAll(g);
	}
	
	/**
	 * Hozzaadja a listahoz a parameterkent kapott objektumot
	 * @param o, a hozzaadni kivant objektum
	 */
	public void add(Object o) {

		Gene newgene = (Gene)o;
		//Ellen�rz�s hogy meg volt e m�r tanulva kor�bban! 
		if(!hasLearned(newgene.getId())) {
			genes.add((Gene)o);
			acquired+=1;
			if(acquired == 4) {		//nem kell belesz�m�tani a Bear_gene-t
				//CALL Game game_over method
			}
		}
		
	}
	
	/**
	 * Getter a megszerzett gen szekvenciak szamaval ter vissza
	 * @return acquired
	 */
	public int getAcquired() {

		return acquired;
	}

	/**
	 * Torli a listabol a parameterkent kapott objektumot, majd visszater vele
	 * @param o, a torolni kivant objektum
	 * @return o, a torolt objektm
	 */
	public Object remove(Object o) {

		genes.remove(o);
		
		return o;
	}
	
	/**
	 * kapott sz�m alapj�n megn�zi, hogy a kor�bban megtanult g�nek k�z�l m�r van e ilyen sz�m
	 * @param a
	 * @return
	 */
	public boolean hasLearned(int a) {

		for(int i=0; i<genes.size(); i++) {
			if(genes.get(i).getId()==a) { 
				

				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Getter a listahoz.
	 * @return genes
	 */
	public ArrayList<Gene> getGenes(){
		return genes;
	}
	
	/**
	 * Torli a lista tartalmat
	 */
	public void clear() {

		genes.clear();
		
	}

}
