/**
 * 
 */
package hotgammon.AI;

import hotgammon.domain.Board;
import hotgammon.domain.Color;
import hotgammon.domain.Location;

public class Info {
    /**
     * 
     */
    int blots, unsafe, totalDistancefromHome, moves;
    
    public Info(BoardState bs) {
        Board board = bs.getBoard();
        Color player = bs.getPlayer();
        moves = bs.getGameMoves().size();
        Location bar = (player==Color.BLACK) ? Location.R_BAR : Location.B_BAR;
        Location home = (player==Color.BLACK) ? Location.B6 : Location.R6;
        for (Location l: board) {
            int checkers = board.getCount(l);
            if ( l==bar) 
                blots = checkers;
            if (board.getColor(l) == player) {
                if (checkers == 1)
                    unsafe++;
                totalDistancefromHome += Math.abs(checkers * Location.distance(l, home));
            }
            
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + blots;
        result = prime * result + moves;
        result = prime * result + totalDistancefromHome;
        result = prime * result + unsafe;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Info other = (Info) obj;
        if (blots != other.blots)
            return false;
        if (moves != other.moves)
            return false;
        if (totalDistancefromHome != other.totalDistancefromHome)
            return false;
        if (unsafe != other.unsafe)
            return false;
        return true;
    }
    
    
}