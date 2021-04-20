import java.util.Scanner;

public class TicTacToe {

//    private int playerCount = 2;

    private int[][] spielFeld = new int[3][3]; //Spielfeld + Spielzüge; Wenn es nicht befüllt ist (hier mit 1 (X) oder 2 (O)), dann ist es null
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
            String s = spieler1 ? "1" : "2"; // einfacheres if Statement; spieler 1 muss immer ein boolscher Ausdruck sein. Er entscheidet über die Wertzuweisung. Ist er true , so wird der Wert nach dem Fragezeichen zugewiesen, ansonsten der Wert nach dem Doppelpunkt.
            System.out.println("Spieler " + s + " ist an der Reihe.");//oder String anfang =
            String eingabeFeld = scanner.nextLine();
            if (eingabeFeld.contains("a") ^ eingabeFeld.contains("b") ^ eingabeFeld.contains("c")) {
                if (eingabeFeld.contains("1") ^ eingabeFeld.contains("2") ^ eingabeFeld.contains("3")) {
                    int[] position = eingabeZuFeld(eingabeFeld);//bezieht sich auf die zahlen 1 & 2 in einagbeZuFeld
                    if (spielFeld[position[0]][position[1]] == 0) {// Wenn 0, dann ist die Stelle, da ein Array von sich aus mit Nullen gefüllt
                        blockadeFürFeld(eingabeFeld);//mit Beispiel b1
                        printSpielfeld();
                        spieler1 = !spieler1;//dreht den Wert um
                        continue; //läuft weiter, weil es um spielLäuft und nicht spieler1 in der while Schleife geht
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
            spielFeld[zeile][spalte] = 2;// Hier wird das ausgewählte Feld mit einer 1 oder 2 blockiert
        }
    }

    private int[] eingabeZuFeld(String eingabeString) {//wird von eingabeFeld über eingabe bis hier eingabeString herreicht; wird auf b1 kopiert/enhält ihn jetzt auch
        String buchstabe = eingabeString.substring(0, 1);//substring = Teil eines Strings (z.B. A in Auto) ; Ziffern geben Position an
        int zeile = 7;// ist random
        int spalte = Integer.parseInt(eingabeString.substring(1, 2)) - 1;//Integer.parseInt = casten zu int (String zu int); IMMER bei String zu int; -1 zum umrechnen (z.B. a1 -> 0 0)
        switch (buchstabe.toLowerCase()) {
            case "a" -> zeile = 0;//Zuweisung von Buchstabe auf Zahl
            case "b" -> zeile = 1;
            case "c" -> zeile = 2;
//            default -> System.out.println("")
        }
        int[] arr = {zeile, spalte}; // Array mit 2 Stellen erstellt; return new int[]{zahl1, zahl2};
        return arr; //geben arr zurück
    }

    private void printSpielfeld() {

        System.out.println("  1 2 3");
//        System.out.println("-------");

        for (int zeile = 0; zeile < 3; zeile++) { //Von oben nach unten
            char zeilenName = (char) (65 + zeile);
            System.out.print(zeilenName + " ");

            for (int spalte = 0; spalte < 3; spalte++) { //Von Links nach Rechts

                int feldWert = spielFeld[zeile][spalte];//Entweder 1 & 2 von "blockadeFürFeld"
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
//        System.out.println("-------");
    }

//    public static void startGame() {
//
//    }

    private boolean hatGewonnen() {
        // prüfe Zeilen
        for (int zeile = 0; zeile < spielFeld.length; zeile++) {//bis 3
            if (spielFeld[zeile][0] == spielFeld[zeile][1] && spielFeld[zeile][1] == spielFeld[zeile][2] && spielFeld[zeile][0] != 0) {
                return true; //true, weil wir oben im Methodenkopf deklariert/eingeführt
            }
        }

        // prüfe Spalten
        for (int spalte = 0; spalte < spielFeld.length; spalte++) {
            if (spielFeld[0][spalte] == spielFeld[1][spalte] && spielFeld[1][spalte] == spielFeld[2][spalte] && spielFeld[0][spalte] != 0) {//==, damit nur ein Spieler gewinnt
                return true;
            }
        }

        // prüfe Diagonalen
        if (spielFeld[0][0] == spielFeld[1][1] && spielFeld[1][1] == spielFeld[2][2] && spielFeld[1][1] != 0) {// "0" ist auch gleich 2; //von oben links nach unten rechts
            return true;
        } else if (spielFeld[0][2] == spielFeld[1][1] && spielFeld[1][1] == spielFeld[2][0] && spielFeld[1][1] != 0) {// "0" ist auch gleich 2; //von oben rechts nach unten links
            return true;
        }

        return false;
    }
}
