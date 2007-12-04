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
        // make sure its allowed to bear off
        // der er ikke nogen i forevejen
        // det er lovligt at tage hjem
        if (game.getPlayerInTurn() == Color.BLACK && to == Location.B_BEAR_OFF
                && game.getCount(Location.B_BEAR_OFF) == 0
                && allInInnerField.winner(game, 1) != Color.BLACK)
            return -1;

        if (game.getPlayerInTurn() == Color.RED && to == Location.R_BEAR_OFF
                && game.getCount(Location.R_BEAR_OFF) == 0
                && allInInnerField.winner(game, 1) != Color.RED)
            return -1;
        // ellers bare som betamon
        return betamonMoveStrategy.isValidMove(game, from, to);
    }

}
