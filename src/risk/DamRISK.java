package risk;

import java.util.Scanner;

public class DamRISK {

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
			"Ontario", "Labrador", "Territorio del oeste", "Territorio del este", "America central",

			"Venezuela", "Perú", "Argentina", "Brasil", "África del norte", "Egipto", "Africa Oriental", "Congo",
			"África del sur", "Magadascar", "Europa Occidental", "Gran Bretaña", "Islandia", "Escandinavia",
			"Europa del norte", "Europa del sur", "Ucrania", "Ural", "Afganistan", "Oriente Medio", "Siberia",
			"Yakutia", "Chita", "Kamchatka", "Mongolia", "Japon", "China", "Siam", "India", "Indonesia", "Nueva Guinea",
			"Australia Occidental", "Australia Oriental" };

	/**
	 * Matriu (array de dues dimensions) que ens permet identificar els païssos
	 * veïns i així poder moure exèrcits entre ells o atacar. Segons moment de
	 * la partida.
	 */
	// public static final String[][] fronteres={{"Alaska,Kamchatka"}};
	public static final String[][] veins = { { "Alaska,Kamchatka" } };

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
	public static final int[] exercitsInicials = { 35, 30, 25, 20 };

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

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		int numJugadors;
		int contadorJugador1 = 0;
		int contadorJugador2 = 0;
		int contadorJugador3 = 0;
		int contadorJugador4 = 0;
		int contadorJugador5 = 0;
		int contadorJugador6 = 0;
		int tropasJugador1 = 0;
		int tropasJugador2 = 0;
		int tropasJugador3 = 0;
		int tropasJugador4 = 0;
		int tropasJugador5 = 0;
		int tropasJugador6 = 0;
		int indiceTerritorio = 0;

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
		for (int territori = 0; territori < tauler.length; territori++) {
			tauler[territori][0] = -1;
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
		// TODO Codi per demanar el nombre de jugadors
		System.out.println("introduce el numero de jugadores. 6 como máximo");
		do {
			numJugadors = lector.nextInt();
		} while ((numJugadors > maxJugadors) || (numJugadors < minJugadors));

		if ((numJugadors == 4) || (numJugadors == 5)) {
			int territorisPerJugador = 40;
			contadorJugador1--;
			contadorJugador2--;
		}

		// TODO Dimensionar els vectors nomsJugadors i infoJugadors

		nomsJugadors = new String[numJugadors];
		infoJugadors = new int[numJugadors][5];

		// TODO Calcular nombre d'exèrcits inicials

		switch (numJugadors) {
		case 3:
			int exercitsInicials = 35;
		case 4:
			exercitsInicials = 30;
		case 5:
			exercitsInicials = 25;
		case 6:
			exercitsInicials = 20;
		}

		// TODO Demanar les dades als jugadors i preparar-los per poder iniciar
		// el joc.
		for (int jugador = 0; jugador < numJugadors; jugador++) {

			// TODO Demanar el nom i guardar-ho en el vector
			System.out.println("dime el nombre del jugador numero " + (jugador + 1) + ".");
			nomsJugadors[jugador] = lector.next();

			// TODO Assignar objetiu
			// infoJugadors = new int[numJugadors][objectiu, cavalleria,
			// artilleria, cano, comodi]
			// 1,2,3

			int numeroAleatorio = (int) (Math.random() * 2 + 0);
			infoJugadors[jugador][0] = numeroAleatorio;

			// TODO Repartir territorios
		}

		// TODO Assignar tropes
		System.out.println(" debes asignar las tropas");
		switch (numJugadors) {
		case 3:
			tropasJugador1 = 35;
			tropasJugador2 = 35;
			tropasJugador3 = 35;
		case 4:
			tropasJugador1 = 30;
			tropasJugador2 = 30;
			tropasJugador3 = 30;
			tropasJugador4 = 30;
		case 5:
			tropasJugador1 = 25;
			tropasJugador2 = 25;
			tropasJugador3 = 25;
			tropasJugador4 = 25;
			tropasJugador5 = 25;
		case 6:
			tropasJugador1 = 20;
			tropasJugador2 = 20;
			tropasJugador3 = 20;
			tropasJugador4 = 20;
			tropasJugador5 = 20;
			tropasJugador6 = 20;
		}
		for (int i = 0; i < territoris.length; i++) {

			int territorisPerJugador = territoris.length / numJugadors;

			tauler[i][0] = (int) (Math.random() * numJugadors + 0);

			if ((tauler[i][0] == 0) && (contadorJugador1 < territorisPerJugador)) {
				contadorJugador1++;
				tauler[i][1] = 1;
				tropasJugador1--;
			} else {
				if ((tauler[i][0] == 1) && (contadorJugador2 < territorisPerJugador)) {
					contadorJugador2++;
					tauler[i][1] = 1;
					tropasJugador2--;
				} else {
					if ((tauler[i][0] == 2) && (contadorJugador3 < territorisPerJugador)) {
						contadorJugador3++;
						tauler[i][1] = 1;
						tropasJugador3--;
					} else {
						if ((tauler[i][0] == 3) && (numJugadors > 3) && (contadorJugador4 < territorisPerJugador)) {
							contadorJugador4++;
							tauler[i][1] = 1;
							tropasJugador4--;
						} else {
							if ((tauler[i][0] == 4) && (numJugadors > 4) && (contadorJugador5 < territorisPerJugador)) {
								contadorJugador5++;
								tauler[i][1] = 1;
								tropasJugador5--;
							} else {
								if ((tauler[i][0] == 5) && (numJugadors > 4)
										&& (contadorJugador6 < territorisPerJugador)) {
									contadorJugador6++;
									tauler[i][1] = 1;
									tropasJugador6--;
								} else {
									i--;
								}
								{
									System.out.println("***************************************************");
								}
							}
						}
					}
				}
			}
			// TODO Decidir qui comença a jugar i dir-ho per pantalla

			/**
			 * Pinta el tauler com ha quedat
			 * 
			 */
		}
		for (int territori = 0; territori < tauler.length; territori++) {
			System.out.println(territoris[territori] + "-" + nomsJugadors[tauler[territori][0]] + "-tropas-"
					+ tauler[territori][1]);

		}
		System.out.println("***************************************************");
		System.out.println("***************************************************");
		System.out.println("***************************************************");
		switch (numJugadors) {
		case 3:
			System.out.println("Al jugador 1 le quedan " + tropasJugador1 + " tropas por colocar");
			System.out.println("Al jugador 2 le quedan " + tropasJugador2 + " tropas por colocar");
			System.out.println("Al jugador 3 le quedan " + tropasJugador3 + " tropas por colocar");
			break;
		case 4:
			System.out.println("Al jugador 1 le quedan " + tropasJugador1 + " tropas por colocar");
			System.out.println("Al jugador 2 le quedan " + tropasJugador2 + " tropas por colocar");
			System.out.println("Al jugador 3 le quedan " + tropasJugador3 + " tropas por colocar");
			System.out.println("Al jugador 4 le quedan " + tropasJugador4 + " tropas por colocar");
			break;
		case 5:
			System.out.println("Al jugador 1 le quedan " + tropasJugador1 + " tropas por colocar");
			System.out.println("Al jugador 2 le quedan " + tropasJugador2 + " tropas por colocar");
			System.out.println("Al jugador 3 le quedan " + tropasJugador3 + " tropas por colocar");
			System.out.println("Al jugador 4 le quedan " + tropasJugador4 + " tropas por colocar");
			System.out.println("Al jugador 5 le quedan " + tropasJugador5 + " tropas por colocar");
			break;
		case 6:
			System.out.println("Al jugador 1 le quedan " + tropasJugador1 + " tropas por colocar");
			System.out.println("Al jugador 2 le quedan " + tropasJugador2 + " tropas por colocar");
			System.out.println("Al jugador 3 le quedan " + tropasJugador3 + " tropas por colocar");
			System.out.println("Al jugador 4 le quedan " + tropasJugador4 + " tropas por colocar");
			System.out.println("Al jugador 5 le quedan " + tropasJugador5 + " tropas por colocar");
			System.out.println("Al jugador 6 le quedan " + tropasJugador6 + " tropas por colocar");
			break;
		}
		System.out.println("***************************************************");
		System.out.println(nomsJugadors[0] + ", estos son tus territorios y sus tropas");
		System.out.println("***************************************************");
		for (int j = tropasJugador1; j > 0; j--) {
			for (int i = 0; i < 42; i++) {
				if (tauler[i][0] == 0) {
					System.out.println((i + 1) + "." + territoris[i] + " tiene " + tauler[i][1] + " tropas");
				}
			}
			System.out.println(nomsJugadors[0] + ", indica donde quieres asignar una tropa ");
			indiceTerritorio = lector.nextInt();
			indiceTerritorio = indiceTerritorio--;

			if ((tauler[indiceTerritorio - 1][0] == 0) && (tropasJugador1 > 0)) {
				tauler[indiceTerritorio - 1][1] = tauler[indiceTerritorio - 1][1] + 1;
				tropasJugador1 = tropasJugador1--;
			} else {
				System.out.println("ese territorio NO es tuyo, vuelve a intentarlo");

			}
		}

	}

}
