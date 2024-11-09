package buscamines;

/**
 *
 * @author Marta López-Nuño
 */
public class BuscaMines {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Funcions f = new Funcions();

        //escollir nivell del joc:
        int nivell = f.grauDificultat();

        //definim tamany del taulell i l'inicialitzem segons el nivell:
        int taulellDescobert[][] = f.dimensionsTaulellDescobert(nivell);
        String taulellTapat[][] = f.dimensionsTaulellTapat(nivell);

        //definim el nombre de mines que té el taulell segons el numero de dificultat:
        int mines = f.numeroMines(nivell);

        //posem les mines de forma aleatòria al taulell:      
        f.colocarMines(taulellDescobert, mines);

        //imprimir el taulell que veurà el jugador:
        f.printTaulell(taulellTapat);

        //imprimir el taulell descobert: taulell per fer comprovacions
            //f.printTaulellDescobert(taulellDescobert);

        //comença el joc
        boolean jocActiu = true;

        //comença el temporitzador
        long inici = System.currentTimeMillis();

        while (jocActiu == true) {

            //El jugador selecciona el que vol fer
            char eleccio = '.';
            eleccio = f.eleccio();

            //per descobrir
            if (eleccio == 'd') {
                Posicio pd = f.descobrir(taulellDescobert, eleccio);

                if (f.verificarDerrota(taulellDescobert, pd) == true) {

                    System.out.println("Era una mina! Has perdut.");
                    System.out.println("");
                    jocActiu = false;

                } else if (f.verificarVictoriaD(taulellTapat, taulellDescobert)) {
                    System.out.println("Has descobert totes les cel.les sense mines! Has guanyat!");
                    System.out.println("");
                    jocActiu = false;

                } else {
                    f.descobrirVoltant(taulellDescobert, taulellTapat, pd.fila, pd.columna);
                    f.printTaulell(taulellTapat);
                }

                //per marcar
            } else if (eleccio == 'm') {
                Posicio pm = f.marcar(taulellTapat, eleccio);
                taulellTapat[pm.fila][pm.columna] = "X";
                f.printTaulell(taulellTapat);

                if (f.verificarVictoriaM(taulellTapat, taulellDescobert, mines) == true) {
                    System.out.println("Has trobat totes les mines! Has guanyat!");
                    jocActiu = false;
                }

                //per desmarcar
            } else if (eleccio == 'x') {
                Posicio px = f.desmarcar(taulellTapat, eleccio);
                taulellTapat[px.fila][px.columna] = "#";
                f.printTaulell(taulellTapat);

                //demanar ajuda
            } else if (eleccio == 'a') {
                f.ajuda();

                //reiniciar joc
            } else if (eleccio == 'r') {
                f.reset(nivell, taulellDescobert, taulellTapat, mines);
                f.printTaulell(taulellTapat);
            } //acabar joc
            else if (eleccio == 'f') {
                jocActiu = f.finalitzarPartida();
            }

            //es para el temporitzador
            long fi = System.currentTimeMillis();

            System.out.println(
                    "");
            System.out.println(
                    "Durada de la partida: " + ((fi - inici) / 1000) + (" segons."));
            System.out.println(
                    "");

        }

    }
}
