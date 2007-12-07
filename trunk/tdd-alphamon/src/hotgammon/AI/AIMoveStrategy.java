package hotgammon.AI;

import hotgammon.domain.Board;
import hotgammon.domain.Location;

public interface AIMoveStrategy {
    public int isValidMove(Board board, int[] dieValuesLeft, Location from, Location to);
}
