/**
 * 
 */
package hotgammon.AI;

import hotgammon.domain.Board;
import hotgammon.domain.Color;
import hotgammon.domain.Location;

public class AlotInfo implements BoardInformation {
    /**
     * 
     */
    int blots, unsafe, atHome, moves, inBearOff, pipCount, distancesScore, highOwn;
    int oBlots, oUnsafe, oAtHome, oMoves, oInBearOff, oPipCount, oDistancesScore;
    boolean takeHomeMode;

    public AlotInfo(BoardState bs) {
        Board board = bs.getBoard();
        Color player = bs.getPlayer();
        moves = bs.getGameMoves().size();
        highOwn = 0;
        int highOwnUnsafe = 0;
        int lowOther = 100;
        distancesScore = 0;
        Location bar = (player == Color.BLACK) ? Location.R_BAR
                : Location.B_BAR;
        Location home = (player == Color.BLACK) ? Location.B_BEAR_OFF
                : Location.R_BEAR_OFF;
        Location oBar = (player == Color.RED) ? Location.R_BAR
                : Location.B_BAR;
        Location oHome = (player == Color.RED) ? Location.B_BEAR_OFF
                : Location.R_BEAR_OFF;
        
        
        for (Location l : board) {
            int checkers = board.getCount(l);
            //only check locationa with checkers
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
            
            if (l == oBar) {
                oBlots = checkers;
                continue;
            }
            if (l == oHome) {
                oInBearOff = checkers;
                continue;
            }
                        
            int dist = Math.abs(Location.distance(l, home));
            
            if (board.getColor(l) == player) {
                pipCount++;
                if (dist > highOwn)
                    highOwn = dist;
                if (checkers == 1) {
                    unsafe++;
                    if (dist > highOwnUnsafe)
                        highOwnUnsafe = dist;
                }
                if (dist < 7)
                    atHome += checkers;
                distancesScore += dist;
            } else {
                if (dist < lowOther)
                    lowOther = dist;
                
                    oPipCount++;
                    
                    if (checkers == 1)
                        oUnsafe++;
                    
                    
                    if (dist > 17)
                        oAtHome += checkers;
                    oDistancesScore += dist;
            }

        }
        if (lowOther > highOwnUnsafe && board.getCount(bar)==0)
            unsafe = 0;
        takeHomeMode = lowOther > highOwn;
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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + atHome;
        result = prime * result + blots;
        result = prime * result + distancesScore;
        result = prime * result + inBearOff;
        result = prime * result + moves;
        result = prime * result + oAtHome;
        result = prime * result + oBlots;
        result = prime * result + oDistancesScore;
        result = prime * result + oInBearOff;
        result = prime * result + oMoves;
        result = prime * result + oPipCount;
        result = prime * result + oUnsafe;
        result = prime * result + pipCount;
        result = prime * result + (takeHomeMode ? 1231 : 1237);
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
        final AlotInfo other = (AlotInfo) obj;
        if (atHome != other.atHome)
            return false;
        if (blots != other.blots)
            return false;
        if (distancesScore != other.distancesScore)
            return false;
        if (inBearOff != other.inBearOff)
            return false;
        if (moves != other.moves)
            return false;
        if (oAtHome != other.oAtHome)
            return false;
        if (oBlots != other.oBlots)
            return false;
        if (oDistancesScore != other.oDistancesScore)
            return false;
        if (oInBearOff != other.oInBearOff)
            return false;
        if (oMoves != other.oMoves)
            return false;
        if (oPipCount != other.oPipCount)
            return false;
        if (oUnsafe != other.oUnsafe)
            return false;
        if (pipCount != other.pipCount)
            return false;
        if (takeHomeMode != other.takeHomeMode)
            return false;
        if (unsafe != other.unsafe)
            return false;
        return true;
    }

}