import java.util.Scanner;

public class TicTacToe {

//    private int playerCount = 2;

    private int[][] spielFeld = new int[3][3]; //Spielfeld + Spielzüge
    private boolean spieler1 = true; //private, nur aus der Klasse zugreifen (hier die TicTacToe)

    // Constructor
    public TicTacToe() { // Wegen Aufruf in Main --> public
    }

    /**
     * Starts a new TTT game
     */
    // public = Methodenaufruf von überall, aber nicht für Variablen (müsste direkt in die class)
    public void startGame() { // = Methode
        boolean spielLäuft = true;
        System.out.println("Das Spiel beginnt!");
        System.out.println("Wähle ein Feld mit z.B. a1.");
        Scanner scanner = new Scanner(System.in); //um nicht jedes Mal einen neuen Scanner zu erschaffen in der while Schleife
        while (spielLäuft) {
            String s = spieler1 ? "1" : "2"; // einfacheres if Statement
            System.out.println("Spieler " + s + " ist an der Reihe.");//oder String anfang =
            String eingabeFeld = scanner.nextLine();
            if (eingabeFeld.contains("a") ^ eingabeFeld.contains("b") ^ eingabeFeld.contains("c")) {
                if (eingabeFeld.contains("1") ^ eingabeFeld.contains("2") ^ eingabeFeld.contains("3")) {
                    int[] position = eingabeZuFeld(eingabeFeld);
                    if (spielFeld[position[0]][position[1]] == 0) {// Wenn 0, dann ist die Stelle, da ein Array von sich aus mit Nullen gefüllt
                        blockadeFürFeld(eingabeFeld);
                        printSpielfeld();
                        spieler1 = !spieler1;//dreht den Wert um
                        continue;
                    }
                    System.out.println("Dieses Feld ist schon bestetzt. Bitte wähle ein Anderes.");
                    continue; //continue = neuer Start für die while Schleife und durchläuft nicht mehr den Rest
                }
            }

            System.out.println("Bitte gib etwas zwischen A-C und 1-3 ein.");
        }
    }

    private void blockadeFürFeld(String eingabe) {
        int[] position = eingabeZuFeld(eingabe);        // eingabe = A1 -> position = {0, 0}
        if (spieler1) {
            spielFeld[position[0]][position[1]] = 1; //greift auf arr zu mit der jeweiligen Stelle und weißt zahl1 & 2 position zu
        } else {
            spielFeld[position[0]][position[1]] = 2;// Hier wird das ausgewählte Feld mit einer 1 oder 2 blockiert
        }
    }

    private int[] eingabeZuFeld(String eingabeString) {//wird von eingabeFeld bis hier eingabeString herreicht
        String buchstabe = eingabeString.substring(0, 1);//substring = Teil eines Strings (z.B. A in Auto) ; Ziffern geben Position an
        int zahl1 = 7;
        int zahl2 = Integer.parseInt(eingabeString.substring(1, 2)) - 1;//Integer.parseInt = casten zu int (String zu int); IMMER bei String zu int; -1 zum umrechnen (z.B. a1 -> 0 0)
        switch (buchstabe.toLowerCase()) {
            case "a" -> zahl1 = 0;
            case "b" -> zahl1 = 1;
            case "c" -> zahl1 = 2;
//            default -> System.out.println("")
        }
        int[] arr = {zahl1, zahl2}; // Array mit 2 Stellen erstellt; return new int[]{zahl1, zahl2};
        return arr;
    }

    private void printSpielfeld() {

        System.out.println("-------");
        for (int zeile = 0; zeile < 3; zeile++) { //Von oben nach unten
            for (int spalte = 0; spalte < 3; spalte++) { //Von Links nach Rechts

                int feldWert = spielFeld[zeile][spalte];
                String drucke; //Ersetzt sout
                switch (feldWert) {
                    case 1 -> drucke = "X";
                    case 2 -> drucke = "O";
                    default -> drucke = " ";
//                if (feldWert != 0){
//                    if (feldWert == 1){
//                        System.out.println("X");
//                    } else {
//                        System.out.println("O");
//                    }
                }
                System.out.print(drucke + " ");//wird ausgegeben
            }
            System.out.println();
        }
        System.out.println("-------");
    }

//    public static void startGame() {
//
//    }

}
