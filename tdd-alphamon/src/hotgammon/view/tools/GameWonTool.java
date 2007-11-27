package hotgammon.view.tools;

import hotgammon.domain.Color;
import hotgammon.view.BackgammonUserInterface;
import minidraw.standard.NullTool;

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
    public GameWonTool(BackgammonUserInterface ui) {
        Color winner = ui.getGame().winner();
        ui.getEditor().showStatus(winner + " won the game, congratulations");
    }

}
