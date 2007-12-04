package hotgammon.view;

import java.awt.event.MouseEvent;

import minidraw.standard.NullTool;

/**
 * Board state change tool: tool that on mouse down triggers some event on the
 * board which then hopefully is reflected in the GUI. Note that this particular
 * testing only tool only works at the testing board setup where a single black
 * checker is located on B12.
 */

public class TestBoardStateChangeTool extends NullTool {

	BackgammonUserInterface ui;
	private StateChange[] changeList;

	public TestBoardStateChangeTool(BackgammonUserInterface ui,
			StateChange[] changeList) {
		this.ui = ui;
		this.changeList = changeList;
	}

	int index = 0;

	public void mouseDown(MouseEvent arg0, int arg1, int arg2) {
		
		if (index < changeList.length) {
			if (ui.getGame().getNumberOfMovesLeft()==0)
				ui.getGame().nextTurn();
			StateChange s = changeList[index++];
			ui.getEditor().showStatus(s.c + " is Moving from " + s.from + " to " + s.to);
			if (ui.getGame().move(s.from, s.to))
				System.out.println("Legal move");
			else
				System.out.println("Illegal move");
		}
	}
}
