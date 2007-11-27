package hotgammon.view;

import hotgammon.view.figures.*;
import hotgammon.domain.Color;

import java.awt.Point;

import minidraw.standard.*;
import minidraw.framework.*;


/** Show the dice on the view.
 * 
 * Author Henrik Bærbak Christensen
 */
public class ShowDice {
  
  public static void main(String[] args) {
    DrawingEditor window = 
      new MiniDrawApplication( 
                              "Test: Shows some dice in their proper position",
                              new BackgammonFactory() );
    window.open();
    
    Figure redDie = new DieFigure(4, new Point(216, 202));
    Figure blackDie = new DieFigure(2, new Point(306, 202));
    Figure blackChecker = new CheckerFigure(Color.BLACK, null, 
                                            new Point(40, 40));
    window.drawing().add(redDie);
    window.drawing().add(blackDie);
    window.drawing().add(blackChecker);
    window.view().checkDamage();
  }
}
