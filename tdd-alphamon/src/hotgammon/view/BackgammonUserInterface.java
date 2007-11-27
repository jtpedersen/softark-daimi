package hotgammon.view;

import minidraw.framework.DrawingEditor;
import hotgammon.domain.Game;

/** This interface expresses the role as object server for the
 * graphical user interface of backgammon.
 * 
 * Responsibility:
 * A) To act as collaborator look-up table where you can get access to
 *    the delegates responsible for various graphical management
 * 
 * Author Henrik Bærbak Christensen
 */
public interface BackgammonUserInterface {
  /** get the game instance that this UI is attached to
   * @return the game instance	
  */	
  public Game getGame();
  
  /** return the jhd drawing editor instance */
  public DrawingEditor getEditor();
}
