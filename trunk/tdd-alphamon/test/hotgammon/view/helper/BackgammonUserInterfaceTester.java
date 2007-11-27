package hotgammon.view.helper;

import hotgammon.domain.*;
import hotgammon.view.*;

import minidraw.framework.*;
import minidraw.standard.*;

/** An object server that instantiates some predefined
 * stub objects that serves for testing purposes.
 *
 * Author Henrik Bærbak Christensen
 */

public class BackgammonUserInterfaceTester 
  implements BackgammonUserInterface {

  private DrawingEditor editor;
  private Game game;
  
  public BackgammonUserInterfaceTester( String applTitle ) {
    BackgammonFactory factory = new BackgammonFactory();
    
    editor =
      new MiniDrawApplication( applTitle, factory );
    game = new FixedSetupGame();

    // we need to set the dependency between game and
    // the minidraw drawing instance.
    BackgammonDrawing bd = (BackgammonDrawing) factory.drawing;
    bd.setObjectServer( this );
    
    // and register the drawing as listener on game events
    game.addGameListener( bd );
  }
  
  public Game getGame() { return game; }
  
  public DrawingEditor getEditor() { return editor; }
  
}
