package hotgammon.view.figures;

import java.awt.Point;

import minidraw.standard.ImageFigure;

/**
 * MiniDraw Figure that displays one of the die values.
 *
 * Author Henrik B�rbak Christensen (c) 2003
 */
public class DieFigure extends ImageFigure {

  public DieFigure(int value, Point pos) {
    super("die"+value, pos);
  }

}
