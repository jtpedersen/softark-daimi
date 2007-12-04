package hotgammon.domain;

public class AlphamonMoveStrategy implements MoveStrategy {
    public int isValidMove(Game game, Location from, Location to) {
        if (game.getNumberOfMovesLeft() == 0)
            return -1;
        if (game.getCount(from) == 0)
            return -1;
        if (game.getColor(from) != game.getPlayerInTurn())
            return -1;
        if (game.getColor(to) != Color.NONE
                && game.getColor(to) != game.getPlayerInTurn())
            return -1;

        return game.diceValuesLeft()[0];
    }
}
