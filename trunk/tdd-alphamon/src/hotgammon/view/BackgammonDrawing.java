package hotgammon.view;

import hotgammon.domain.*;
import hotgammon.view.figures.*;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.Point;
import java.awt.Rectangle;

import java.util.*;

/** A Drawing replacement that maintains figures according to
 * changes in the underlying Backgammon Game object.
 *
 * Author Henrik Bærbak Christensen
*/

public class BackgammonDrawing 
  extends StandardDrawing 
  implements GameListener {

  public BackgammonDrawing() {
    super();
  }

  private BackgammonUserInterface objectServer;
  public void setObjectServer( BackgammonUserInterface objServer ) {
    objectServer = objServer;
  }

  /** invoked when a move has been made on the board */
  public void boardChange() {
    // ensure no concurrent modifications occur,,,
    lock();
    /* I use a rather simplistic algorithm here. I simply
       delete all checkers figures and then recreate them
       at their proper positions. 
       
       It is slow - but due to JHotDraw's double buffering there
       is no flickering of the display and you do not see the
       change.
       
       It may seem not very elegant - but it is SIMPLE and less
       prone to errors!
      */
    // remove all figures that are checkers - first populate
    // a remove list as the iterator is not safe over removals.
    List<Figure> removeList = new ArrayList<Figure>();
    for (Figure f : fFigures ) { 
      if ( f instanceof CheckerFigure ) {
        removeList.add(f);
      }
    }
    for (Figure f : removeList ) {
      remove(f);
    }

    // now populate all location sites
    Game g = objectServer.getGame();
    Iterator<Location> i = g.boardIterator();
    while( i.hasNext() ) {
      Location l = i.next();
      Color color = g.getColor(l);
      for (int j = 0; j < g.getCount(l); j++) {
        // create the checker at a dummy position
        CheckerFigure checker = new CheckerFigure(color, l, new Point(30, 30));
        adjustCheckerPosition(checker, l, j);
        add(checker);
        // and use the algorithms in site to move it into its proper place
        //AbstractLocationSite site =
        //  getDropSiteManager().getDropSiteForLocation(l);
        //site.adjustCheckerPosition(checker, j);
        // the method call above automatically notify JHD about 
        // the state change.
      }
    }
    unlock();
    requestUpdate();
  }
  
  private Figure dieFigureCache[] = new Figure[2];
  /** invoked when the dice have been rolled */
  public void diceRolled() {
    // ensure no concurrent modifications occur
    lock();
    int[] dice = objectServer.getGame().diceThrown();

    if (dieFigureCache[0] != null) {
      remove(dieFigureCache[0]);
    }
    if (dieFigureCache[1] != null) {
      remove(dieFigureCache[1]);
    }
 
    dieFigureCache[0] = new DieFigure(dice[0], new Point(216, 202));
    dieFigureCache[1] = new DieFigure(dice[1], new Point(306, 202));

    add(dieFigureCache[0]);
    add(dieFigureCache[1]);
    unlock();

    requestUpdate();
   }

  /** adjust the position of a checker according to the location
      it belongs to and the number of checkers already there */
  private void adjustCheckerPosition(CheckerFigure checker,
                                     Location location,
                                     int position ) {
    int checkersOnThisLocation = position;
    Rectangle checkersBox = checker.displayBox();
    Rectangle box = getDropSiteForLocation(location);

    int height = checkersBox.height;
    if ( isOutLocation(location) ) { height /= 2; }
    // calculate new Y position
    int newy, oldy;
    
    if (isBlackSide(location)) {
      // adjust y
      newy = height * checkersOnThisLocation + box.y;
      oldy = checkersBox.y;
      
    } else {
      newy =
        (box.y + box.height)
        - (height * (checkersOnThisLocation + 1));
      oldy = checkersBox.y;
    }
    // adjust x
    int newx = box.x + (box.width - checkersBox.width) / 2;
    int oldx = checkersBox.x;
    
    checker.moveBy(newx - oldx, newy - oldy);
  }

  private boolean isOutLocation(Location location) {
    return location == Location.R_BEAR_OFF || 
      location == Location.B_BEAR_OFF;
  }

  private boolean isBlackSide(Location location) {
    // this is rather clumsy, but it is pretty easy to spot errors!
    if (location == Location.B1
      || location == Location.B2
      || location == Location.B3
      || location == Location.B4
      || location == Location.B5
      || location == Location.B6
      || location == Location.B7
      || location == Location.B8
      || location == Location.B9
      || location == Location.B10
      || location == Location.B11
      || location == Location.B12
      || location == Location.R_BAR
      || // NOTE! Red bar is on black side!
    location == Location.B_BEAR_OFF) {
      return true;
    }
    return false;
  }
  

  private Rectangle getDropSiteForLocation(Location l) {
    BoardLayout bl = BoardLayout.getInstance();
    return bl.getDropSiteForLocation(l);
  }
}
