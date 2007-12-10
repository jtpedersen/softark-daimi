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
    int distancesScore;
    boolean takeHomeMode;

    public InfoMania(BoardState bs) {
        Board board = bs.getBoard();
        Color player = bs.getPlayer();
        moves = bs.getGameMoves().size();
        int highOwn = 0;
        int highOwnUnsafe = 0;
        int lowOther = 100;
        distancesScore = 0;
        Location bar = (player == Color.BLACK) ? Location.R_BAR
                : Location.B_BAR;
        Location home = (player == Color.BLACK) ? Location.B_BEAR_OFF
                : Location.R_BEAR_OFF;
        for (Location l : board) {
            int checkers = board.getCount(l);
            if (checkers == 0)
                continue;

            if (l == bar) {
                blots = checkers;
                continue;
            }
            if (l == home) {
                inBearOff = checkers;
                continue;
            }
            int dist = Math.abs(Location.distance(l, home));
            if (board.getColor(l) == player) {
                if (dist > highOwn)
                    highOwn = dist;
                if (checkers == 1) {
                    unsafe++;
                    if (dist > highOwnUnsafe)
                        highOwnUnsafe = dist;
                }
                if (dist < 7)
                    atHome += checkers;
                distancesScore += dist*checkers;
            } else {
                if (dist < lowOther)
                    lowOther = dist;
            }

        }
//        if (lowOther > highOwnUnsafe)
//            unsafe = 0;
        takeHomeMode = lowOther > highOwn;
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

    @Override
    public String toString() {
        String tmp = "blots " + blots + "\tunsafe " + unsafe + "\t atHome "
                + atHome;
        tmp += "\t moves " + moves + "\t inBearOff " + inBearOff
                + "\tdistancesScore " + distancesScore + "\t takeHomeMode "
                + takeHomeMode;
        return tmp + "\n";
    }

}