package hotgammon.AI;

import hotgammon.domain.Board;
import hotgammon.domain.Color;
import hotgammon.domain.Location;
import hotgammon.domain.MoveStrategy;
import hotgammon.domain.RealBackgammonMoveStrategy;

public class AIMoveStrategyAdapter implements AIMoveStrategy {

    private MoveStrategy ms;
    private Color player;
    
    public AIMoveStrategyAdapter(Color player) {
        this.player = player;
        this.ms = new RealBackgammonMoveStrategy();
    }
    
     public int isValidMove(Board board, int[] dieValuesLeft, Location from,
            Location to) {
         return ms.isValidMove(new FakeGame(board,dieValuesLeft, player), from, to);
    }

}
