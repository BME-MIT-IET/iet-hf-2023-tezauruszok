/**
 * A loggolasert felelos osztalyt
 */
public class Logger {
	static int indend_level = 0;
	/**
	 * A függvény hívásakor ez a függvény logolja, és írja ki a paraméterben kapott osztályt, és metódusnevet
	 * @param osztaly, a hívott osztály
	 * @param metodus, a hívott metódus
	 */
	public static void callMethod(String osztaly, String metodus) {
		printThings(osztaly + " " + metodus +"\n");
		indend_level+=1;
	}
	/**
	 * A függvény visszatérésekor ez a függvény logolja, és írja ki a paraméterben kapott osztályt, és metódusnevet
	 * @param osztaly, a hívott osztály
	 * @param metodus, a hívott metódus
	 */
	public static void returnMethod(String osztaly, String metodus) {
		indend_level-=1;
		printThings(metodus +" "+"returns\n");
	}
	/**
	 * A megfelelõ indentálásért, és a String kiírásáért felelõs függvény
	 * @param s, a kiírandó String
	 */
	public static void printThings(String s) {
		for(int i=0; i<indend_level; i++) {
			System.out.printf("\t");
		}
		System.out.printf(s);
	}
}
