/**
 * A loggolasert felelos osztalyt
 */
public class Logger {
	static int indend_level = 0;
	/**
	 * A f�ggv�ny h�v�sakor ez a f�ggv�ny logolja, �s �rja ki a param�terben kapott oszt�lyt, �s met�dusnevet
	 * @param osztaly, a h�vott oszt�ly
	 * @param metodus, a h�vott met�dus
	 */
	public static void callMethod(String osztaly, String metodus) {
		printThings(osztaly + " " + metodus +"\n");
		indend_level+=1;
	}
	/**
	 * A f�ggv�ny visszat�r�sekor ez a f�ggv�ny logolja, �s �rja ki a param�terben kapott oszt�lyt, �s met�dusnevet
	 * @param osztaly, a h�vott oszt�ly
	 * @param metodus, a h�vott met�dus
	 */
	public static void returnMethod(String osztaly, String metodus) {
		indend_level-=1;
		printThings(metodus +" "+"returns\n");
	}
	/**
	 * A megfelel� indent�l�s�rt, �s a String ki�r�s��rt felel�s f�ggv�ny
	 * @param s, a ki�rand� String
	 */
	public static void printThings(String s) {
		for(int i=0; i<indend_level; i++) {
			System.out.printf("\t");
		}
		System.out.printf(s);
	}
}
