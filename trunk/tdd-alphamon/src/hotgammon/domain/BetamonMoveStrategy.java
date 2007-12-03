package hotgammon.domain;

public class BetamonMoveStrategy implements MoveStrategy {
    public boolean isValidMove(Game game, Location from, Location to) {
        int move = Location.distance(from, to);
	boolean contains = false;
        for(int i : game.diceValuesLeft())
		if(i == Math.abs(move))
			contains = true;

	if(!contains)
		return false;
		
        // are there chekers in the bar ?
        if (game.getPlayerInTurn() == Color.BLACK
            && game.getCount(Location.B_BAR) > 0) {
            if (from != Location.B_BAR) // only move from bar
                return false;
            if ( !(to.getIndex()>0 && to.getIndex()<7 ) ) //only move to otherplayers home
                return false;
        } else if (game.getPlayerInTurn() == Color.RED
                   && game.getCount(Location.R_BAR) > 0) {
            if (from != Location.R_BAR) // only move from bar
                return false;
            if ( !(to.getIndex()>18 && to.getIndex()<25 ) ) //only move to otherplayers home
                return false;
        }

        

        if (game.getPlayerInTurn() != game.getColor(from))
            return false;
        if (game.getCount(to) > 1
            && game.getColor(to) != game.getPlayerInTurn())
            return false;
        if (move < 0 && game.getPlayerInTurn() == Color.BLACK)
            return false;
        if (move > 0 && game.getPlayerInTurn() == Color.RED)
            return false;
        return true;
    }
}
