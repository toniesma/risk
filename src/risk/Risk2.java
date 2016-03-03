package risk;

import java.util.Random;
import java.util.Scanner;

public class Risk2 {
	/**
	 * DEFINICIÓ DE CONSTANTS
	 */

	/**
	 * Véctor (array) amb els noms dels continents. La posició del continent
	 * dins del vector l'identifica en les diferents matrius o arrays on es
	 * relaciona. Seria la seva clau primària.
	 */
	public static final String[] continents = { "Amèrica Nord", "Amèrica Sud", "Àfrica", "Europa", "Àsia", "Oceania" };

	/**
	 * Vector (array) amb els noms dels territoris. Es relacionen amb el seu
	 * continent ja que el nombre de fila correspon a la posició del array
	 * continents.
	 *
	 * territoris[0] -> Correspondrà als territoris del continent[0] Amèrica del
	 * Nord territoris[1] -> Correspondrà als territoris del continent[1]
	 * Amèrica Sud
	 */
	public static final String[] territoris = { "Alaska", "Territorio del nor-oeste", "Groenlandia", "Alberta",
			"Ontario", "Labrador", "Territorio del oeste", "Territorio del este", "America central", "Venezuela",
			"Perú", "Argentina", "Brasil", "África del norte", "Egipto", "Africa Oriental", "Congo", "África del sur",
			"Magadascar", "Europa Occidental", "Gran Bretaña", "Islandia", "Escandinavia", "Europa del norte",
			"Europa del sur", "Ucrania", "Ural", "Afganistan", "Oriente Medio", "Siberia", "Yakutia", "Chita",
			"Kamchatka", "Mongolia", "Japon", "China", "Siam", "India", "Indonesia", "Nueva Guinea",
			"Australia Occidental", "Australia Oriental" };

	/**
     * Matriu (array de dues dimensions) que ens permet identificar els païssos veïns i així poder moure exèrcits entre ells o atacar. Segons moment de la partida.
     *
     * veins[0] ens llista els territoris fronteres amb Alaska.
     */
	
	// 0 y 2 ?? Son vecinos
	
    public static final int[][] veins={{1,3,32},{0,2,3,4},{1,4,5,21},{0,1,4,6},{1,2,3,5,6,7},{2,4,7},{3,4,5,7,8},{6,4,5,8},{6,7,9},{8,10,12},{9,11,12},{10,12},{9,10,11,13},{12,14,15,16,19},{13,15,24,28},{13,14,16,17,18},{13,15,18},{15,17},
   		 {20,13,23,24},{19,21,23,22},{20,23,2},{21,25,23,20},{19,20,22,24,25},{19,20,22,24,25},{13,14,19,23,25},{22,23,24,26,27,28},{25,27,28,29},{25,26,28,37,35},{27,37,14},{28,31,33,34,35},{29,31,32},{30,31,32,33,34,0},{29,30,32,33},{29,31,32,34,35},
   		{32,33},{29,28,27,37,36,35},{35,37,38},{35,36,27,28},{40,39,36},{38,40,41},{38,39,41},{40,39}};

	/**
	 * Véctor (array) amb els objectius del joc. La posició de l'objectiu dins
	 * del vector l'identifica en les diferents matrius o arrays on es
	 * relaciona. Seria la seva clau primària.
	 */
	public static final String[] objectius = { "Amèrica sur i Àfrica", "Amèrica del nord i Oceania", "Àfrica i Àsia" };

	/**
	 * Véctor (array) amb la quantitat d'exèrcits inicials. En la posició 0
	 * correspon a 3 jugadors i la posició 3 a 6 jugadors.
	 */
	public static final int[] exercicitsInicials = { 35, 30, 25, 20 };

	/**
	 * Véctor (array) amb la quantitat d'exèrcits que guanyes per continent
	 * conquistat. En la posició 0 correspon a Amèrica del Nord i la 5 a
	 * Oceania.
	 */
	public static final int[] continentsComplets = { 5, 2, 3, 5, 7, 2 };

	/**
	 * Nombre que divideix els païssos conquerits per saber les unitats de
	 * reforç.
	 */
	public static final int divisioTerritori = 3;
	/**
	 * Nombre màxims de jugadors que poden jugar al DamRISK.
	 */
	public static final int maxJugadors = 6;
	/**
	 * Nombre mínim de jugadors que poden jugar al DamRISK.
	 */
	public static final int minJugadors = 3;
	
