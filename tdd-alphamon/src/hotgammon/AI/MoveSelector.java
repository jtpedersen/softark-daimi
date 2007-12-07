package hotgammon.AI;

import java.util.List;

public interface MoveSelector {
    public List<GameMove> getMoves(List<BoardState> states);
}
