package hotgammon.domain;

public class BetamonMoveStrategy implements MoveStrategy {
    public int isValidMove(Game game, Location from, Location to) {

        //er det den korekte spiller der flytter?
        if (game.getPlayerInTurn() != game.getColor(from))
            return -1;
        
//        er det et reeelt move
        if (to==from)
            return -1;

        //passer det med et terningslag
        int move = Location.distance(from, to);
        boolean contains = false;
        for (int i : game.diceValuesLeft())
            if (i == Math.abs(move))
                contains = true;

        if (!contains)
            return -1;


        // are there chekers in the bar ?
        if (game.getPlayerInTurn() == Color.BLACK
                && game.getCount(Location.B_BAR) > 0) {
            if (from != Location.B_BAR) // only move from bar
                return -1;
            if (!(to.getIndex() > 0 && to.getIndex() < 7)) // only move to
                                                            // otherplayers home
                return -1;
        } else if (game.getPlayerInTurn() == Color.RED
                && game.getCount(Location.R_BAR) > 0) {
            if (from != Location.R_BAR) // only move from bar
                return -1;
            if (!(to.getIndex() > 18 && to.getIndex() < 25)) // only move to
                                                                // otherplayers
                                                                // home
                return -1;
        }

        if (game.getCount(to) > 1
                && game.getColor(to) != game.getPlayerInTurn())
            return -1;
        if (move < 0 && game.getPlayerInTurn() == Color.BLACK)
            return -1;
        if (move > 0 && game.getPlayerInTurn() == Color.RED)
            return -1;
        return Math.abs(move);
    }
}
