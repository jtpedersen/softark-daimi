package hotgammon.domain;

import java.util.Iterator;

public class BearOffMoveStrategy implements MoveStrategy {
    private AllInInnerFieldWinnerStrategy allInInnerField;

    public BearOffMoveStrategy() {
        this.allInInnerField = new AllInInnerFieldWinnerStrategy();
    }

    // Precondition to is bearOff
    public int isValidMove(Game game, Location from, Location to) {

        // if (!BasicValidation.isValidMove(game, from, to))
        // return -1;

        int move = Math.abs(Location.distance(from, to));

        Color player = game.getPlayerInTurn();
        
        Location home = (player == Color.BLACK) ? Location.B_BEAR_OFF
                : Location.R_BEAR_OFF;
        
        
        Iterator<Location> it = game.boardIterator();
        int high = 0;
        while (it.hasNext()) {
            Location testFrom = (Location) it.next();
            if (game.getColor(testFrom) == player && testFrom != home ) {
                int dist = Math.abs(Location.distance(testFrom, home));
                if (dist > 6)
                    return -1;
                else if (dist>high)
                    high = dist;
            }
        }


        // need to check whether we have larger diceValue
//         = getHighestInHome(game, player);
        return validatedBearOff(game, move, high);

    }

    private int validatedBearOff(Game game, int move, int high) {
//        System.out.println("move " + move + " high " + high);

        // do we have an exact dice
        for (int i : game.diceValuesLeft())
            if (i == move)
                return move;

        if (high <= game.diceValuesLeft()[0] && move == high)
            return game.diceValuesLeft()[0];

        return -1;

    }

    private int getHighestInHome(Game game, Color player) {
        if (player == Color.BLACK) {
            if (game.getCount(Location.B6) > 0)
                return 6;
            if (game.getCount(Location.B5) > 0)
                return 5;
            if (game.getCount(Location.B4) > 0)
                return 4;
            if (game.getCount(Location.B3) > 0)
                return 3;
            if (game.getCount(Location.B2) > 0)
                return 2;
            if (game.getCount(Location.B1) > 0)
                return 1;
        } else {
            if (game.getCount(Location.R6) > 0)
                return 6;
            if (game.getCount(Location.R5) > 0)
                return 5;
            if (game.getCount(Location.R4) > 0)
                return 4;
            if (game.getCount(Location.R3) > 0)
                return 3;
            if (game.getCount(Location.R2) > 0)
                return 2;
            if (game.getCount(Location.R1) > 0)
                return 1;
        }

        return -1;
    }

}
