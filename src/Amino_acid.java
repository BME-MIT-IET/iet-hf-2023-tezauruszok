/**
 * A jatekban az agensekhez szukseges alapanyagok egyik tipusa
  */
public class Amino_acid extends Resource{
    /**
     * Konstruktor
     */
    public Amino_acid(){
        id = 1;
    }
    /**
     * F�ggv�ny, mely visszaadja Stringben az amino sav nevet
     * @return amino sav neve
     */
    public String toString(){
        return "Amino_acid";
    }
}
