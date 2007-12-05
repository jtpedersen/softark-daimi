package hotgammon.view;

import hotgammon.view.helper.BackgammonUserInterfaceTester;
import hotgammon.view.tools.CheckerMoveTool;
import minidraw.framework.DrawingEditor;

/** Test that graphical checkers are populated correctly onto the
 * graphical board and are moved in response to state changes
 * in the underlying board object.
 * @author Henrik B�rbak Christensen (c) 2004
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
