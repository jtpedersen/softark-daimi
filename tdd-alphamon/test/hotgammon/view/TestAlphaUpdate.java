package hotgammon.view;

import hotgammon.view.helper.AlphaMonUserInterface;
import hotgammon.view.tools.MonTool;
import minidraw.framework.DrawingEditor;

/** Test that graphical checkers are populated correctly onto the
 * graphical board and are moved in response to state changes in the
 * underlying board object.
 *
 *
 */

public class TestAlphaUpdate {

  public static void main(String[] args) {
    BackgammonUserInterface ui = 
      new AlphaMonUserInterface("alpha wayyyy");
    DrawingEditor editor = ui.getEditor();
    
    editor.open();
    
    ui.getGame().newGame();
    
    editor.setTool( new MonTool(ui) );

    
  }
}
