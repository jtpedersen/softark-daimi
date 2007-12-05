package hotgammon.view.tools;

import hotgammon.domain.Color;
import hotgammon.view.BackgammonUserInterface;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * Tool to specifically move checkers. Note that this tool is tightly coupled to
 * the BoardLayout as it uses its definitions to match graphical location with
 * game locations.
 * 
 */

public class GameWonTool extends NullTool {

    /**
     * Precondition this tool is only used when there is a winner!
     * 
     * @param ui
     */
    
    BackgammonUserInterface ui;

    public GameWonTool(BackgammonUserInterface ui) {
        this.ui = ui;
        Color winner = ui.getGame().winner();
        ui.getEditor().showStatus(winner + " won the game, congratulations " +
                                  "(click to start a new game)");
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        ui.getGame().newGame();
    }

}
