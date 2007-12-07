package hotgammon.AI;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SimpleMoveSelector implements MoveSelector {

    public List<GameMove> getMoves(List<BoardState> states) {
     BoardState best = Collections.min(states, new Comparator<BoardState>() {

         public int compare(BoardState b1, BoardState b2) {
             if (b1.getGameMoves().size() > b2.getGameMoves().size())
                 return 1;
             else if (b1.getGameMoves().size() < b2.getGameMoves().size())
                 return -1;
             else
                 return 0;
         }

     });
     
     return best.getGameMoves();
    }

}
