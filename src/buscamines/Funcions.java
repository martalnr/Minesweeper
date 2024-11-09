package buscamines;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Marta López-Nuño
 */
public class Funcions {

    /*
     * En aquest mètode donem opció de dificultat pel jugador.
     *
     * @return el grau de dificultat
     *
     */
    public int grauDificultat() {

        Scanner teclat = new Scanner(System.in);
        int grau;

        System.out.println("EL JOC DEL BUSCAMINES!");
        System.out.println("Escull nivell de dificultat: 1(fàcil) - 2(normal) - 3(avançat) ");
        grau = teclat.nextInt();

        while (grau != 1 && grau != 2 && grau != 3) {
            System.out.println("Escull nivell de dificultat: 1(fàcil) - 2(normal) - 3(avançat) ");
            grau = teclat.nextInt();
        }

        if (grau == 1) {
            System.out.println("Nivell fàcil");

        } else if (grau == 2) {
            System.out.println("Nivell normal");

        } else if (grau == 3) {
            System.out.println("Nivell avançat");

        }
        System.out.println("");
        return grau;
    }

    /*
     * En aquest mètode definim el tamany del taulell descobert i l'inicialitzem.
     * 
     */
    public int[][] dimensionsTaulellDescobert(int grauDificultat) {

        int taula[][] = new int[0][0];

        if (grauDificultat == 1) {
            taula = new int[10][10];

        } else if (grauDificultat == 2) {
            taula = new int[15][15];

        } else if (grauDificultat == 3) {
            taula = new int[16][30];

        }

        for (int i = 0; i < taula.length; i++) {
            for (int j = 0; j < taula[i].length; j++) {
                taula[i][j] = 0;
            }
        }
        return taula;
    }

    /*
     * En aquest mètode definim el tamany del taulell tapat i l'inicialitzem.
     */
    public String[][] dimensionsTaulellTapat(int grauDificultat) {

        String taula[][] = new String[0][0];

        if (grauDificultat == 1) {
            taula = new String[10][10];

        } else if (grauDificultat == 2) {
            taula = new String[15][15];

        } else if (grauDificultat == 3) {
            taula = new String[16][30];

        }

        for (int i = 0; i < taula.length; i++) {
            for (int j = 0; j < taula[i].length; j++) {
                taula[i][j] = "#";
            }
        }
        return taula;
    }

    public int numeroMines(int grauDificultat) {

        int nMines = 0;

        if (grauDificultat == 1) {
            nMines = 15;

        } else if (grauDificultat == 2) {
            nMines = 35;

        } else if (grauDificultat == 3) {
            nMines = 99;

        }
        return nMines;

    }

    public void colocarMines(int taulellTapat[][], int mines) {

        Random rand = new Random();
        Posicio p = new Posicio();

        while (mines > 0) {

            p.fila = rand.nextInt(taulellTapat.length);
            p.columna = rand.nextInt(taulellTapat[0].length);
            if (taulellTapat[p.fila][p.columna] != -1) {
                taulellTapat[p.fila][p.columna] = -1;
                mines--;
            }

        }
        //bucle para colocar nums alrededor de las minas
        for (int i = 0; i < taulellTapat.length; i++) {

            for (int j = 0; j < taulellTapat[i].length; j++) {

                if (taulellTapat[i][j] == -1) {

                    for (int k = i - 1; k <= i + 1; k++) {

                        for (int l = j - 1; l <= j + 1; l++) {

                            if (k >= 0 && l >= 0 && k < taulellTapat.length && l < taulellTapat[0].length && taulellTapat[k][l] != -1) {

                                taulellTapat[k][l]++;

                            }

                        }

                    }

                }
            }
        }
    }

    public void printTaulell(String taulellTapat[][]) {
        Posicio p = new Posicio();

        for (int i = -1; i < taulellTapat[0].length; i++) {

            System.out.printf("%3d|", (i + 1));
        }
        System.out.println("");

        for (int i = 0; i < taulellTapat[0].length + 1; i++) {
            System.out.print("----");
        }

        for (int i = 0; i < taulellTapat.length; i++) {
            System.out.println("");
            System.out.printf("%3d|", (i + 1));
            for (int j = 0; j <= taulellTapat[i].length - 1; j++) {
                System.out.printf("%3s|", taulellTapat[i][j]);

            }

            System.out.println("");

        }
        System.out.println("");
        System.out.println("");
    }

    //LUEGO LO HABRÉ DE CERRAR
    public void printTaulellDescobert(int taulellDescobert[][]) {

        for (int i = -1; i < taulellDescobert[0].length; i++) {

            System.out.printf("%3s|", (i + 1));
        }
        System.out.println("");

        for (int i = 0; i < taulellDescobert[0].length + 1; i++) {
            System.out.print("----");
        }

        for (int i = 0; i < taulellDescobert.length; i++) {
            System.out.println("");
            System.out.printf("%3s|", (i + 1));
            for (int j = 0; j <= taulellDescobert[i].length - 1; j++) {
                System.out.printf("%3d|", taulellDescobert[i][j]);

            }

            System.out.println("");

        }

    }

    public char eleccio() {

        char eleccio = '.';

        System.out.println("");
        System.out.println("Introdueix 'd' per descobrir, 'm' per marcar o 'x' per desmarcar.");
        System.out.println("Per reiniciar la partida introdueix 'r'. Per finalitzar la partida introdueix 'f'.");
        System.out.println("Per rebre ajuda prem 'a'.");
        Scanner teclat = new Scanner(System.in);

        eleccio = teclat.next().charAt(0);

        return eleccio;
    }

