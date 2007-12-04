package hotgammon.domain;

public class RealBackgammonMoveStrategy implements MoveStrategy {

    private AllInInnerFieldWinnerStrategy allInInnerField;
    private MoveStrategy betamonMoveStrategy;

    public RealBackgammonMoveStrategy(
            AllInInnerFieldWinnerStrategy allInInnerField,
            BetamonMoveStrategy betamonMoveStrategy) {
        this.allInInnerField = allInInnerField;
        this.betamonMoveStrategy = betamonMoveStrategy;
    }

    public int isValidMove(Game game, Location from, Location to) {
        // man må ikke flytte væk fra bearoff
        if (from == Location.B_BEAR_OFF || from == Location.R_BEAR_OFF)
            return -1;

        // make sure its allowed to bear off
        // der er ikke nogen i forevejen
        // det er lovligt at tage hjem
        int move = Math.abs(Location.distance(from, to));
        if (to == Location.B_BEAR_OFF ) {
            if (game.getCount(Location.B_BEAR_OFF) == 0
                    && allInInnerField.winner(game, 1) != Color.BLACK) {
                return -1;
            } else {
                // need to check whether we have larger diceValue
                int high = getHighestInHome(game, Color.BLACK);
                if (high < game.diceValuesLeft()[0] && move>=high)
                    return game.diceValuesLeft()[0];
            }
        }
        if (to == Location.R_BEAR_OFF ) {
            if (game.getCount(Location.R_BEAR_OFF) == 0
                    && allInInnerField.winner(game, 1) != Color.RED) {
                return -1;
            } else {
                // need to check whether we have larger diceValue
                int high = getHighestInHome(game, Color.RED);
//                System.out.println("high " +high);
                if (high < game.diceValuesLeft()[0] && move>=high )
                    return game.diceValuesLeft()[0];
            }
        }

        // ellers bare som betamon

        return betamonMoveStrategy.isValidMove(game, from, to);
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