	/**
     * Matriu (array de dues dimensions) que ens permet identificar a quin continent pertany cada païs. Com sempre juguem amb la posició dels arrays com a clau primària
     *
     * paissosContinent[0] són els païssos d'Amèrica del Nord
     *
     * paissosContinent[0][0] -> hi ha el territoris[0]
     */
    public static final int[][] paissosContinent= {{0,1,2,3,4,5,6,7,8},{9,10,11,12},{13,14,15,16,17,18},{19,20,21,22,23,24,25},{26,27,28,29,30,31,32,33,34,35,36,37},{38,39,40,41}};

    // paissosContinent[0][0] = territoris[0];
    // paissosContinent[1][0] = territoris[9];
    // paissosContinent[2][0] = territoris[13];
    // paissosContinent[3][0] = territoris[19];
    // paissosContinent[4][0] = territoris[26];
    // paissosContinent[5][0] = territoris[38];

	public static void main(String[] args) {
		
		/**
		 * Matriu que representa el tauler de joc. Cada posició té un array on
		 * es guarda la informació següent {Id jugador, Número exercits} Cada
		 * posició és un territori.
		 */
		int[][] tauler = new int[42][2];

		/**
		 * Inicialitzem el tauler sense jugadors, valor -1. Doncs el jugador 0
		 * existeix
		 */
		for (int i = 0; i < tauler.length; i++) {
			tauler[i][0] = -1;
			tauler[i][1] = -1;
		}

		/**
		 * Vector per guardar els noms dels jugadors. La posició dins del vector
		 * és l'identificador de jugador.
		 */
		String[] nomsJugadors = null;
		/**
		 * Matriu on guardem la informació del joc per a cada jugador. On
		 * guardem la informació del jugador. Per a cada jugador guardem
		 * nomsJugadors[0] li correspon la infoJugadors {objectiu, cavalleria,
		 * artilleria, cano, comodi }
		 */
		int[][] infoJugadors = null;
		
		
		

		/**
		 * Enter que indicarà el nombre de jugador que li toca tirar.
		 */
		int torn = 0;

		/**
		 * Java.util.Scanner ens permet llegir de consola les dades dels usuaris
		 */
		// Codi per demanar el nombre de jugadors
		int nJugadors = demanarNumeroJugadors();

		// Dimensionar els vectors nomsJugadors i infoJugadors
		nomsJugadors = new String[nJugadors];
		infoJugadors = new int[nJugadors][5];

		// Calcular nombre d'exèrcits inicials
		int exercitsInicicials = exercicitsInicials[nJugadors - 3];

		// Demanar les dades als jugadors i preparar-los per poder iniciar el
		// joc.
		for (int i = 0; i < nJugadors; i++) {

			// Demanar el nom i guardar-ho en el vector
			nomsJugadors[i] = demanarNomJugador(i);

			// Assignar objetiu
			infoJugadors[i][0] = rnd(objectius.length);

			printObjectiuJugador(i, infoJugadors);

			// Repartir territoris
			repartirTerritoris(tauler, nJugadors, i, nomsJugadors);

			// Assignar tropes
			assignarTropes(tauler, i, exercitsInicicials);

		}
		// Decidir qui comença a jugar i dir-ho per pantalla
		iniciaTorn(nomsJugadors);
		printTauler(tauler, nomsJugadors);
		
		
		
		// Fase de refuerzo
		// En función de los territoris se asignan tropas.
		// 1/3 de los territorios se dan como tropas
		
		//faseRefuerzo(jugador);
		
		// + de un soldado
		// Desde un territorio propio elegimos un territorio
		// adyacente al que atacar (de otro jugador)
		// faseCombate();
		
		// Empate gana el defensor
		// Atacante - de 1 a 3 dados
		// Defensor - de 1 a 2 dados
		
		// Se comparan las mejores puntuaciones 
		
		
		
	}

	private static int iniciaTorn(String[] nomsJugadors) {
		int torn = rnd(nomsJugadors.length);
		System.out.println("Comença el jugador " + nomsJugadors[torn]);

		return torn;
	}

