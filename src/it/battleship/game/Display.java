package it.battleship.game;

import it.battleship.board.Board;
import it.battleship.board.Position;
import it.battleship.board.exceptions.PositionException;
import it.battleship.game.utils.DisplayColors;
import it.battleship.player.Input;
import it.battleship.player.Player;
import it.battleship.ships.Ship;
import it.battleship.ships.utils.ShipType;

import java.util.Scanner;

public class Display {

    public static void printTitle(){
        System.out.println("" +
                "\n" +
                "██████╗░░█████╗░████████╗████████╗██╗░░░░░███████╗░██████╗██╗░░██╗██╗██████╗░\n" +
                "██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║░░░░░██╔════╝██╔════╝██║░░██║██║██╔══██╗\n" +
                "██████╦╝███████║░░░██║░░░░░░██║░░░██║░░░░░█████╗░░╚█████╗░███████║██║██████╔╝\n" +
                "██╔══██╗██╔══██║░░░██║░░░░░░██║░░░██║░░░░░██╔══╝░░░╚═══██╗██╔══██║██║██╔═══╝░\n" +
                "██████╦╝██║░░██║░░░██║░░░░░░██║░░░███████╗███████╗██████╔╝██║░░██║██║██║░░░░░\n" +
                "╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚══════╝╚═════╝░╚═╝░░╚═╝╚═╝╚═╝░░░░░");
    }

    public static int printMenu(){
        printTitle();
        //System.out.println("\nBenvenuto nel gioco Battaglia Navale! Scegli una voce nel menu per continuare... ");
        System.out.println("\n(1) - Inizia a giocare");
        System.out.println("(2) - Simula partita");
        System.out.println("(3) - Regole e legenda");
        System.out.println("(0) - Esci\n");
        return Input.readOption(new Scanner(System.in),"Risposta: ");
    }

    public static void printRules(){
        System.out.println(DisplayColors.ANSI_YELLOW + "\nCome vincere:" + DisplayColors.ANSI_RESET);
        System.out.println(DisplayColors.ANSI_WHITE +
                "- Ogni giocatore ha un campo di battaglia rappresentato da una griglia 10x10 (predefinita) sulla quale posiziona "+ ShipType.sizeAllShips() +" navi, nascoste al suo avversario.\n" +
                "- L'obiettivo del gioco è affondare tutte le navi avversarie! Una nave viene affondata quando viene colpita una volta per ogni spazio che occupa.\n" +
                "- In altre parole, un "+ShipType.toItalianNameShip(ShipType.values()[0])+", che occupa "+ShipType.values()[0].getShipLength()+" spazi, viene affondato dopo essere stato colpito 2 volte.\n" +
                "- Le "+ ShipType.sizeAllShips() +" navi occupano "+ShipType.lengthAllShips()+" spazi totali, così il primo giocatore a registrare 25 colpi vince!" +
                DisplayColors.ANSI_RESET);

        System.out.println(DisplayColors.ANSI_YELLOW + "\nGameplay:" + DisplayColors.ANSI_RESET);
        System.out.println(DisplayColors.ANSI_WHITE +
                "- Per giocare, segui le istruzioni per configurare le tue "+ ShipType.sizeAllShips() +" navi in qualsiasi schema tu voglia (non sono ammessi piazzamenti diagonali o adiacenti ad altre navi).\n" +
                "- Per piazzare una nave è necessario inserire una coordinata di partenza (A1-J10 per la scheda 10x10 predefinita) e una direzione (verticale o orizzontale).\n" +
                "- Ad esempio: A1 o B7. Le navi non possono sovrapporsi o adicenti (attaccate) e si deve rimanere entro i limiti del bordo.\n" +
                "- Una volta che entrambi i giocatori hanno configurato le loro navi, la battaglia può avere inzio!\n" +
                "- Lancia siluri contro le navi del tuo avversario indovinando le coordinate.\n" +
                "- Le righe sono rappresentate dalle lettere A-J e le colonne con i numeri 1-10 (tavola 10x10).\n" +
                "- Coordinate valide includono una riga seguita da una colonna, ad es. A1, B7, J10, ecc.\n" +
                "- Sarai informati se avete colpito o mancato una nave.\n" +
                "- Affonda tutte e 8 le navi del computer per vincere!" +
                DisplayColors.ANSI_RESET);
        System.out.println(DisplayColors.ANSI_YELLOW + "\nLegenda:" + DisplayColors.ANSI_RESET);
        for (ShipType type: ShipType.values()){
            System.out.println(DisplayColors.ANSI_WHITE +
                    "- (" + DisplayColors.ANSI_YELLOW + Board.SHIP + DisplayColors.ANSI_WHITE+ "x"+type.getShipLength() + ")\t: " + ShipType.toItalianNameShip(type) +
                    DisplayColors.ANSI_RESET);
        }
        System.out.println(
                "- ( " + DisplayColors.ANSI_BLUE + Board.WATER + DisplayColors.ANSI_WHITE + " )\t: Acqua\n" +
                "- (" + DisplayColors.ANSI_YELLOW + Board.SHIP + DisplayColors.ANSI_WHITE + ")\t: Nave\n" +
                "- (" + DisplayColors.ANSI_RED + Board.HIT + DisplayColors.ANSI_WHITE + ")\t: Nave colpita\n" +
                "- (" + DisplayColors.ANSI_WHITE + Board.MISS + DisplayColors.ANSI_WHITE + ")\t: Colpo mancato\n");

        System.out.print("\nPremi un tasto per continuare...");
        new Scanner(System.in).nextLine();
    }

