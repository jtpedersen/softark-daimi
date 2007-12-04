package hotgammon.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hotgammon.domain.BetamonFactory;
import hotgammon.domain.Color;
import hotgammon.domain.Location;
import hotgammon.view.helper.BackgammonUserInterfaceTester;
import minidraw.framework.DrawingEditor;

/**
 * Test that graphical checkers are populated correctly onto the graphical board
 * and are moved in response to state changes in the underlying board object.
 * 
 * Author: Henrik B�rbak Christensen
 */

public class TestBetaMonUpdate {

	public static void main(String[] args) {
    BackgammonUserInterface ui = 
      new MonUserInterface( "Click to see Beta updates", new BetamonFactory() );
    DrawingEditor editor = ui.getEditor();
    
    editor.open();
	    
	StateChange[] changelist =
	  {
			new StateChange(Location.R1, Location.R2, Color.BLACK),
	        new StateChange(Location.R1, Location.R3, Color.BLACK),

	        // to der gerne skulle vaere lovlige og stemme med terningerne
	        new StateChange(Location.R6, Location.R5, Color.RED),
	        new StateChange(Location.R8, Location.R6, Color.RED),

	        new StateChange(Location.R2, Location.R5, Color.BLACK),
	        new StateChange(Location.R3, Location.R7, Color.BLACK),
	        // der staar nu to sorte paa R4
	        new StateChange(Location.R6, Location.R3, Color.RED) //illegal
	    
	  };
	
    
    editor.setTool( new TestBoardStateChangeTool(ui, changelist) );

    ui.getGame().newGame();
  }
}
