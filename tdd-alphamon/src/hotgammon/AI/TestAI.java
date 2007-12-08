package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.MonFactory;
import hotgammon.domain.RealBackgammonFactory;
import hotgammon.sound.MonPlayer;
import hotgammon.view.BackgammonUserInterface;
import hotgammon.view.MonUserInterface;
import hotgammon.view.tools.MonTool;
import minidraw.framework.DrawingEditor;

public class TestAI {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String title = null;
        MonFactory factory = null;

        factory = new RealBackgammonFactory();
        title = "Real Backgammon";

        BackgammonUserInterface ui = new MonUserInterface(title, factory);
        DrawingEditor editor = ui.getEditor();
        editor.open();
        new MonPlayer(ui.getGame());
        AIFactory aif = new SmartFactory(ui.getGame(), Color.BLACK);
        new AITurnHandler(aif);
//        new AITurnHandler(ui.getGame(), Color.RED);
        
        ui.getGame().newGame();

        editor.setTool(new MonTool(ui));
    }
}
