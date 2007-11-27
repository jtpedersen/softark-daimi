package hotgammon.view;

import hotgammon.view.tools.*;
import hotgammon.view.helper.*;


import minidraw.framework.*;

/** Test the DieRollToll that allows the user to roll the dice.
 *
 * @author Henrik B�rbak Christensen (c) 2003
 */
public class TestDiceRolling {

  public static void main(String[] args) {
    BackgammonUserInterface ui = 
      new BackgammonUserInterfaceTester( 
       "TestDiceRolling: Test the rolling"
       +" of dice by clicking them" );
    
    DrawingEditor editor = ui.getEditor();
    
    editor.open();
    
    editor.setTool( new DieRollTool(ui) );

    ui.getGame().nextTurn();
  }
}