	public static void assignarTropes(int[][] tauler, int jugador, int nExercits) {

		int exercitsPosicio = 0;
		for (int i = 0; i < tauler.length; i++) {
			// Assignem un exèrcit per defecte
			if (tauler[i][0] == jugador) {

				exercitsPosicio = demanarExercitsPosicio(tauler, i, nExercits);

				// Afegim exercits al territori
				tauler[i][1] += exercitsPosicio;
				// Eliminem exercits de les tropes disponibles
				nExercits -= exercitsPosicio;
				if (nExercits == 0) {
					System.out.println("Ja no et queden exèrcits per assignar.");
					break;
				}
			}
		}
	}

	private static int demanarExercitsPosicio(int[][] tauler, int i, int maxExercits) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Actualment tens " + tauler[i][1] + " exèrcits en el territori " + territoris[i]);
		System.out.println("Indica quants exèrcits vols afegir més (mínim 0 i màxim " + maxExercits + ")");

		int nExercits = 0;
		do {
			nExercits = (scanner.hasNextInt() ? scanner.nextInt() : 0);
		} while (nExercits < 0 || nExercits > maxExercits);

		return nExercits;
	}

	private static int demanarNumeroJugadors() {
		Scanner scanner = new Scanner(System.in);

		int nJugadors;
		System.out.println("Indica el número de jugadors (mínim 3 i màxim 6)?");
		do {
			nJugadors = (scanner.hasNextInt() ? scanner.nextInt() : 0);
		} while (nJugadors < 3 && nJugadors > 6);

		return nJugadors;
	}

	private static String demanarNomJugador(int jugador) {
		Scanner scanner = new Scanner(System.in);

		String nom = "";
		System.out.println("Indica el nom del jugador " + (jugador + 1) + ".");

		do {
			nom = (scanner.hasNext() ? scanner.next() : "");
		} while (nom.length() <= 0);

		return nom;
	}

	private static void printObjectiuJugador(int jugador, int[][] infoJugadors) {
		System.out.println("L'objectiu és " + objectius[infoJugadors[jugador][0]]);
	}

	private static void printTauler(int[][] tauler, String[] nomsJugadors) {
		for (int i = 0; i < tauler.length; i++) {
			System.out.println(territoris[i] + "-" + nomsJugadors[tauler[i][0]] + "-" + tauler[i][1]);
		}
	}

	private static void repartirTerritoris(int[][] tauler, int nJugadors, int jugador, String[] nomsJugadors) {

		int nTerritoris = territoris.length / nJugadors;
		int restaTerritoris = territoris.length % nJugadors;

		int t = 0;
		for (int i = 0; i < nTerritoris; i++) {
			t = trobaPosicioLliure(tauler);
			// Jugador per defecte
			tauler[t][0] = jugador;
			// Exèrcit per defecte
			tauler[t][1] = 1;
		}

		// Mirem si resten territoris per repartir en funció del número de
		// jugadors
		if (restaTerritoris > 0 && jugador < restaTerritoris) {
			t = trobaPosicioLliure(tauler);
			tauler[t][0] = jugador;
			tauler[t][1] = 1;
		}

		printTerritoris(tauler, jugador, nomsJugadors);
	}

	private static int trobaPosicioLliure(int[][] tauler) {

		// Prenem una posició a l'atzar
		int t = rnd(tauler.length);

		// Iterem fins que trobem una posició lliure
		while (tauler[t][0] != -1) {
			// Incrementem t i comprovem que no es surti del rang del vector
			t = t + 1;
			t = t % (tauler.length - 1);
		}

		return t;
	}

	private static int rnd(int max) {
		Random rnd = new Random();
		return rnd.nextInt(max);
	}

	private static void printTerritoris(int[][] tauler, int jugador, String[] nomsJugadors) {

		System.out.println("Aquests són els teus territoris...");

		for (int i = 0; i < tauler.length; i++) {
			if (tauler[i][0] == jugador) {
				System.out.println(territoris[i] + " - " + nomsJugadors[jugador] + " - " + tauler[i][1]);
			}
		}
		System.out.println();
	}
	
	private static boolean esVei(int territoriActual, int territoriDesconegut) {
		for (int i=0; i < veins[territoriActual].length; i++ ) {
			if (veins[territoriActual][i] == territoriDesconegut) return true;
		}
		return false;
	}

}