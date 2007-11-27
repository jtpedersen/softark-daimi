package hotgammon.view.figures;

import java.awt.Image;
import java.awt.Point;
import java.util.Vector;

import minidraw.standard.*;

/**
 * MiniDraw Figure that displays one of the die values.
 *
 * Author Henrik Bærbak Christensen (c) 2003
 */
public class DieFigure extends ImageFigure {

  public DieFigure(int value, Point pos) {
    super("die"+value, pos);
  }

}
