package hotgammon.view.tools;

import hotgammon.domain.Location;
import hotgammon.view.BackgammonUserInterface;
import hotgammon.view.BoardLayout;
import hotgammon.view.figures.CheckerFigure;

import java.awt.event.MouseEvent;

import minidraw.framework.Figure;
import minidraw.standard.NullTool;

/**
 * Tool to specifically move checkers. Note that this tool is tightly coupled to
 * the BoardLayout as it uses its definitions to match graphical location with
 * game locations.
 * 
 * Author Henrik B�rbak Christensen
 */

public class CheckerMoveTool extends NullTool {

    private CheckerFigure draggedFigure = null;
    private int fLastX, fLastY; // previous mouse position
    private int fStartX, fStartY; // original mouse position

    private BackgammonUserInterface objectServer;

    public CheckerMoveTool(BackgammonUserInterface ui) {
        this.objectServer = ui;
        draggedFigure = null;

    }

    public void mouseDown(MouseEvent e, int x, int y) {
        fStartX = x;
        fStartY = y;

        // find the figure that was directly under the (x,y) clicked...
        Figure f = objectServer.getEditor().drawing().findFigure(e.getX(),
                e.getY());
        if (f instanceof CheckerFigure) {
            objectServer.getEditor().drawing().lock();
            draggedFigure = (CheckerFigure) f;
            fLastX = x;
            fLastY = y;
        }
    }

    public void mouseDrag(MouseEvent e, int x, int y) {
        if (draggedFigure != null) {
            draggedFigure.moveBy(x - fLastX, y - fLastY);
            fLastX = x;
            fLastY = y;
        }
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        if (draggedFigure != null) {
            objectServer.getEditor().drawing().unlock();
            Location from = draggedFigure.getAssociatedLocation();
            Location to = BoardLayout.getInstance().getLocationForPoint(x, y);
            boolean valid = false;
            if (to != null) {
                valid = objectServer.getGame().move(from, to);
            }
            if (!valid) {
                int dx = fStartX - x;
                int dy = fStartY - y;
                draggedFigure.moveBy(dx, dy);
            } else {
                
            }
        }
        draggedFigure = null;
    }
}
