package hotgammon.view.tools;

import hotgammon.domain.*;
import hotgammon.view.*;
import hotgammon.view.figures.*;

import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import minidraw.framework.*;
import minidraw.standard.*;

/**
 * Tool to specifically move checkers. Note that this tool is tightly coupled to
 * the BoardLayout as it uses its definitions to match graphical location with
 * game locations.
 * 
 */

public class MonTool extends NullTool implements GameListener {

	private BackgammonUserInterface objectServer;
	private Tool dieTool, checkerTool, currentTool;

	public MonTool(BackgammonUserInterface ui) {
		dieTool = new DieRollTool(ui);
		checkerTool = new CheckerMoveTool(ui);
		this.objectServer = ui;
		ui.getGame().addGameListener(this);
		checkState();

	}

	public void mouseDown(MouseEvent e, int x, int y) {
		currentTool.mouseDown(e, x, y);
	}

	public void mouseDrag(MouseEvent e, int x, int y) {
		currentTool.mouseDrag(e, x, y);
	}

	public void mouseUp(MouseEvent e, int x, int y) {
		currentTool.mouseUp(e, x, y);
	}

	private void checkState() {
		if (objectServer.getGame().getPlayerInTurn() == Color.NONE) {
			currentTool = dieTool;
		} else {
			currentTool = checkerTool;
		}

		objectServer.getEditor().showStatus( objectServer.getGame().getPlayerInTurn().name() );
	}

	public void boardChange() {
		checkState();
	}

	public void diceRolled() {
		checkState();
	}
}
