package hotgammon.view;

import hotgammon.domain.*;
import hotgammon.view.helper.*;
import hotgammon.view.tools.*;

import java.awt.event.MouseEvent;

import minidraw.framework.*;
import minidraw.standard.*;

/** Test that graphical checkers are populated correctly onto the
 * graphical board and are moved in response to state changes
 * in the underlying board object.
 * @author Henrik Bærbak Christensen (c) 2004
 */

public class TestCheckerAdjusting {

  public static void main(String[] args) {
    BackgammonUserInterface ui = 
      new BackgammonUserInterfaceTester( 
         "Move Checker: R3/B3 illegal, try stacking!" );
    DrawingEditor editor = ui.getEditor();
    
    editor.open();
    
    editor.setTool( new CheckerMoveTool(ui) );

    ui.getGame().newGame();
  }
}
