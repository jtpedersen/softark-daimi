package hotgammon.view;

import hotgammon.domain.*;
import hotgammon.view.*;

import minidraw.framework.*;
import minidraw.standard.*;

import hotgammon.view.tools.*;

/** An object server that instantiates some predefined
 * stub objects that serves for testing purposes.
 *
 */

public class MonUserInterface
implements BackgammonUserInterface {

	private DrawingEditor editor;
	private Game game;

	public MonUserInterface( String applTitle , MonFactory monFactory ) {
		BackgammonFactory factory = new BackgammonFactory();

		editor =
			new MiniDrawApplication( applTitle, factory );
		game = new StandardGame(monFactory);

		// we need to set the dependency between game and
		// the minidraw drawing instance.
		BackgammonDrawing bd = (BackgammonDrawing) factory.drawing;
		bd.setObjectServer( this );

		// and register the drawing as listener on game events
		game.addGameListener( bd );
	}

	public static void main(String[] args) {		

		String title = null;
		MonFactory factory = null;
		if(args[0].equalsIgnoreCase("alphamon"))
		{
			factory = new AlphamonFactory();
			title = "Alphamon!!";
		}
		else if(args[0].equalsIgnoreCase("betamon"))
		{
			factory = new BetamonFactory();
			title = "Betamon!!";
		}
		else if(args[0].equalsIgnoreCase("gammamon"))
		{
			factory = new GammamonFactory();
			title = "Gammamon!!";
		}
		else if(args[0].equalsIgnoreCase("deltamon"))
		{
			factory = new DeltamonFactory();
			title = "Deltamon!!";
		}
			
		BackgammonUserInterface ui = 
      			new MonUserInterface(title,factory);
    		DrawingEditor editor = ui.getEditor();
    
		editor.open();

		ui.getGame().newGame();

		editor.setTool( new MonTool(ui) );
	}

	public Game getGame() { return game; }

	public DrawingEditor getEditor() { return editor; }

}