    public static void printCredits(){
        System.out.println("\nGrazie per aver giocato!");
        System.out.println("\nRealizato da Erik Gurzau\n© 2021 Erik Gurzau. Tutti i diritti riservati.");
    }

    public static void printError(String message){
        System.out.println(DisplayColors.ANSI_RED + message + DisplayColors.ANSI_RESET);
    }

    public static void printShot(Player player, Position position, boolean isHit){
        System.out.println("- " + player.getName() + " ha sparato in " + position.toStringEncode(position) + ": " +
                (isHit ? DisplayColors.ANSI_BLUE + "Colpito!" + DisplayColors.ANSI_RESET :
                        DisplayColors.ANSI_RED + "Mancato!" + DisplayColors.ANSI_RESET));
    }

    public static void printWinner(Player player){
        System.out.println(DisplayColors.ANSI_BLUE + "\n✔ " + player.getName() + " ha vinto!" + DisplayColors.ANSI_RESET + "\n");
        System.out.print("\nPremi un tasto per continuare...");
        new Scanner(System.in).nextLine();
    }
    
    public static void printCurrentShip(Ship ship, int numShipLeft){
        System.out.println("☛ " + ship.getName() + " (" +
                DisplayColors.ANSI_YELLOW + ship.toGraphicLength() + DisplayColors.ANSI_RESET +
                ") x" + numShipLeft);
    }

    public static void printAdjacentBoard(Player pOne, Player pTwo) throws PositionException {
        System.out.println(toStringAdjacentBoard(pOne, pTwo));
    }

