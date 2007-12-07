package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.GameListener;
import hotgammon.domain.Location;
import hotgammon.domain.MonFactory;
import hotgammon.domain.MoveStrategy;
import hotgammon.domain.RealBackgammonFactory;
import hotgammon.domain.RealBackgammonMoveStrategy;
import hotgammon.view.BackgammonUserInterface;
import hotgammon.view.MonUserInterface;
import hotgammon.view.tools.MonTool;

import java.util.Iterator;

import minidraw.framework.DrawingEditor;

public class TestAI implements GameListener, Runnable {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String title = null;
        MonFactory factory = null;

        factory = new RealBackgammonFactory(false);
        title = "Real Backgammon";

        BackgammonUserInterface ui = new MonUserInterface(title, factory);
        DrawingEditor editor = ui.getEditor();

        editor.open();
        new AITurnHandler(ui.getGame());
//        new AITurnHandler(ui.getGame(), Color.RED);
        
        ui.getGame().newGame();

        editor.setTool(new MonTool(ui));
    }

    private Color player;
    private MoveStrategy ms;
    private Game game;
    private AITurnHandler turnHandler;

    public TestAI(Game game, Color c, AITurnHandler turnHandler) {
        this.game = game;
        this.player = c;
        this.ms = new RealBackgammonMoveStrategy();
        game.addGameListener(this);
        this.turnHandler = turnHandler;
    }

    private void generateMove() {
        System.out.println(player + " generates move");

        Iterator<Location> itfrom = game.boardIterator();
        boolean foundMove = false;
        Location goFrom = null, goTo = null;
        int move = -1;
        while (!foundMove && itfrom.hasNext()) {
            Location from = (Location) itfrom.next();
            if (!foundMove && game.getColor(from) == player
                    && game.getCount(from) > 0) {
                Iterator<Location> itTo = game.boardIterator();
                while (!foundMove && itTo.hasNext()) {
                    Location to = (Location) itTo.next();
                    if (this.isValidMove(from, to)) {
                        move = ms.isValidMove(game, from, to);

                        if (move > 0) {
                            // System.out.println(move);
                            foundMove = true;
                            goTo = to;
                            goFrom = from;
                        }
                    }
                }
            }
        }

        System.out.println(player + " moves from " + goFrom + " to " + goTo
                + " using a " + move + " die");
        game.move(goFrom, goTo);

    }

    public void boardChange() {
//         System.out.println("BoardChange til player " +  player);
//        if (game.winner() != Color.NONE) {
//            System.out.println(player + " saw that " + game.winner()
//                    + " won the game");
//            return;
//        }
//
//        if (game.getPlayerInTurn() != Color.NONE
//                && player != game.getPlayerInTurn()) {
//            System.out.println(player + " says doThrow()");
//            turnHandler.doThrow();
//        }
//        if (player == game.getPlayerInTurn()) {
//            generateMove();
//        }

    }

    public void diceRolled() {
    }

    /**
     * basic general movement thats not allowed as a *mon move
     */
    private boolean isValidMove(Location from, Location to) {
        if (to == from || game.getColor(from) == Color.NONE
                || to == Location.R_BAR || to == Location.B_BAR
                || game.getPlayerInTurn() != game.getColor(from))
            return false;

        return true;
    }

    public void run() {
        try {
            this.wait(30);
        } catch (Exception e) {
            // TODO: handle exception
            return;
        }

    }
}