    public Posicio descobrir(int taulellDescobert[][], char eleccio) {

        Posicio pd = new Posicio();
        Scanner teclat = new Scanner(System.in);

        System.out.println("Sel.lecciona fila: ");
        pd.fila = teclat.nextInt() - 1;

        System.out.println("Sel.lecciona columna: ");
        pd.columna = teclat.nextInt() - 1;

        return pd;
    }

    public void descobrirVoltant(int taulellDescobert[][], String taulellTapat[][], int fila, int columna) {

        if (taulellDescobert[fila][columna] > 0 && taulellDescobert[fila][columna] != -1) {

            taulellTapat[fila][columna] = String.valueOf(taulellDescobert[fila][columna]);

        } else {
            if (!" ".equals(taulellTapat[fila][columna])) {
                taulellTapat[fila][columna] = " ";

                for (int k = fila - 1; k <= fila + 1; k++) {
                    for (int l = columna - 1; l <= columna + 1; l++) {
                        if (k >= 0 && l >= 0 && k < taulellDescobert.length && l < taulellDescobert[0].length && taulellDescobert[k][l] != -1) {
                            descobrirVoltant(taulellDescobert, taulellTapat, k, l);
                        }
                    }
                }
            }
        }
    }

    public Posicio marcar(String taulellTapat[][], char eleccio) {
        Posicio p = new Posicio();
        Scanner teclat = new Scanner(System.in);

        System.out.println("Sel.lecciona fila: ");
        p.fila = teclat.nextInt() - 1;

        System.out.println("Sel.lecciona columna: ");
        p.columna = teclat.nextInt() - 1;

        return p;
    }

    public Posicio desmarcar(String taulellTapat[][], char eleccio) {
        Posicio p = new Posicio();

        Scanner teclat = new Scanner(System.in);

        System.out.println("Sel.lecciona fila: ");
        p.fila = teclat.nextInt() - 1;

        System.out.println("Sel.lecciona columna: ");
        p.columna = teclat.nextInt() - 1;

        return p;
    }

    public void reset(int grau, int taulellDescobert[][], String taulellTapat[][], int mines) {
        taulellDescobert = dimensionsTaulellDescobert(grau);

        for (int i = 0; i < taulellTapat.length; i++) {
            for (int j = 0; j < taulellTapat[0].length; j++) {
                taulellTapat[i][j] = "#";
            }
        }

        colocarMines(taulellDescobert, mines);
        System.out.println("");
        
        System.out.println("Juego reiniciado.");

    }

    public boolean finalitzarPartida() {
        boolean boleano = false;
        System.out.println("");
        System.out.println("El joc ha finalitzat.");
        return boleano;
    }

    public void ajuda() {

        System.out.println("");
        System.out.println("El Buscamines és un joc de tauler amb cel·les que contenen mines.");
        System.out.println("L'objectiu del joc és descobrir totes les cel·les que no contenen mines sense destapar cap cel·la que en tingui una.");
        System.out.println("");
        System.out.println("1. Al principi del joc, es genera un taulell amb cel·les tancades. Algunes d'aquestes cel·les contenen mines i altres no.");
        System.out.println("");
        System.out.println("2. El jugador pot seleccionar una cel·la per a ser destapada. Si la cel·la té una mina, el joc acaba i el jugador perd. Si "
                + "no té una mina, la cel·la mostra un número que indica quantes mines hi ha al seu voltant.");
        System.out.println("");
        System.out.println("3. L'objectiu del joc és descobrir totes les cel·les que no contenen mines. Per fer-ho, el jugador ha d'utilitzar les pistes "
                + "proporcionades pels números mostrats a les cel·les destapades.");
        System.out.println("");
        System.out.println("4. El jugador pot marcar les cel·les que creu que contenen mines amb una bandera o altres marcadors. Això ajuda a evitar "
                + "destapar accidentalment una cel·la amb una mina.");
        System.out.println("");
        System.out.println("5. El joc acaba quan el jugador ha destapat totes les cel·les segures o quan destapa una cel·la amb una mina.");
        System.out.println("");
        System.out.println("6.  Aquest Buscamines té tres nivells de dificultat, amb taulells de mides variables i un nombre diferent de mines: "
                + "1(fàcil): 10x10 15 mines; "
                + "2(normal): 15x15 35 mines; "
                + "3(avançat): 16x30 99 mines.");
        System.out.println("");
        System.out.println("7.  Per guanyar al Buscaminas, els jugadors han de fer servir la lògica i l'estratègia per evitar les mines i descobrir "
                + "les cel·les segures.");

    }

    public boolean verificarDerrota(int taulellDescobert[][], Posicio p) {

        if (taulellDescobert[p.fila][p.columna] == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verificarVictoriaM(String taulellTapat[][], int taulellDescobert[][], int mines) {

        boolean victoria = false;
        int compt = 0;
        for (int i = 0; i < taulellTapat.length; i++) {
            for (int j = 0; j < taulellTapat[0].length; j++) {
                if (compt < mines && taulellDescobert[i][j] == -1 && "X".equals(taulellTapat[i][j])) {
                    compt++;
                    victoria = false;
                }
                if (compt == mines) {
                    victoria = true;
                }
            }
        }

        return victoria;
    }

    public boolean verificarVictoriaD(String taulellTapat[][], int taulellDescobert[][]) {

        for (int i = 0; i < taulellTapat.length; i++) {
            for (int j = 0; j < taulellTapat[0].length; j++) {

                if (taulellDescobert[i][j] == -1 && !"X".equals(taulellTapat[i][j])) {
                    return false;
                }

                if (!" ".equals(taulellTapat[i][j]) && taulellDescobert[i][j] != -1 && !"X".equals(taulellTapat[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }
}
