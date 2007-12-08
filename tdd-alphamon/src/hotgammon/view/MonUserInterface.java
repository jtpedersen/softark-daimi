package hotgammon.view;

import hotgammon.domain.AlphamonFactory;
import hotgammon.domain.BetamonFactory;
import hotgammon.domain.DeltamonFactory;
import hotgammon.domain.Game;
import hotgammon.domain.GammamonFactory;
import hotgammon.domain.MonFactory;
import hotgammon.domain.RealBackgammonFactory;
import hotgammon.domain.SemimonFactory;
import hotgammon.domain.StandardGame;
import hotgammon.sound.MonPlayer;
import hotgammon.view.tools.MonTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

/**
 * An object server that instantiates some predefined stub objects that serves
 * for testing purposes.
 * 
 */

public class MonUserInterface implements BackgammonUserInterface {

    private DrawingEditor editor;
    private Game game;

    public MonUserInterface(String applTitle, MonFactory monFactory) {
        BackgammonFactory factory = new BackgammonFactory();

        editor = new MiniDrawApplication(applTitle, factory);
        game = new StandardGame(monFactory);

        // we need to set the dependency between game and
        // the minidraw drawing instance.
        BackgammonDrawing bd = (BackgammonDrawing) factory.drawing;
        bd.setObjectServer(this);

        // and register the drawing as listener on game events
        game.addGameListener(bd);
    }

    public static void main(String[] args) {

        String title = null;
        MonFactory factory = null;
        if (args[0].equalsIgnoreCase("alphamon")) {
            factory = new AlphamonFactory();
            title = "Alphamon!!";
        } else if (args[0].equalsIgnoreCase("betamon")) {
            factory = new BetamonFactory();
            title = "Betamon!!";
        } else if (args[0].equalsIgnoreCase("gammamon")) {
            factory = new GammamonFactory();
            title = "Gammamon!!";
        } else if (args[0].equalsIgnoreCase("deltamon")) {
            factory = new DeltamonFactory();
            title = "Deltamon!!";
        } else if (args[0].equalsIgnoreCase("semimon")) {
            factory = new SemimonFactory();
            title = "Semimon!!";
        } else if (args[0].equalsIgnoreCase("backgammon")) {
            factory = new RealBackgammonFactory();
            title = "Real Backgammon";
        }

        BackgammonUserInterface ui = new MonUserInterface(title, factory);
        DrawingEditor editor = ui.getEditor();

        editor.open();
        new MonPlayer(ui.getGame());
        ui.getGame().newGame();

        editor.setTool(new MonTool(ui));
    }

    public Game getGame() {
        return game;
    }

    public DrawingEditor getEditor() {
        return editor;
    }

}
