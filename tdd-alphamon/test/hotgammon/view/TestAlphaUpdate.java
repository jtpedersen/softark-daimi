package hotgammon.view;

import hotgammon.domain.*;
import hotgammon.view.helper.*;
import hotgammon.view.tools.DieRollTool;
import hotgammon.view.tools.MonTool;

import java.awt.event.MouseEvent;

import minidraw.framework.*;
import minidraw.standard.*;

/** Test that graphical checkers are populated correctly onto the
 * graphical board and are moved in response to state changes in the
 * underlying board object.
 *
 * Author: Henrik B�rbak Christensen
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
