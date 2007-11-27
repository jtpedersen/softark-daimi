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
 * Author: Henrik Bærbak Christensen
 */

public class TestDomainUpdate {

  public static void main(String[] args) {
    BackgammonUserInterface ui = 
      new BackgammonUserInterfaceTester( "Click to see domain updates" );
    DrawingEditor editor = ui.getEditor();
    
    editor.open();
    
    editor.setTool( new BoardStateChangeTool(ui) );

    ui.getGame().newGame();
  }
}

/** Board state change tool: tool that on mouse down triggers some
 * event on the board which then hopefully is reflected in the GUI.
 * Note that this particular testing only tool only works at the
 * testing board setup where a single black checker is located on
 * B12.
 */
class BoardStateChangeTool extends NullTool {
  
  BackgammonUserInterface ui;
  public BoardStateChangeTool(BackgammonUserInterface ui) {
    this.ui = ui;
  }
  
  private class StateChange {
    Location from;
      Location to;
    Color c;
    StateChange(Location l, Location t, Color col) {
      from = l;
      to = t;
      c = col;
    }
  };
  StateChange[] changelist =
  {
    new StateChange(Location.B12, Location.B2, Color.BLACK),
    new StateChange(Location.B2, Location.B4, Color.BLACK),
    new StateChange(Location.B4, Location.B7, Color.BLACK),
    new StateChange(Location.B7, Location.R2, Color.BLACK),
    
    new StateChange(Location.R1, Location.R2, Color.RED),
    new StateChange(Location.R2, Location.B_BEAR_OFF, Color.BLACK),
    
    new StateChange(Location.R2, Location.R_BAR, Color.RED),
    
    
  };
  int index = 0;
  public void mouseDown(MouseEvent arg0, int arg1, int arg2) {
    if (index < changelist.length) {
      StateChange s = changelist[index++];
      ui.getEditor().showStatus("Moving from " + s.from + " to " + s.to);
      ui.getGame().move(s.from, s.to);
    } else {
      ui.getEditor().showStatus("No more moves left in changelist...");
    }
  }
}
