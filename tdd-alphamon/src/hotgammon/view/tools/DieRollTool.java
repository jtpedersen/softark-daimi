package hotgammon.view.tools;

import hotgammon.view.BackgammonUserInterface;
import hotgammon.view.figures.DieFigure;

import java.awt.event.MouseEvent;

import minidraw.framework.Figure;
import minidraw.standard.NullTool;

/** Tool to specifically move checkers. Note that this tool is tightly
 * coupled to the BoardLayout as it uses its definitions to match
 * graphical location with game locations.
 * 
*/

public class DieRollTool extends NullTool  {

  
  private BackgammonUserInterface objectServer;
  public DieRollTool(BackgammonUserInterface ui) {
    this.objectServer = ui;
    
  }
  
  public void mouseDown(MouseEvent e, int x, int y) {
    // find the figure that was directly under the (x,y) clicked...
    Figure f = objectServer.getEditor().drawing().
      findFigure(e.getX(), e.getY());
    if (f instanceof DieFigure) {
    	objectServer.getGame().nextTurn();
    } 
  }
}
