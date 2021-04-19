import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Willst du ein Spiel starten?");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine();
        // eingabe = "JA"
        // eingabe.toLowercase() = "ja"
        if (eingabe.toLowerCase().contains("j")){ // toLowerCase = macht es in Kleinbuchstaben -> nur kleine Buchstaben werden durchsucht
            TicTacToe game = new TicTacToe();   // instance | game = Deklaration; new TTT() = Initialisierung
            //int anzahl = 0; //int ist auch eine Klasse
            // do something
            game.startGame();                   // Starte das game Auto
            //TicTacToe.startGame();            // Starte das Auto
        } else {
            System.out.println("Programm wird abgebrochen!");
            System.exit(0); // System /Programm wird mit Status 0 beendet
        }
    }

}
