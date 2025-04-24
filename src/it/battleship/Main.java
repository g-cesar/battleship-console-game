package it.battleship;

import it.battleship.board.exceptions.PositionException;
import it.battleship.game.BattleshipGame;
import it.battleship.game.Display;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws PositionException {
        Scanner sc = new Scanner(System.in);
        BattleshipGame game;
        String name = "";
        int opt;
        boolean hasName = false;
        String risposta = "n";
        boolean randomPlacement = false;

        try {
            do {
                opt = Display.printMenu();

                switch (opt) {
                    case 1 -> {
                        if (!hasName) {
                            System.out.print("\nInserisci il tuo nome: ");
                            name = sc.next();
                            hasName = true;
                            System.out.print("Vuoi posizionare tutte le navi casualmente? (s/n): ");
                            risposta = sc.next();
                            randomPlacement = risposta.trim().equalsIgnoreCase("s") || risposta.trim().equalsIgnoreCase("si");
                        }
                        game = new BattleshipGame(name, randomPlacement);
                        game.run();
                    }
                    case 2 -> {
                        game = new BattleshipGame();
                        game.run();
                    }
                    case 3 -> {
                        Display.printRules();
                    }
                }
            } while (opt != 0);
        } catch (InputMismatchException e) { }
        Display.printCredits();
    }
}
