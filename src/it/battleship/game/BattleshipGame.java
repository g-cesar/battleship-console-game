package it.battleship.game;
import it.battleship.board.Position;
import it.battleship.board.exceptions.BoardException;
import it.battleship.board.exceptions.PositionException;
import it.battleship.player.Player;

public class BattleshipGame {
    private final Player pOne;
    private final Player pTwo;


    public BattleshipGame(String name, boolean randomPlacement) {
        pOne = new Player(name, randomPlacement);
        pTwo = new Player(true);
    }

    public BattleshipGame(){
        pOne = new Player(true);
        pTwo = new Player(true);
    }

    private boolean turn(Player attack, Player defend) throws PositionException {
        Position shoot = null;
        boolean isHit, isAddHit;
        if (attack.hasShipsLive()){
            if (attack.isAI() && !defend.isAI()) {
                System.out.println("\nIn attesa dell'altro giocatore ...");
                try { Thread.sleep(1500); } catch (InterruptedException e) { }
            }
            do {
                try {
                    shoot = attack.shoot(defend.getBoard().getBoardHideShips());
                    isAddHit = defend.addShoot(shoot);
                } catch (BoardException e) {
                    if (!attack.isAI()) Display.printError("Errore, hai già sparato in questa posizione!");
                    isAddHit = false;
                }
            } while (!isAddHit);
            isHit = defend.getBoard().thereIsHit(shoot);
            if (isHit) attack.registerShoot(shoot);
            Display.printShot(attack, shoot, isHit);
    
            if (attack.isAI() && defend.isAI()) Display.printAdjacentBoard(attack, defend);
            else if (!attack.isAI()) Display.printAdjacentBoard(attack, defend);
            else if (!defend.isAI()) Display.printAdjacentBoard(defend, attack);
    
            if (!attack.isAI() && !defend.isAI()) try { Thread.sleep(1000); } catch (InterruptedException e) { }
            return true;
        } else return false;
    }

    private void addAllShips(){
        pOne.addAllShips();
        pTwo.addAllShips();
    }

    private void printResultGame(){
        if (pOne.shipsLeft() > pTwo.shipsLeft()) Display.printWinner(pOne);
        else Display.printWinner(pTwo);
    }

    public void run() throws PositionException {
        addAllShips();
        while (turn(pOne, pTwo) && turn(pTwo, pOne)) { }
        printResultGame();
    }
}
