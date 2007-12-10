package hotgammon.domain;

import java.util.Iterator;

class AllInInnerFieldWinnerStrategy implements WinnerStrategy {
    public Color winner( Game game, int turn ) {
        int blackCheckers, redCheckers;
        blackCheckers = redCheckers = 0;

        Iterator<Location> i = game.boardIterator();
        Location l;
        while(i.hasNext()) {
            l = i.next();
            if ( ( (l.getIndex() >= 1 && l.getIndex() <= 6) || l == Location.R_BEAR_OFF) 
                && game.getColor(l) == Color.RED)
                    redCheckers += game.getCount(l);

            if ( ( (l.getIndex() >= 19 && l.getIndex() <= 24) || l == Location.B_BEAR_OFF) 
                && game.getColor(l) == Color.BLACK)
                blackCheckers += game.getCount(l);
        }
        
        if (redCheckers >= 15)
            return Color.RED;
        if (blackCheckers >= 15)
            return Color.BLACK;
        return Color.NONE;
    }
    
    
}
