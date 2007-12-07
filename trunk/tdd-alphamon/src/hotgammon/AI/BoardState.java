package hotgammon.AI;

import hotgammon.domain.Board;
import hotgammon.domain.Color;

import java.util.List;

public interface BoardState {
    public Board getBoard();
    public void addValidState(BoardState bs);
    public Color getPlayer();
    public int getMove();
    public List<BoardState> getValidStates();
    public List<GameMove> getGameMoves();
}
