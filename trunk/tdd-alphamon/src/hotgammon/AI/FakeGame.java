package hotgammon.AI;

import hotgammon.domain.Board;
import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.GameListener;
import hotgammon.domain.Location;

import java.util.Iterator;

public class FakeGame implements Game {

    private Board board;
    private int[] dieValuesLeft;
    private Color player;

    public FakeGame(Board b, int[] dieValuesLeft, Color player) {
        this.board = b;
        this.dieValuesLeft = dieValuesLeft;
        this.player = player;
    }

    public void addGameListener(GameListener gl) {
        throw new UnsupportedOperationException();

    }

    public Iterator<Location> boardIterator() {
        return board.iterator();
    }

    public int[] diceThrown() {
        throw new UnsupportedOperationException();
    }

    public int[] diceValuesLeft() {
        return dieValuesLeft;
    }

    public Color getColor(Location location) {
        return board.getColor(location);
    }

    public int getCount(Location location) {
        return board.getCount(location);
    }

    public int getNumberOfMovesLeft() {
        return 1; // just a positive number......
    }

    public Color getPlayerInTurn() {
        return player;
    }

    public boolean move(Location from, Location to) {
        throw new UnsupportedOperationException();
    }

    public void newGame() {
        throw new UnsupportedOperationException();
    }

    public void nextTurn() {
        throw new UnsupportedOperationException();
    }

    public Color winner() {
        return Color.NONE;//nobody
    }

}
