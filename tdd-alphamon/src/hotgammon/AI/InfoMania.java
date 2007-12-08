/**
 * 
 */
package hotgammon.AI;

import hotgammon.domain.Board;
import hotgammon.domain.Color;
import hotgammon.domain.Location;

public class InfoMania implements BoardInformation {
    /**
     * 
     */
    int blots, unsafe, atHome, moves, inBearOff;

    public InfoMania(BoardState bs) {
        Board board = bs.getBoard();
        Color player = bs.getPlayer();
        moves = bs.getGameMoves().size();
        Location bar = (player == Color.BLACK) ? Location.R_BAR
                : Location.B_BAR;
        Location home = (player == Color.BLACK) ? Location.B_BEAR_OFF
                : Location.R_BEAR_OFF;
        for (Location l : board) {
            int checkers = board.getCount(l);
            if (l == bar)
                blots = checkers;
            else if (l == home )
                inBearOff = checkers;
            else if (board.getColor(l) == player) {
                if (checkers == 1)
                    unsafe++;
                if (Math.abs(checkers * Location.distance(l, home)) < 7)
                    atHome += checkers;

            }

        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + atHome;
        result = prime * result + blots;
        result = prime * result + inBearOff;
        result = prime * result + moves;
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
        final InfoMania other = (InfoMania) obj;
        if (atHome != other.atHome)
            return false;
        if (blots != other.blots)
            return false;
        if (inBearOff != other.inBearOff)
            return false;
        if (moves != other.moves)
            return false;
        if (unsafe != other.unsafe)
            return false;
        return true;
    }

}