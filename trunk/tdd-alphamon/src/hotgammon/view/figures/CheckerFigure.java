package hotgammon.view.figures;

import hotgammon.domain.Color;
import hotgammon.domain.Location;

import java.awt.Image;
import java.awt.Point;

import minidraw.standard.ImageFigure;
import minidraw.standard.ImageManager;

/** A MiniDraw figure that displays a backgammon checker.
 *
 * Author Henrik B�rbak Christensen
 */
public class CheckerFigure extends ImageFigure {

  private Color color;
  private final Location associatedLocation;
  
  public CheckerFigure( Color color, Location l, Point pos) {
    super();
    associatedLocation = l;
    this.color = color;

    Image img;
    if ( color == Color.BLACK ) { 
      img = ImageManager.getSingleton().getImage("blackstone");
    } else {
      img = ImageManager.getSingleton().getImage("whitestone");
    }
    set( img, pos );
  }

  /** return color of the this stone */
  public Color getColor() {
    return color;
  }

  /**
   * @return the location this checker figure is located on
   */
  public Location getAssociatedLocation() {
    return associatedLocation;
  }
}
