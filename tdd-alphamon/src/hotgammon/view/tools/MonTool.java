package hotgammon.view.tools;

import hotgammon.domain.Color;
import hotgammon.domain.GameListener;
import hotgammon.view.BackgammonUserInterface;

import java.awt.event.MouseEvent;

import minidraw.framework.Tool;
import minidraw.standard.NullTool;

/**
 * Tool to specifically move checkers. Note that this tool is tightly coupled to
 * the BoardLayout as it uses its definitions to match graphical location with
 * game locations.
 * 
 */

public class MonTool extends NullTool implements GameListener {

    private BackgammonUserInterface objectServer;
    private Tool dieTool, checkerTool, currentTool;

    public MonTool(BackgammonUserInterface ui) {
        dieTool = new DieRollTool(ui);
        checkerTool = new CheckerMoveTool(ui);
        this.objectServer = ui;
        ui.getGame().addGameListener(this);
        checkState();

    }

    public void mouseDown(MouseEvent e, int x, int y) {
        currentTool.mouseDown(e, x, y);
    }

    public void mouseDrag(MouseEvent e, int x, int y) {
        currentTool.mouseDrag(e, x, y);
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        currentTool.mouseUp(e, x, y);
    }

    private void checkState() {
        // System.out.println("checkState movesLeft==" +
        // objectServer.getGame().getNumberOfMovesLeft());
        if (objectServer.getGame().winner()!=Color.NONE) {
            currentTool = new GameWonTool(objectServer);
            return;
        } 
        Color player = objectServer.getGame().getPlayerInTurn();
        int movesLeft = objectServer.getGame().getNumberOfMovesLeft();
        
        if ( objectServer.getGame().getPlayerInTurn() == Color.NONE) {
            objectServer.getEditor().showStatus("Please click dice to roll");
            currentTool = dieTool;
        } else {
            objectServer.getEditor().showStatus(player + " please move a checker (" +
                                                movesLeft + " moves left)");
            currentTool = checkerTool;            
        }

        // objectServer.getEditor().showStatus(
        // objectServer.getGame().getPlayerInTurn().name() + " with tool " +
        // currentTool.getClass().getName());
    }

    public void boardChange() {
//        System.out.println("board changed");
        checkState();

    }

    public void diceRolled() {
//        System.out.println("diceRolled");
        checkState();

    }
}
