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

public class TestAI implements GameListener {

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
        ui.getGame().addGameListener(new TestAI(ui, Color.BLACK));
        ui.getGame().addGameListener(new TestAI(ui, Color.RED));
        ui.getGame().newGame();

        editor.setTool(new MonTool(ui));
    }

    private BackgammonUserInterface ui;
    private Color player;
    private MoveStrategy ms;
    private Game game;

    public TestAI(BackgammonUserInterface ui, Color c) {
        this.ui = ui;
        this.player = c;
        this.ms = new RealBackgammonMoveStrategy();
        this.game = ui.getGame();
    }

    private void generateMove() {
//        System.out.println("generate move");
        Iterator<Location> itfrom = game.boardIterator();
        while (itfrom.hasNext()) {
            Location from = (Location) itfrom.next();
            if (game.getColor(from) == player && game.getCount(from) > 0) {
                Iterator<Location> itTo = game.boardIterator();
                while (itTo.hasNext()) {
                    Location to = (Location) itTo.next();
                    if (this.isValidMove(from, to)) {
                        int move = ms.isValidMove(game, from, to);
                        if (move > 0) {
                            game.move(from, to);
//                            if (game.getNumberOfMovesLeft()==0)
//                                game.nextTurn();
                            return;
                        }
                    }
                }
            }
        }
    }

    public void boardChange() {
//        System.out.println("BoardChange til player " + ui.getGame().getPlayerInTurn());
        if (game.winner() != Color.NONE)
            return;
        
        if (game.getPlayerInTurn() != player && game.getPlayerInTurn() != Color.NONE 
                && game.getNumberOfMovesLeft() == 0) 
            game.nextTurn(); 
        
        if (player == ui.getGame().getPlayerInTurn())
            generateMove();

    }

    public void diceRolled() {
//        System.out.println("Dierolled");
        // checkState();

    }

    /**
     * basic general movement thats not allowed as a *mon move
     */
    private boolean isValidMove(Location from, Location to) {
        Game game = ui.getGame();
        if (to == from || game.getColor(from) == Color.NONE
                || to == Location.R_BAR || to == Location.B_BAR
                || game.getPlayerInTurn() != game.getColor(from))
            return false;

        return true;
    }
}
