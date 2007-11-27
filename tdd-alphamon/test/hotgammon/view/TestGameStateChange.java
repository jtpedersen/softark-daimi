package hotgammon.view;

import hotgammon.view.helper.BackgammonUserInterfaceTester;
import hotgammon.view.tools.MonTool;
import minidraw.framework.DrawingEditor;

/*
 * test that MonTool displays the correct information
 * and changes between different states.
 * 
 * hmmm the fixed game only has one listener so its only the last that is used, So here its only MonTool
 * which sould have been done another way testAlphaUpdate is more accurate. (and at the moment thaere are no winner ind fixed gameSetup)
 */

public class TestGameStateChange {

  public static void main(String[] args) {
    BackgammonUserInterface ui = 
      new BackgammonUserInterfaceTester( "Change state stuff" );
    DrawingEditor editor = ui.getEditor();
    
    editor.open();
    
    
    ui.getGame().newGame();
    editor.setTool( new MonTool(ui) );
    
  }
}