    public static String toStringAdjacentBoard(Player pOne, Player pTwo) throws PositionException {
        Board firstBoard = pOne.getBoard();
        Board secondBoard = pTwo.getBoard().getBoardHideShips();
        int size = firstBoard.getLength();
        StringBuilder s = new StringBuilder("\n    ");
    
        // Intestazione colonne per entrambe le griglie
        for (int i = 1; i <= size; i++) s.append(String.format(" %2d ", i));
        s.append("       ");
        for (int i = 1; i <= size; i++) s.append(String.format(" %2d ", i));
        s.append("\n    ");
    
        // Riga superiore per entrambe le griglie
        for (int i = 0; i < size; i++) s.append("+---");
        s.append("+    ");
        s.append("  ");
        for (int i = 0; i < size; i++) s.append("+---");
        s.append("+\n");
    
        // Righe della griglia
        for (int i = 0; i < size; i++) {
            // Prima griglia
            s.append(" ").append((char)('A' + i)).append("  ");
            for (int j = 0; j < size; j++) {
                s.append("| ");
                char cell = firstBoard.getBoard()[i][j];
                if (cell == Board.WATER)
                    s.append(DisplayColors.ANSI_BLUE).append(cell).append(DisplayColors.ANSI_RESET);
                else if (cell == Board.HIT)
                    s.append(DisplayColors.ANSI_RED).append(cell).append(DisplayColors.ANSI_RESET);
                else if (cell == Board.MISS)
                    s.append(DisplayColors.ANSI_WHITE).append(cell).append(DisplayColors.ANSI_RESET);
                else
                    s.append(DisplayColors.ANSI_YELLOW).append(cell).append(DisplayColors.ANSI_RESET);
                s.append(" ");
            }
            s.append("| ");

            // Seconda griglia: lettera riga esterna
            s.append("   ").append((char)('A' + i)).append(" ");
            for (int j = 0; j < size; j++) {
                s.append("| ");
                char cell = secondBoard.getBoard()[i][j];
                if (cell == Board.WATER)
                    s.append(DisplayColors.ANSI_BLUE).append(cell).append(DisplayColors.ANSI_RESET);
                else if (cell == Board.HIT)
                    s.append(DisplayColors.ANSI_RED).append(cell).append(DisplayColors.ANSI_RESET);
                else if (cell == Board.MISS)
                    s.append(DisplayColors.ANSI_WHITE).append(cell).append(DisplayColors.ANSI_RESET);
                else
                    s.append(DisplayColors.ANSI_YELLOW).append(cell).append(DisplayColors.ANSI_RESET);
                s.append(" ");
            }
            s.append("|\n    ");

            // Riga separatrice per entrambe le griglie
            for (int j = 0; j < size; j++) s.append("+---");
            s.append("+    ");
            s.append("  ");
            for (int j = 0; j < size; j++) s.append("+---");
            s.append("+\n");
        }
        return s.toString();
    }

    public static void printBoard(Board board){
        System.out.println(toStringBoard(board));
    }

    public static String toStringBoard(Board board){
        StringBuilder s = new StringBuilder("\n    ");
        int size = board.getLength();
    
        // Intestazione colonne
        for (int i = 1; i <= size; i++) {
            s.append(String.format(" %2d ", i));
        }
        s.append("\n    ");
        // Riga superiore
        for (int i = 0; i < size; i++) {
            s.append("+---");
        }
        s.append("+\n");
    
        // Righe della griglia
        for (int i = 0; i < size; i++) {
            s.append(" ").append((char)('A' + i)).append("  "); // Lettera riga
            for (int j = 0; j < size; j++) {
                s.append("| ");
                char cell = board.getBoard()[i][j];
                if (cell == Board.WATER)
                    s.append(DisplayColors.ANSI_BLUE).append(cell).append(DisplayColors.ANSI_RESET);
                else if (cell == Board.HIT)
                    s.append(DisplayColors.ANSI_RED).append(cell).append(DisplayColors.ANSI_RESET);
                else if (cell == Board.MISS)
                    s.append(DisplayColors.ANSI_WHITE).append(cell).append(DisplayColors.ANSI_RESET);
                else
                    s.append(DisplayColors.ANSI_YELLOW).append(cell).append(DisplayColors.ANSI_RESET);
                s.append(" ");
            }
            s.append("|\n    ");
            // Riga separatrice
            for (int j = 0; j < size; j++) {
                s.append("+---");
            }
            s.append("+\n");
        }
        return s.toString();
    }


}
