package hotgammon.view;

import hotgammon.domain.*;
import hotgammon.view.figures.*;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.Point;
import java.awt.Rectangle;

import java.util.*;

/** A singleton instance to help calculate positions on the
 * Backgammon board.
 *
 * Author Henrik Bærbak Christensen
 *
 */

public class BoardLayout {

  private static BoardLayout instance = null;
  public static BoardLayout getInstance() {
    if ( instance == null ) {
      instance = new BoardLayout();
    }
    return instance;
  }

  private BoardLayout() {
    defineLocation2DropSiteMap();
  }

  public Rectangle getDropSiteForLocation(Location l) {
    return mapLocation2DropSite.get(l);
  }

  /** given a point find the location whose rectangle covers it */
  public Location getLocationForPoint(int x, int y) {
    Set<Location> s = mapLocation2DropSite.keySet();
    for( Location l : s ) {
      Rectangle r = getDropSiteForLocation(l);
      if (r.contains(x,y)) {
        return l;
      }
    }
    return null;
  }

  private Map<Location,Rectangle> mapLocation2DropSite;
  private void defineLocation2DropSiteMap() {
    mapLocation2DropSite = new HashMap<Location,Rectangle>();
     // Black normal points
    processADropSiteCreationForLocation(Location.B12, new Rectangle(15 + 0 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B11, new Rectangle(15 + 1 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B10, new Rectangle(15 + 2 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B9, new Rectangle(15 + 3 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B8, new Rectangle(15 + 4 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B7, new Rectangle(15 + 5 * 40, 20, 40, 200));
    
    processADropSiteCreationForLocation(Location.B6, new Rectangle(300 + 0 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B5, new Rectangle(300 + 1 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B4, new Rectangle(300 + 2 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B3, new Rectangle(300 + 3 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B2, new Rectangle(300 + 4 * 40, 20, 40, 200));
    processADropSiteCreationForLocation(Location.B1, new Rectangle(300 + 5 * 40, 20, 40, 200));
    
    // red normal points
    processADropSiteCreationForLocation(Location.R12, new Rectangle(15 + 0 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R11, new Rectangle(15 + 1 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R10, new Rectangle(15 + 2 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R9, new Rectangle(15 + 3 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R8, new Rectangle(15 + 4 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R7, new Rectangle(15 + 5 * 40, 217, 40, 200));
    
    processADropSiteCreationForLocation(Location.R6, new Rectangle(300 + 0 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R5, new Rectangle(300 + 1 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R4, new Rectangle(300 + 2 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R3, new Rectangle(300 + 3 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R2, new Rectangle(300 + 4 * 40, 217, 40, 200));
    processADropSiteCreationForLocation(Location.R1, new Rectangle(300 + 5 * 40, 217, 40, 200));
    
    // black special points
    processADropSiteCreationForLocation(Location.R_BAR, new Rectangle(260, 220, 36, 200));
    processADropSiteCreationForLocation(Location.B_BEAR_OFF, new Rectangle(545, 12, 40, 200));
    
    // red special points
    processADropSiteCreationForLocation(Location.B_BAR, new Rectangle(260, 12, 36, 200));
    processADropSiteCreationForLocation(Location.R_BEAR_OFF, new Rectangle(545, 220, 40, 200));
  }
   
  private void processADropSiteCreationForLocation(Location l, Rectangle r) {
    mapLocation2DropSite.put(l,r);
  }
}
