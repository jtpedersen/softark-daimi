package hotgammon.view;

import hotgammon.domain.*;
import hotgammon.view.helper.*;

import java.awt.event.MouseEvent;

import minidraw.framework.*;
import minidraw.standard.*;

/** Test that graphical checkers are populated correctly onto the
 * graphical board and are moved in response to state changes in the
 * underlying board object.
 *
 * Author: Henrik B�rbak Christensen
 */

public class TestAlphaMonState {

  public static void main(String[] args) {
    BackgammonUserInterface ui = 
      new MonUserInterface("testAlpha", new AlphamonFactory());
    DrawingEditor editor = ui.getEditor();
    
    editor.open();

	StateChange[] changelist =
	  {
//			* black vil always start in alphamo
	    new StateChange(Location.R1, Location.R3, Color.BLACK),
	    new StateChange(Location.R1, Location.R3, Color.BLACK),
	    
	    new StateChange(Location.B1, Location.B3, Color.RED),
	    new StateChange(Location.B1, Location.R9, Color.RED),
//	    
//	    new StateChange(Location.R1, Location.R2, Color.RED),
//	    new StateChange(Location.R2, Location.B_BEAR_OFF, Color.BLACK),
//	    
//	    new StateChange(Location.R2, Location.R_BAR, Color.RED),
	    
	    
	  };
	
    
    editor.setTool( new TestBoardStateChangeTool(ui, changelist) );

    ui.getGame().newGame();
    ui.getGame().nextTurn();
  }
}

